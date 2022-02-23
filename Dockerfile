FROM adoptopenjdk/openjdk8
COPY target/pms-schedule-service.jar pms-schedule-service.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "pms-schedule-service.jar"]