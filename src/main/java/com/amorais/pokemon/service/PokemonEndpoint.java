package com.amorais.pokemon.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amorais.pokemon.business.PokemonBusiness;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/pokemon")
@Api(value = "pokemon")
@Component
public class PokemonEndpoint {

	@Autowired
	private PokemonBusiness pokemonBusiness; 


	@GET
	@ApiOperation(value = "List")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response list() throws JSONException {
		
		String list = pokemonBusiness.find(null);
		JSONObject jsonObj = new JSONObject(list);
		
		String vacantJobId = jsonObj.get("results").toString();
		
		return Response.status(Status.OK.getStatusCode()).entity(vacantJobId).build();			
	}
	
}
