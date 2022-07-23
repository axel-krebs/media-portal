# Media-Portal prototype

Leverage reactive technologies like Quarkus, Vertx, Kotlon.JS


### Building

The example can be built with

    gradle clean build

### Running the example locally

The example can be run locally using the following Maven goal:

    gradle quarkusDev

### Build a native image

Configuration in 'gradle.properties' is already set to quarkus.package.type=native

    gradle server:build

### Access services using a web browser

When the application is running, you can use a web browser to access the service. 
