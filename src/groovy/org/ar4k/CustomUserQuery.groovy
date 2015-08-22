package org.ar4k

import java.util.ArrayList
import java.util.Iterator
import java.util.List

import org.activiti.engine.identity.User
import org.activiti.engine.identity.UserQuery

/**
 * Simplified implementation of {@link UserQuery}. It is adapts results from {@link UserDao}.
 * Implementation is just as sample and it is not performance efficient. 
 * 
 * @author mondhs
 *
 */
public class CustomUserQuery implements UserQuery {
	
	public UserQuery asc() {
		throw new IllegalArgumentException("Not Impl")
	}

	public UserQuery desc() {
		throw new IllegalArgumentException("Not Impl")
	}

	public long count() {
		return Utente.count()
	}

	public User singleResult() {
		throw new IllegalArgumentException("Not Impl")
	}

	public List<User> list() {
		return Utente.findAll()
	}

	public List<User> listPage(int firstResult, int maxResults) {
		return Utente.findAll([max: maxResults, offset: firstResult])
	}

	public UserQuery userId(String idRicerca) {

		UserQuery aSubUserQuery = new UserQuery() {

			UserQuery userFullNameLike(String dato){
				throw new IllegalArgumentException("Not Impl")
			}
			
			public User singleResult() {
				Utente user = Utente.findAllById(idRicerca)
				return user;
			}

			public List<User> listPage(int firstResult, int maxResults) {
				throw new IllegalArgumentException("Not Impl")
			}

			public List<User> list() {
				throw new IllegalArgumentException("Not Impl")
			}

			public UserQuery desc() {
				throw new IllegalArgumentException("Not Impl")
			}

			public long count() {
				throw new IllegalArgumentException("Not Impl")
			}

			public UserQuery asc() {
				throw new IllegalArgumentException("Not Impl")
			}

			public UserQuery userLastNameLike(String lastNameLike) {
				throw new IllegalArgumentException("Not Impl")
			}

			public UserQuery userLastName(String lastName) {
				throw new IllegalArgumentException("Not Impl")
			}

			public UserQuery userId(String idCercato) {
				throw new IllegalArgumentException("Not Impl")
			}

			public UserQuery userFirstNameLike(String firstNameLike) {
				throw new IllegalArgumentException("Not Impl")
			}

			public UserQuery userFirstName(String firstName) {
				throw new IllegalArgumentException("Not Impl");
			}

			public UserQuery userEmailLike(String emailLike) {
				throw new IllegalArgumentException("Not Impl");
			}

			public UserQuery userEmail(String email) {
				throw new IllegalArgumentException("Not Impl");
			}

			public UserQuery potentialStarter(String procDefId) {
				throw new IllegalArgumentException("Not Impl");
			}

			public UserQuery orderByUserLastName() {
				throw new IllegalArgumentException("Not Impl");
			}

			public UserQuery orderByUserId() {
				throw new IllegalArgumentException("Not Impl");
			}

			public UserQuery orderByUserFirstName() {
				throw new IllegalArgumentException("Not Impl");
			}

			public UserQuery orderByUserEmail() {
				throw new IllegalArgumentException("Not Impl");
			}

			public UserQuery memberOfGroup(String groupId) {
				throw new IllegalArgumentException("Not Impl");
			}

		};

		return aSubUserQuery;
	}

	public UserQuery userFirstName(String firstName) {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery userFirstNameLike(String firstNameLike) {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery userLastName(String lastName) {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery userLastNameLike(String lastNameLike) {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery userEmail(String email) {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery userEmailLike(String emailLike) {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery memberOfGroup(String groupId) {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery potentialStarter(String procDefId) {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery orderByUserId() {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery orderByUserFirstName() {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery orderByUserLastName() {
		throw new IllegalArgumentException("Not Impl");
	}

	public UserQuery orderByUserEmail() {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public UserQuery userFullNameLike(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
