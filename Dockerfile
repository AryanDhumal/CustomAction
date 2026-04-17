# Step 1: Build the app
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

# 🔥 IMPORTANT: change folder where pom.xml exists
WORKDIR /app/CustomActionMain
RUN mvn clean package -DskipTests

# Step 2: Run the app
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=build /app/CustomActionMain/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
