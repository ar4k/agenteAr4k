package org.ar4k;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;

class SpringSecurityGroupManagerFactory implements SessionFactory {
	public Class<?> getSessionType() {
		return org.activiti.engine.impl.persistence.entity.GroupEntityManager.class;
	}

	public Session openSession() {
		return new GroupManager();
	}
}
