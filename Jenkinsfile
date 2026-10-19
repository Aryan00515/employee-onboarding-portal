
pipeline {
    agent any

    parameters {
        choice(
            name: 'DEPLOY_ENV',
            choices: ['dev', 'test', 'prod'],
            description: 'Select the deployment environment'
        )

        string(
            name: 'APP_PORT',
            defaultValue: '8081',
            description: 'Host port for the application'
        )
    }

    environment {
    DOCKERHUB_USER = 'aryanparulekar'
    IMAGE_NAME = 'aryanparulekar/employee-onboarding-portal'
    CONTAINER_NAME = 'employee-onboarding-portal'
    PATH = "/Users/aryanparulekar15/.docker/bin:${env.PATH}"
}

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean compile'
            }
        }

        
        stage('Continuous Testing') {
            steps {
                sh '''
                    set -eu

                    echo "Starting application for Selenium tests..."

                    ./mvnw spring-boot:run \
                      -Dspring-boot.run.arguments=--server.port=18088 \
                      -Dspring-boot.run.jvmArguments=-Dspring.devtools.restart.enabled=false \
                      > app.log 2>&1 &

                    APP_PID=$!

                    cleanup() {
                        pkill -P "$APP_PID" 2>/dev/null || true
                        kill "$APP_PID" 2>/dev/null || true
                    }

                    trap cleanup EXIT

                    APP_READY=0

                    for i in $(seq 1 30); do
                        if curl -fsS http://127.0.0.1:18088/ \
                            >/dev/null; then
                            APP_READY=1
                            break
                        fi

                        sleep 2
                    done

                    if [ "$APP_READY" -ne 1 ]; then
                        echo "Application failed to start."
                        cat app.log
                        exit 1
                    fi

                    echo "Application is ready. Running Selenium tests..."

                    ./mvnw test \
                      -Dapp.url=http://127.0.0.1:18088
                '''
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/TEST-*.xml',
                          allowEmptyResults: false
                }
            }
        }


        stage('Package') {
            steps {
                sh './mvnw package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    docker build \
                      -t "$IMAGE_NAME:$BUILD_NUMBER" \
                      -t "$IMAGE_NAME:latest" .
                '''
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DH_USER',
                        passwordVariable: 'DH_TOKEN'
                    )
                ]) {
                    sh '''
                        set +x
                        echo "$DH_TOKEN" | docker login \
                          --username "$DH_USER" \
                          --password-stdin

                        docker push "$IMAGE_NAME:$BUILD_NUMBER"
                        docker push "$IMAGE_NAME:latest"

                        docker logout
                    '''
                }
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                    docker rm -f "$CONTAINER_NAME" 2>/dev/null || true

                    docker run -d \
                      --name "$CONTAINER_NAME" \
                      --restart unless-stopped \
                      -p "${APP_PORT}:8080" \
                      "$IMAGE_NAME:$BUILD_NUMBER"

                    sleep 10

                    docker ps --filter "name=$CONTAINER_NAME"

                    curl --retry 5 --retry-delay 3 --retry-connrefused \
                      -f "http://127.0.0.1:${APP_PORT}/"
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
            echo "Docker image: ${IMAGE_NAME}:${BUILD_NUMBER}"
            echo "Application URL: http://localhost:${APP_PORT}"
        }

        failure {
            echo 'Pipeline failed. Check the Jenkins console output.'
        }
    }
}
