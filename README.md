# Devices Manager

Welcome to the Devices Manager, an application for creation, update, deletion and that also helps you to locate a
specific device or devices.
This application is still on early stages so feel free to reach out if you want to suggest a new feature.

Features available:
* Create a new device;
* Update existing device;
* Fetch device by Id;
* Fetch All devices;
* Fetch devices by Brand;
* Fetch devices by State;
* Delete device by Id;

You can also find a postman collection to test the endpoints at src/main/resources/postman/collections

# Pre-requisites:
* JDK 21
* [Maven](https://maven.apache.org/install.html)

# How to run the app - Windows
1. Download the project to your local desktop
2. Open CMD and navigate to project's directory
3. Run the following command 
```
 mvn clean install
```
4. Run the following command, where X.X.X represents the most recent version:
``` 
 java -jar DevicesManager-X.X.X-SNAPSHOT.jar
```
5. Wait for application to finish startup, then you should be good to go!

# API Documentation
* [Swagger](http://localhost:8080/swagger-ui/index.html#/)

### Pending points
- [ ] Include in-memory DB for unit tests
- [ ] Include relevant tests for endpoints - without in-memory DB, unit tests repository is not working
- [ ] Include Docker compose to start DB and Application
- [X] API Documentation (Swagger)