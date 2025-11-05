pipeline {
    agent any

    tools {
        jdk 'JDK17'         // Must match your Jenkins JDK name
        maven 'Maven_3.9'   // Must match your Jenkins Maven installation name
    }

    options {
        timestamps()
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/saugatp0kharel/CalculatorJUnitLab.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Use 'sh' for macOS/Linux Jenkins, or 'bat' for Windows
                sh 'mvn clean verify'
            }
        }

        stage('Generate Coverage Report') {
            steps {
                jacoco execPattern: '**/jacoco.exec'
            }
        }
    }

    post {
        success {
            echo '✅ Build and coverage completed successfully!'
        }
        failure {
            echo '❌ Build or tests failed. Check logs!'
        }
    }
}
