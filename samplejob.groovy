


pipelineJob('life-group-protection-allfinanz-frontend-datawarehouse-stacks-deploy') {
  description('')
  logRotator(10, 10)
  parameters {
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
  