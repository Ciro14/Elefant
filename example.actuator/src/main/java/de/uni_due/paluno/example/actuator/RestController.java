package de.uni_due.paluno.example.actuator;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.openstack.OSFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ole Meyer
 */

@org.springframework.web.bind.annotation.RestController
public class RestController {


    OSClient.OSClientV2 os;

    @RequestMapping(value = "/vm/{uuid}/activate")
    public void activate(@PathVariable(value = "uuid") String uuid){
        authenticate();
        os.compute().servers().action(uuid, Action.UNPAUSE);
    }

    @RequestMapping(value = "/vm/{uuid}/deactivate")
    public void deactivate(@PathVariable(value = "uuid") String uuid){
       authenticate();
        os.compute().servers().action(uuid, Action.PAUSE);
    }

    private void authenticate(){
        os=OSFactory.builderV2()
                .endpoint("http://192.168.40.2:5000/v2.0")
                //.endpoint("http://localhost:5000/v2.0")
                .credentials("admin","0penstack")
                .tenantName("admin")
                .authenticate();
        System.out.println(os.compute().servers().list().size());
    }
}
