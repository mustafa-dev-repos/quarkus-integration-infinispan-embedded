package io.quarkus.json.adapter;

import javax.json.Json;
import javax.json.JsonValue;
import javax.json.bind.adapter.JsonbAdapter;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeAdapter implements JsonbAdapter<LocalDateTime, JsonValue> {

	@Override
	public JsonValue adaptToJson(LocalDateTime obj) throws Exception {
		return Json.createValue(obj.toString("yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public LocalDateTime adaptFromJson(JsonValue obj) throws Exception {
		String strDateTime = obj.toString();
		strDateTime = ((strDateTime == null) ? "" : strDateTime).replaceAll("\"", "");
		if (!"".equals(strDateTime)) {
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(strDateTime, formatter);
			return dateTime;
		} else {
			return null;
		}
	}

}
