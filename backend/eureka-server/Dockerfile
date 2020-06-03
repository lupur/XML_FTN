FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/eureka-server-1.0.0.jar eureka-server-1.0.0.jar
RUN chmod +x /entrypoint.sh
EXPOSE 8761
CMD ["/entrypoint.sh"]
