pipeline {
	agent any
    triggers {
        pollSCM('H/15 * * * *')
    }


	stages {
	    stage("Checkout") {
		steps {
			git url: 'https://github.com/Kostua/calculator.git'
		}
	   
	  }
	    stage("Build") {
		steps {
			sh "mvn -B -DskipTests clean package"
     		 }
 		
	   }

}

}
