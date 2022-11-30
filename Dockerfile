FROM openjdk:17-jdk-alpine
LABEL maintainer="AymenChikeb meeting-planner"
COPY target/meeting-planner-0.0.1.jar meeting-planner-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/meeting-planner-0.0.1.jar"]