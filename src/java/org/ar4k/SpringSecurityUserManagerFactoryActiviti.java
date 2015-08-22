package org.ar4k;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;



class SpringSecurityUserManagerFactoryActiviti implements SessionFactory {
	
	public Class<?> getSessionType() {
		return UserIdentityManager.class;
		//return UserEntityManager.class;
		//return org.ar4k.UserManager.class;
	}

	public Session openSession() {
		return (Session)new org.ar4k.UserManager();
	}
}
