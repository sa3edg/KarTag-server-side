package com.kartag.server.model;

import java.math.BigInteger;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.ReplyDAO;

@Entity
@Table(name="replies")
public class Reply extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="reply_id", unique=true, nullable=false)
	@Type(type = "int")
	private int id ;
	
	@Column(name="message_id", unique=true, nullable=false)
	@Type(type = "int")
	private int messageId ;
	
	@Column(name="from_uid", nullable=false)
	private BigInteger fromUid ;
	
	@Column(name="to_uid", nullable=false)
	private BigInteger toUid ;
	
	@Column(name="from_name", nullable=false)
	private String fromName = "";
	
	@Column(name="to_name", nullable=false)
	private String toName = "";
	
	@Column(name="message_text", nullable=false)
	private String text = "";
	
	@Column(name = "message_time")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date time ;

	@Column(name="status")
	private String status = "";
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id", nullable = false, insertable=false, updatable=false)
	private Message message = new Message(); 
	
	
	@JsonIgnore
	@Transient
	private ReplyDAO dao = new ReplyDAO();
	
	@JsonIgnore
	@Transient
	public final static  String  NEW = "NEW";
	
	@JsonIgnore
	@Transient
	public final static  String  SEEN = "SEEN";
	
	public Reply()
	{
		
	}
	public Reply(LinkedHashMap<String, String> fields)
	{
		this.messageId = Integer.parseInt(fields.get("messageId"));
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
		this.dao = (ReplyDAO)dao;
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
	public void setMessage(Message message) {
		this.message = message;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getMessageId() {
		return messageId;
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