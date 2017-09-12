package com.amorais.pokemon.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.amorais.pokemon.business.TeamBusiness;
import com.amorais.pokemon.domain.Pokemon;
import com.amorais.pokemon.domain.Team;
import com.amorais.pokemon.repository.TeamRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/team")
@Api(value = "team")
@Component
public class TeamEndpoint {
	
	@Autowired
	private TeamBusiness teamBusiness; 
	
	@Autowired
	private TeamRepository teamRepository; 
	
		
	@GET
	@ApiOperation(value = "List")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response list() {
		
		List<Team> list = teamRepository.findByOrderByName();
		
		return Response.status(Status.OK.getStatusCode()).entity(list).build();			
	}
		
	@GET 
	@Path("/{id}")
	@ApiOperation(value = "Find by ID")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public Response get(@ApiParam(value = "ID", required = false) @PathParam("id") Integer id) {
		
		Team team = teamRepository.findOne(id);

		return team != null ? Response.status(Status.OK.getStatusCode()).entity(team).build() : Response.status(Status.NOT_FOUND.getStatusCode()).build();
	}
	
	@POST	
	@Path("/save")
	@ApiOperation(value = "Save")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response save(@ApiParam(value = "Json object to save", required = true) @RequestBody Team team) {

		teamBusiness.save(team);
		
		return Response.status(Status.OK.getStatusCode()).build();
	}
	
	@POST
	@ApiOperation(value = "Delete")
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response delete(@ApiParam(value = "ID of object to delete. Format: {id: number}", required = true)@PathParam("id") Integer id) {	
		
		teamBusiness.delete(id);

		return Response.status(Status.OK.getStatusCode()).build();
	}
	
	@GET
	@Path("/{teamId}/pokemons")
	@ApiOperation(value = "Find pokemons of team by id")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public Response getPokemons(@ApiParam(value = "team Id", required = false) @PathParam("teamId") Integer teamId) throws JSONException {
		
		List<Pokemon> list = teamBusiness.findPokemonByTeamId(teamId);

		return Response.status(Status.OK.getStatusCode()).entity(list).build();
	}
	
}
