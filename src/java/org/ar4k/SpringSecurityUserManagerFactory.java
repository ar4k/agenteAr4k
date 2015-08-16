package org.ar4k;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;

class SpringSecurityUserManagerFactory implements SessionFactory {
	public Class<?> getSessionType() {
		return org.activiti.engine.impl.persistence.entity.UserEntityManager.class;
	}

	public Session openSession() {
		return new UserManager(); // Customized UserManger extended from
									// org.activiti.engine.impl.persistence.entity.UserManager
	}
}
