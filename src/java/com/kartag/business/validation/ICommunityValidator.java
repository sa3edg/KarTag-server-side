package com.kartag.business.validation;

import com.kartag.server.model.User;

public interface ICommunityValidator {
public boolean isCommunityUser(User user) throws Exception;
}
