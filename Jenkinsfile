pipeline {
    agent any
    triggers {
        pollSCM('*/15 * * * *')
    }
    options { disableConcurrentBuilds() }
    stages {
        stage('Permissions') {
            steps {
                sh 'chmod 775 *'
            }
        }
stage('Cleanup') {
            steps {
                sh './mvnw clean'
            }
        }
stage('Test') {
            steps {
                sh './mvnw test'
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
        stage('Build') {
            steps {
                sh './mvnw package'
            }
        }
        stage('Update Docker UAT image') {
            when { branch "master" }
            steps {
                sh '''
                    docker build --no-cache -t travelAgency .
                    docker tag travelAgency:latest amritendudockerhub/travelAgency:latest
                    docker push amritendudockerhub/travelAgency:latest
docker rmi travelAgency:latest
                '''
            }
        }
        stage('Update UAT container') {
            when { branch "master" }
            steps {
                sh '''
                    docker pull amritendudockerhub/travelAgency:latest
                    docker stop travelAgency
                    docker rm travelAgency
                    docker run -p 9090:9090 --name travelAgency -t -d amritendudockerhub/travelAgency
                    docker rmi -f $(docker images -q --filter dangling=true)
                '''
            }
        }
        stage('Release Docker image') {
            when { buildingTag() }
            steps {
                sh '''
                    docker build --no-cache -t person .
                    docker tag person:latest amritendudockerhub/travelAgency:${TAG_NAME}
                    docker push amritendudockerhub/travelAgency:${TAG_NAME}
docker rmi $(docker images -f “dangling=true” -q)
               '''
            }
        }
    }
}
