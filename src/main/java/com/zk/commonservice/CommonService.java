package com.zk.commonservice;

import java.util.Properties;

import org.json.JSONObject;

import com.zkfront.Interface.CommonConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommonService implements CommonConstant {

	public StringBuilder commonServiceForList(String subUrl, JSONObject jsonObject, String apiType) {
		
		StringBuilder stringBuffer = new StringBuilder();
		Properties properties = new Properties();
		
		try {
			String resourceName = "zkapplication.properties"; // could also be a constant
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
				properties.load(resourceStream);
			} catch (Exception e) {
				System.err.println("Property file error occured...");
			}
			
//			properties.load(Files.newBufferedReader(Paths.get("src/main/resources/zkapplication.properties")));
			String baseUrl = properties.getProperty("baseurl").trim();
			subUrl = subUrl.trim();
			URL url = new URL(baseUrl + subUrl);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			if (POST.equals(apiType)) {
				connection.setRequestMethod(POST);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
				outputStreamWriter.write(jsonObject.toString());
				outputStreamWriter.flush();
			} else if (GET.equals(apiType)) {
				connection.setRequestMethod(GET);
			}
			
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + "\n");
			}
			bufferedReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer;
	}
	
}
