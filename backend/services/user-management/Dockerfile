FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/user-management-1.0.0.jar user-management-1.0.0.jar
RUN apk --no-cache add \
    && chmod +x /entrypoint.sh \
    && apk --no-cache del
EXPOSE 8081
CMD ["/entrypoint.sh"]
