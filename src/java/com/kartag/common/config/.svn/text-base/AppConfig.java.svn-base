package com.kartag.common.config;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

import com.kartag.common.util.FileUtil;

public class AppConfig {

    public static PropertiesConfiguration config = null;

	public static synchronized void init() throws Exception{
		InputStream proprtiesInputStream = AppConfig.class
				.getResourceAsStream("/config/ApplicationProperties.properties");
		File proprtiesFile = FileUtil
				.convertInputStreamToFile(proprtiesInputStream);
		config = new PropertiesConfiguration(
				proprtiesFile);
		config.setReloadingStrategy(new FileChangedReloadingStrategy());
	}
}
