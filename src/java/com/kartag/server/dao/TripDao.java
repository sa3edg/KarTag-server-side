package com.kartag.server.dao;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Filter;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.kartag.business.common.Response;
import com.kartag.business.notification.NotificationService;
import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.common.dao.jdbc.BaseJDBCDao;
import com.kartag.common.dao.jdbc.JDBCSessionFactory;
import com.kartag.common.dao.jdbc.JQuery;
import com.kartag.common.util.DAOUtil;
import com.kartag.common.util.DateUtil;
import com.kartag.server.model.Notification;
import com.kartag.server.model.Pool;
import com.kartag.server.model.Trip;
import com.kartag.server.model.User;
import com.kartag.server.model.UserTrip;

public class TripDao extends BaseJDBCDao {

	public Set<Trip> getActiveTrips(String countryId, String tripType)
			throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			// String sqlQuery =
			// "select p.pool_id , p.pool_name , t.trip_id , t.uid , t.trip_time , t.trip_type , t.from_id , t.to_id , t.available_seats , "
			// +
			// "t.user_count , t.cost , t.smoking_allowed , t.friends_only , t.women_only , t.status , t.comment "
			// +
			// "from pools p inner join trips t on  (p.pool_id = t.from_id or p.pool_id = t.to_id) "
			// + "where t.trip_type = :type ";//and user_count <
			// available_seats";
			// @SuppressWarnings("unchecked")
			// List<Pool> rows =
			// session.createSQLQuery(sqlQuery).setParameter("type", tripType)
			// .list();
			// String query =
			// "from Pool as p inner join p.trips as t where p.poolId = t.fromId or p.poolId = t.toId and t.type = :type";
			// String query =
			// "from Pool p where p.poolId in (select tp.poolId from TripPool tp where  tp.tripId in (select t.id from Trip t where t.type = :type) )";//and
			// p.poolId = t.fromId or p.poolId = t.toId and t.type = :type";
			// Filter filter = session.enableFilter("countryPool");
			// filter.setParameter("countryId", Integer.parseInt(countryId));
			// String query =
			// "from Trip t where t.type = :type and t.time >= :time) ";
			@SuppressWarnings("unchecked")
			List<Trip> rows = session
					.createCriteria(Trip.class)
					.add(Restrictions
							.conjunction()
							.add(Restrictions.eq("type", tripType))
							.add(Restrictions.ge("time",
									DateUtil.getValidTripsTime())))
					.setFetchMode("pools", FetchMode.JOIN).list();
			// List<Trip> rows = session.createQuery(query).setParameter("type",
			// tripType).setParameter("time", DateUtil.getValidTripsTime())
			// .list();

			// List<Trip> rows = session.createCriteria(Trip.class, "t")
			// .add(Restrictions.conjunction()
			// //
			// .add(Restrictions.disjunction().add(Restrictions.eqProperty("p.poolId",
			// "t.fromId")).add(Restrictions.eqProperty("p.poolId", "t.toId") ))
			// // .add(Restrictions.eqProperty("p.poolId", "t.fromId"))
			// // .add(Restrictions.or(Restrictions.eqProperty("p.poolId",
			// "t.fromId"), Restrictions.eqProperty("p.poolId", "t.toId") ))
			// .add(Restrictions.eq("t.type", tripType))
			// .add(Restrictions.le("t.time", DateUtil.getValidTripsTime()))
			// )
			// .list();

			transaction.commit();
			Set<Trip> trips = new HashSet<Trip>(rows);
			return trips;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public ArrayList<Trip> getUserTrips(String userId, String tripType)
			throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<UserTrip> rows = session.createCriteria(UserTrip.class)
					.add(Restrictions.eq("uid", new BigInteger(userId)))
					.add(Restrictions.eq("type", tripType))
					.addOrder(Order.desc("time")).list();

			List<Trip> dumyTrips = new ArrayList<Trip>();
			if (rows.size() > 0) {
				Map<String, UserTrip> userTripsMap = new HashMap<String, UserTrip>();
				for (UserTrip ut : rows) {
					userTripsMap.put(String.valueOf(ut.getTripId()), ut);
				}
				Integer[] tripsIds = new Integer[rows.size()];

				for (int i = 0; i < rows.size(); i++) {
					UserTrip tempTrip = rows.get(i);
					tripsIds[i] = new Integer(tempTrip.getTripId());
				}
				@SuppressWarnings("unchecked")
				List<Trip> userTrips = session.createCriteria(Trip.class)
						.add(Restrictions.in("id", tripsIds))
						.addOrder(Order.desc("time")).list();

				for (Trip trip : userTrips) {
					trip.setStatus(((UserTrip) userTripsMap.get(String
							.valueOf(trip.getId()))).getStatus());
				}
				transaction.commit();
				// TreeSet<Trip> trips = new TreeSet<Trip>(userTrips);
				return new ArrayList<Trip>(userTrips);
			}
			transaction.commit();

			// TreeSet<Trip> trips = new TreeSet<Trip>(dumyTrips);
			return new ArrayList<Trip>(dumyTrips);
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public ArrayList<Trip> filterTrips(String tripType, String startPoolId,
			String endPoolId, String date) throws Exception {
		if ((startPoolId != null && !"".equals(startPoolId))
				&& (endPoolId == null || "".equals(endPoolId))
				&& (date == null || "".equals(date))) {
			return filterTripsByStartPool(tripType, startPoolId);
		} else if ((endPoolId != null && !"".equals(endPoolId))
				&& (startPoolId == null || "".equals(startPoolId))
				&& (date == null || "".equals(date))) {
			return filterTripsByEndPool(tripType, endPoolId);
		} else if ((startPoolId != null && !"".equals(startPoolId))
				&& (endPoolId != null && !"".equals(endPoolId))
				&& (date == null || "".equals(date))) {
			return filterTripsByStartAndEndPool(tripType, startPoolId,
					endPoolId);
		} else {
			return filterTripsByAllParams(tripType, startPoolId, endPoolId,
					date);
		}
	}

	public ArrayList<Trip> filterTripsByStartPool(String tripType,
			String startPoolId) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Trip> userTrips = session
					.createCriteria(Trip.class)
					.add(Restrictions.eq("fromId",
							Integer.parseInt(startPoolId)))
					.add(Restrictions.gt("time", DateUtil.getValidTripsTime()))
					.add(Restrictions.eq("type", tripType))
					.add(Restrictions.gt("availableSeats", 0)).list();

			transaction.commit();
			LinkedHashSet<Trip> allRows = new LinkedHashSet<Trip>(userTrips);
			ArrayList<Trip> trips = new ArrayList<Trip>(allRows);
			return trips;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public ArrayList<Trip> filterTripsByEndPool(String tripType, String endPool)
			throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Trip> userTrips = session.createCriteria(Trip.class)
					.add(Restrictions.eq("toId", Integer.parseInt(endPool)))
					.add(Restrictions.gt("time", DateUtil.getValidTripsTime()))
					.add(Restrictions.eq("type", tripType))
					.add(Restrictions.gt("availableSeats", 0)).list();

			transaction.commit();
			LinkedHashSet<Trip> allRows = new LinkedHashSet<Trip>(userTrips);
			ArrayList<Trip> trips = new ArrayList<Trip>(allRows);
			return trips;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public ArrayList<Trip> filterTripsByStartAndEndPool(String tripType,
			String startPool, String endPool) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Trip> userTrips = session
					.createCriteria(Trip.class)
					.add(Restrictions.eq("toId", Integer.parseInt(endPool)))
					.add(Restrictions.eq("fromId", Integer.parseInt(startPool)))
					.add(Restrictions.gt("time", DateUtil.getValidTripsTime()))
					.add(Restrictions.eq("type", tripType))
					.add(Restrictions.gt("availableSeats", 0)).list();

			transaction.commit();
			LinkedHashSet<Trip> allRows = new LinkedHashSet<Trip>(userTrips);
			ArrayList<Trip> trips = new ArrayList<Trip>(allRows);
			return trips;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public ArrayList<Trip> filterTripsByAllParams(String tripType,
			String startPoolId, String endPoolId, String date) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Trip> userTrips = session
					.createCriteria(Trip.class)
					.add(Restrictions.eq("fromId",
							Integer.parseInt(startPoolId)))
					.add(Restrictions.eq("toId", Integer.parseInt(endPoolId)))
					.add(Restrictions.eq("type", tripType))
					.add(Restrictions.between("time",
							DateUtil.getStartSearchDate(date),
							DateUtil.getEndSearchDate(date)))
					.add(Restrictions.gt("availableSeats", 0)).list();

			transaction.commit();
			LinkedHashSet<Trip> allRows = new LinkedHashSet<Trip>(userTrips);
			ArrayList<Trip> trips = new ArrayList<Trip>(allRows);
			return trips;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public boolean addTrip(Set<Trip> trips) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			for (final Trip trip : trips) {
				session.save(trip);
				session.flush();
				session.clear();
				// should be executed of rows exceed 20 rows
				// if (i % 20 == 0) { // 20, same as the JDBC batch size
				// // flush a batch of inserts and release memory:
				// session.flush();
				// session.clear();
				// }
			}
			transaction.commit();
			return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public boolean deleteTrip(String id, String uid) throws Exception {
		try {
			BigInteger userId = new BigInteger(uid);
			String[] parts = id.split(",");
			if (parts.length <= 0) {
				return false;
			}
			boolean successed = true;
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			for (int i = 0; i < parts.length; i++) {
				Trip trip = (Trip) session.get(Trip.class,
						new Integer(parts[i]));
				long tripTime = trip.getTime().getTime();
				long currentTime = new Date().getTime();

				if (trip.getUid().equals(userId)) {
					String deleteTripQuery = "delete from Trip  where id =:id and uid =:uid";
					session.createQuery(deleteTripQuery)
							.setParameter("id", Integer.parseInt(parts[i]))
							.setParameter("uid", userId).executeUpdate();
					String deletePoolTripQuery = "delete from TripPool  where tripId =:id";
					session.createQuery(deletePoolTripQuery)
							.setParameter("id", Integer.parseInt(parts[i]))
							.executeUpdate();
					String deleteUserTripsQuery = "delete from UserTrip  where tripId =:id";
					session.createQuery(deleteUserTripsQuery)
							.setParameter("id", Integer.parseInt(parts[i]))
							.executeUpdate();
					String deleteNotificationQuery = "delete from Notification  where tripId =:id and toUid =:uid";
					session.createQuery(deleteNotificationQuery)
							.setParameter("id", Integer.parseInt(parts[i]))
							.setParameter("uid", userId).executeUpdate();
					// update notification for all trip users
					if (tripTime >= currentTime) {
						String updateTripsStatus = "update UserTrip set status =:status where tripId =:id";
						session.createQuery(updateTripsStatus)
								.setParameter("id", Integer.parseInt(parts[i]))
								.setParameter("status", Trip.CANCELED)
								.executeUpdate();
						String updateNotification = "update Notification set status =:status where tripId =:id and fromUid =:uid";
						session.createQuery(updateNotification)
								.setParameter("id", Integer.parseInt(parts[i]))
								.setParameter("uid", userId)
								.setParameter("status",
										Notification.CANCELED_STATUS)
								.executeUpdate();
					}
				} else {
					String deleteUserTripsQuery = "delete from UserTrip  where tripId =:id and uid=:uid";
					session.createQuery(deleteUserTripsQuery)
							.setParameter("id", Integer.parseInt(parts[i]))
							.setParameter("uid", userId).executeUpdate();

					// update valid trips and notify trip owner
					if (tripTime >= currentTime) {
						// increment available seats
						int availableSeats = trip.getAvailableSeats() - 1;
						trip.setAvailableSeats(availableSeats);
						session.update(trip);

						// notify trip owner
						String updateNotification = "update Notification set status =:status where tripId =:id and fromUid =:uid and toUid =:toId";
						session.createQuery(updateNotification)
								.setParameter("id", Integer.parseInt(parts[i]))
								.setParameter("uid", userId)
								.setParameter("toId", trip.getUid())
								.setParameter("status",
										Notification.CANCELED_STATUS)
								.executeUpdate();
					}
				}

				// notify joined users for deleted trip
				// if (tripTime >= currentTime) {
				//
				// @SuppressWarnings("unchecked")
				// List<UserTrip> userTrips = session
				// .createCriteria(UserTrip.class)
				// .add(Restrictions.eq("tripId",
				// Integer.parseInt(parts[i])))
				// .add(Restrictions.eq("status",
				// Notification.ACCEPTED_STATUS)).list();
				// if (userTrips.size() > 0) {
				// Pool startPool = (Pool) session.get(Pool.class,
				// trip.getFromId());
				// Pool endPool = (Pool) session.get(Pool.class,
				// trip.getToId());
				// User user = (User) session.get(User.class, userId);
				// for (int k = 0; k < userTrips.size(); k++) {
				// UserTrip userTrip = userTrips.get(k);
				// Notification notification = new Notification();
				// notification.setTripId(userTrip.getTripId());
				// notification.setFromUid(trip.getUid());
				// notification.setToUid(trip.getUid());
				// notification.setFromName(user.getName());
				// notification.setFromPoolName(startPool
				// .getPoolName());
				// notification.setToPoolName(endPool.getPoolName());
				// notification
				// .setType(Notification.REPLY_REQUEST_TYPE);
				// notification.setTripTime(trip.getTime());
				// notification
				// .setNotificationTime(new java.util.Date());
				// notification
				// .setStatus(Notification.CANCELED_STATUS);
				// session.save(notification);
				// }
				// }
				//
				// }
			}
			transaction.commit();
			return successed;

		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public String addJoinRequest(String userId, String tripId, String type)
			throws Exception {

		Notification notification = null;
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserTrip.class);
			UserTrip userTrip = (UserTrip) criteria
					.add(Restrictions.eq("tripId", Integer.parseInt(tripId)))
					.add(Restrictions.eq("uid", new BigInteger(userId)))
					.uniqueResult();
			Trip trip = (Trip) session.get(Trip.class, new Integer(tripId));
			if (userTrip != null) {
				if (Trip.REJECTED.equals(userTrip.getStatus())) {
					return Trip.REJECTED;
				} else if (Trip.REQUEST_SENT.equals(userTrip.getStatus())) {
					return Trip.JOIN_REQUEST_ALREADY_EXIST;
				} else if (Trip.ACCEPTED.equals(userTrip.getStatus())) {
					return Trip.JOIN_REQUEST_ALREADY_ACCEPTED;
				}
			} else if (trip.getAvailableSeats() == 0
					|| Trip.COMPLETED.equals(trip.getStatus())) {
				return Trip.COMPLETED;
			} else {
				userTrip = new UserTrip();
				userTrip.setTripId(Integer.parseInt(tripId));
				userTrip.setUid(new BigInteger(userId));
				if (Trip.OFFER.equals(type)) {
					userTrip.setType(Trip.REQUEST);
				} else {
					userTrip.setType(Trip.OFFER);
				}

				userTrip.setStatus(Trip.REQUEST_SENT);
				session.save(userTrip);

			}
			// add notification

			User user = (User) session.get(User.class, new BigInteger(userId));
			Pool startPool = (Pool) session.get(Pool.class, trip.getFromId());
			Pool endPool = (Pool) session.get(Pool.class, trip.getToId());
			if (trip != null && user != null && startPool != null
					&& endPool != null) {
				notification = new Notification();
				notification.setTripId(Integer.parseInt(tripId));
				notification.setFromUid(new BigInteger(userId));
				notification.setToUid(trip.getUid());
				notification.setFromName(user.getName());
				notification.setFromPoolName(startPool.getPoolName());
				notification.setToPoolName(endPool.getPoolName());
				notification.setType(Notification.JOIN_REQUEST_TYPE);
				notification.setTripTime(trip.getTime());
				notification.setNotificationTime(new java.util.Date());
				notification.setStatus(Notification.REQUEST_SENT_STATUS);
				session.save(notification);
			} else {
				return Response.ERROR_STATUS;
			}

			transaction.commit();
			return Trip.REQUEST_SENT;
		} finally {
			PersistenceSessionFactory.closeSession();
			if (notification != null) {
				User notificationUser = null;
				notificationUser = new UserDAO().getUserByUid(notification
						.getToUid());
				// notify user
				if (notificationUser != null) {
					NotificationService.getInstance().notifyUser(notification,
							notificationUser);
				}
			}
		}
	}

	public String acceptJoinRequest(String userId, String tripId, String fromUid)
			throws Exception {

		Notification newNotification = null;
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();

			Trip trip = (Trip) session.get(Trip.class, new Integer(tripId));

			if (trip != null) {
				int availableSeats = trip.getAvailableSeats();
				if (availableSeats == 0
						|| Trip.COMPLETED.equals(trip.getStatus())) {
					return Trip.COMPLETED;
				}
				if (Trip.OFFER.equals(trip.getType())) {
					availableSeats--;
					if (availableSeats == 0) {
						trip.setStatus(Trip.COMPLETED);
					}

					trip.setAvailableSeats(availableSeats);
					session.update(trip);
				}
			} else {
				return Response.ERROR_STATUS;
			}

			UserTrip userTrip = (UserTrip) session
					.createQuery(
							"from UserTrip where tripId = :tripId and uid = :uid ")
					.setParameter("tripId", Integer.parseInt(tripId))
					.setParameter("uid", new BigInteger(fromUid))
					.uniqueResult();

			userTrip.setStatus(Trip.ACCEPTED);
			session.update(userTrip);

			// update notification record
			Notification existNotification = (Notification) session
					.createQuery(
							"from Notification where tripId = :tripId and toUid = :uid and fromUid = :fromUid")
					.setParameter("tripId", Integer.parseInt(tripId))
					.setParameter("uid", new BigInteger(userId))
					.setParameter("fromUid", new BigInteger(fromUid))
					.uniqueResult();

			if (existNotification != null) {
				existNotification.setStatus(Notification.ACCEPTED_STATUS);
				session.update(existNotification);
			}
			// create new notification
			User user = (User) session.get(User.class, new BigInteger(userId));
			Pool startPool = (Pool) session.get(Pool.class, trip.getFromId());
			Pool endPool = (Pool) session.get(Pool.class, trip.getToId());
			if (trip != null && user != null && startPool != null
					&& endPool != null) {
				newNotification = new Notification();
				newNotification.setTripId(Integer.parseInt(tripId));
				newNotification.setFromUid(trip.getUid());
				newNotification.setToUid(new BigInteger(fromUid));
				newNotification.setFromName(user.getName());
				newNotification.setFromPoolName(startPool.getPoolName());
				newNotification.setToPoolName(endPool.getPoolName());
				newNotification.setType(Notification.REPLY_REQUEST_TYPE);
				newNotification.setTripTime(trip.getTime());
				newNotification.setNotificationTime(new java.util.Date());
				newNotification.setStatus(Notification.ACCEPTED_STATUS);
				session.save(newNotification);
			} else {
				return Response.ERROR_STATUS;
			}

			transaction.commit();
			return Notification.ACCEPTED_STATUS;
		} finally {
			PersistenceSessionFactory.closeSession();
			if (newNotification != null) {
				User notificationUser = null;
				notificationUser = new UserDAO().getUserByUid(newNotification
						.getToUid());
				// notify user
				if (notificationUser != null) {
					NotificationService.getInstance().notifyUser(
							newNotification, notificationUser);
				}
			}
		}
	}

	public String rejectJoinRequest(String userId, String tripId, String fromUid)
			throws Exception {

		Notification newNotification = null;
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();

			Trip trip = (Trip) session.get(Trip.class, new Integer(tripId));

			if (trip == null) {
				return Response.ERROR_STATUS;
			}

			UserTrip userTrip = (UserTrip) session
					.createQuery(
							"from UserTrip where tripId = :tripId and uid = :uid ")
					.setParameter("tripId", Integer.parseInt(tripId))
					.setParameter("uid", new BigInteger(fromUid))
					.uniqueResult();

			userTrip.setStatus(Trip.REJECTED);
			session.update(userTrip);

			// update notification record
			Notification existNotification = (Notification) session
					.createQuery(
							"from Notification where tripId = :tripId and toUid = :uid and fromUid = :fromUid")
					.setParameter("tripId", Integer.parseInt(tripId))
					.setParameter("uid", new BigInteger(userId))
					.setParameter("fromUid", new BigInteger(fromUid))
					.uniqueResult();

			if (existNotification != null) {
				existNotification.setStatus(Notification.REJECTED_STATUS);
				session.update(existNotification);
			}
			// create new notification
			User user = (User) session.get(User.class, new BigInteger(userId));
			Pool startPool = (Pool) session.get(Pool.class, trip.getFromId());
			Pool endPool = (Pool) session.get(Pool.class, trip.getToId());
			if (trip != null && user != null && startPool != null
					&& endPool != null) {
				newNotification = new Notification();
				newNotification.setTripId(Integer.parseInt(tripId));
				newNotification.setFromUid(trip.getUid());
				newNotification.setToUid(new BigInteger(fromUid));
				newNotification.setFromName(user.getName());
				newNotification.setFromPoolName(startPool.getPoolName());
				newNotification.setToPoolName(endPool.getPoolName());
				newNotification.setType(Notification.REPLY_REQUEST_TYPE);
				newNotification.setTripTime(trip.getTime());
				newNotification.setNotificationTime(new java.util.Date());
				newNotification.setStatus(Notification.REJECTED_STATUS);
				session.save(newNotification);
			} else {
				return Response.ERROR_STATUS;
			}

			transaction.commit();
			return Notification.REJECTED_STATUS;
		} finally {
			PersistenceSessionFactory.closeSession();
			if (newNotification != null) {
				User notificationUser = null;
				notificationUser = new UserDAO().getUserByUid(newNotification
						.getToUid());
				// notify user
				if (notificationUser != null) {
					NotificationService.getInstance().notifyUser(
							newNotification, notificationUser);
				}
			}
		}
	}

	public ArrayList<Pool> getValidPoolsTrips(String tripType) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Filter filter = session.enableFilter("validTrips");
			filter.setParameter("type", tripType).setParameter("time",
					DateUtil.getValidTripsTime());
			@SuppressWarnings("unchecked")
			List<Pool> rows = session.createCriteria(Pool.class).list();
			transaction.commit();
			Set<Pool> allRows = new HashSet<Pool>(rows);
			ArrayList<Pool> pools = new ArrayList<Pool>(allRows);
			return pools;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public ArrayList<Pool> getTrips(String tripType) throws Exception {

		try {
			String sqlQuery = "";
			if (Trip.OFFER.equals(tripType)) {
				sqlQuery = "select p.pool_id , p.pool_name , t.trip_id , t.uid , t.trip_time , t.trip_type , t.from_id , t.to_id , t.available_seats , "
						+ "t.user_count , t.cost , t.smoking_allowed , t.friends_only , t.women_only , t.status , t.comment , u.uid , u.user_name , u.name"
						+ "from pools p inner join trips t on  (p.pool_id = t.from_id or p.pool_id = t.to_id) "
						+ " inner join users u  on  (p.uid = u.uid) "
						+ "where Now() <= t.trip_time - ? and t.trip_type = ? and user_count < available_seats";
			} else if (Trip.REQUEST.equals(tripType)) {
				sqlQuery = "select p.pool_id , p.pool_name , t.trip_id , t.uid , t.trip_time , t.trip_type , t.from_id , t.to_id , t.available_seats , "
						+ "t.user_count , t.cost , t.smoking_allowed , t.friends_only , t.women_only , t.status , t.comment , u.uid , u.user_name , u.name"
						+ "from pools p inner join trips t on  (p.pool_id = t.from_id or p.pool_id = t.to_id) "
						+ " inner join users u  on  (p.uid = u.uid) "
						+ "where Now() <= t.trip_time - ? and t.trip_type = ? and ";
			}
			JQuery query = new JQuery(sqlQuery);
			connection = JDBCSessionFactory.currentSession();
			statement = connection.prepareStatement(query.getQuery());
			DAOUtil.bindExecuteQuery(statement, query);
			rs = statement.executeQuery();
			return createPoolsFromRS(rs);
		} finally {
			JDBCSessionFactory.closeSession();
			close();
		}
	}

	public boolean rateTrip(String tripId, String rating) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Trip trip = (Trip) session.get(Trip.class, new Integer(tripId));
			float rate = Float.parseFloat(rating);
			trip.setRate((int) rate);
			session.update(trip);
			transaction.commit();
			return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public Set<Trip> getCommunityTrips(String tripType, String communityId)
			throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Trip> userTrips = session
					.createCriteria(Trip.class)
					.add(Restrictions.eq("communityId",
							Integer.parseInt(communityId)))
					.add(Restrictions.gt("time", DateUtil.getValidTripsTime()))
					.add(Restrictions.eq("type", tripType))
					.add(Restrictions.gt("availableSeats", 0)).list();

			transaction.commit();
			Set<Trip> trips = new HashSet<Trip>(userTrips);
			return trips;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	private ArrayList<Pool> createPoolsFromRS(ResultSet resultSet) {
		SortedMap<String, Pool> trips = new TreeMap<String, Pool>();
		return new ArrayList<Pool>(trips.values());
	}
}
