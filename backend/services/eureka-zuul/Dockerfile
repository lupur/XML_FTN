FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/eureka-zuul-1.0.0.jar eureka-zuul-1.0.0.jar
RUN apk --no-cache add \
    && chmod +x /entrypoint.sh \
    && apk --no-cache del
EXPOSE 8080
CMD ["/entrypoint.sh"]
