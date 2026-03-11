pipeline {
    agent any

    tools {
        maven 'Maven'  // ← must match EXACTLY what you named it in Jenkins Tools
    }

    environment {
    DEPLOY_DIR = 'C:\\tomcat10\\webapps'
    TOMCAT_HOME = 'C:\\tomcat10'
    APP_NAME = 'myapp'
    CATALINA_HOME = 'C:\\tomcat10'
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
                bat 'dir target\\*.war'
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
                dir "%DEPLOY_DIR%"
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