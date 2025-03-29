import groovy.json.JsonSlurper

pipeline {
    agent any

    environment {
        BUILD_VERSION                =         'v0.0.1'
        GIT_CREDENTIALS_ID           =         'YabiSkywalker'
        DOCKER_CREDENTIALS_ID        =         'JenkinsToDocker'
        JAVA_HOME                    =         '/usr/lib/jvm/java-17-openjdk-amd64'
        ACC_ID                       =         'kQsoKw8wQV6hD_BlSuffZA'
        PROJ_ID                      =         'default_project'
        ORG_ID                       =         'default'
        DPL_PIPE                     =         'RollingDeploy'
        HARN_API                     =         'app.harness.io/gateway/pipeline/api/pipeline/execute'
        CHECK_API                    =         'app.harness.io/pipeline/api/pipelines/execution/v2'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: "main", url: "https://github.com/YabiSkywalker/uMeet-service.git"
            }
        }

        stage('Clean Package') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build') {
            steps {
                sh "docker buildx build --platform=linux/amd64 -t umeet-service ."
            }
        }

        stage('Tag') {
            steps {
                sh "docker tag umeet-service yabiskywalker/umeet-service-images:${env.BUILD_VERSION}"
            }
        }

        stage('Authenticate Docker') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: env.DOCKER_CREDENTIALS_ID,
                    usernameVariable: 'DOCKER_HUB_USER',
                    passwordVariable: 'DOCKER_HUB_PASSWORD'
                )]) {
                    sh """echo ${DOCKER_HUB_PASSWORD} | docker login -u ${DOCKER_HUB_USER} --password-stdin"""
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh "docker push yabiskywalker/umeet-service-images:${env.BUILD_VERSION}"
            }
        }
    }

    post {
        success {
            echo "✅ Build successful!"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}