package com.kartag.business.security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;

public class Encryptor {
	private static StandardPBEStringEncryptor  strongEncryptor = new StandardPBEStringEncryptor ();
	private static SimplePBEConfig config = new SimplePBEConfig();
	private static Encryptor self = null;
	private Encryptor(){
		
	}
	public static Encryptor getInstance(){
		if(self ==  null){
			self = new Encryptor();
			config.setPassword("KarTag");
			config.setAlgorithm("PBEWithMD5AndDES");
	        strongEncryptor.setConfig(config);
		}
		return self;
	}
	public String encrypt(String plainText){
		return strongEncryptor.encrypt(plainText);
	}
	public String decrypt(String encryptedText){
		return strongEncryptor.decrypt(encryptedText);
	}

}
