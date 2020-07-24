# Onco-App
Final Ironhack Project
* [Goal](#goal)

* [How to Works](#work)

* [Tools](#tools)

* [Documentation](#documentation)


## <a name= "goal"></a>Goal
The aim of this project is to develop an Api to control the diet of oncology patients. 
Cancer patients often suffer from diverse degrees of malnutrition. It is associated with morbidity and mortality.
Patient-generated subjective global screening (VSG-GP) remains the reference malnutrition screening method, but its complexity and training requirements prevent wider applicability by oncologists. Patients should complete the first part of the form, which should be supervised and completed by the doctor with a final score. 
The score is as simple as three main categories, A (normo-nutrition), B (malnutition), C(severe malnutrition).
This application, helps both patient and doctor to track and complete their evaluations withouth needing face-to-face sessions. 


## <a name= "work"></a>How it Works

**Deployment Servers**
* [Heroku Config Server](https://onco-app-config-server.herokuapp.com/)

**Deployment Service**
* [Heroku Patient](https://onco-app-patient-client.herokuapp.com/)
* [Heroku doctor](https://onco-app-doctor-client.herokuapp.com/)
* [Heroku treatment](https://onco-app-treatment-client.herokuapp.com/)
* [Heroku evaluation](https://onco-app-evaluation-client.herokuapp.com/)
* [Heroku User](https://onco-app-user-client.herokuapp.com/)
* [Heroku result](https://https://onco-app-result-client.herokuapp.com/)
* [Heroku edge-service](https://onco-app-edge-service.herokuapp.com/)

Test our endpoints with swagger
* [Heroku Edge Service Swagger](https://onco-app-edge-service.herokuapp.com//swagger-ui.html#/)

The only route permitted by all users is the sign-in route (auth-controller in Heroku Edge-Service). 
Since JWT(JSON Web Tokens) authentication is used, a token would be generated. Introduce that token in the header request as: Bearer "token"

## <a name= "tools"></a>Tools
- Heroku 
- MongoAtlas

**Backend**
- Spring Boot Dependencies (Spring Boot DevTools, Spring Data JPA, Spring Web)
- Netflix OSS (Eureka, Ribbon, Hystrix)
- Postman 
- Swagger (API Document with HTML)
- h2 database
- mongoDB

**Frontend**
- Angular
- Boostrap
