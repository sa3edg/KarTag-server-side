package com.kartag.server.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

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
import com.kartag.server.dao.CountryDao;

@Entity
@Table(name="countries")
public class Country extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="country_id", unique=true, nullable=false)
	@Type(type="int")
	private int id;
	
	@Column(name="country_name")
	private String name = "";
	
	@Transient
	private CountryDao dao = new CountryDao();

	@JsonIgnore
	@Transient
	private Map<BigInteger, User> users = new HashMap<BigInteger, User>(); 
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

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
		this.dao = (CountryDao) dao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setUsers(Map<BigInteger, User> users) {
		this.users = users;
	}

	public Map<BigInteger, User> getUsers() {
		return users;
	}


}
