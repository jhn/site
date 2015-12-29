FROM java:8

RUN apt-get update

ADD target/site-0.1.0-SNAPSHOT-standalone.jar /srv/site.jar

CMD ["java", "-jar", "/srv/site.jar"]
