package org.ar4k

import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User
import org.activiti.engine.identity.UserQuery
import org.activiti.engine.impl.Page
import org.activiti.engine.impl.UserQueryImpl
import org.activiti.engine.impl.context.Context
import org.activiti.engine.impl.persistence.AbstractManager
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity
import org.activiti.engine.impl.persistence.entity.UserEntity
import org.activiti.engine.impl.persistence.entity.UserIdentityManager
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import grails.util.Holders as AH
//import grails.plugin.springsecurity.SpringSecurityUtils as SSU


import org.codehaus.groovy.grails.web.pages.FastStringWriter
import org.activiti.engine.impl.persistence.entity.UserEntityManager
import org.activiti.engine.impl.interceptor.Session

class UserManager extends AbstractManager implements UserIdentityManager {
	
	static final Log LOG = LogFactory.getLog(this)


	User createNewUser(String userId) {
		throw new UnsupportedOperationException("Please use ${getUserDomainClassName()}.save() to create User.")
	}


	void insertUser(User user) {
		throw new UnsupportedOperationException("Please use ${getUserDomainClassName()}.save() to insert User.")
	}


	void updateUser(User updatedUser) {
		throw new UnsupportedOperationException("Please use ${getUserDomainClassName()}.save() to update User.")
	}


	void deleteUser(String userId) {
		throw new UnsupportedOperationException("Please use ${getUserDomainClassName()}.delete() to delete User.")
	}


	UserEntity findUserById(String userId) {
		LOG.debug "findUserById ($userId)"
		Utente user = Utente.findAllById(userId)
		return user
	}

	List<User> findUserByQueryCriteria(Object query, Page page) {
		LOG.debug "findUserByQueryCriteria (${query.class.name}, $page)"
		List<Utente> users
		String queryString = this.createUserQueryString(query)
		LOG.debug "queryString = $queryString"
		if (page) {
			// listPage()
			users = Utente.findAll(queryString, [offset:page.firstResult, max:page.maxResults])
		} else {
			// list()
			users = Utente.findAll(queryString, Collections.emptyMap())
		}
		LOG.debug "query.groupId = ${query.groupId}"
		if (users && query.groupId) {
			users = Utente.findAll { it.authorities*.id.contains(query.groupId) }
		}
		return users
	}
	
	long findUserCountByQueryCriteria(Object query) {
		LOG.debug "findUserCountByQueryCriteria (${query.class.name})"
		String queryString = this.createUserQueryString(query)
		LOG.debug "queryString = $queryString"
		return Utente.executeQuery("select count(u) ${queryString}")[0]
	}


	List<Group> findGroupsByUser(String userId) {
		LOG.debug "findGroupsByUser (${userId})"
		Utente user = Utente.findAllById(userId)
		Ruolo groups = user?.authorities.toList()
		return groups
	}


	UserQuery createNewUserQuery() {
		return super.createNewUserQuery()
		//return new UserQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutor())
		//return new CustomUserQuery()
	}


	IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
		throw new UnsupportedOperationException()
	}


	List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
		throw new UnsupportedOperationException()
	}

	private String createUserQueryString(Object query) {
		FastStringWriter queryString = new FastStringWriter()
		queryString << "from Utente as u where 1=1"
		if (query.id)
			queryString << " and u.username='${query.id}'"

		if (query.firstName) {
			queryString << " and u.firstName = '${query.firstName}'"
		}

		if (query.firstNameLike) {
			queryString << " and u.firstName like '${query.firstNameLike}'"
		}

		if (query.lastName) {
			queryString << " and u.lastName = '${query.lastName}'"
		}

		if (query.lastNameLike) {
			queryString << " and u.lastName like '${query.lastNameLike}'"
		}

		if (query.email) {
			queryString << " and u.email = '${query.email}'"
		}

		if (query.emailLike) {
			queryString << " and u.email like '${query.emailLike}'"
		}

		if (query.orderBy) {
			String orderBy = query.orderBy.toLowerCase().replace('_', '')
			orderBy = orderBy.replace("last", "lastName")
			orderBy = orderBy.replace("first", "firstName")
			queryString << " order by ${orderBy}"
		}
		return queryString.toString()
	}

	private getUsernamePropertyName() {
		LOG.debug "getUsernamePropertyName(): username"
		return 'username'
	}

	private getUserDomainClassName() {
		LOG.debug "getUserDomainClassName(): org.ar4k.Utente"
		return 'org.ar4k.Utente'
	}

	private getUserDomainClass() {
		return AH.getGrailsApplication().getDomainClass(getUserDomainClassName()).clazz
	}


	@Override
	public Boolean checkPassword(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findPotentialStarterUsers(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findUserByQueryCriteria(UserQueryImpl arg0, Page arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long findUserCountByNativeQuery(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public long findUserCountByQueryCriteria(UserQueryImpl arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<User> findUsersByNativeQuery(Map<String, Object> arg0,
			int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Picture getUserPicture(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isNewUser(User arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setUserPicture(String arg0, Picture arg1) {
		// TODO Auto-generated method stub
		
	}
}

