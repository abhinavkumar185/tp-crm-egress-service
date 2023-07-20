pipeline{
    agent { label 'ba007' }

    environment {
        NAME = "${SERVICE}"
        GIT_COMMIT_MSG = getCommitMessage()
        //tag = getCommitHash('git rev-parse HEAD')
        tag = "${env.gitlabBranch}"
        image = "${IMAGE_PATH}"
        dockerRegistry= "reg.qpayi.com:5000/in/gateway/"
        targetPropertyBranchRegex = /^[A-Za-z0-9]*\/(bugfix|hotfix)\/[A-Za-z0-9\-\.]+$/
        featureBranchRegex = /^[A-Za-z0-9]*\/(IPS|feature)\/[A-Za-z0-9\-\.]+$/
        longLivedBranchRegex = /^[A-Za-z0-9]*\/(master|master_prod)$/
        //releaseBranch = /^(master)$/
        //releaseBranch = /^[A-Za-z0-9]*\/(master)$/
        releaseBranch = /^rc-[0-9\.]*$/
        RUN_INTEGRATION_TEST = true
        workDir = "${SERVICE}"
    }

    triggers {
        pollSCM('H H(2-3) * * *') //runs this pipeline on every commit
        //cron('30 23 * * *') //run at 23:30:00
    }

    stages{
        stage('Export'){
            steps{
                script{
                    display()
                }
            }
        }

        stage('Checkout'){
            steps{
                checkout changelog: false,
                poll: false,
                scm: [$class: 'GitSCM',
                branches: [[name: "*/${env.gitlabBranch}"]],
                doGenerateSubmoduleConfigurations: false,
                extensions: [],
                submoduleCfg: [],
                userRemoteConfigs: [[credentialsId: 'git', url: 'https://gl.qpayi.com/in/gateway/crm-egress-gateway.git']]]
            }
        }

        stage('build crm-egress-gateway'){
            steps{
                script{
                    setDefaultTargetBranch()
                    checkForLongLivedBranch()
                    checkBuildCause()
                    checkEmptyCommit()

                    checkChangeSets()

                    stage('UNIT TEST'){
                        unitTest()
                    }

//                     if(env.SPECS_UPDATED.toBoolean() || env.FORCE_PUSH_SPECS.toBoolean()){
//                         stage('PUBLISH swagger specs'){
//                             sshagent(credentials : ['acquiring_staging']) {
//                                 sh 'ssh -o StrictHostKeyChecking=no ec2-user@54.78.183.209 uptime'
//                                 sh 'scp public/specs/crm-egress-gateway.json ec2-user@54.78.183.209:/home/ec2-user/openapi'
//                                 sh 'ssh ec2-user@54.78.183.209 /home/ec2-user/openapi/launch'
//                             }
//                         }
//                     }

                        if(env.SHOULD_BUILD.toBoolean() || env.NIGHTLY_BUILD.toBoolean()){
                            stage("BUILD ::" + env.workDir){
                               build()
                            }
                         }


//                     if(env.NIGHTLY_BUILD.toBoolean()){
//                         stage("NIGHTLY BUILD S-ANALYSIS (LL) :: "+ env.workDir){
//                             staticAnalysisForLongLivedBranch()
//                         }
//                     }
//                     else{
//                         echo "BUILD IS USER INITIATED...CHECKING FOR SONARQUBE"
//                         if(env.SONAR_ANALYSIS_REQUIRED.toBoolean()){
//                             if(env.LONG_LIVED_BRANCH.toBoolean()){
//                                 stage("ON-DEMAND ANALYSIS (LL) :: "+ env.workDir){
//                                     staticAnalysisForLongLivedBranch()
//                                 }
//                             }else{
//                                 stage("ON-DEMAND ANALYSIS (SL) :: "+ env.workDir){
//                                     staticAnalysis()
//                                 }
//                             }
//                         }
//                         else{
//                             echo "SONARQUBE ANALYSIS NOT REQUESTED"
//                         }
//                     }

                    if(env.DOCKER_BUILD_REQUIRED.toBoolean()){
                        stage("DOCKER IMAGE :: "+ env.workDir){
                            dockerBuild()
                        }
                    }
                }
            }
        }
    }
}

def display(){
    sh """export -p"""
}

def unitTest(){
    sh """echo crm-egress-gateway :: Unit test"""
}

def build(){
    sh """echo building crm-egress-gateway
            rm -rf target/
            mvn clean package"""
}

def staticAnalysis(){
    def updatedKey = "${BRANCH_NAME}"
    updatedKey = updatedKey.replaceAll("-",".");
    sh """echo staticAnalysis
        mvn sonarScan -Dsonar.host.url=${SONAR_HOST} -Dsonar.projectName=${NAME} -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.login=${LOGIN_TOKEN}"""
}

def staticAnalysisForLongLivedBranch(){
    def updatedKey = "${BRANCH_NAME}"
    updatedKey = updatedKey.replaceAll("-",".");
    sh """echo staticAnalysisForLongLivedBranch
        mvn sonarScan -Dsonar.host.url=${SONAR_HOST} -Dsonar.projectName=${NAME} -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.login=${LOGIN_TOKEN}"""
}

def dockerBuild(){
    sh """echo DOCKER BUILD
            mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
            docker build -t ${dockerRegistry}${image}:${tag} .
            docker push ${dockerRegistry}${image}:${tag}"""
}

@NonCPS
def setDefaultTargetBranch(){
	env.TARGET_BRANCH = "master"
	def branchName = "${env.gitlabBranch}"
	if(branchName ==~ env.targetPropertyBranchRegex){
		env.TARGET_BRANCH_REQUIRED = true
	}
	else{
		env.TARGET_BRANCH_REQUIRED = false
	}
}

@NonCPS
def checkForLongLivedBranch(){
	def branchName = "${env.gitlabBranch}"
	if(branchName ==~ env.longLivedBranchRegex){
		env.LONG_LIVED_BRANCH = true
	}
	else{
		env.LONG_LIVED_BRANCH = false
	}
}

@NonCPS
def checkBuildCause(){
	echo "${currentBuild.buildCauses}"
	echo "${currentBuild.getBuildCauses('hudson.model.Cause$UserCause')}"
	echo "${currentBuild.getBuildCauses('hudson.triggers.TimerTrigger$TimerTriggerCause')}"
	def branchName = "${env.gitlabBranch}"
	def causes = currentBuild.getBuildCauses()
	echo "${causes}"
	env.NIGHTLY_BUILD = false

	if(causes[0].toString().contains("TimerTriggerCause")){
	    env.NIGHTLY_BUILD = true
	}

	if(causes[0].toString().contains("UserIdCause") || causes[0].toString().contains("GenericCause")){
		env.IS_MANUAL_BUILD = true
	}
}

@NonCPS
def checkEmptyCommit(){
	def branchName = "${env.gitlabBranch}"
	env.EMPTY_COMMIT = false
	env.SHOULD_BUILD = false
	env.DOCKER_BUILD_REQUIRED = false
	def changeLogSets = currentBuild.changeSets
	if(changeLogSets.size() == 0 ){
		env.EMPTY_COMMIT = true
		if((branchName ==~ env.releaseBranch )){
			env.SHOULD_BUILD = true
		}
	}
}

@NonCPS
def checkChangeSets(){
	def branchName = "${env.gitlabBranch}"

	echo "##### :: ${branchName}"
	env.SHOULD_BUILD = true
    env.SONAR_ANALYSIS_REQUIRED = false
    env.SPECS_UPDATED = false

	def changeLogSets = currentBuild.changeSets
	echo "${changeLogSets.size()} files changed"

	for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
            def files = new ArrayList(entry.affectedFiles)
            for (int k = 0; k < files.size(); k++) {
                def file = files[k]
                def filePath = file.path
              	echo "${file.editType.name} ${file.path}"
              	if(filePath.contains("specs") && filePath.contains("crm-egress-gateway.json")){
              	    env.SPECS_UPDATED = true
              	}
			}
		}
	}

	if(GIT_COMMIT_MSG.contains("BUILD::DOCKER") || branchName ==~ env.releaseBranch){
        env.DOCKER_BUILD_REQUIRED = true
    }
    else{
        env.DOCKER_BUILD_REQUIRED = false
    }

    if(GIT_COMMIT_MSG.contains("RUN::SONARQUBE") || branchName ==~ env.releaseBranch){
        env.SONAR_ANALYSIS_REQUIRED = true
        if(env.TARGET_BRANCH_REQUIRED.toBoolean()){
            if(GIT_COMMIT_MSG.contains("SONAR-TARGET-BRANCH::")){
                def matches = (GIT_COMMIT_MSG =~ "SONAR-TARGET-BRANCH::([A-Za-z0-9_\\-]*)")
                def firstmatch = matches[0][1]
                if(firstmatch && !firstmatch.allWhitespace){
                    env.TARGET_BRANCH = firstmatch
                    echo "SONAR TARGET BRANCH SET - ${env.TARGET_BRANCH}"
                }
                else{
                    echo "SONAR TARGET BRANCH PROPERTY MISSING, DECLINING REQUEST FOR STATIC ANALYSIS"
                    env.SONAR_ANALYSIS_REQUIRED = false
                }
            }
            else{
                echo "SONAR TARGET BRANCH PROPERTY MISSING, DECLINING REQUEST FOR STATIC ANALYSIS"
                env.SONAR_ANALYSIS_REQUIRED = false
            }
        }
    }
}

def getCommitHash(cmd) {
   if (isUnix()){
       return sh(returnStdout:true , script: '#!/bin/sh -e\n' + cmd).trim()
   } else{
       stdout = bat(returnStdout:true , script: cmd).trim()
       result = stdout.readLines().drop(1).join(" ")
       return result
   }
}

def getCommitMessage(){
	return sh (script: '#!/bin/sh -e\n git log -1 --pretty=%B ${GIT_COMMIT}', returnStdout: true).trim()
}
