pipeline {
    agent any

    stages {
        stage('Initialize') {
                    def dockerHome = tool 'DockerLaTest'
                    def mavenHome = tool 'MavenLaTest'
                    env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
                }
        stage('Checkout') {
                    checkout scm
                }
        stage('Build with test') {
            steps {
                echo 'Building..'
            }
            steps{
                sh "mvn clean install"
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
