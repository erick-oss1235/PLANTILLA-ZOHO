package com.example.plantilla.app.configuration;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zoho.crm.library.setup.restclient.ZCRMRestClient;

@Component
public class ConfigurationComponent {
	private static final Logger log = LoggerFactory.getLogger(ConfigurationComponent.class);
	
	
	
	public void configurationZoho() {
		HashMap<String, String> zcrmConfigurations = new HashMap<String, String>();
		zcrmConfigurations.put("minLogLevel", "OFF");
		zcrmConfigurations.put("currentUserEmail", "lyanamango@m4g.com.pe");//
		zcrmConfigurations.put("client_id", "1000.5BE0D6AOXEDCW7AWRIMYTE2ONGT0LS");//
		zcrmConfigurations.put("client_secret", "9789af9b5cd0e077a21b9b2441b522403e1c4ac9e2");//
		zcrmConfigurations.put("redirect_uri", "https://www.google.com/response");
		zcrmConfigurations.put("persistence_handler_class", "com.zoho.oauth.clientapp.ZohoOAuthFilePersistence");
		zcrmConfigurations.put("oauth_tokens_file_path","C:\\Users\\pc\\Documents\\m4g-projects\\plantilla-zoho\\src\\main\\java\\path_to_tokens\\token_demo.properties");//
//		zcrmConfigurations.put("oauth_tokens_file_path", "/opt/iksa/tokenIksa.properties");// optional
		zcrmConfigurations.put("domainSuffix", "com");// optional. Default is com. "cn" and "eu" supported
		zcrmConfigurations.put("accessType", "Production");// Production->www(default), Development->developer,// Sandbox->sandbox(optional)
//		zcrmConfigurations.put("accessType", "Sandbox");// Production->www(default), Development->developer,// Sandbox->sandbox(optional)
		 zcrmConfigurations.put("scope","ZohoCRM.modules.ALL,ZohoCRM.settings.ALL,Aaaserver.profile.read");
		try {
			ZCRMRestClient.initialize(zcrmConfigurations);
		} catch (Exception e) {
			log.info(e.getMessage().concat(" : ").concat(e.getCause().getMessage()));
		}
	}
}
