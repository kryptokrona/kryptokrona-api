# Using Tomcat 9.0 since the latest doesn't work with Spring Boot
FROM tomcat:9.0-jdk17-openjdk
ARG WAR_FILE=build/libs/kryptokrona-api.war
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]