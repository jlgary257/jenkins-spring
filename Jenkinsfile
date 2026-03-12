pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        IMAGE_NAME = 'myapp'
        CONTAINER_NAME = 'myapp-container'
    }

    stages {

        stage('Checkout Source') {
            steps {
                checkout scm
            }
        }

        stage('Build WAR') {
            steps {
                bat 'mvn clean package -DskipTests'
                bat 'dir target\\*.war.original'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %IMAGE_NAME% ."
            }
        }

        stage('Stop Old Container') {
            steps {
                bat """
                docker stop %CONTAINER_NAME% || exit 0
                docker rm %CONTAINER_NAME% || exit 0
                """
            }
        }

        stage('Run Docker Container') {
            steps {
                bat "docker run -d -p 8088:8080 --name %CONTAINER_NAME% %IMAGE_NAME%"
            }
        }

    }

    post {
        success {
            echo 'Deployment to Docker completed successfully.'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}