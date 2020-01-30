package io.quarkus.test;

import java.util.Locale;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.adapter.JsonbAdapter;

import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TestClazz {

	public static void main(String[] args) {
		
		//new TokenConfiguration.ConfigurationBuilder(null,"token").build().getClient_id();
		
		//TokenConfig.getInstance().getConfig("token").getClient_id();
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime1 = LocalDateTime.parse("2020-01-30 21:00:00", formatter);
		
		LocalDateTime dateTime = new LocalDateTime();
		
		System.out.println("Sonuc : " +  dateTime.toString() );
		
		//dateTime.plusSeconds(1799);
		
		System.out.println("Sonuc : " +  dateTime.plusSeconds(1799).toString() );
		
		
		Duration duration = new Duration(dateTime.toDateTime(), dateTime.plusSeconds(1799).toDateTime());
		
		System.out.println(" duration :" + duration.getMillis());
		
		TestModel testModel = new TestModel();
		testModel.setDate(new LocalDateTime());
		testModel.setDeger(10);
		
		System.out.println("final sonuc :" + JsonbBuilder.create(new JsonbConfig()).toJson(testModel));
		TestModel tst = JsonbBuilder.create(new JsonbConfig()).fromJson(JsonbBuilder.create(new JsonbConfig()).toJson(testModel), TestModel.class);
		System.out.println(tst.getDate().toString());
		
//		TokenResponse tokenResponse = new TokenResponse();
//		tokenResponse.setAccessToken("deneme");
//		tokenResponse.setExpires_in(20000);
//		tokenResponse.setScope("default");
//		tokenResponse.setToken_type("grant_credential");
//		
//		//JsonbBuilder.create().toJson(tokenResponse)
//		System.out.println(JsonbBuilder.create(new JsonbConfig()).toJson(tokenResponse));
//		
//		tokenResponse = JsonbBuilder.create(new JsonbConfig()).fromJson(JsonbBuilder.create().toJson(tokenResponse), TokenResponse.class);
		
	}

}
