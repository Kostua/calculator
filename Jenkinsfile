pipeline {
	agent any
	tools {
		maven 'maven 3.5.4'
		jdk 'java_11'
		}
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
	    stage("Build") {
		steps {
		  sh 'mvn -B -Dmaven.test.skip=true clean package' 
     		 }
	   }


}

}
