package com.kartag.business.community;

import static com.kartag.common.config.Constants.APP_PASSWORD_EMAIL_SUBJECT;
import static com.kartag.common.config.Constants.APP_FORGET_PASSWORD_EMAIL_SUBJECT;
import static com.kartag.common.config.Constants.APP_CHANGE_PASSWORD_EMAIL_SUBJECT;
import org.apache.commons.lang.RandomStringUtils;

import com.kartag.business.email.SimpleEmailService;
import com.kartag.common.logging.LocalLogger;
import com.kartag.common.text.TextProvider;
import com.kartag.common.util.StringUtil;
import com.kartag.server.model.User;

public class CommunityHandler {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(CommunityHandler.class);
	private static CommunityHandler instance = null;
	private final SimpleEmailService emailService = new SimpleEmailService();

	private CommunityHandler() {

	}

	public static CommunityHandler getInstance() {
		if (instance == null) {
			instance = new CommunityHandler();
		}
		return instance;
	}

	public String sendGeneratedPasswordViaEmail(User user) {
		try {

			String chars = "abcdefghjklmnopqrstuvxwyz123456789(0)+-@#$&";
			String userPassword = RandomStringUtils.random(10, chars);
			StringBuilder msg = new StringBuilder();
			//append email header
			msg.append(TextProvider.getText("app.email.header", new String[]{user.getFirstName()}));
			msg.append("\n");
			msg.append("\n");
			//append email body
			msg.append(TextProvider.getText("app.email.user.password", new String[]{userPassword}));
			msg.append("\n");
			msg.append("\n");
			//append email footer
			msg.append(TextProvider.getText("app.email.footer"));
			String messageId = emailService.sendSimpleEmail(
					user.getCommunityEmail(),
					APP_PASSWORD_EMAIL_SUBJECT, msg.toString());
			return StringUtil.isNotEmpty(messageId) ? userPassword : User.YOUR_PASSWORD_SENT_FAILED ;
		} catch (Exception ex) {
			logger.error("000000003", ex.getMessage(), ex);
		}
		return User.YOUR_PASSWORD_SENT_FAILED;
	}
	
	public String sendPasswordViaEmail(User user) {
		try {

			StringBuilder msg = new StringBuilder();
			//append email header
			msg.append(TextProvider.getText("app.email.header", new String[]{user.getName()}));
			msg.append("\n");
			msg.append("\n");
			//append email body
			msg.append(TextProvider.getText("app.email.forget.password", new String[]{user.getCommunityPassword()}));
			//append email footer
			msg.append("\n");
			msg.append("\n");
			msg.append(TextProvider.getText("app.email.footer"));
			
			String messageId = emailService.sendSimpleEmail(
					user.getCommunityEmail(),
					APP_FORGET_PASSWORD_EMAIL_SUBJECT, msg.toString());
			return StringUtil.isNotEmpty(messageId) ? User.YOUR_PASSWORD_SENT_SUCCESSFULLY : User.YOUR_PASSWORD_SENT_FAILED ;
		} catch (Exception ex) {
			logger.error("000000003", ex.getMessage(), ex);
		}
		return User.YOUR_PASSWORD_SENT_FAILED;
	}
	public String sendPasswordChangeViaEmail(User user) {
		try {

			StringBuilder msg = new StringBuilder();
			//append email header
			msg.append(TextProvider.getText("app.email.header", new String[]{user.getName()}));
			msg.append("\n");
			msg.append("\n");
			//append email body
			msg.append(TextProvider.getText("app.email.password.changed"));
			msg.append("\n");
			msg.append("\n");
			//append email footer
			msg.append(TextProvider.getText("app.email.footer"));
			String messageId = emailService.sendSimpleEmail(
					user.getCommunityEmail(),
					APP_CHANGE_PASSWORD_EMAIL_SUBJECT, msg.toString());
			return StringUtil.isNotEmpty(messageId) ? User.YOUR_PASSWORD_CHANGED_SUCCESSFULLY : User.YOUR_PASSWORD_CHANGED_FAILED ;
		} catch (Exception ex) {
			logger.error("000000003", ex.getMessage(), ex);
		}
		return User.YOUR_PASSWORD_CHANGED_FAILED;
	}
}
