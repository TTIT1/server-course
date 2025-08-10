FROM eclipse-temurin:23-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests -Dmaven.test.skip=true
CMD ["java", "-jar", "target/springjpa-0.0.1-SNAPSHOT.jar"]
