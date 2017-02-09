package com.kartag.common.filters;

public interface SecurityHelper {
	//method to check page level access
	public boolean isAccessAllowed(String uri);

	//method to check if the attribute can be viewed by the user
	public boolean isViewableField(String page, String attribute);

	//method to check if the attribute can be edited by the user
	public boolean isEditableField(String page, String attribute);

	//method to refresh the cached user roles if there is some change
	public void refreshCache();
}
