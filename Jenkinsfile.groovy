pipeline {
    agent any
    stages{
        stage("Run Command"){
            steps {
                sh '''
                sudo yum install wget httpd -y
                ping -c 4 google.com
                '''
            }
        }
        stage("Download Terraform"){
            steps{
                ws("tmp/"){
                    sh "pwd"
                    sh "https://releases.hashicorp.com/terraform/0.12.17/terraform_0.12.17_linux_amd64.zip"
                }
            }
        }
    }
}