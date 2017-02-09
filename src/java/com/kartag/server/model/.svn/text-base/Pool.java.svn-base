package com.kartag.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.PoolDao;

@Entity
@Table(name = "pools")
@FilterDef(name="countryPool", parameters={@ParamDef( name="countryId", type="integer" )} )

public class Pool extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "pool_id", unique = true, nullable = false)
	@Type(type = "int")
	private int id;

	@Column(name = "pool_name")
	private String poolName = "";
	
	@Column(name = "country_id")
	@Type(type = "int")
	private int countryId;

	@ManyToMany(fetch = FetchType.EAGER)
	@Filter(name = "validTrips", condition = "trip_type = :type and trip_time >= :time and available_seats > :availableSeats")
	@JoinTable(name = "trip_pools", joinColumns = { @JoinColumn(name = "pool_id", referencedColumnName = "pool_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", nullable = false, updatable = false) })
	//@JsonBackReference
	private List<Trip> trips = new ArrayList<Trip>();
	
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

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	
	public List<Trip> getTrips() {
		
		return trips;
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
	
}
