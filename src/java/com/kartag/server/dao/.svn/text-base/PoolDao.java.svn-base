package com.kartag.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.common.dao.jdbc.BaseJDBCDao;
import com.kartag.common.model.IModel;
import com.kartag.common.util.DateUtil;
import com.kartag.server.model.Pool;
import com.kartag.server.model.Trip;
import com.kartag.server.presentation.action.PoolsActions;

public class PoolDao extends BaseJDBCDao {

	public ArrayList<Pool> getValidPoolsTrips(String tripType, String countryId)
			throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Filter filter = session.enableFilter("validTrips");
			filter.setParameter("type", tripType).setParameter("time",
					DateUtil.getValidTripsTime()).setParameter("availableSeats", 0);

			Filter countryFilter = session.enableFilter("countryPool");
			countryFilter.setParameter("countryId", Integer.valueOf(countryId));

			@SuppressWarnings("unchecked")
			List<Pool> rows = session.createCriteria(Pool.class)
					.addOrder(Order.asc("poolName")).list();
			transaction.commit();
			LinkedHashSet<Pool> allRows = new LinkedHashSet<Pool>(rows);
			ArrayList<Pool> pools = new ArrayList<Pool>(allRows);
			return pools;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public ArrayList<? extends IModel> list(IModel baseObj,
			LinkedHashMap<String, String> filters) throws SQLException {
		try {

			Criteria criteria = getCriteria(baseObj);
			if (isExistParamter(filters, PoolsActions.COUNTRY_ID_PARAM)) {

				criteria = criteria.add(Restrictions.eq("countryId", Integer
						.parseInt(filters.get(PoolsActions.COUNTRY_ID_PARAM))));
			}
			@SuppressWarnings("unchecked")
			List<Pool> rows = criteria.list();
			transaction.commit();
			Set<Pool> allRows = new HashSet<Pool>(rows);
			ArrayList<? extends IModel> allRowsList = new ArrayList<IModel>(allRows);
			return allRowsList;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
}
