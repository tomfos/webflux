FROM eclipse-temurin:21-jdk-alpine
LABEL authors="AiZEN"

WORKDIR /app

# Create logs directory with proper permissions
RUN mkdir -p /app/logs

COPY target/produit.jar ./produit.jar

EXPOSE 8089

CMD ["java", "-jar", "produit.jar"]