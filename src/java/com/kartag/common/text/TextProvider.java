package com.kartag.common.text;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kartag.common.config.AppConfig;

public class TextProvider {
	private static Properties resource = null;

	private TextProvider() {

	}

	public static synchronized void init() throws Exception {
		InputStream proprtiesInputStream = TextProvider.class
				.getResourceAsStream("/resource/text-messages/text-messages.properties");
		resource = new Properties();
		resource.load(proprtiesInputStream);
	}

	public static String getText(String key) {
		if (resource != null) {
			return resource.getProperty(key);
		}
		return "";
	}
	public static String getText(String key, Object[] params) {
		String text = getText(key);
		if(!"".equals(text)){
			return MessageFormat.format(text, params);
		}
		return "";
	}
	public static String getText(String key,Object[] paramsNames,  Object[] paramsValues) {
		String text = getText(key);
		if("".equals(text) || (paramsNames.length != paramsValues.length)){
			return "";
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		int index = 0;
		for(final Object name : paramsNames){
			map.put(name, paramsValues[index]);
			index++;
		}
		if(!"".equals(text)){
			return MapFormat.format(text, map);
		}
		return "";
	}
}
