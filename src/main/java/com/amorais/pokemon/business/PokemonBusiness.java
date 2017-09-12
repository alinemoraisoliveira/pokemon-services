package com.amorais.pokemon.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amorais.pokemon.util.EnvProperties;

@Component
public class PokemonBusiness {

	@Autowired
	private EnvProperties envProperties;
	
	public String find(Integer pokemonId) {

		String str = null;
		
		try {	
			String id = pokemonId != null ? pokemonId.toString() : "";
			HttpURLConnection con = (HttpURLConnection) new URL(envProperties.getUrlPokeapi() + "pokemon/" + id).openConnection();
			
			con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			con.addRequestProperty("Connection", "keep-alive");
			con.addRequestProperty("Referer", "www.google.com");
			
			con.connect();

			if (con.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			str = br.readLine();

			con.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}

}
