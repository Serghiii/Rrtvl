FROM tomcat:9-jre8-alpine
# RUN cp -r $CATALINA_HOME/webapps.dist/* $CATALINA_HOME/webapps/
# COPY rrt-vl-1.0.war /usr/local/tomcat/webapps/
COPY server.xml tomcat-users.xml /usr/local/tomcat/conf/
EXPOSE 80
CMD ["catalina.sh", "run"]