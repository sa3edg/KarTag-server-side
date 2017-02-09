package com.kartag.server.model;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.TripDao;

@Entity
@FilterDef(name="validTrips", parameters={@ParamDef( name="type", type="string" ),@ParamDef( name="time", type="date" ),@ParamDef( name="availableSeats", type="int" )} )
@Table(name = "trips")
@org.hibernate.annotations.Entity(
		dynamicUpdate = true)
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
// property="id")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Trip extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "trip_id", unique = true, nullable = false)
	@Type(type = "int")
	private int id;

	@Column(name = "uid", nullable = false)
	private BigInteger uid ;

	@Column(name = "trip_time")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date time ;

	@Column(name = "trip_type")
	private String type = "";

	@Column(name = "from_id")
	@Type(type = "int")
	private int fromId;

	@Column(name = "to_id")
	@Type(type = "int")
	private int toId;

	@Column(name = "available_seats")
	@Type(type = "int")
	private int availableSeats;

	@Column(name = "user_count")
	@Type(type = "int")
	private int userCount;

	@Column(name = "smoking_allowed")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean smokingAllowed = false;

	@Column(name = "friends_only")
    @Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean friendsOnly = false;

	@Column(name = "women_only")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean womenOnly = false;

	@Column(name = "status")
	private String status = "";

	
	@Column(name = "comment")
	private String comment = "";
	
	@Column(name = "rate")
	@Type(type = "int")
	private int rate;
	
	@Column(name = "community_id")
	@Type(type = "int")
	private int communityId;
	
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid", nullable = false, insertable=false, updatable=false)
	private User user = new User();
	
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "trips")
//	private Map<String, User> users = new LinkedHashMap<String, User>();

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "trip_pools", joinColumns = { @JoinColumn(name = "trip_id", nullable = false, updatable = false) }, 
//			inverseJoinColumns = { @JoinColumn(name = "pool_id", nullable = false, updatable = false) })
	//@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "trip_pools", joinColumns = { @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "pool_id", referencedColumnName = "pool_id", nullable = false, updatable = false) })
    @Filter(name = "countryPool", condition = "parent_id = :countryId")
	//@JsonManagedReference
	@JsonIgnore
	private Map<String, Pool> pools = new LinkedHashMap<String, Pool>();

	@JsonIgnore
	@Transient
	public static final String REQUEST = "REQUEST";

	@JsonIgnore
	@Transient
	public static final String OFFER = "OFFER";
	
	@JsonIgnore
	@Transient
	public static final String OPEN = "OPEN";

	@JsonIgnore
	@Transient
	public static final String COMPLETED = "COMPLETED";
	
	@JsonIgnore
	@Transient
	public static final String CANCELED = "CANCELED";
	
	@JsonIgnore
	@Transient
	public static final String REQUEST_SENT = "REQUEST_SENT";
	
	@JsonIgnore
	@Transient
	public static final String ACCEPTED = "ACCEPTED";
	
	@JsonIgnore
	@Transient
	public static final String REJECTED = "REJECTED";
	
	@JsonIgnore
	@Transient
	public static final String JOIN_REQUEST_ALREADY_EXIST = "REQUEST_EXIST";
	
	@JsonIgnore
	@Transient
	public static final String JOIN_REQUEST_ALREADY_ACCEPTED = "REQUEST_ACCEPTED";
	
	@JsonIgnore
	@Transient
	public static final String TRIP_OWNER = "OWNER";
	

	@JsonIgnore
	@Transient
	private TripDao dao = new TripDao();

	@Override
	@JsonIgnore
	@Transient
	public IDefaultDao getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	@JsonIgnore
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@JsonIgnore
	@Transient
	public void setDao(IDefaultDao dao) {
		// TODO Auto-generated method stub
		this.dao = (TripDao) dao;
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

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getFromId() {
		return fromId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public int getToId() {
		return toId;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}

	public boolean isSmokingAllowed() {
		return smokingAllowed;
	}

	public void setFriendsOnly(boolean friendsOnly) {
		this.friendsOnly = friendsOnly;
	}

	public boolean isFriendsOnly() {
		return friendsOnly;
	}

	public void setWomenOnly(boolean womenOnly) {
		this.womenOnly = womenOnly;
	}

	public boolean isWomenOnly() {
		return womenOnly;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	public BigInteger getUid() {
		return uid;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setPools(Map<String, Pool> pools) {
		this.pools = pools;
	}

	public Map<String, Pool> getPools() {
		return pools;
	}

//	public void setUsers(Map<String, User> users) {
//		this.users = users;
//	}
//
//	public Map<String, User> getUsers() {
//		return users;
//	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getRate() {
		return rate;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public int getCommunityId() {
		return communityId;
	}

}
