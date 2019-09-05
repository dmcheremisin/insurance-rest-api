#About this project.

## Task: 
Create rest api for insurance calculation depending on tariff, bonus, customer age and customer inception date.

## Tech Stack:
-	Java 8
-	Maven
-	SpringBoot 2.1.7
-	H2
-	jUnit 5
-	Mockito
-	Swagger 2.7.0
-	Project Lombok
-   ValidationAPI
-   Log4j

## Project structure.

Maven multi module project consisting of 3 parts:
-	“insurance-controller” main module with controllers, swagger and SpringBootApplication 
-	“insurance-data” module related to database features
-	“insurance-engine” module related to final price calculation

## How to Start
### Requirements:
-	JDK 8
-	Preinstalled Maven 3+
-	Free port 8080
### Steps:
1.	Execute command in the root folder: mvn clean package
2.	Go to {project_root_folder}/insurance-controller/target
3.	Execute command: java -jar insurance-controller-0.0.1-SNAPSHOT.jar
4.	Go to browser: http://localhost:8080/swagger-ui.html


## How to use
There are 2 ways of project usage:
1.	Use swagger to explore all rest api methods. 
It contains documented information about required path parameters, fields and json bodies.
Additionally, it is possible to execute all methods from swagger’s ui.
2.	Use Postman collection from the file: {project_root_folder}/notForBuild/Insurance Api.postman_collection.json

## Logs
There will be file with logs when you start the application. It will be located in the {jar_folder}/logs/application.log.
Try to get user with wrong id or create customer with date of birth in the future, then you will observe messages in the log file.

## Docker
Docker may be used to test this application.

- Build image command should be performed from {project_root_folder}/insurance-controller/target directory

Command: docker build -t insurance-app -f ../../notForBuild/Dockerfile .

- Run docker image

Command: docker run -d -p 8080:8080 insurance-app
