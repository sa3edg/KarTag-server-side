package com.kartag.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.model.Model;
import com.kartag.server.dao.UserRulesDAO;

@Entity
@Table(name="trip_pools")
public class TripPool extends Model{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -220251055932386276L;

	@Id
	@Column(name="trip_id")
	private int tripId ;
	
	@Id
	@Column(name="pool_id")
	private int poolId ;
	
	@Transient
	private UserRulesDAO  dao = new UserRulesDAO();

	@Override
	@Transient
	public IDefaultDao getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	@Transient
	public void setDao(IDefaultDao dao) {
		// TODO Auto-generated method stub
		this.dao = (UserRulesDAO)dao;
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transient
	public int getBeanParentId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public int getTripId() {
		return tripId;
	}

	public void setPoolId(int poolId) {
		this.poolId = poolId;
	}

	public int getPoolId() {
		return poolId;
	}
}
