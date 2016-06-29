package de.uni_due.paluno.elefant.verification;

import aima.core.logic.propositional.parsing.PLParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.visitors.BiconditionalElimination;
import aima.core.logic.propositional.visitors.ConvertToCNF;
import aima.core.logic.propositional.visitors.ConvertToDNF;
import aima.core.logic.propositional.visitors.ImplicationElimination;
import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;
import de.uni_due.paluno.elefant.featuremodel.Feature;
import de.uni_due.paluno.elefant.featuremodel.FeatureConnection;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ole Meyer
 */
public class FeatureModelFormalization {

    private PLParser parser=new PLParser();

    public Sentence formalizeToCNF(Feature model) {

        //Formalize the root feature
        String sentenceStr = formalizeRootFeature(model);
        //Formalize the subfeatures
        sentenceStr = and(sentenceStr, formalizeSubFeatures(model));
        Sentence sentence= parser.parse(sentenceStr);
        sentence= ImplicationElimination.eliminate(BiconditionalElimination.eliminate(sentence));
        sentence=beautify(sentence);
        sentence=ConvertToCNF.convert(sentence);
        sentence=beautify(sentence);
        //System.out.println(beautify(ConvertToDNF.convert(sentence)));
        return sentence;
    }

    public Sentence beautify(Sentence sentence){
        String sentenceStr=sentence.toString();
        sentenceStr=sentenceStr.replace("~","!");
        sentenceStr.toString();
        Expression<String> exp= RuleSet.simplify(ExprParser.parse(sentenceStr));
        sentenceStr=exp.toString();
        sentenceStr=sentenceStr.replace("!","~");
        sentence=parser.parse(sentenceStr);
        return sentence;
    }


    public Sentence formalizeToDNF(Feature model) {
        //Formalize the root feature
        String sentenceStr = formalizeRootFeature(model);
        //Formalize the subfeatures
        sentenceStr = and(sentenceStr, formalizeSubFeatures(model));
        Sentence sentence= new PLParser().parse(sentenceStr);
        sentence= ConvertToDNF.convert(sentence);
        sentence=beautify(sentence);

        return sentence;
    }


    private String and(String s1, String s2) {
        if(s1.length()==0){
            return s2;
        }else if(s2.length()==0){
            return s1;
        }
        return s1 + "&" + s2;
    }

    private String or(String s1, String s2) {
        if(s1.length()==0){
            return s2;
        }else if(s2.length()==0){
            return s1;
        }
        return s1 + "|" + s2;
    }

    private String not(String s1){
        if(s1.length()==0)return s1;
        return "~"+s1;
    }

    private String equivalent(String s1, String s2){
        if(s1.length()==0){
            return s2;
        }else if(s2.length()==0){
            return s1;
        }
        return s1+"<=>"+s2;
    }

    private String putInBrackets(String s1){
        if(s1.length()==0)return s1;
        return "("+s1+")";
    }


    private String formalizeRootFeature(Feature root) {
        //The root feature must be always active
        return putInBrackets(root.getUuid());
    }

    private String formalizeSubFeatures(Feature parent) {
        String sentence = "";
        for (FeatureConnection fc : parent.getFeatureConnections()) {
            sentence=or(sentence, formalizeFeatureGroup(fc,parent));
        }
        sentence=putInBrackets(sentence);
        for(FeatureConnection fc:parent.getFeatureConnections()){
            for(Feature feature:fc.getFeatures()){
                if(feature.getFeatureConnections().size()>0)sentence=and(sentence,formalizeSubFeatures(feature));
            }
        }
        //System.out.println(sentence);
        return putInBrackets(sentence);
    }

    private String formalizeFeatureGroup(FeatureConnection fc, Feature parent) {
        String sentence="";
        for(int i=fc.getMin();i<=fc.getMax();++i){
            sentence=or(sentence,buildPossibleCombinations(fc.getFeatures(),i));
        }
        return or(putInBrackets(equivalent(putInBrackets(sentence),parent.getUuid())),
                    putInBrackets(and(not(parent.getUuid()),putInBrackets(buildPossibleCombinations(fc.getFeatures(),0)))));
    }

    private String buildPossibleCombinations(List<Feature> features, int count){
        String sentence="";
        List<Feature> copy=new LinkedList<Feature>(features);
        List<String> list= buildPossibleCombinationsHelper(copy,count);
        for(String s:list){
            sentence=or(sentence,putInBrackets(s));
        }
        return putInBrackets(sentence);
    }

    private List<String> buildPossibleCombinationsHelper(List<Feature> features, int count){
        List<String> sentences=new LinkedList<String>();
        if(count==0){
            String sentence="";
            for(int j=0;j<features.size();++j)sentence=and(sentence,not(features.get(j).getUuid()));
            sentences.add(sentence);
        }else {
            for (int i = 0; features.size() - i >= count; ++i) {
                List<String> s1 = new LinkedList<String>();
                if (count == 1) {
                    String sentence = features.get(i).getUuid();
                    for (int j = i + 1; j < features.size(); ++j)
                        sentence = and(sentence, not(features.get(j).getUuid()));
                    s1.add(sentence);
                } else {
                    List<String> s2 = buildPossibleCombinationsHelper(features.subList(i + 1, features.size()), count - 1);
                    for (String s : s2) {
                        s1.add(and(features.get(i).getUuid(), s));
                    }
                }
                for (String sentence : s1) {
                    for (int j = 0; j < i; ++j) sentence = and(sentence, not(features.get(j).getUuid()));
                    sentences.add(sentence);
                }
            }
        }

        return sentences;
    }

}
