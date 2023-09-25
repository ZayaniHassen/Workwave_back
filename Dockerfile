# syntax=docker/dockerfile:experimental
FROM maven:3.8.3-jdk-11 AS builder

# Configuration de l'environnement de construction
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline

# Copie du code source et compilation de l'application
COPY src/ src/
RUN --mount=type=cache,target=/root/.m2 mvn package

# Configuration de l'image finale basée sur Alpine
FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ENV SERVER_URL=http://185.192.96.18
EXPOSE 8886
COPY --from=builder /app/target/LitigeRecouvrement-0.0.1-SNAPSHOT.jar LitigeRecouvrement-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/LitigeRecouvrement-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-Djdk.tls.client.protocols=TLSv1.2","-jar","/LitigeRecouvrement-0.0.1-SNAPSHOT.jar"]
