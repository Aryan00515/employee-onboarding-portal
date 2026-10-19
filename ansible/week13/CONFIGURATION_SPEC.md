# Week 13: Configuration Management Specification

## Target Node
- Local MacBook running macOS
- Ansible local connection
- Existing macOS login user

## Packages and Prerequisites
- Homebrew
- OpenJDK 21
- Git
- Docker Desktop and Docker CLI
- Maven Wrapper (`./mvnw`)
- Jenkins

## Users
- Reuse the current macOS login user.
- No additional user is created.

## Directories
- `~/employee-onboarding-portal-ops/`
- `~/employee-onboarding-portal-ops/config/`
- `~/employee-onboarding-portal-ops/logs/`

## Files
- `Jenkinsfile`: Jenkins pipeline
- `Dockerfile`: Docker image definition
- `pom.xml`: Maven project configuration
- `src/`: application source code
- `configuration-spec.txt`: generated configuration record

## Ports
- `8080`: Jenkins host port and application container port
- `8081`: published application host port
- `18088`: temporary continuous testing port

## Services
- Jenkins: pipeline orchestration
- Docker Desktop: container runtime
- Employee Onboarding Portal: application container

## Scope
- Verify macOS and Homebrew.
- Install OpenJDK 21 if missing.
- Create configuration directories.
- Write the configuration specification.
- Check the expected Docker CLI location.

## Safety
- Does not change firewall rules.
- Does not create users.
- Does not restart Jenkins.
- Does not replace the running application container.
