pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/doanvanhung123/Selenium_testNG_project'
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

            publishHTML([
                        reportDir: 'report/extentV5',
                        reportFiles: 'ExtentReport.html',
                        reportName: 'Extent Report'
                    ])
        }
    }
}