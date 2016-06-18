# Documentation

### The Featuremodel

##### What is the object of this model?

This Model is a extended version of the fcore diagram. It is used to represent the state of a system and provides informations on activities that must be executed if the model changes. Therefore a construct is added to the fcore model to describe the possible activities for each feature. It is described in the following section.

##### How it is represented as JSON?

###### Describe a feature

A feature describes a certain aspect of the system that could be active or deactive. Therefore there could be two types of activities, either for activating the feature if it is disabled or for deactivating if it is enabled. Furthoremore a feature has a name and a uuid. The id must be unique because it is used for creating constraint between the features. Subfeatures can be added through the use of the type described in the next section.
    

    {
        "uuid":"<a unique id>",
        "active":{false|true},
        "name":"<a name>",
        "activateActivities":[<activities>],
        "deactivateActivities":[<activities>],
        "featureConnections":[<connection to one or more subfeatures>]
    }

###### Define the connection between a feature and its subfeatures

This object describes the connection between two or more features. It can be used to model a single connection or to create alternative groups with more than one feature. Different types can be modeled through the use of the min and the max field.

    {
        "max":<int>,
        "min":<int>,
        "features":[<features>]
    }

###### Define activitys that should be executed if the model changes

This object contains the url which should be called if this activity is executed. The logic may be different in the most applications and can be hidden behind this url. You can also add some requirements that must be fullfield in order to execute this action.

    {
        "url":"<call this url>",
        "featureRequirements":[<requirements for features>],
        "attributeReqirements":[<requirements for attributes>]
    }  
    
##### Describe constraints between features

Two types of constraints or requirements could be described. The first one says what feature must be enabled or disabled if an aactivity is executed.

    {
        "feature_uuid":"<uuid of the referenced feature>"
        "active":{true|false}
    }
    
The second one determines the value range of a attribute that must be fullfield.

    {
        "feauture_uuid":"<uuid of the referenced feature>",
        "attribute_uuid":"<uuid of the referenced attribute>",
        "min":<a number>,
        "max":<a number>
    }
    

##### Example 

    {
    "uuid":"1",    
    "name":"Wordpress",
    "featureConnections":[
        {
            "max":3,
            "min":1,
            "features":[
                {
                    "uuid":"c6a8ff39-dc69-4542-9139-b721b9008f58",
                    "active":true,
                    "name":"vm1",
                    "activateActivities":[
                        {
                            "url":"http://10.8.100.45/vm/c6a8ff39-dc69-4542-9139-b721b9008f58/activate"
                        }    
                    ],
                    "deactivateActivities":[
                        {
                            "url":"http://10.8.100.45/vm/c6a8ff39-dc69-4542-9139-b721b9008f58/deactivate"
                        }    
                    ]
                },
                {
                    "uuid":"818bda7a-9506-4ed1-bbe2-bd6f5f6b39ca",
                    "active":true,
                    "name":"vm2",
                    "activateActivities":[
                        {
                            "url":"http://10.8.100.45/vm/818bda7a-9506-4ed1-bbe2-bd6f5f6b39ca/activate"
                        }    
                    ],
                    "deactivateActivities":[
                        {
                            "url":"http://10.8.100.45/vm/818bda7a-9506-4ed1-bbe2-bd6f5f6b39ca/deactivate"
                        }    
                    ]
                },
                {
                    "uuid":"71b320b8-de1b-4d63-8db0-8752d953dd5e",
                    "active":true,
                    "name":"vm3",
                    "activateActivities":[
                        {
                            "url":"http://10.8.100.45/vm/71b320b8-de1b-4d63-8db0-8752d953dd5e/activate"
                        }    
                    ],
                    "deactivateActivities":[
                        {
                            "url":"http://10.8.100.45/vm/71b320b8-de1b-4d63-8db0-8752d953dd5e/deactivate"
                        }    
                    ]
                }
            ]
        }
        
    ]
    }


    
