package io.quarkus.test;

import javax.json.bind.annotation.JsonbTypeAdapter;

import org.joda.time.LocalDateTime;

import io.quarkus.json.adapter.DateTimeAdapter;

public class TestModel {
	
	@JsonbTypeAdapter(DateTimeAdapter.class)
	private LocalDateTime date;
	
	private int deger;

	public int getDeger() {
		return deger;
	}

	public void setDeger(int deger) {
		this.deger = deger;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
