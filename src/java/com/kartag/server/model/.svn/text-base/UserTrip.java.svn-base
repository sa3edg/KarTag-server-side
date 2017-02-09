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
import com.kartag.common.util.DateUtil;
import com.kartag.server.dao.UserRulesDAO;

@Entity
@Table(name="user_trips")
@org.hibernate.annotations.Entity(
		dynamicUpdate = true)
public class UserTrip extends Model{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -220251055932386276L;

	@Id
	@Column(name="trip_id")
	private int tripId ;
	
	@Id
	@Column(name="uid")
	private BigInteger uid ;
	
	@Id
	@Column(name="trip_type")
	private String type = "";
	
	@Column(name="trip_status")
	private String status = "";
	
	@Column(name = "trip_time")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date time = DateUtil.now();
	
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

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public int getTripId() {
		return tripId;
	}


	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	public BigInteger getUid() {
		return uid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public java.util.Date getTime() {
		return time;
	}
}
