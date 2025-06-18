FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Now run the build
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]
