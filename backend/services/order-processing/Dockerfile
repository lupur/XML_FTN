FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/order-processing-1.0.0.jar order-processing-1.0.0.jar
RUN apk --no-cache add \
    && chmod +x /entrypoint.sh \
    && apk --no-cache del
EXPOSE 8083
CMD ["/entrypoint.sh"]


