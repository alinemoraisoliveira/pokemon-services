package com.amorais.pokemon.util;

import org.springframework.stereotype.Component;

@Component
public class EnvProperties {

	private static final String PROPERTY_URL_SERVICES = "URL_SERVICES";
	private static final String PROPERTY_URL_POKEAPI = "URL_POKEAPI";
	private static final String PROPERTY_URL_POKEAPI_V1 = "URL_POKEAPI_V1";
	
	private String urlServices;
	private String urlPokeapi;
	private String urlPokeapiV1;
	
	public String getUrlServices() {
		if(urlServices == null) {
			urlServices = System.getenv(PROPERTY_URL_SERVICES);
		}
		
		return urlServices;
	}
	
	public String getUrlPokeapi() {
		if(urlPokeapi == null) {
			urlPokeapi = System.getenv(PROPERTY_URL_POKEAPI);
		}
		
		return urlPokeapi;
	}
	
	public String getUrlPokeapiV1() {
		if(urlPokeapiV1 == null) {
			urlPokeapiV1 = System.getenv(PROPERTY_URL_POKEAPI_V1);
		}
		
		return urlPokeapiV1;
	}

}
