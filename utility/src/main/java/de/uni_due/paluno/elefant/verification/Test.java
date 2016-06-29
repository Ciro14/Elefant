package de.uni_due.paluno.elefant.verification;

import com.google.gson.Gson;
import de.uni_due.paluno.elefant.featuremodel.Feature;

/**
 * Created by Ole Meyer
 */
public class Test {
    public static void main(String[] args){
        String m="\"featureConnections\":[\n" +
                "                        {\n" +
                "                            \"min\":0,\n" +
                "                            \"max\":1,\n" +
                "                            \"features\":[\n" +
                "                                {\n" +
                "                                    \"uuid\":\"vm31\",\n" +
                "                                    \"name\":\"VM3.1\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }    \n" +
                "                    ],";
        String m2="{\n" +
                "                            \"min\":1,\n" +
                "                            \"max\":1,\n" +
                "                            \"features\":[\n" +
                "                                {\n" +
                "                                    \"uuid\":\"vmN1\",\n" +
                "                                    \"name\":\"VM3.1\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },   \n";
        String model="{\n" +
                "    \"active\":true,\n" +
                "    \"uuid\":\"root\",\n" +
                "    \"name\":\"Infrastructure\",\n" +
                "    \"featureConnections\":[\n" +
                "        {\n" +
                "            \"min\":3,\n" +
                "            \"max\":3,\n" +
                "            \"features\":[\n" +
                "                {\n" +
                "                    \"active\":true,\n" +
                "                    \"uuid\":\"vm1\",\n" +
                "                    \"name\":\"VM_1\",\n" +
                "                    \"activateActivities\":[\n" +
                "                        {\n" +
                "                            \"url\":\"https://infrastructure.com/vm/2aac4355-f97a-4371-9f47-179252cf490f/activate\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"deactivateActivities\":[\n" +
                "                        {\n" +
                "                            \"url\":\"https://infrastructure.com/vm/2aac4355-f97a-4371-9f47-179252cf490f/deactivate\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"active\":true,\n" +
                "                    \"uuid\":\"vm2\",\n" +
                "                    \"name\":\"VM_2\",\n" +
                "                    \"activateActivities\":[\n" +
                "                        {\n" +
                "                            \"url\":\"https://infrastructure.com/vm/cf091c5b-b529-43fc-b2d6-a3e70df7a258/activate\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"deactivateActivities\":[\n" +
                "                        {\n" +
                "                            \"url\":\"https://infrastructure.com/vm/cf091c5b-b529-43fc-b2d6-a3e70df7a258/deactivate\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"active\":true,\n" +
                "                    \"uuid\":\"vm3\",\n" +
                "                    \"name\":\"VM_3\",\n" +m+
                "                    \"activateActivities\":[\n" +
                "                        {\n" +
                "                            \"url\":\"https://infrastructure.com/vm/cf091c5b-b529-43fc-b2d6-a3e70df7a258/activate\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"deactivateActivities\":[\n" +
                "                        {\n" +
                "                            \"url\":\"https://infrastructure.com/vm/cf091c5b-b529-43fc-b2d6-a3e70df7a258/deactivate\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Feature feature=new Gson().fromJson(model,Feature.class);
        //System.out.println(new FeatureModelFormalization().formalizeToDNF(feature));
        System.out.println(new FeatureModelFormalization().formalizeToCNF(feature));
    }
}
