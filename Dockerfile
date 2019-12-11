FROM openjdk:11-jdk

VOLUME /tmp

ADD build/libs/deploy-travis.jar deploy-travis.jar

RUN bash -c 'touch /warranty-service.jar'

EXPOSE 8050

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/warranty-service.jar"]
