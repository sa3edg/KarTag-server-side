package com.kartag.server.model;

import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.common.util.DateUtil;

public class Report extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4201578243761565865L;
	private int countryId = 0;
	private int registeredUsers = 0;
	private int iphoneUsers = 0;
	private int androidUsers = 0;
	private int needArideRequested = 0;
	private int giveArideRequested = 0;
	private int tripsCount = 0;
	private int acceptedTrips = 0;
	private int rejectedTrips = 0;
	private String startDate = "17-11-2013";
	private String endDate = DateUtil.getNowAsString();
	private int poolId = 0;
	private int communityId = 0;
	@Override
	public IDefaultDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDao(IDefaultDao dao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setRegisteredUsers(int registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

	public int getRegisteredUsers() {
		return registeredUsers;
	}

	public void setIphoneUsers(int iphoneUsers) {
		this.iphoneUsers = iphoneUsers;
	}

	public int getIphoneUsers() {
		return iphoneUsers;
	}

	public void setAndroidUsers(int androidUsers) {
		this.androidUsers = androidUsers;
	}

	public int getAndroidUsers() {
		return androidUsers;
	}

	public void setNeedArideRequested(int needArideRequested) {
		this.needArideRequested = needArideRequested;
	}

	public int getNeedArideRequested() {
		return needArideRequested;
	}

	public void setGiveArideRequested(int giveArideRequested) {
		this.giveArideRequested = giveArideRequested;
	}

	public int getGiveArideRequested() {
		return giveArideRequested;
	}

	public void setTripsCount(int tripsCount) {
		this.tripsCount = tripsCount;
	}

	public int getTripsCount() {
		return tripsCount;
	}

	public void setAcceptedTrips(int acceptedTrips) {
		this.acceptedTrips = acceptedTrips;
	}

	public int getAcceptedTrips() {
		return acceptedTrips;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setPoolId(int poolId) {
		this.poolId = poolId;
	}

	public int getPoolId() {
		return poolId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public int getCommunityId() {
		return communityId;
	}

	public void setRejectedTrips(int rejectedTrips) {
		this.rejectedTrips = rejectedTrips;
	}

	public int getRejectedTrips() {
		return rejectedTrips;
	}

}
