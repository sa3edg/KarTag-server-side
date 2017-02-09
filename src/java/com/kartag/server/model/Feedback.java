package com.kartag.server.model;

import java.math.BigInteger;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.FeedbackDAO;

@Entity
@Table(name="feedback")
public class Feedback extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="feedback_id", unique=true, nullable=false)
	@Type(type = "int")
	private int id ;
	
	@Column(name="uid", nullable=false)
	private BigInteger uid ;

	@Column(name="feedback_type", nullable=false)
	private String type = "";
	
	@Column(name="feedback_text")
	private String text = "";
	
	@JsonIgnore
	@Transient
	private FeedbackDAO dao = new FeedbackDAO();
	
	@JsonIgnore
	@Transient
	public final static  String  REPORT_BUG = "REPORT_BUG";
	
	@JsonIgnore
	@Transient
	public final static  String  SUGGEST_FEATURE = "SUGGEST_FEATURE";
	
	@JsonIgnore
	@Transient
	public final static  String  GENERAL_INQUERY = "GENERAL_INQUERY";
	
	public Feedback(LinkedHashMap<String, String> fields)
	{
		this.uid = new BigInteger(fields.get("userId"));
		this.type = fields.get("type");
		this.text = fields.get("text");
	}
	public Feedback(){
		
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
		this.dao = (FeedbackDAO)dao;
	}
	
	@Override
	@JsonIgnore
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setUid(BigInteger uid) {
		this.uid = uid;
	}
	public BigInteger getUid() {
		return uid;
	}
}