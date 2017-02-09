package com.kartag.common.filters;

import org.apache.struts.taglib.html.SelectTag;
import javax.servlet.jsp.JspException;

public class SecurityCombo extends SelectTag {
	private String page;
	private String attribute;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int doStartTag() throws JspException{
		SecurityHelper securityHelper = (SecurityHelper) pageContext.getSession().
				getAttribute("SECURITY_PROFILE");
		if(securityHelper==null) {
			//log this error and let the default implementation of
			//the select tag take over.
			return super.doStartTag();
		}
		boolean isViewable =
			securityHelper.isViewableField(page, attribute);

		if(!isViewable) { //Do not show the values in the UI
			return SKIP_BODY;
		}

		boolean isModifiable =
			securityHelper.isEditableField(page, attribute);

		if(!isModifiable) { // do not allow the user to modify the contents
			setDisabled(true);
		}
        else{
            setDisabled(false);
        }

		return super.doStartTag();
	}
}