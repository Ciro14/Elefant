# Documentation

### The Execution Service

##### What is the object of this service?

The Execution Service analyze changes in the model and generates a list with all necessary steps to adapt the system. Optionally, they can be executed directly.

##### How it is implemented?

The Application is implemented as microservice on top of the java framework Spring Boot. You can access the service through a simple REST interface. There are two methods. Please make sure that the first methode is called once at starttime.

###### PUT /model

Send the whole model of the application JSON. You will find more details in the documentation of the extended feature model. Here is as short example:

The example system have two virtual machines that can be switched on and off. At least one must be active. You can read the JSON as follows. The infrastructure is represented as root Feature and is the base of the whole model. The root feature is connected to a group of subfeatures, which consists of two features (VM 1 and VM 2). The fact that at least one machine must be active is ensured through the min and max field of the connection. Each VM has an activity for switching it on and off. Each activity has a url that is provided by the developer and that will be called by this service.

    {
        "uuid":"32134841-202b-44ae-95e2-02dc59d5f3f6",
        "name":"Infrastructure",
        "featureConnections":[
            {
                "min":1,
                "max":2,
                "features":[
                    {
                        "uuid":"2aac4355-f97a-4371-9f47-179252cf490f",
                        "name":"VM_1"
                        "activateActivities":[
                            {
                                "url":"https://infrastructure.com/vm/2aac4355-f97a-4371-9f47-179252cf490f/activate"
                            }
                        ],
                        "deactivateActivities":[
                            {
                                "url":"https://infrastructure.com/vm/2aac4355-f97a-4371-9f47-179252cf490f/deactivate"
                            }
                        ]
                    },
                    {
                        "uuid":"cf091c5b-b529-43fc-b2d6-a3e70df7a258",
                        "name":"VM_2"
                        "activateActivities":[
                            {
                                "url":"https://infrastructure.com/vm/cf091c5b-b529-43fc-b2d6-a3e70df7a258/activate"
                            }
                        ],
                        "deactivateActivities":[
                            {
                                "url":"https://infrastructure.com/vm/cf091c5b-b529-43fc-b2d6-a3e70df7a258/deactivate"
                            }
                        ]
                    }
                ]
            }
        ]
    }

###### POST /model

This method finds the differences between the old and the updated model and creates a list of all activities that must be performed to adjust the system. Optional, the activities can be executed immediately by setting the parameter execute to true (/mode?execute=true). It is not necessary to send the entire model. The part of the tree in which the changes occurred is enough. For the example in the previous section, a part of the model is sufficient to deactivate one of the virtual machine.

    {
        "active":false,
        "uuid":"cf091c5b-b529-43fc-b2d6-a3e70df7a258",
        "name":"VM_2"
    }
    
You will get the following response:

    [
        {
            "activity": {
                "featureRequirements": [],
                "attributeRequirements": [],
                "url": "https://infrastructure.com/vm/cf091c5b-b529-43fc-b2d6-a3e70df7a258/deactivate"
            },
            "diff": {
                "target": {<The old state of the feature>}
                "diffValue": false 
            }
        }
    ]

It is a simple list of all a necessary activities. Addition you will find some informations about the difference that caused this action, consisting of the encountered feature and the expected value.
