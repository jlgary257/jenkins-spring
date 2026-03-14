pipeline {
    agent any

    tools {
        maven 'Maven3'
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
            }
        }

    }

    post {
        success {
            echo 'Build completed successfully.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}