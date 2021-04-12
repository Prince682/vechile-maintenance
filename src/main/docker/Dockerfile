FROM tomcat:8.0.50-jre8-alpine
RUN mkdir /tmp/truststore/
COPY conf/urlease-truststore.jks /tmp/truststore
#env CATALINA_OPTS="-Djavax.net.ssl.trustStore=/tmp/truststore/urlease-truststore.jks -Djavax.net.ssl.trustStorePassword=urlease"
ADD files/api.war /usr/local/tomcat/webapps/
EXPOSE 8080