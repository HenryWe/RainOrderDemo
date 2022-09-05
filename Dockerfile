
FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/*.jar rain.jar
EXPOSE 443
ENTRYPOINT exec java $JAVA_OPTS -jar rain.jar