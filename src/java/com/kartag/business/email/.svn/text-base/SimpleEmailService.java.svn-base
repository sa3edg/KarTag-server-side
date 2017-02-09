package com.kartag.business.email;
import static com.kartag.common.config.Constants.APP_EMAIL_NAME;
import static com.kartag.common.config.Constants.APP_EMAIL_HOST_NAME;
import static com.kartag.common.config.Constants.APP_PASSWORD_EMAIL;
import static com.kartag.common.config.Constants.APP_EMAIL_SMTP_PORT_NUMBER;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class SimpleEmailService {

	public String sendSimpleEmail(String toEmail,String subject, String msg) throws Exception{
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setHostName(APP_EMAIL_HOST_NAME);
		simpleEmail.setFrom(APP_PASSWORD_EMAIL, APP_EMAIL_NAME);
		simpleEmail.setSmtpPort(APP_EMAIL_SMTP_PORT_NUMBER);
		simpleEmail.setAuthenticator(new DefaultAuthenticator(APP_PASSWORD_EMAIL, "kartag@sa3eed_"));
//		simpleEmail.setSSLOnConnect(true);
		simpleEmail.addTo(toEmail);
		simpleEmail.setSubject(subject);
		simpleEmail.setMsg(msg);
		return simpleEmail.send();
	}
}
