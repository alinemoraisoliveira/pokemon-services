package com.amorais.pokemon.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class TeamPokemonId implements Serializable {
	
	@Column(name = "TEAM_ID", nullable = false)
	private Integer teamId;
	
	@Column(name = "POKEMON_ID", nullable = false)
	private Integer pokemonId;

	
	public TeamPokemonId() {
		super();
	}

	public TeamPokemonId(Integer teamId, Integer pokemonId) {
		super();
		this.teamId = teamId;
		this.pokemonId = pokemonId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(Integer pokemonId) {
		this.pokemonId = pokemonId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pokemonId == null) ? 0 : pokemonId.hashCode());
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamPokemonId other = (TeamPokemonId) obj;
		if (pokemonId == null) {
			if (other.pokemonId != null)
				return false;
		} else if (!pokemonId.equals(other.pokemonId))
			return false;
		if (teamId == null) {
			if (other.teamId != null)
				return false;
		} else if (!teamId.equals(other.teamId))
			return false;
		return true;
	}

}
