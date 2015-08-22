package org.ar4k;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

class SpringSecurityGroupManagerFactoryActiviti implements SessionFactory {
	
	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
		//return GroupEntityManager.class;
		//return org.ar4k.GroupManager.class;
	}

	public Session openSession() {
		return (Session)new org.ar4k.GroupManager();
	}
}
