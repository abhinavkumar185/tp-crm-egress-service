# This is image for CRM egress MicroService
FROM openjdk:8-jdk-alpine
LABEL maintainer="abhinav.kumar@nexxo.com"
ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.ips.gateway.crm.CrmApplication"]