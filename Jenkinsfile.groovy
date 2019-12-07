pipeline {
    agent any
    stages{
        stage("Run Command"){
            steps {
                sh '''
                sudo yum install wget unzip httpd -y
                ping -c 4 google.com
                '''
            }
        }
        stage("Download Terraform"){
            steps{
                ws("tmp/"){
                    sh "pwd"
                    sh "wget https://releases.hashicorp.com/terraform/0.12.17/terraform_0.12.17_linux_amd64.zip"
                    sh "unzip terraform_0.12.17_linux_amd64.zip"
                    sh "sudo mv terraform /bin"
                    sh "terraform version"
                }
            }
        }
    }
}