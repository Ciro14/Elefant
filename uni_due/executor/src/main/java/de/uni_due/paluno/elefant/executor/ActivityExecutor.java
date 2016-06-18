package de.uni_due.paluno.elefant.executor;

import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Ole Meyer
 */
public class ActivityExecutor {
    public void execute(List<ActivityContainer> activities){
        RestTemplate restTemplate=new RestTemplate();
        for(ActivityContainer activity:activities){
            restTemplate.getForObject(activity.getActivity().getUrl(),String.class);
            activity.getDiff().getTarget().setActive(activity.getDiff().getDiffValue());
        }

    }
}
