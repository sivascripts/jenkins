  // Global Variables declaration
  // CHECK - Is this the correct bucket/prefix?
 

    // Global Variables declaration
  // CHECK - Is this the correct bucket/prefix?
 

  // Local Variables declaration
  // CHECK - These may change depending on Terraform development
  node('master')
  {
  def terraformdir_LinuxInstance = "linux_instance"
  terraformBucket = "csaterraformremote/"
  terraformPrefix = "sab/dev/Jenkins"

  stage('Initiating'){
    echo "Cleaning up workspace"
    deleteDir()
    WORKSPACE = pwd()
    sh("rm -rf ${WORKSPACE}/*")
    echo "Preparing Environment"
  }

  def db_stacks_2d_list = []

  //Backend Stacks
  db_stacks_2d_list.add([tfPlanLinuxInstance,terraformdir_LinuxInstance,'Simple Linux Instance', 'linux_instance'])
 

  dir(terraformenv) {
    stage ('Checkout') {
        git_checkout()
    }
    for (stack_list in db_stacks_2d_list) {
       if (stack_list[0] == 'true') {
          run_terraform(stack_list[1], stack_list[2], stack_list[3])
      }
    }
  }
  }


def git_checkout() {
    checkout([$class: 'GitSCM', branches: [[name: gitBranch]], clearWorkspace: true, doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'SubmoduleOption', disableSubmodules: false, parentCredentials: true, recursiveSubmodules: true, reference: '', trackingSubmodules: false]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: gitCreds, url: gitUrl]]])
}

def terraform_init(terraformBucket, terraformPrefix, terraformkey) {
  
      //sh "terraform init -no-color -force-copy -input=false"
      sh "terraform init -no-color -force-copy -input=false -upgrade=true -backend=true -backend-config='bucket=csaterraformremote' -backend-config='prefix=${terraformPrefix}/${terraformkey}'"
      sh "terraform get -no-color -update=true"
		}
	
def terraform_plan(workspace) {
    sh "terraform workspace select ${workspace} || terraform workspace new ${workspace}"
	 
    sh "terraform plan -destroy -no-color -out=tfplan"
    

}

def terraform_destroy() {
	sh "terraform apply -input=false -no-color tfplan"
}
 
def run_terraform(terraformdir,stage_description,tfstate_key) {
  dir(terraformdir) {
    stage ('Terraform Remote State') {
      print ("### Terraform Remote State for ${stage_description} ###")
      terraformKey = "${tfstate_key}.tfstate"
      terraform_init(terraformBucket, terraformPrefix, terraformKey)
    }
    stage ('Terraform Plan') {
      print ("### Terraform Plan for ${stage_description} ###")
      terraform_plan(terraformenv)

    if (terraformDestroy == 'true') {
      stage ('Terraform Destroy') {
        print ("### Terraform Destroy for ${stage_description} ###")
        terraform_destroy()
      }
    }
    else {
      stage ('Terraform Destroy') {
        print ("### Skip Terraform Destroy for ${stage_description} ###")
      }
    }
  }
}
}