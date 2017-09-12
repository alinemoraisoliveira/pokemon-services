package com.amorais.pokemon.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PokemonHabilityId implements Serializable {
	
	@Column(name = "POKEMON_ID", nullable = false)
	private Integer pokemonId;

	@Column(name = "HABILITY_ID", nullable = false)
	private Integer habilityId;
	
	
	public PokemonHabilityId() {
		super();
	}

	public PokemonHabilityId(Integer pokemonId, Integer habilityId) {
		super();
		this.pokemonId = pokemonId;
		this.habilityId = habilityId;
	}

	public Integer getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(Integer pokemonId) {
		this.pokemonId = pokemonId;
	}

	public Integer getHabilityId() {
		return habilityId;
	}

	public void setHabilityId(Integer habilityId) {
		this.habilityId = habilityId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((habilityId == null) ? 0 : habilityId.hashCode());
		result = prime * result + ((pokemonId == null) ? 0 : pokemonId.hashCode());
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
		PokemonHabilityId other = (PokemonHabilityId) obj;
		if (habilityId == null) {
			if (other.habilityId != null)
				return false;
		} else if (!habilityId.equals(other.habilityId))
			return false;
		if (pokemonId == null) {
			if (other.pokemonId != null)
				return false;
		} else if (!pokemonId.equals(other.pokemonId))
			return false;
		return true;
	}

}
