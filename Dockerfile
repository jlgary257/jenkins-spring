FROM tomcat:10.1-jdk17

# Remove default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR into Tomcat
COPY target/*.war.original /usr/local/tomcat/webapps/myapp.war

EXPOSE 8088

CMD ["catalina.sh", "run"]