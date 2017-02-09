package com.kartag.server.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.SystemUserDAO;

@Entity
@Table(name="system_users")
public class SystemUser extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String ADMIN = "ADMIN";
	
	public static final String SUPER_USER = "SUPERUSER";
	
	public static final String MALL_USER = "MALLUSER";
	public static final String STORE_USER = "STOREUSER";
	
	public static final String HASHED_FIELD_NAME = "user_pass";
	
	@Transient
	protected String tableName = "system_users";

	@Transient
	private SystemUserDAO dao = new SystemUserDAO();
	
	@Id
	@Column(name="user_name", unique=true, nullable=false)
	private String userName = "";
	
	@Column(name="user_pass", nullable=false)
	private String userPass = "";
	
	@Transient
	private String type="";
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
		this.dao = (SystemUserDAO)dao;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserPass() {
		return userPass;
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return tableName;
	}

	@Override
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
