pipeline {
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
	    stage("Build") {
		steps {
		   echo 'This is minimal pipeline.'
     		 }
	   }


}

}
