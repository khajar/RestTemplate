package com.example.retstTemplateClientA.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyRequestInterceptor implements ClientHttpRequestInterceptor {

	private Logger log=LoggerFactory.getLogger(MyRequestInterceptor.class);
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
			log.info("Request Details");
			log.info("URI- {}",request.getURI());
			log.info("Header-{}",request.getHeaders());
			log.info("Methods",request.getMethod());
			
			ClientHttpResponse response=execution.execute(request, body);
			MyClientHttpResponse myClientHttpResponse = new MyClientHttpResponse(response);
			
			log.info("Response Details");
			log.info("status - {}", myClientHttpResponse.getStatusCode());
	        log.info("Body - {}", getResponseBody(myClientHttpResponse.getBody()));
		
		return myClientHttpResponse;
	}

	private String getResponseBody(InputStream responseBody)
	{
		StringBuilder inputStringBuilder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseBody, StandardCharsets.UTF_8))) 
		{
			String line= bufferedReader.readLine();
			while(line!=null)
			{
				inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
			}
			 return inputStringBuilder.toString();
			
		}catch(IOException e)
		{
			e.printStackTrace();
            return null;
		}
	}
	
}
