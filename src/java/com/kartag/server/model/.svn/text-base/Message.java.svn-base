package com.kartag.server.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.MessageDAO;

@Entity
@Table(name="messages")
public class Message extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="message_id", unique=true, nullable=false)
	@Type(type = "int")
	private int id ;
	
	@Column(name="from_uid", nullable=false)
	private BigInteger fromUid ;
	
	@Column(name="to_uid", nullable=false)
	private BigInteger toUid ;
	
	@Column(name="from_name", nullable=false)
	private String fromName = "";
	
	@Column(name="to_name", nullable=false)
	private String toName = "";
	
	@Column(name="message", nullable=false)
	private String text = "";
	
	@Column(name = "message_time")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date time ;

	@Column(name="status")
	private String status = "";
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "message")
	private List<Reply> replies = new ArrayList<Reply>();
	
	
	@JsonIgnore
	@Transient
	private MessageDAO dao = new MessageDAO();
	
	@JsonIgnore
	@Transient
	public final static  String  NEW = "NEW";
	
	@JsonIgnore
	@Transient
	public final static  String  SEEN = "SEEN";
	
	public Message()
	{
		
	}
	public Message(LinkedHashMap<String, String> fields)
	{
		this.fromUid = new BigInteger(fields.get("fromUid"));
		this.toUid = new BigInteger(fields.get("toUid"));
		this.fromName = fields.get("fromName");
		this.toName = fields.get("toName");
		this.text = fields.get("text");
		this.time = new java.util.Date();
		this.status = NEW;
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
		this.dao = (MessageDAO)dao;
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

	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromName() {
		return fromName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToName() {
		return toName;
	}
}