package com.kartag.server.dao;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

import com.kartag.business.security.Encryptor;
import com.kartag.common.dao.BaseDao;
import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.common.util.DateUtil;
import com.kartag.common.util.StringUtil;
import com.kartag.server.model.User;

public class UserDAO extends BaseDao {

	public boolean addUser(User user) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			User cashedUser = (User) session.get(User.class, user.getUid());
			if (cashedUser == null) {
				session.save(user);
			} else{
				user.setCommunityPassword(cashedUser.getCommunityPassword());
				if(user.getCommunityId() == 0 && StringUtil.isEmpty(user.getCommunityEmail())){
					user.setCommunityId(cashedUser.getCommunityId());
					user.setCommunityEmail(cashedUser.getCommunityEmail());
				}
				session.merge(user);
			}
			// } else if ((user.getDeviceId() != null
			// && !user.getDeviceId().equals(cashedUser.getDeviceId()) || !user
			// .getSource().equals(cashedUser.getSource()))
			// || (user.getSource() != null && !user.getSource().equals(
			// cashedUser.getSource()))) {
			// session.merge(user);
			// }

			// if (user.getLocation() != null) {
			// session.save(user.getLocation());
			// }
			transaction.commit();
			return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public boolean login(User user) throws Exception {

		try {
			boolean succeed = false;
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			User cashedUser = (User) session.get(User.class, user.getUid());
			String plainPassword = Encryptor.getInstance().decrypt(cashedUser.getCommunityPassword());
			if (cashedUser != null && cashedUser.getCommunityEmail() != null && cashedUser.getCommunityPassword() != null
					&& cashedUser.getCommunityEmail().equals(
							user.getCommunityEmail())
					&& user.getCommunityPassword().equals(
							plainPassword)) {
				succeed = true;
			}
			transaction.commit();
			return succeed;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public boolean changePassword(User user, String oldPassword, String newPassword) throws Exception {

		if(StringUtil.isEmpty(newPassword)){
			return false;
		}
		try {
			boolean succeed = false;
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			User cashedUser = (User) session.get(User.class, user.getUid());
			String plainPassword = Encryptor.getInstance().decrypt(cashedUser.getCommunityPassword());
			if (cashedUser != null && StringUtil.isNotEmpty(user.getCommunityPassword())
					&& plainPassword.equals(oldPassword)) {
				cashedUser.setCommunityPassword(Encryptor.getInstance().encrypt(newPassword));
				session.merge(cashedUser);
				succeed = true;
			}
			transaction.commit();
			return succeed;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public boolean updateCommunityPassword(User user, String newPassword) throws Exception {

		if(StringUtil.isEmpty(newPassword)){
			return false;
		}
		try {
			boolean succeed = false;
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			User cashedUser = (User) session.get(User.class, user.getUid());
			if (cashedUser != null) {
				cashedUser.setCommunityPassword(Encryptor.getInstance().encrypt(newPassword));
				session.merge(cashedUser);
				succeed = true;
			}
			transaction.commit();
			return succeed;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public User getPassword(User user) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			User cashedUser = (User) session.get(User.class, user.getUid());
			transaction.commit();
			return cashedUser;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public User getUserByUid(BigInteger uid) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			User cashedUser = (User) session.get(User.class, uid);
			transaction.commit();
			return cashedUser;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public int getAllRegisteredUser(int countryId) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> rows = session.createCriteria(User.class)
					.add(Restrictions.eq("countryId", countryId)).list();
			transaction.commit();
			return rows.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public Set<User> getAllRegisteredUsers() throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> rows = session.createCriteria(User.class).list();
			transaction.commit();
			return new HashSet<User>(rows);
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public Set<User> getRegisteredUsers(String source) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> rows = session.createCriteria(User.class)
					.add(Restrictions.eq("source", source)).list();
			transaction.commit();
			return new HashSet<User>(rows);
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public int getAllDevicesUser(int countryId, String source) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> rows = session.createCriteria(User.class)
					.add(Restrictions.eq("countryId", countryId))
					.add(Restrictions.eq("source", source)).list();
			transaction.commit();
			return rows.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public int getFilteredRegisteredUser(int countryId, String start, String end)
			throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> rows = session
					.createCriteria(User.class)
					.add(Restrictions.eq("countryId", countryId))
					.add(Restrictions.between("registerationTime",
							DateUtil.getDateFromString(start),
							DateUtil.getDateFromString(end))).list();
			transaction.commit();
			return rows.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public int getFilteredDeviceUser(int countryId, String start, String end,
			String source) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> rows = session
					.createCriteria(User.class)
					.add(Restrictions.eq("countryId", countryId))
					.add(Restrictions.eq("source", source))
					.add(Restrictions.between("registerationTime",
							DateUtil.getDateFromString(start),
							DateUtil.getDateFromString(end))).list();
			transaction.commit();
			return rows.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

}
