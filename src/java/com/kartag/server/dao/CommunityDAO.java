package com.kartag.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.common.dao.jdbc.BaseJDBCDao;
import com.kartag.common.model.IModel;
import com.kartag.server.model.Community;
import com.kartag.server.model.Pool;
import com.kartag.server.model.User;
import com.kartag.server.presentation.action.CommunitiesActions;

public class CommunityDAO extends BaseJDBCDao{

	public ArrayList<Community> getCountryCommunities(String countryId) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Community> communities = session
					.createCriteria(Community.class)
					.add(Restrictions.eq("countryId",
							Integer.parseInt(countryId))).addOrder(Order.asc("communityName")).list();

			transaction.commit();
			LinkedHashSet<Community> allRows = new LinkedHashSet<Community>(communities);
			ArrayList<Community> communityList = new ArrayList<Community>(allRows);
			return communityList;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	public Community getCommunityById(int communityId) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Community community = (Community)session.get(Community.class, communityId);
			transaction.commit();
			return community;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	public ArrayList<? extends IModel> list(IModel baseObj,
			LinkedHashMap<String, String> filters) throws SQLException {
		try {

			Criteria criteria = getCriteria(baseObj);
			if (isExistParamter(filters, CommunitiesActions.COUNTRY_ID_PARAM)) {

				criteria = criteria.add(Restrictions.eq("countryId", Integer
						.parseInt(filters.get(CommunitiesActions.COUNTRY_ID_PARAM))));
			}
			@SuppressWarnings("unchecked")
			List<Community> rows = criteria.list();
			transaction.commit();
			Set<Community> allRows = new HashSet<Community>(rows);
			ArrayList<? extends IModel> allRowsList = new ArrayList<IModel>(allRows);
			return allRowsList;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
}
