def gitCreds             = 'sivasiva_credentials'
def gitDeployRepo        = 'https://github.com/sivascripts/terraform'
def gitBranch            = 'main'
def terraformaccounttype = 'nonprod'

pipelineJob('csa-terraform-deploy') {
  description('')
  logRotator(10, 10)
  parameters {
   choiceParam('gitCreds', [gitCreds], '')
   choiceParam('gitUrl', [gitDeployRepo], '')
   stringParam('gitBranch', 'master', 'Git branch name')
   stringParam('terraformaccounttype', 'nonprod', 'Terraform account')
   choiceParam('terraformenv', ['rwy','test','pre'],'Environment that will the stacks will be deployed')
   booleanParam('tfPlanFrontendASG_AISServer', false, 'terraform plan on ASG_AISServer')
	 booleanParam('tfPlanFrontendDNS_AISServer', false, 'terraform plan on DNS_AISServer')
	 booleanParam('tfPlanFrontendASG_InsightServer', false, 'terraform plan on ASG_InsightServer')
	 booleanParam('tfPlanFrontendDNS_InsightServer', false, 'terraform plan on DNS_InsightServer')
	 booleanParam('tfPlanAllFinanzTest', false, 'Select this to build AllFinanz Test Server')
   nonStoredPasswordParam('proxy_pass',  'Proxy password')
   booleanParam('terraformApplyPlan', false, 'terraform APPLY on above selected terraform plans')
  }
  definition {
    cps {
      script(readFileFromWorkspace('pipelines/csa-test-deployment/deploy/deploy.groovy'))
	    sandbox()
    }
  }
}