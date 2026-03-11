pipeline {
    agent any

    environment {
        DEPLOY_DIR = 'C:\\tomcat10\\webapps'
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

        stage('Deploy to Tomcat') {
            steps {
                bat """
                copy /Y target\\*.war "%DEPLOY_DIR%"
                """
            }
        }

    }

    post {
        success {
            echo 'Deployment completed successfully.'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}