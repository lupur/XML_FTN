FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/vehicle-management-1.0.0.jar vehicle-management-1.0.0.jar
RUN apk --no-cache add \
    && chmod +x /entrypoint.sh \
    && apk --no-cache del
EXPOSE 8082
CMD ["/entrypoint.sh"]

