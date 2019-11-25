package com.cspingenieria.bambooapi;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class BambooConfiguration extends Configuration {
	
	@NotEmpty
	private String rutaBambooBase;

	public String getRutaBambooBase() {
		return rutaBambooBase;
	}

	public void setRutaBambooBase(String rutaBambooBase) {
		this.rutaBambooBase = rutaBambooBase;
	}
	
}
