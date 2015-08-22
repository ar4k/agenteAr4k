package org.ar4k

import java.util.List;
import java.util.Map;

import grails.util.GrailsNameUtils as GNU

import org.activiti.engine.identity.Group
import org.activiti.engine.identity.GroupQuery
import org.activiti.engine.impl.GroupQueryImpl
import org.activiti.engine.impl.Page
import org.activiti.engine.impl.context.Context
import org.activiti.engine.impl.persistence.AbstractManager
import org.activiti.engine.impl.persistence.entity.GroupEntity
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import grails.util.Holders as AH
import grails.plugin.springsecurity.SpringSecurityUtils as SSU

import org.codehaus.groovy.grails.web.pages.FastStringWriter
import org.jclouds.openstack.services.ObjectStore;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager

class GroupManager extends AbstractManager implements GroupIdentityManager {
	
	static final Log LOG = LogFactory.getLog(this)


	Group createNewGroup(String groupId) {
		throw new UnsupportedOperationException("Please use ${getGroupDomainClass()}.save() to create Group.")
	}


	void insertGroup(Group group) {
		throw new UnsupportedOperationException("Please use ${getGroupDomainClass()}.save() to create Group.")
	}


	void updateGroup(Group updatedGroup) {
		throw new UnsupportedOperationException("Please use ${getGroupDomainClass()}.save() to update Group.")
	}


	void deleteGroup(String groupId) {
		throw new UnsupportedOperationException("Please use ${getGroupDomainClass()}.delete() to delete Group.")
	}


	GroupQuery createNewGroupQuery() {
		return super.createNewGroupQuery()
		//return new GroupQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutor())
	}


	List<Group> findGroupsByUser(String userId) {
		LOG.debug "findGroupsByUser (${userId})"
		def user = Utente.findByUsername(userId)
		def groups = user?.authorities.toList()
		return groups
	}

	List<Group> findGroupByQueryCriteria(Object query, Page page) {
		LOG.debug "findGroupByQueryCriteria (${query.class.name}, $page)"
		List<Group> groups
		String queryString = createGroupQueryString(query)
		LOG.debug "queryString = $queryString"
		if (page) {
			// listPage()
			groups = getGroupJoinDomainClass().findAll(queryString, [offset:page.firstResult, max:page.maxResults]).collect{it.userGroup}
		} else { // list()
			groups = getGroupJoinDomainClass().findAll(queryString, Collections.emptyMap()).collect{it."${GNU.getPropertyName(getGroupDomainClassName())}"}
		}
		return groups
	}

	long findGroupCountByQueryCriteria(Object query) {
		LOG.debug "findGroupCountByQueryCriteria (${query.class.name})"
		String queryString = createGroupQueryString(query)
		LOG.debug "queryString = $queryString"
		return getGroupJoinDomainClass().executeQuery("select count(g) ${queryString}")[0]
	}

	private String createGroupQueryString(Object query) {
		FastStringWriter queryString = new FastStringWriter()
		queryString << "from Ruolo as g where 1=1"
		String groupPropertyName = GNU.getPropertyName(getGroupDomainClassName())
		if (query.id)
			queryString << " and g.${groupPropertyName}.id='${query.id}'"

		if (query.name) {
			queryString << " and g.${groupPropertyName}.name = '${query.name}'"
		}

		if (query.nameLike) {
			queryString << " and g.${groupPropertyName}.name like '${query.nameLike}'"
		}

		if (query.type) {
			queryString << " and g.${groupPropertyName}.type = '${query.type}'"
		}

		if (query.userId) {
			queryString << " and g.${GNU.getPropertyName(getUserDomainClassName())}.id = '${query.userId}'"
		}

		if (query.orderBy) {
			String orderBy = query.orderBy.toLowerCase().replace('_', '')
			orderBy = orderBy.replace('g', "g.${groupPropertyName}")
			queryString << " order by ${orderBy}"
		}
		return queryString.toString()
	}
	
	GroupEntity findGroupById(String groupId) {
		throw new UnsupportedOperationException("Please use ${getGroupDomainClass()}.get(id) to find Group by Id.")
	}

	private getGroupDomainClassName() {
		return SSU.securityConfig.authority.className
		//return "org.ar4k.Ruolo"
	}

	private getGroupDomainClass() {
		return AH.getGrailsApplication().getDomainClass(getGroupDomainClassName()).clazz
	}

	private getGroupJoinDomainClassName() {
		LOG.debug "getGroupJoinDomainClassName(): "+SSU.securityConfig.userLookup.authorityJoinClassName
		return SSU.securityConfig.userLookup.authorityJoinClassName
		//return "org.ar4k.UtenteRuolo"
	}

	private getGroupJoinDomainClass() {
		return AH.getGrailsApplication().getDomainClass(getGroupJoinDomainClassName()).clazz
	}

	private getUserDomainClassName() {
		LOG.debug "getUserDomainClassName(): "+SSU.securityConfig.userLookup.userDomainClassName
		return SSU.securityConfig.userLookup.userDomainClassName
		//return "org.ar4kUtente"
	}

	private getUserDomainClass() {
		return AH.getGrailsApplication().getDomainClass(getUserDomainClassName()).clazz
	}

	private getUsernamePropertyName() {
		LOG.debug "getUsernamePropertyName(): "+SSU.securityConfig.userLookup.usernamePropertyName
		return SSU.securityConfig.userLookup.usernamePropertyName
		//return 'username'
	}

	private getUsernameClassName() {
		return GNU.getClassNameRepresentation(getUsernamePropertyName())
	}


	@Override
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl arg0, Page arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long findGroupCountByNativeQuery(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public long findGroupCountByQueryCriteria(GroupQueryImpl arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Group> findGroupsByNativeQuery(Map<String, Object> arg0,
			int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isNewGroup(Group arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
