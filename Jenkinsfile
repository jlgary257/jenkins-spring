pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        DEPLOY_DIR = 'C:\\tomcat10\\webapps'
        TOMCAT_HOME = 'C:\\tomcat10'
        APP_NAME = 'Jenkinspring-0.0.1-SNAPSHOT'
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

        stage('Shutdown Tomcat') {
            steps {
                bat """
                call %TOMCAT_HOME%\\bin\\shutdown.bat
                timeout /t 5
                """
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                bat """
                if exist "%DEPLOY_DIR%\\%APP_NAME%.war" del /F "%DEPLOY_DIR%\\%APP_NAME%.war"
                if exist "%DEPLOY_DIR%\\%APP_NAME%" rmdir /S /Q "%DEPLOY_DIR%\\%APP_NAME%"
                copy /Y target\\*.war "%DEPLOY_DIR%\\%APP_NAME%.war"
                """
            }
        }

        stage('Start Tomcat') {
            steps {
                bat "call %TOMCAT_HOME%\\bin\\startup.bat"
            }
        }

    }

    post {
        success {
            echo 'Deployment completed successfully.'
        }
        failure {
            echo 'Deployment failed. Attempting to restart Tomcat...'
            bat "call %TOMCAT_HOME%\\bin\\startup.bat"
        }
    }
}
