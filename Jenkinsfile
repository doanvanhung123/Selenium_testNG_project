pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/automation-framework.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Test') {
            steps {
                bat 'mvn test'
            }
        }

    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}