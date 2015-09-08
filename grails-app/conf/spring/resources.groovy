import org.activiti.engine.impl.interceptor.SessionFactory

// Place your Spring DSL code here

beans = {
	grailsApplication = ref('grailsApplication')

	userEntityManagerFactory(org.ar4k.SpringSecurityUserManagerFactoryActiviti)

	groupEntityManagerFactory(org.ar4k.SpringSecurityGroupManagerFactoryActiviti)
	
	activitiEventListenerAr4k(org.ar4k.ActivitiEventListenerAr4k)

	processEngineConfiguration(org.activiti.spring.SpringProcessEngineConfiguration) {
		databaseSchemaUpdate = org.activiti.engine.ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP
		//jdbcUrl = "jdbc:h2:mem:activitiDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
		dataSource = ref('dataSource')
		asyncExecutorEnabled = true
		asyncExecutorActivate = grailsApplication.config.activiti.jobExecutorActivate
		mailServerHost = grailsApplication.config.activiti.mailServerHost
		mailServerPort = grailsApplication.config.activiti.mailServerPort
		mailServerUsername = grailsApplication.config.activiti.mailServerUsername
		mailServerPassword = grailsApplication.config.activiti.mailServerPassword
		mailServerDefaultFrom = grailsApplication.config.activiti.mailServerDefaultFrom
		history = grailsApplication.config.activiti.history // "none", "activity", "audit" or "full"
		transactionManager = ref('transactionManager')
		customSessionFactories = [ref('userEntityManagerFactory'),ref('groupEntityManagerFactory')]
		eventListeners = ref('activitiEventListenerAr4k')
	}

	processEngine(org.activiti.spring.ProcessEngineFactoryBean) { processEngineConfiguration = ref('processEngineConfiguration') }

	runtimeService(processEngine:"getRuntimeService")
	repositoryService(processEngine:"getRepositoryService")
	taskService(processEngine:"getTaskService")
	managementService(processEngine:"getManagementService")
	identityService(processEngine:"getIdentityService")
	historyService(processEngine:"getHistoryService")
	formService(processEngine:"getFormService")
}
