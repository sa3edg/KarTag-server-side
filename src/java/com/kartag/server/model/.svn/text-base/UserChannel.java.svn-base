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
@Table(name="users_channels")
public class UserChannel extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Transient
	private SystemUserDAO dao = new SystemUserDAO();
	
	@Id
	@Column(name="id", unique=true, nullable=false)
	private int id = 0;
	
	@Column(name="channel_id", nullable=false)
	private int channelId = 0;
	
	@Column(name="user_id", nullable=false)
	private String userName = "";
	
	@Column(name="channel_name", nullable=false)
	private String channelName = "";
	
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

	@Override
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}



	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
