1. Pre-requisites - Need the below tools.
2. eclipse or spring tool suite, gradle7.4 and mongo db instance running locally.
3. To import the project, open git bash and clone the repo using git clone
4. Open Spring Tool suit or Eclipse and import the project as a gradle project, select the folder where the project is cloned. 
5. For example {path to the project on your computer}\myretail-restservices\myretail-restservices
6. Then select Override settings and in the Local installation directory select the gradle folder installed on the system.
7. Open cmd and change directory to the folder where you can see gradle folder inside the project and run gradle clean build.
8. Now open mongo, and run an new mongo db instance and copy paste the connection string from mongo in the properties file.
9. Click on the main class and select run as spring boot app.
