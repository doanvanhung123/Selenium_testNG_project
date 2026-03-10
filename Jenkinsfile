pipeline {
    agent any

    parameters {
        choice(name: 'ENV', choices: ['dev', 'sit', 'uat'])
    }

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/doanvanhung123/Selenium_testNG_project'
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