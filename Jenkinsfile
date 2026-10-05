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
            description: 'Port on which the Spring Boot application will run'
        )
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

        stage('Package') {
            steps {
                sh './mvnw package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying Employee Onboarding Portal to ${params.DEPLOY_ENV}"
                echo "Application port: ${params.APP_PORT}"

                sh '''
                    mkdir -p deployment

                    if [ -f deployment/app.pid ]; then
                        if kill -0 "$(cat deployment/app.pid)" 2>/dev/null; then
                            kill "$(cat deployment/app.pid)" || true
                            sleep 3
                        fi
                    fi

                    cp target/*.jar deployment/onboarding-portal.jar

                    nohup java -jar deployment/onboarding-portal.jar \
                        --server.port=${APP_PORT} \
                        > deployment/app.log 2>&1 &

                    echo $! > deployment/app.pid

                    sleep 10

                    curl -f http://127.0.0.1:${APP_PORT}/
                '''
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully."
            echo "Deployment environment: ${params.DEPLOY_ENV}"
            echo "Application running on port: ${params.APP_PORT}"
            echo "Nginx URL: http://localhost:8088"
        }

        failure {
            echo "Pipeline failed."
        }
    }
}