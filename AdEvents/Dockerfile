FROM bde2020/spark-base:2.4.4-hadoop2.7

LABEL maintainer="Revanth Nyalakonda <revanthreddy.n@gmail.com>"

ENV SPARK_MASTER_NAME spark-master
ENV SPARK_MASTER_PORT 7077
ENV SPARK_APPLICATION_JAR_LOCATION /usr/src/app/target/AdEvents-0.0.1-SNAPSHOT-with-dependencies.jar
ENV SPARK_APPLICATION_MAIN_CLASS com.adevents.SparkApplication
ENV SPARK_APPLICATION_ARGS "/Users/revanthnyalakonda/Downloads/ad-events/part-00000-732eeb74-f251-4f96-85d5-5c9ae95ba709-c000.txt /Users/revanthnyalakonda/Downloads/ad-events/results"

#ADD demo.sh /

#RUN chmod +x /demo.sh

#CMD ["/bin/bash", "/demo.sh"]

RUN apk add --no-cache openjdk8 maven\
      && mkdir -p /app \
      && mkdir -p /usr/src/app

# Copy the POM-file first, for separate dependency resolving and downloading
ONBUILD COPY pom.xml /usr/src/app
ONBUILD RUN cd /usr/src/app \
      && mvn dependency:resolve
ONBUILD RUN cd /usr/src/app \
      && mvn verify

# Copy the source code and build the application
ONBUILD COPY . /usr/src/app
ONBUILD RUN cd /usr/src/app \
      && mvn clean package
      
COPY . /usr/src/app
RUN cd /usr/src/app \
    && mvn -DskipTests=true package

COPY submit.sh /

CMD ["/bin/bash", "/submit.sh"]