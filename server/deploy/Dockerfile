FROM 10.216.70.22:7443/common/java-font:8-jre-alpine

ADD server/target/serverb.jar /serverb.jar

ENTRYPOINT ["java", "-jar", "/serverb.jar"]
