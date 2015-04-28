//ctx.getBean('accoppiatoreService').salvaConfigurazione('base')
//ctx.getBean('accoppiatoreService').caricaConfigurazione('base')
//ctx.getBean('accoppiatoreService').listaOggetti()*.etichetta
//ctx.getBean('accoppiatoreService').listaContesti()*.etichetta
//ctx.getBean('accoppiatoreService').esegui('master','df -h')
//ctx.getBean('accoppiatoreService').configuraPadrone()
//ctx.getBean('accoppiatoreService').nuovoSSH('test','descrizione','utente','host',22,'password')
//ctx.getBean('accoppiatoreService').sessione('master')
//ctx.getBean('accoppiatoreService').freeMemory()
//ctx.getBean('accoppiatoreService').remoteWeb('master','www.rossonet.org','80','/','focus')
ctx.metaClass.methods*.name.sort().unique()