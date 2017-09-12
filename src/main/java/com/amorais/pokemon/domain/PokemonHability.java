package com.amorais.pokemon.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "POKEMON_HABILITY")
public class PokemonHability implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PokemonHabilityId id;
	
	public PokemonHabilityId getId() {
		return id;
	}

	public void setId(PokemonHabilityId id) {
		this.id = id;
	}

}
