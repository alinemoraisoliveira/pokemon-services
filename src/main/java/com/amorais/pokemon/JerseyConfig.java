package com.amorais.pokemon;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.amorais.pokemon.service.PokemonEndpoint;
import com.amorais.pokemon.service.SecurityEndpoint;
import com.amorais.pokemon.service.TeamEndpoint;

import io.swagger.jaxrs.config.BeanConfig;

@Component
@ApplicationPath("/services")
public class JerseyConfig extends ResourceConfig {
	
	private static final String RESOURCE_PACKAGE = "com.amorais.pokemon";

	public JerseyConfig() {
		register(PokemonEndpoint.class);
		register(SecurityEndpoint.class);
		register(TeamEndpoint.class);

        // Swagger
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	}
	
    @Bean
    public BeanConfig swaggerConfig() {
        final BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setBasePath("/services");
        swaggerConfig.setVersion("1.0.0");
        swaggerConfig.setTitle("Pokemon Services");
        swaggerConfig.setResourcePackage(RESOURCE_PACKAGE);
        swaggerConfig.setScan(true);
        return swaggerConfig;
    }

}
