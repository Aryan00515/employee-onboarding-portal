# Week 11: Docker Image and Container Lifecycle

## 1. Objective

To containerize the Employee Onboarding Portal, build and tag a Docker image, run the container with port mapping, inspect logs and container details, and demonstrate the complete Docker container lifecycle.

## 2. Environment

- Application: Employee Onboarding Portal
- Framework: Spring Boot
- Java: 21
- Docker image: `employee-onboarding-portal:week11`
- Container name: `onboarding-container`
- Container port: 8080
- Host port: 8082

## 3. Dockerfile

The Dockerfile uses a multi-stage build. Maven compiles and packages the application, and a Java 21 runtime image runs the resulting JAR.

```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x mvnw

COPY src ./src

RUN ./mvnw -DskipTests package

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/onboarding-portal-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

## 4. Build the Docker Image

Command:

```bash
docker build -t employee-onboarding-portal:week11 .
```

The image built successfully.

Recorded image details:

- Tag: `employee-onboarding-portal:week11`
- Image ID prefix: `2c25ebc80f5f`
- Reported disk usage: 589 MB
- Reported content size: 165 MB

## 5. Run the Container

Command:

```bash
docker run -d --name onboarding-container -p 8082:8080 employee-onboarding-portal:week11
```

The container was created with host port 8082 mapped to container port 8080.

## 6. Verify the Running Container

Command:

```bash
docker ps
```

Observed results:

- Container ID prefix: `5c976db06580`
- Image: `employee-onboarding-portal:week11`
- Container name: `onboarding-container`
- Status: Running
- Port mapping: `8082->8080`

## 7. Inspect Logs and Container Resources

Commands executed:

```bash
docker inspect onboarding-container
docker stats --no-stream onboarding-container
```

The inspection output confirmed that the container was running, using the expected image, and exposing container port 8080 on host port 8082.

Observed resource usage at the time of inspection:

- CPU: 0.40%
- Memory: 378.2 MiB
- Memory usage percentage: 4.77%

The logs were also checked using `docker logs onboarding-container`.

## 8. Stop the Container

Commands:

```bash
docker stop onboarding-container
docker ps -a
```

Observed result: The container status was `Exited (143)`, confirming that it had stopped.

## 9. Restart the Container

Commands:

```bash
docker restart onboarding-container
docker ps
```

Observed result: The container returned to the running state, with host port 8082 mapped to container port 8080.

## 10. Start the Stopped Container

Commands:

```bash
docker start onboarding-container
docker ps
```

Observed result: The container started successfully and showed status `Up`.

## 11. Remove the Container

Commands:

```bash
docker stop onboarding-container
docker rm onboarding-container
docker ps -a
```

Observed result: The container was stopped and removed successfully. The final `docker ps -a` output showed no containers.

Removing the container does not automatically remove its Docker image.

## 12. Deliverables

- `Dockerfile`
- `.dockerignore`
- `docs/week11-docker-lifecycle.md`
- Docker image tagged `employee-onboarding-portal:week11`
- Docker command history and lifecycle results
- Screenshots of the running and stopped container states, where captured

## 13. Conclusion

The Employee Onboarding Portal was packaged into a Docker image and run as a container with port mapping. Container inspection and resource usage were checked, and the container was successfully restarted, stopped, started again, and removed.
