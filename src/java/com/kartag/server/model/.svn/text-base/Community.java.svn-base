package com.kartag.server.model;

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
import com.kartag.server.dao.PoolDao;

@Entity
@Table(name = "communities")
public class Community extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "community_id", unique = true, nullable = false)
	@Type(type = "int")
	private int id;

	@Column(name = "community_name")
	private String communityName = "";
	
	@Column(name = "country_id")
	@Type(type = "int")
	private int countryId;
	
	@JsonIgnore
	@Column(name = "dns_server")
	private String dnsServer = "";
	
	@JsonIgnore
	@Column(name="effective_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date effStartDate;
	
	@JsonIgnore
	@Column(name="effective_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date effEndDate ;
	@Transient
	private PoolDao dao = new PoolDao();

	@Override
	@Transient
	@JsonIgnore
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
		this.dao = (PoolDao) dao;
	}

	@Override
	@JsonIgnore
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return countryId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setDnsServer(String dnsServer) {
		this.dnsServer = dnsServer;
	}

	public String getDnsServer() {
		return dnsServer;
	}

	public void setEffStartDate(java.util.Date effStartDate) {
		this.effStartDate = effStartDate;
	}

	public java.util.Date getEffStartDate() {
		return effStartDate;
	}

	public void setEffEndDate(java.util.Date effEndDate) {
		this.effEndDate = effEndDate;
	}

	public java.util.Date getEffEndDate() {
		return effEndDate;
	}
	
}
