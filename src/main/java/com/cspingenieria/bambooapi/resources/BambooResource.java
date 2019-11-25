package com.cspingenieria.bambooapi.resources;

import java.io.IOException;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.cspingenieria.bambooapi.cli.BambooClientRemote;
import com.mongodb.BasicDBObject;

import okhttp3.ResponseBody;

/**
 * 
 * @author clodoaldosanchez
 *
 */
@Path("/rest/api")
@Produces(MediaType.APPLICATION_JSON)
public class BambooResource {

	private BambooClientRemote bambooClientRemote;

	public BambooResource(BambooClientRemote bambooClientRemote) {
		this.bambooClientRemote = bambooClientRemote;
	}

	@GET
	@Timed
	@Path("/bamboo")
	public Response getResources(@QueryParam("parametroOpcional") Optional<String> parametroOpcional) {
		ResponseBody result = null;
		BasicDBObject objeto = null;
		try {
			result = bambooClientRemote.getBambooResources();
			Object o = com.mongodb.util.JSON.parse(result.string());
			objeto = (BasicDBObject) o;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(objeto).build();
	}

}
