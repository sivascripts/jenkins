def gitCreds             = 'siva_credentials'
def gitDeployRepo        = 'https://github.com/sivascripts/Terraform.git'                       
def gitBranch            = 'master'
def terraformaccounttype = 'nonprod'

pipelineJob('csa-terraform-deploy') {
  description('Deploy Pipeline')
  logRotator(10, 10)
  parameters {
   choiceParam('gitCreds', [gitCreds], 'git credntials id')
   choiceParam('gitUrl', [gitDeployRepo], 'Git Url')
   stringParam('gitBranch', 'master', 'Git branch name')
   stringParam('terraformaccounttype', 'nonprod', 'Terraform account')
   choiceParam('terraformenv', ['dev','test','pre'],'Environment that will the stacks will be deployed')
   booleanParam('tfPlanLinuxInstance', false, 'terraform plan on ASG_AISServer')
   booleanParam('terraformApplyPlan', false, 'terraform APPLY on above selected terraform plans')
  }
  definition {
    cps {
      script(readFileFromWorkspace('jenkins-csa-jobs-master/pipelines/csa-test-deployment/deploy/deploy.groovy'))
	    sandbox()
    }
  }
}

pipelineJob('csa-terraform-destroy') {
  description('Deploy Pipeline')
  logRotator(10, 10)
  parameters {
   choiceParam('gitCreds', [gitCreds], 'git credntials id')
   choiceParam('gitUrl', [gitDeployRepo], 'Git Url')
   stringParam('gitBranch', 'master', 'Git branch name')
   stringParam('terraformaccounttype', 'nonprod', 'Terraform account')
   choiceParam('terraformenv', ['dev','test','pre'],'Environment that will the stacks will be deployed')
   booleanParam('tfPlanLinuxInstance', false, 'terraform plan on ASG_AISServer')
   booleanParam('terraformDestroy', false, 'terraform APPLY on above selected terraform plans')
  }
  definition {
    cps {
      script(readFileFromWorkspace('jenkins-csa-jobs-master/pipelines/csa-test-deployment/deploy/destroy.groovy'))
	    sandbox()
    }
  }
}
