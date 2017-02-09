package com.kartag.common.dao;

import com.kartag.common.model.IModel;

/**
 * 
 * @author SG
 */
public abstract class HQLQueryGenerator extends SQLQueryGenerator{

	public String deleteAllHQL(IModel entityBean) {
		String query = "delete from " + entityBean.getClass().getSimpleName();
		return query;
	}
	public String selectByPrimaryKeyHQL(IModel entityBean, Object id) {
		String query = "from "+entityBean.getClass().getSimpleName()+" where id = :id";
		return query;
	}
}
