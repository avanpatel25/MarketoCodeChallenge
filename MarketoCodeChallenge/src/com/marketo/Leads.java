package com.marketo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

public class Leads {

	public final String id;
	public final String email;
	public Date entryDate;
	public final JSONObject json;
	
	public Leads(final JSONObject lead) {
		this.id = lead.get("_id").toString();
		this.email = lead.get("email").toString();
		this.json = lead;
		try {
			// 2014-05-07T17:30:20+00:00
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
			this.entryDate = df.parse(lead.get("entryDate").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
