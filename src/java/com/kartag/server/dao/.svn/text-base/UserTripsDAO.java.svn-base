package com.kartag.server.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.common.dao.jdbc.BaseJDBCDao;
import com.kartag.common.util.DateUtil;
import com.kartag.server.model.Pool;
import com.kartag.server.model.Trip;
import com.kartag.server.model.TripPool;
import com.kartag.server.model.UserTrip;

public class UserTripsDAO extends BaseJDBCDao {

	public int getTripsCount(int countryId, int poolId, String StartDate, String endDate) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Trip> rows = session
					.createCriteria(Trip.class)
					.add(Restrictions.between("time",
							DateUtil.getDateFromStringWithoutTime(StartDate),
							DateUtil.getDateFromStringWithoutTime(endDate))).list();
			
			@SuppressWarnings("unchecked")
			List<Pool> countryPools = session
				.createCriteria(Pool.class)
				.add(Restrictions.eq("countryId", countryId)).list();
			
			Integer[] tripsIds = new Integer[rows.size()];
			int count = 0;
			for(final Trip trip : rows)
			{
				tripsIds[count] = new Integer(trip.getId());
				count++;
			}
			Integer[] poolsId = new Integer[countryPools.size()];
			count = 0;
			for(final Pool pool : countryPools)
			{
				poolsId[count] = new Integer(pool.getId());
				count++;
			}
			Set<TripPool> filteredTrips = new HashSet<TripPool>();
			if(poolId == 0)
			{
				if(tripsIds.length > 0 && poolsId.length > 0)
				{
					@SuppressWarnings("unchecked")
					List<TripPool> filteredTripsList = session.createCriteria(TripPool.class)
						.add(Restrictions.in("tripId", tripsIds))
						.add(Restrictions.in("poolId", poolsId)).list();
					filteredTrips = new HashSet<TripPool>(filteredTripsList);
				}
			}
			else
			{
				if(tripsIds.length > 0 )
				{
					@SuppressWarnings("unchecked")
					List<TripPool> filteredTripsList = session.createCriteria(TripPool.class)
						.add(Restrictions.in("tripId", tripsIds))
						.add(Restrictions.eq("poolId", poolId)).list();
					filteredTrips = new HashSet<TripPool>(filteredTripsList);
				}
			}
			transaction.commit();

			return filteredTrips.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public int getCommunityTripsCount(int countryId, int communityId, String StartDate, String endDate) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Trip> rows = session
					.createCriteria(Trip.class)
					.add(Restrictions.between("time",
							DateUtil.getDateFromStringWithoutTime(StartDate),
							DateUtil.getDateFromStringWithoutTime(endDate))).list();
			
			Integer[] tripsIds = new Integer[rows.size()];
			int count = 0;
			for(final Trip trip : rows)
			{
				tripsIds[count] = new Integer(trip.getId());
				count++;
			}
			
			Set<Trip> filteredTrips = new HashSet<Trip>();
			if(communityId != 0)
			{
				if(tripsIds.length > 0)
				{
					@SuppressWarnings("unchecked")
					List<Trip> filteredTripsList = session.createCriteria(Trip.class)
						.add(Restrictions.in("id", tripsIds))
						.add(Restrictions.eq("communityId", communityId)).list();
					filteredTrips = new HashSet<Trip>(filteredTripsList);
				}
			}
			transaction.commit();

			return filteredTrips.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public int getCommunityTripsCountByType(String type, int countryId, int communityId, String StartDate, String endDate) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Trip> rows = session
					.createCriteria(Trip.class)
					.add(Restrictions.eq("type", type))
					.add(Restrictions.between("time",
							DateUtil.getDateFromStringWithoutTime(StartDate),
							DateUtil.getDateFromStringWithoutTime(endDate))).list();
			
			Integer[] tripsIds = new Integer[rows.size()];
			int count = 0;
			for(final Trip trip : rows)
			{
				tripsIds[count] = new Integer(trip.getId());
				count++;
			}
			
			Set<Trip> filteredTrips = new HashSet<Trip>();
			if(communityId != 0)
			{
				if(tripsIds.length > 0)
				{
					@SuppressWarnings("unchecked")
					List<Trip> filteredTripsList = session.createCriteria(Trip.class)
						.add(Restrictions.in("id", tripsIds))
						.add(Restrictions.eq("communityId", communityId)).list();
					filteredTrips = new HashSet<Trip>(filteredTripsList);
				}
			}
			transaction.commit();

			return filteredTrips.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public int getCommunityTripsCountByStatus(String status, int countryId, int communityId, String StartDate, String endDate) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<UserTrip> rows = session
					.createCriteria(UserTrip.class)
					.add(Restrictions.eq("status", status))
					.add(Restrictions.between("time",
							DateUtil.getDateFromStringWithoutTime(StartDate),
							DateUtil.getDateFromStringWithoutTime(endDate))).list();
			
			Integer[] tripsIds = new Integer[rows.size()];
			int count = 0;
			for(final UserTrip trip : rows)
			{
				tripsIds[count] = new Integer(trip.getTripId());
				count++;
			}
			
			Set<Trip> filteredTrips = new HashSet<Trip>();
			if(communityId != 0)
			{
				if(tripsIds.length > 0)
				{
					@SuppressWarnings("unchecked")
					List<Trip> filteredTripsList = session.createCriteria(Trip.class)
						.add(Restrictions.in("id", tripsIds))
						.add(Restrictions.eq("communityId", communityId)).list();
					filteredTrips = new HashSet<Trip>(filteredTripsList);
				}
			}
			transaction.commit();

			return filteredTrips.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public int getTripsCountByType(String type, int countryId, int poolId, String StartDate, String endDate) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Trip> rows = session
					.createCriteria(Trip.class)
					.add(Restrictions.eq("type", type))
					.add(Restrictions.between("time",
							DateUtil.getDateFromStringWithoutTime(StartDate),
							DateUtil.getDateFromStringWithoutTime(endDate))).list();
			
			@SuppressWarnings("unchecked")
			List<Pool> countryPools = session
				.createCriteria(Pool.class)
				.add(Restrictions.eq("countryId", countryId)).list();
			
			Integer[] tripsIds = new Integer[rows.size()];
			int count = 0;
			for(final Trip trip : rows)
			{
				tripsIds[count] = new Integer(trip.getId());
				count++;
			}
			Integer[] poolsId = new Integer[countryPools.size()];
			count = 0;
			for(final Pool pool : countryPools)
			{
				poolsId[count] = new Integer(pool.getId());
				count++;
			}
			Set<TripPool> filteredTrips = new HashSet<TripPool>();
			if(poolId == 0)
			{
				if(tripsIds.length > 0 && poolsId.length > 0)
				{
					@SuppressWarnings("unchecked")
					List<TripPool> filteredTripsList = session.createCriteria(TripPool.class)
						.add(Restrictions.in("tripId", tripsIds))
						.add(Restrictions.in("poolId", poolsId)).list();
					filteredTrips = new HashSet<TripPool>(filteredTripsList);
				}
			}
			else
			{
				if(tripsIds.length > 0 )
				{
					@SuppressWarnings("unchecked")
					List<TripPool> filteredTripsList = session.createCriteria(TripPool.class)
						.add(Restrictions.in("tripId", tripsIds))
						.add(Restrictions.eq("poolId", poolId)).list();
					filteredTrips = new HashSet<TripPool>(filteredTripsList);
				}
			}
			transaction.commit();

			return filteredTrips.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public int getTripsCountByStatus(String status, int countryId, int poolId, String StartDate, String endDate) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<UserTrip> rows = session
					.createCriteria(UserTrip.class)
					.add(Restrictions.eq("status", status))
					.add(Restrictions.between("time",
							DateUtil.getDateFromStringWithoutTime(StartDate),
							DateUtil.getDateFromStringWithoutTime(endDate))).list();
			
			@SuppressWarnings("unchecked")
			List<Pool> countryPools = session
				.createCriteria(Pool.class)
				.add(Restrictions.eq("countryId", countryId)).list();
			
			Integer[] tripsIds = new Integer[rows.size()];
			int count = 0;
			for(final UserTrip trip : rows)
			{
				tripsIds[count] = new Integer(trip.getTripId());
				count++;
			}
			Integer[] poolsId = new Integer[countryPools.size()];
			count = 0;
			for(final Pool pool : countryPools)
			{
				poolsId[count] = new Integer(pool.getId());
				count++;
			}
			Set<TripPool> filteredTrips = new HashSet<TripPool>();
			if(poolId == 0)
			{
				if(tripsIds.length > 0 && poolsId.length > 0)
				{
					@SuppressWarnings("unchecked")
					List<TripPool> filteredTripsList = session.createCriteria(TripPool.class)
						.add(Restrictions.in("tripId", tripsIds))
						.add(Restrictions.in("poolId", poolsId)).list();
					filteredTrips = new HashSet<TripPool>(filteredTripsList);
				}
			}
			else
			{
				if(tripsIds.length > 0 )
				{
					@SuppressWarnings("unchecked")
					List<TripPool> filteredTripsList = session.createCriteria(TripPool.class)
						.add(Restrictions.in("tripId", tripsIds))
						.add(Restrictions.eq("poolId", poolId)).list();
					filteredTrips = new HashSet<TripPool>(filteredTripsList);
				}
			}
			transaction.commit();

			return filteredTrips.size();
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	
}
