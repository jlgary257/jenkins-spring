pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        DEPLOY_DIR = 'C:\\tomcat10\\webapps'
        TOMCAT_HOME = 'C:\\tomcat10'
        CATALINA_HOME = 'C:\\tomcat10'
        APP_NAME = 'myapp'
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
                taskkill /F /IM tomcat*.exe /T || exit 0
                taskkill /F /FI "WINDOWTITLE eq Tomcat*" /T || exit 0
                timeout /t 3
                """
            }
        }

        stage('Deploy to Tomcat') {
    steps {
        bat """
        if exist "%DEPLOY_DIR%\\%APP_NAME%.war" del /F "%DEPLOY_DIR%\\%APP_NAME%.war"
        if exist "%DEPLOY_DIR%\\%APP_NAME%" rmdir /S /Q "%DEPLOY_DIR%\\%APP_NAME%"
        copy /Y target\\%APP_NAME%.war.original "%DEPLOY_DIR%\\%APP_NAME%.war"
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
            bat "call %TOMCAT_HOME%\\bin\\startup.bat || exit 0"
        }
    }
}