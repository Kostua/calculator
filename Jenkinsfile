pipeline {

	    stage("Checkout") {
		steps {
			git url: 'https://github.com/Kostua/calculator.git'
			}
	   
	  	}

	  stage("Unit test") {
        steps {
			sh "./mvnw test" }
		}


}