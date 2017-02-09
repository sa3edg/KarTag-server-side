package com.kartag.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SimpleEntityResolver implements EntityResolver
{

	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		InputSource source = null;  
		   
		if (publicId.equals("-//Hibernate/Hibernate Configuration DTD 3.0//EN") || 
				systemId.equals("http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd")) {  
		    InputStream in = SimpleEntityResolver.class.getResourceAsStream("/config/hibernate-configuration-3.0.dtd");
			source =  new InputSource(in);  
		}   
		//we want to return null to signal default behavior  
		return source; 
	}
	
}