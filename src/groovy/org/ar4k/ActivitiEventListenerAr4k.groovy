package org.ar4k

import grails.converters.JSON
import org.activiti.engine.delegate.event.ActivitiEvent
import org.activiti.engine.delegate.event.ActivitiEventListener
import grails.util.Holders

class ActivitiEventListenerAr4k implements ActivitiEventListener {

	@Override
	public void onEvent(ActivitiEvent event) {
		try {
			Holders.applicationContext.getBean("interfacciaContestoService").sendMessage("activemq:topic:activiti.eventi",([tipo:event.getType().toString(),processo:event.getProcessDefinitionId(),istanza:event.getProcessInstanceId(),esecuzione:event.getExecutionId()] as JSON).toString())
		} catch (Exception ee){
			log.info "Evento di Activiti non comunicato: "+ee.toString()
		}
		/*
		 switch (event.getType()) {
		 case JOB_EXECUTION_SUCCESS:
		 System.out.println("A job well done!");
		 break;
		 case JOB_EXECUTION_FAILURE:
		 System.out.println("A job has failed...");
		 break;
		 default:
		 System.out.println("Event received: " + event.getType());
		 }
		 */
	}

	@Override
	public boolean isFailOnException() {
		log.info "Errore nella gestione degli eventi activiti..."
		return false
	}
}

