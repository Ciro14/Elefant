# Execution Service

##Installation
This service is written in Java. Therefore you need an actuel version of the Java Virtual Machine (version 1.8 or higher). Please make sure that this requirement is fulfilled!

Then download the actual jar file from the folder with the name ["target"](https://github.com/Ciro14/Elefant/tree/master/execution/target). This file bundles all things you need.

You can start this service from your command line. Just type the Following command.

    java -jar execution-0.0.1-SNAPSHOT.jar

This application uses the port 8080 by default. If already an other application listening on this port, you can specify the port using an additional parameter.

    java -jar execution-0.0.1-SNAPSHOT.jar --server.port=1234

