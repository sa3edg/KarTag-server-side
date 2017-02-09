package com.kartag.server.model;

import java.math.BigInteger;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.common.util.DateUtil;
import com.kartag.common.util.StringUtil;
import com.kartag.server.dao.UserDAO;

@Entity
@Table(name="users")
//@TypeDef(
//		    name="encryptedString", 
//		    typeClass = org.jasypt.hibernate3.type.EncryptedStringType.class,
//		    parameters = {@Parameter( name = "encryptorRegisteredName", value="myHibernateStringEncryptor")}
//		)
//@FilterDef(name="filterUsers", parameters={@ParamDef( name="uid", type="big_integer" )} )
public class User extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="uid", unique=true, nullable=false)
	private BigInteger uid ;
	
	@Column(name = "country_id")
	@Type(type = "int")
	private int countryId;
	
	@Column(name="user_name", nullable=false)
	private String userName = "";
	
	@Column(name="name")
	private String name = "";
	
	@Column(name="first_name")
	private String firstName = "";
	
	@Column(name="middle_name")
	private String middleName = "";
	
	@Column(name="last_name")
	private String lastName = "";
	
	@Column(name="link")
	private String link = "";
	
	@Column(name="birthday")
	private String birthday = "";
	
	@Column(name="location_id")
	private int locationId = 0;
	
	@Column(name="gender")
	private String gender = "";
	
	@Column(name="status")
	private String status = "";
	
	@JsonIgnore
	@Column(name="source")
	private String source = "";
	
	@JsonIgnore
	@Column(name="device_Id")
	private String deviceId = "";

	@Column(name="email")
	private String email = "";
	
	@JsonIgnore
	@Column(name = "community_id")
	@Type(type = "int")
	private int communityId;
	
	@JsonIgnore
	@Column(name="community_email")
	private String communityEmail = "";
	
	@JsonIgnore
	@Column(name="community_password")
//	@Type(type="encryptedString")
	private String communityPassword = "";
	
	@Column(name = "reg_time")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date registerationTime = DateUtil.now();
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "user_trips", joinColumns = { @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", nullable = false, updatable = false) })
//	private Set<Trip> trips = new HashSet<Trip>();
	
	@JsonIgnore
	@Transient
	private Location location;
	
	@JsonIgnore
	@Transient
	private UserDAO dao = new UserDAO();
	
	@JsonIgnore
	@Transient
	private final static  String  ACTIVE = "ACTIVE";
	
	@JsonIgnore
	@Transient
	public final static  String  YOUR_PASSWORD_SENT_SUCCESSFULLY = "PASSWORD_SENT";
	
	@JsonIgnore
	@Transient
	public final static  String  YOUR_PASSWORD_SENT_FAILED = "PASSWORD_NOT_SENT";
	
	@JsonIgnore
	@Transient
	public final static  String  YOUR_PASSWORD_CHANGED_SUCCESSFULLY = "PASSWORD_CHANGED";
	
	@JsonIgnore
	@Transient
	public final static  String  YOUR_PASSWORD_CHANGED_FAILED = "PASSWORD_NOT_CHANGED";
	
	public User()
	{
		
	}
	public User(LinkedHashMap<String, String> fields, String source)
	{
		String countryId = fields.get("countryId");
		if(StringUtil.isEmpty(countryId)){
			countryId = "11";
		}
		
		String communityId = fields.get("communityId");
		if(StringUtil.isEmpty(communityId)){
			communityId = "0";
		}
		this.uid = new BigInteger(fields.get("uid"));
		this.userName = fields.get("username");
		this.name = fields.get("name");
		this.firstName = fields.get("firstname");
		this.middleName = fields.get("middlename");
		this.lastName = fields.get("lastname");
		this.link = fields.get("link");
		this.birthday = fields.get("birthday");
		this.gender = fields.get("gender");
		this.email = fields.get("email");
		this.deviceId = fields.get("deviceId");
		this.countryId = Integer.valueOf(countryId);
		this.communityId = Integer.valueOf(communityId);
		this.communityEmail = fields.get("communityEmail");
		this.setSource(source);
		this.status = ACTIVE;
		String hasLocation = fields.get("hasLocation");
		if("yes".equals(hasLocation))
		{
			this.locationId = 1;
			this.location = new Location(locationId, uid, fields);
		}
		
	}
	
	public User(LinkedHashMap<String, String> fields)
	{
		this.uid = new BigInteger(fields.get("uid"));
		this.userName = fields.get("username");
		this.name = fields.get("name");
		this.firstName = fields.get("firstname");
		this.middleName = fields.get("middlename");
		this.lastName = fields.get("lastname");
		this.link = fields.get("link");
		this.birthday = fields.get("birthday");
		this.gender = fields.get("gender");
		this.countryId = Integer.valueOf(fields.get("countryId"));
		this.status = ACTIVE;
		String hasLocation = fields.get("hasLocation");
		if("yes".equals(hasLocation))
		{
			this.locationId = 1;
			this.location = new Location(locationId, uid, fields);
		}
		
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
		this.dao = (UserDAO)dao;
	}
	
	@Override
	@JsonIgnore
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLink() {
		return link;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
//	public void setTrips(Set<Trip> trips) {
//		this.trips = trips;
//	}
//	public Set<Trip> getTrips() {
//		return trips;
//	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Location getLocation() {
		return location;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSource() {
		return source;
	}
	public void setRegisterationTime(java.util.Date registerationTime) {
		this.registerationTime = registerationTime;
	}
	public java.util.Date getRegisterationTime() {
		return registerationTime;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setCommunityPassword(String communityPassword) {
		this.communityPassword = communityPassword;
	}
	
	public String getCommunityPassword() {
		return communityPassword;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityEmail(String communityEmail) {
		this.communityEmail = communityEmail;
	}
	public String getCommunityEmail() {
		return communityEmail;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}

}
