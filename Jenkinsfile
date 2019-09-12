pipeline {
	tools {
		maven 'maven 3.5.4'
		jdk 'java_11'
		}
    environment {
    registry = "kostua/calculator"
    registryCredential = 'dockerhub'
	dockerImage = ''
	}
	agent any
    triggers {
        pollSCM('H/15 * * * *')
    }


	stages {
	    stage ('Initialize') {
		steps {
			sh '''
			   echo "PATH = ${PATH}"
			   echo "M2_HOME = ${M2_HOME}"
			'''
			}
		}
	    stage("Checkout") {
		steps {
			git url: 'https://github.com/Kostua/calculator.git'
		}
	   
	  }
	    stage('SonarQube analysis') {
		   steps { 
			withSonarQubeEnv('sonar'){
		        sh 'mvn clean package sonar:sonar'	
			}
		}
	}

	    stage("Build") {
		steps {
		  sh 'mvn -B -Dmaven.test.failure.ignore=true install' 
     		 }
		post {
		   success {
			junit 'target/surefire-reports/**/*.xml'
			}
		}
	   }

	   stage("Docker build"){
		   steps {
			   echo 'Start to build docker image'

			   script {
				dockerImage  = docker.build registry + "latest" 
			   }
		   }
	   }

	   stage('Deploy Image'){
		   steps{
			   script{
				   docker.withRegistry( '', registryCredential) {
					   dockerImage.push()
				   }
			   }
		   }

	   }
	   stage("Deploy to staging"){
		   steps {
			   sh "docker run -d --rm -p 8765:8080 --name calculator kostua/calculator:latest"
		   }
		   post{
			   always {
				   sh "docker stop calculator"
			   }
		   }
	   }
	   stage('Remove Unused docker image'){
		   steps{
			   sh "docker rmi $registry:latest"
		   }
	   }
    }

}
