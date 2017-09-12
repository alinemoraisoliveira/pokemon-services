package com.amorais.pokemon.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TEAM_POKEMON")
public class TeamPokemon implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TeamPokemonId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_ID", referencedColumnName="ID", nullable=true, insertable = false, updatable = false)
	private Team team;

	public TeamPokemonId getId() {
		return id;
	}

	public void setId(TeamPokemonId id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
