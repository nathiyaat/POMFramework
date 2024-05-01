pipeline{
        agent any

             stages{
	            stage("build"){
        steps{

        	echo("build the project")
            }
        }

stage("Run Security Scan"){
steps{

	echo("security testing using burp suite")
}
}

stage("Run Unit test"){
steps{

	echo("Run UTs")
}
}
stage("Run Integration test"){
steps{

	echo("run ITs")
}
}
stage("deploy to dev"){
steps{

	echo("build the project")
}
}
stage("deploy to QA"){
steps{

	echo("deploy to QA")
}
}
stage("Run Regression TC's on QA"){
steps{

	echo("Run testcases on QA")
}
}
stage("Deploy to stage"){
steps{

	echo("deploy to stage")
}
}
stage("Run Sanity TC's on QA"){
steps{

	echo("Run sanity testcases on QA")
}
}
        stage("Deploy to PROD"){
            steps{

	            echo("Deploy to PROD")
                }
            }

            }
        }










