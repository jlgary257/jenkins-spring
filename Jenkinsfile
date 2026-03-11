pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        DEPLOY_DIR = 'C:\\tomcat10\\webapps'
        TOMCAT_HOME = 'C:\\tomcat10'
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
                bat "copy /Y target\\*.war \"%DEPLOY_DIR%\""
            }
        }

        stage('Restart Tomcat') {
            steps {
                bat """
                call %TOMCAT_HOME%\\bin\\shutdown.bat
                timeout /t 5
                call %TOMCAT_HOME%\\bin\\startup.bat
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