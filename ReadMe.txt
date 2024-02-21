Requirements:

  Java 17
  SpringBoot3+ version
  Maria DB Server should be up and running
    Credentials to connect to MariaDB:
      url: jdbc:mariadb://localhost:3306/lufthansa-db
      username: root
      password: root
      driverClassName: org.mariadb.jdbc.Driver

In order to run this application, please follow below steps:
  Build using maven
    mvn clean install
  Start the app:
    mvn spring-boot:run

