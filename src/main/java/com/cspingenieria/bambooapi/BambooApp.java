package com.cspingenieria.bambooapi;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.cspingenieria.bambooapi.cli.BambooClientRemote;
import com.cspingenieria.bambooapi.resources.BambooResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * 
 * @author clodoaldosanchez
 *
 */
public class BambooApp extends Application<BambooConfiguration> {

	public static void main(final String[] args) throws Exception {
		new BambooApp().run(args);
	}

	@Override
	public String getName() {
		return "BambooApp";
	}

	@Override
	public void initialize(final Bootstrap<BambooConfiguration> bootstrap) {
		// TODO: application initialization
	}

	@Override
	public void run(BambooConfiguration configuration, Environment environment) {
		final BambooClientRemote bambooClientRemote = new BambooClientRemote(configuration.getRutaBambooBase());
		final BambooResource bambooResource = new BambooResource(bambooClientRemote);
		environment.jersey().register(bambooResource);
		habilitarCORS(environment);
	}

	private void habilitarCORS(Environment environment) {
		// Enable CORS headers
		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		// Configure CORS parameters
		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		// Add URL mapping
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}

}
