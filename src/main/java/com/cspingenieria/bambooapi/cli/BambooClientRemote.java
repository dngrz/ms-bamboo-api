package com.cspingenieria.bambooapi.cli;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * 
 * @author clodoaldosanchez
 *
 */
public class BambooClientRemote {
	
	private String rutaBambooBase;
	
	private BambooCallClient BambooCallClient;
	
	ObjectMapper objectMapper = Jackson.newObjectMapper();

	public BambooClientRemote(String rutaBambooBase) {
		this.rutaBambooBase = rutaBambooBase;
		Retrofit retrofit = this.obtenerRetrofit(rutaBambooBase);
		this.BambooCallClient = retrofit.create(BambooCallClient.class);
	}
	
	private Retrofit obtenerRetrofit(final String baseUri) {
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).build();
		return new Retrofit.Builder().baseUrl(baseUri).client(okHttpClient).addConverterFactory(JacksonConverterFactory.create(objectMapper)).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
	}
	
	public ResponseBody getBambooResources() throws IOException {
		Call<ResponseBody> callResumen = BambooCallClient.getBambooResources();
		return getString(callResumen);
	}

	private ResponseBody getString(Call<ResponseBody> callResumen) {
		ResponseBody resultado = null;
		try {
			Response<ResponseBody> response = callResumen.execute();
			if (response.isSuccessful()) {
				resultado = response.body();
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultado = null;
		}
		return resultado;
	}

}
