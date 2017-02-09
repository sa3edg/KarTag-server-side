package com.kartag.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.kartag.common.dao.jdbc.ConnectionConfig;

public class FileUtil {
	
	public static ConnectionConfig parseConnConfig(String fileName) throws Exception
	{
		ConnectionConfig config = new ConnectionConfig();
		InputStream xml = FileUtil.class.getResourceAsStream(fileName);
		SAXReader reader = new SAXReader();
		
		reader.setEntityResolver(new SimpleEntityResolver());
		Document doc = reader.read(xml);
		Element root = doc.getRootElement();
	    if(root == null)
	    	throw new DocumentException();
	    
	    Node url = root.selectSingleNode("/hibernate-configuration/session-factory/property[@name='connection.url']/text()");
	    Node driver = root.selectSingleNode("/hibernate-configuration/session-factory/property[@name='connection.driver_class']/text()");
	    Node username = root.selectSingleNode("/hibernate-configuration/session-factory/property[@name='connection.username']/text()");
	    Node password = root.selectSingleNode("/hibernate-configuration/session-factory/property[@name='connection.password']/text()");
	   
	    config.setUrl(url.getText());
	    config.setDriverName(driver.getText());
	    config.setUserName(username.getText());
	    config.setPassword(password.getText());
		return config;
	}
	
	public static File convertInputStreamToFile(InputStream inputStream) throws Exception
	{
		File file = new File("temp.txt");
	    OutputStream outputStream = new FileOutputStream(file);
		IOUtils.copy(inputStream, outputStream);
		outputStream.close();
		file.deleteOnExit();
		return file;
	}
}