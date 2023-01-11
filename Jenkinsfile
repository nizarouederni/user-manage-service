pipeline {
    agent any
    tools {
        maven 'MavenLaTest'
        docker 'DockerLaTest'
    }
    stages {

        stage('Build with test') {
            steps {
                echo 'Building..'
                sh "mvn clean install"
            }
        }
        stage('docker images') {
            steps {
                sh 'docker images'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
