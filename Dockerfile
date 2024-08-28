#
# Build
#
FROM maven:3-amazoncorretto-19 AS backend
WORKDIR /app
COPY . /app
RUN cd /app && mvn clean package

FROM openjdk:19-jdk-slim
COPY --from=backend /app/target/estoque-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8443
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]