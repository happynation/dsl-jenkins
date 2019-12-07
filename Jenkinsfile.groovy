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
                    script {
                        def exists = fileExists 'terraform_0.12.17_linux_amd64.zip'
                        if (exists) {
                            sh "unzip -o terraform_0.12.17_linux_amd64.zip"
                            sh "sudo mv -f terraform /bin"
                            sh "terraform version"
                        } else {
                            sh "wget https://releases.hashicorp.com/terraform/0.12.17/terraform_0.12.17_linux_amd64.zip"
                            sh "unzip -o terraform_0.12.17_linux_amd64.zip"
                            sh "sudo mv -f terraform /bin"
                            sh "terraform version"
                        }
                    }
                }
            }
        }
        stage("Download Packer"){
            steps{
                ws("tmp/"){
                    script {
                        def exists = fileExists 'packer_1.4.5_linux_amd64.zip'
                        if (exists) {
                            sh "unzip -o packer_1.4.5_linux_amd64.zip"
                            sh "sudo mv -f packer /bin"
                            sh "terraform version"
                        } else {
                            sh "wget https://releases.hashicorp.com/packer/1.4.5/packer_1.4.5_linux_amd64.zip"
                            sh "unzip -o packer_1.4.5_linux_amd64.zip"
                            sh "sudo mv -f packer /bin"
                            sh "packer version"
                        }
                    }
                }
            }
        }
    }
}