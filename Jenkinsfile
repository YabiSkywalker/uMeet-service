import groovy.json.JsonSlurper

pipeline {
    agent any

    environment {
        BUILD_VERSION                =         'v0.01'
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
                git branch: "main", url: "https://github.com/YabiSkywalker/bandera.git"
            }
        }

        stage('Clean Package') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build') {
            steps {
                sh "docker buildx build --platform=linux/amd64 -t bandera-svc ."
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
                sh "docker push yabiskywalker/bandera-service-images:${env.BUILD_VERSION}"
            }
        }

        stage('reDeploy') {
            steps {

                script {
                    def harnessTrigger = sh(script: """
                                   curl -X POST "https://${env.HARN_API}/${env.DPL_PIPE}?accountIdentifier=${env.ACC_ID}&orgIdentifier=${env.ORG_ID}&projectIdentifier=${env.PROJ_ID}" \\
                                   -H "Content-Type: application/yaml" \\
                                   -H "x-api-key: <<<<API_KEY>>>" \\
                                   -d '
                                   pipeline:
                                     identifier: "RollingDeploy"
                                     stages:
                                       - stage:
                                           identifier: "Deploy"
                                           type: "Deployment"
                                           spec:
                                             service:
                                               serviceInputs:
                                                 serviceDefinition:
                                                   spec:
                                                     artifacts:
                                                       primary:
                                                         sources:
                                                           - identifier: "Primary"
                                                             spec:
                                                               tag: "${env.BUILD_VERSION}"
                                   '
                               """, returnStdout: true
                    ).trim()
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful!"
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}