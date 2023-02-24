# Using Tomcat 9.0 since the latest doesn't work
FROM tomcat:9.0.68
EXPOSE 8080:8080
COPY ./build/libs/kryptokrona-api.war/ /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat
CMD ["catalina.sh", "run"]