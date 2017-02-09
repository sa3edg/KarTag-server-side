package com.kartag.server.model;

import java.math.BigInteger;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.LocationDao;

@Entity
@Table(name="locations")
public class Location extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="location_id", nullable=false)
	@Type(type="int")
	private int locationId ;
	
	@Column(name="uid", nullable=false)
	private BigInteger uid ;
	
	@Column(name="street", nullable=false)
	private String street = "";
	
	@Column(name="city", nullable=false)
	private String city = "";
	
	@Column(name="state", nullable=false)
	private String state = "";
	
	@Column(name="country", nullable=false)
	private String country = "";
	
	@Column(name="zip_code", nullable=false)
	private String zipCode = "";
	
	
	@Column(name="latitude")
	private String latitude = "";
	
	@Column(name="longitude")
	private String longitude = "";
	
	@Transient
	private LocationDao dao = new LocationDao();
	
	public Location()
	{
		
	}
	public Location(int locationId,BigInteger uid, LinkedHashMap<String, String> fields)
	{
		this.locationId = locationId;
		this.uid = uid;
		this.city = fields.get("city");
		this.country = fields.get("country");
		this.latitude = fields.get("lat");;
		this.longitude = fields.get("lon");
		this.state = fields.get("state");
		this.street = fields.get("street");
		this.zipCode = fields.get("zipcode");
	}
	@Override
	public IDefaultDao getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDao(IDefaultDao dao) {
		// TODO Auto-generated method stub
		this.dao = (LocationDao) dao;
	}
	@Override
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet() {
		return street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry() {
		return country;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setUid(BigInteger uid) {
		this.uid = uid;
	}
	public BigInteger getUid() {
		return uid;
	}
}
