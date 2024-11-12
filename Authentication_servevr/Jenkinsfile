pipeline {
    agent any

    tools {
        maven 'my-maven'
        jdk 'my-jdk'
    }

    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/Book-CICD/authentication-sr.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                bat "mvn clean install -DskipTests"
            }
        }
        stage('Test') {
            steps {
                bat "mvn test"
            }
        }
        stage('Deploy') {
            steps {
                bat "docker build -t authentication-service ."
                bat "docker run -p 8877:8877 -d --name authentication-sr authentication-service"
            }
        }
    }
}