# Multi-stage build
# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create runtime image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/employeedb.war app.war

# Expose the application port
EXPOSE 8090

# Set environment variables for better JVM performance in containers
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Create a non-root user for security
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.war"]
