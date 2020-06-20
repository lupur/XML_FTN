FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/customer-experience-1.0.0.jar customer-experience-1.0.0.jar
RUN apk --no-cache add \
    && chmod +x /entrypoint.sh \
    && apk --no-cache del
EXPOSE 8086
CMD ["/entrypoint.sh"]