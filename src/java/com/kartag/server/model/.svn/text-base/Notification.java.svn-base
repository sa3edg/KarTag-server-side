package com.kartag.server.model;

import java.math.BigInteger;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.NotificationDAO;

@Entity
@Table(name="notifications")
public class Notification extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="notification_id", unique=true, nullable=false)
	@Type(type = "int")
	private int id;
	
	@Column(name="trip_id", nullable=false)
	@Type(type = "int")
	private int tripId ;
	
	@Column(name="from_uid", nullable=false)
	private BigInteger fromUid ;
	
	@Column(name="to_uid", nullable=false)
	private BigInteger toUid ;
	
	@Column(name="from_name", nullable=false)
	private String fromName = "";
	
	@Column(name="from_pool_name", nullable=false)
	private String fromPoolName = "";
	
	@Column(name="to_pool_name", nullable=false)
	private String toPoolName = "";
	
	@Column(name = "trip_time")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date tripTime ;
	
	@Column(name = "notification_time")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date notificationTime ;

	@Column(name="notification_type")
	private String type = "";
	
	@Column(name="status")
	private String status = "";
	
	@JsonIgnore
	@Transient
	private NotificationDAO dao = new NotificationDAO();
	
	@JsonIgnore
	@Transient
	//notification type used for join to trip request.
	public final static  String  JOIN_REQUEST_TYPE = "JOIN_REQUEST_TYPE";
	
	@JsonIgnore
	@Transient
	//notification type used for accept or reject trip.
	public final static  String  REPLY_REQUEST_TYPE = "REPLY_REQUEST_TYPE";
	
	@JsonIgnore
	@Transient
	public final static  String  REQUEST_SENT_STATUS = "REQUEST_SENT_STATUS";
	
	@JsonIgnore
	@Transient
	public final static  String  ACCEPTED_STATUS = "ACCEPTED_STATUS";
	
	@JsonIgnore
	@Transient
	public final static  String  REJECTED_STATUS = "REJECTED_STATUS";
	
	@JsonIgnore
	@Transient
	public final static  String  CANCELED_STATUS = "CANCELED_STATUS";
	
	public Notification()
	{
		
	}
	public Notification(LinkedHashMap<String, String> fields)
	{
		this.tripId = Integer.parseInt(fields.get("tripId"));
		this.fromUid = new BigInteger(fields.get("fromUid"));
		this.toUid = new BigInteger(fields.get("toUid"));
		this.fromName = fields.get("fromName");
		this.fromPoolName = fields.get("fromPoolName");
		this.toPoolName = fields.get("toPoolName");
		this.type = fields.get("type");
		this.tripTime = new java.util.Date(Long.parseLong(fields.get("tripTime")));
		this.notificationTime = new java.util.Date();
		this.status = fields.get("status");;
	}
	@Override
	@Transient
	public IDefaultDao getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	@JsonIgnore
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transient
	public void setDao(IDefaultDao dao) {
		// TODO Auto-generated method stub
		this.dao = (NotificationDAO)dao;
	}
	
	@Override
	@JsonIgnore
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setFromUid(BigInteger fromUid) {
		this.fromUid = fromUid;
	}
	public BigInteger getFromUid() {
		return fromUid;
	}
	public void setToUid(BigInteger toUid) {
		this.toUid = toUid;
	}
	public BigInteger getToUid() {
		return toUid;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public int getTripId() {
		return tripId;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromPoolName(String fromPoolName) {
		this.fromPoolName = fromPoolName;
	}
	public String getFromPoolName() {
		return fromPoolName;
	}
	public void setToPoolName(String toPoolName) {
		this.toPoolName = toPoolName;
	}
	public String getToPoolName() {
		return toPoolName;
	}
	public void setTripTime(java.util.Date tripTime) {
		this.tripTime = tripTime;
	}
	public java.util.Date getTripTime() {
		return tripTime;
	}
	public void setNotificationTime(java.util.Date notificationTime) {
		this.notificationTime = notificationTime;
	}
	public java.util.Date getNotificationTime() {
		return notificationTime;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}