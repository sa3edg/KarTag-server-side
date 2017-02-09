package com.kartag.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.UserRulesDAO;

@Entity
@Table(name="user_roles")
public class UserRole extends Model{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -220251055932386276L;

	@Id
	@Column(name="user_name", unique=true, nullable=false)
	private String userName = "";
	
	@Column(name="role_name", nullable=false)
	private String userRole = "";
	
	@Transient
	private UserRulesDAO  dao = new UserRulesDAO();

	@Override
	@Transient
	public IDefaultDao getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	@Transient
	public void setDao(IDefaultDao dao) {
		// TODO Auto-generated method stub
		this.dao = (UserRulesDAO)dao;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserRole(String userRule) {
		this.userRole = userRule;
	}

	public String getUserRole() {
		return userRole;
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
