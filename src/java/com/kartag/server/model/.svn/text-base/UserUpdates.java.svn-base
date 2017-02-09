package com.kartag.server.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.UserUpdatesDAO;

@Entity
@Table(name="user_updates")
public class UserUpdates extends Model{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -220251055932386276L;

	@Id
	@Column(name="uid", unique=true, nullable=false)
	private BigInteger uid ;
	
	@Column(name = "notifications_time", unique=true, nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date notificationsTime ;
	
	@Column(name = "messages_time", unique=true, nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date messagesTime ;
	
	@Column(name = "replies_time", unique=true, nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date repliesTime ;
	
	@Transient
	private UserUpdatesDAO  dao = new UserUpdatesDAO();

	public UserUpdates()
	{
		
	}
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
		this.dao = (UserUpdatesDAO)dao;
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transient
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	public BigInteger getUid() {
		return uid;
	}

	public void setNotificationsTime(java.util.Date notificationsTime) {
		this.notificationsTime = notificationsTime;
	}

	public java.util.Date getNotificationsTime() {
		return notificationsTime;
	}

	public void setMessagesTime(java.util.Date messagesTime) {
		this.messagesTime = messagesTime;
	}

	public java.util.Date getMessagesTime() {
		return messagesTime;
	}
	public void setRepliesTime(java.util.Date repliesTime) {
		this.repliesTime = repliesTime;
	}
	public java.util.Date getRepliesTime() {
		return repliesTime;
	}

}
