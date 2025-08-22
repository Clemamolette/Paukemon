FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copier les fichiers de configuration Maven
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

# Rendre le script mvnw exécutable et corriger les fins de ligne Windows
RUN chmod +x mvnw && \
    sed -i 's/\r$//' mvnw && \
    ls -la mvnw

# Télécharger les dépendances Maven (cache layer)
RUN ./mvnw dependency:go-offline -B

# Copier le code source
COPY src src

# Construire l'application
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
RUN groupadd -r spring && useradd -r -g spring spring

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

RUN mkdir -p /app/data && chown -R spring:spring /app

USER spring
EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:file:/app/data/paukemon
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_H2_CONSOLE_ENABLED=true
ENV SPRING_JPA_GENERATE_DDL=true

ENTRYPOINT ["java", "-jar", "/app/app.jar"] 