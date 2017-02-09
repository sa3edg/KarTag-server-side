package com.kartag.business.validation;

import com.kartag.common.util.StringUtil;
import com.kartag.server.dao.CommunityDAO;
import com.kartag.server.model.Community;
import com.kartag.server.model.User;

public class CommunityValidator implements ICommunityValidator{

	@Override
	public boolean isCommunityUser(User user) throws Exception{
		// TODO Auto-generated method stub
		if(user == null){
			throw new IllegalArgumentException();
		}
		if(user.getCommunityId() == 0 || StringUtil.isEmpty(user.getCommunityEmail())){
			return false;
		}
		Community userCommunity = new CommunityDAO().getCommunityById(user.getCommunityId());
		if(userCommunity == null){
			return false;
		}
		String[] parts = user.getCommunityEmail().split("@");
		return parts.length == 2 && userCommunity.getDnsServer().equals(parts[1]);
	}
	

}
