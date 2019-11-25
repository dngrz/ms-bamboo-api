package com.cspingenieria.bambooapi.cli;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BambooCallClient {
	
	@Headers("Accept: application/json")
	@GET("latest/")
	Call<ResponseBody> getBambooResources();

}
