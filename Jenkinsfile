def CONTAINER_NAME = "userservice"


node{
    try{
        stage('Initialize') {
            def dockerHome = tool 'DockerLaTest'
            def mavenHome = tool 'MavenLaTest'
            env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
        }

        stage('Checkout') {
            checkout scm
        }

        stage('Build with test') {
            sh "mvn -version"
            sh "mvn clean install"
        }
        stage('Build with test') {
            sh "docker images"
        }
    } finally{

    }
}