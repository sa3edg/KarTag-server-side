package com.kartag.common.config;

public final class Constants {
	public static String APP_KEY_ANDROID = AppConfig.config.getString("app.andriod.key");
	public static String APP_KEY_IPHONE = AppConfig.config.getString("app.iphone.key");
	public static long NOTIFICATION_SERVICE_DELAY_IN_MINUTES = AppConfig.config.getLong("app.notification.delay.in.minutes");
	public static long NOTIFICATION_SERVICE_INTERVAL_IN_MINUTES = AppConfig.config.getLong("app.notification.interval.in.minutes");
	public static String GOOGLE_API_KEY = AppConfig.config.getString("app.google.api.key");
	public static String APPLE_KEY_STORE = AppConfig.config.getString("app.apple.key.store");
	public static String APPLE_PASSWORD = AppConfig.config.getString("app.apple.password");
	public static boolean USE_APPLE_PRODUCTION_SERVERS = AppConfig.config.getBoolean("app.apple.use.production");
	public static boolean IS_PUSH_NOTIFICATION_ENABLED = AppConfig.config.getBoolean("app.notification.enabled");
	public static boolean IS_PUSH_NOTIFICATION_ENABLED_FOR_ANDROID = AppConfig.config.getBoolean("app.notification.android.enabled");
	public static boolean IS_PUSH_NOTIFICATION_ENABLED_FOR_APPLE = AppConfig.config.getBoolean("app.notification.apple.enabled");
	public static String APP_EMAIL_NAME = AppConfig.config.getString("app.email.name");
	public static String APP_PASSWORD_EMAIL = AppConfig.config.getString("app.email.passowrdEmail");
	public static String APP_PASSWORD_EMAIL_SUBJECT = AppConfig.config.getString("app.email.passowrdEmail.subject");
	public static String APP_FORGET_PASSWORD_EMAIL_SUBJECT = AppConfig.config.getString("app.email.forgetPassowrdEmail.subject");
	public static String APP_CHANGE_PASSWORD_EMAIL_SUBJECT = AppConfig.config.getString("app.email.changePassowrdEmail.subject");
	public static String APP_EMAIL_HOST_NAME = AppConfig.config.getString("app.email.hostname");
	public static int APP_EMAIL_SMTP_PORT_NUMBER = AppConfig.config.getInt("app.email.port.number", 465);
	public static boolean APP_COMMUNITY_VALIDATION_ENABLED = AppConfig.config.getBoolean("app.community.validation.enabled", false);
}