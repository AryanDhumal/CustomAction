# Step 1: Build using Gradle
FROM gradle:8.5-jdk17 AS build

WORKDIR /app
COPY . .

# 🔥 IMPORTANT: go into correct folder
WORKDIR /app/CustomActionMain

RUN gradle build -x test

# Step 2: Run the app
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=build /app/CustomActionMain/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
