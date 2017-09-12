package com.amorais.pokemon.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amorais.pokemon.util.EnvProperties;

@Path("/security")
@Component
public class SecurityEndpoint {

	@Autowired
	private EnvProperties envProperties;
		
	@GET
	@Path("/config")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConfig() {	
		Map<String, String> mapConfig = new HashMap<String, String>();
		mapConfig.put("urlServices", envProperties.getUrlServices());
		mapConfig.put("urlPokeapi", envProperties.getUrlPokeapi());
		
		return Response.status(Status.OK).entity(mapConfig).build();
	}

}
