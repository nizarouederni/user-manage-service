def CONTAINER_NAME = "userservice"
def CONTAINER_TAG = getTag(env.BUILD_NUMBER, env.BRANCH_NAME)
def HTTP_PORT = getHTTPPort(env.BRANCH_NAME)
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

        stage("Image Prune") {
            imagePrune(CONTAINER_NAME)
        }

        stage ('Image Build'){
            imageBuild(CONTAINER_NAME, CONTAINER_TAG)
            sh "docker images"
        }

        stage('Push to Docker Registry') {
            withCredentials([usernamePassword(credentialsId: 'dockerhubcredential', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                pushToImage(CONTAINER_NAME, CONTAINER_TAG, USERNAME, PASSWORD)
            }
        }

        stage('Run App') {
            withCredentials([usernamePassword(credentialsId: 'dockerhubcredential', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                runApp(CONTAINER_NAME, CONTAINER_TAG, USERNAME, HTTP_PORT)

            }
        }


    } finally{
        deleteDir()
    }
}

def imagePrune(containerName) {
    try {
        sh "docker image prune -f"
        sh "docker stop $containerName"
    } catch (ignored) {
    }
}

def imageBuild(containerName, tag) {
    sh "docker build -t $containerName:$tag  -t $containerName --pull --no-cache ."
    echo "Image build complete"
    sh "docker images"
}

def pushToImage(containerName, tag, dockerUser, dockerPassword) {
    sh "docker login -u $dockerUser -p $dockerPassword"
    sh "docker tag $containerName:$tag $dockerUser/$containerName:$tag"
    sh "docker push $dockerUser/$containerName:$tag"
    echo "Image push complete"
}

def runApp(containerName, tag, dockerHubUser, httpPort) {
    sh "docker pull $dockerHubUser/$containerName:$tag"
    sh "docker run -p $httpPort:$httpPort --name $containerName $dockerHubUser/$containerName:$tag"
    echo "Application started on port: ${httpPort} (http)"
}

String getHTTPPort(String branchName) {
//     if (branchName == 'master') {
//         return '9001'
//
//     } else if (branchName.startsWith("release-") || branchName.startsWith("hotfix-") || branchName == 'ready') {
//         return '9002'
//     }
//     return '9003'
    return '9001'
}


String getTag(String buildNumber, String branchName) {
    if (branchName == 'master') {
        return buildNumber + '-unstable'
    }
    return buildNumber + '-stable'
}