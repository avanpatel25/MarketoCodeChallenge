package com.marketo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CodeChallenge {

	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject = JsonConfigReader.readFile(new File("resources/leads.json"));
		List<Leads> leadsList = dedupLeads((JSONArray) jsonObject.get("leads"));
		writeOutput(leadsList);
		jsonObject = JsonConfigReader.readFile(new File("resources/output.json"));
		System.out.println(jsonObject);
	}
	
	private static List<Leads> dedupLeads(JSONArray rawLeads) {
		List<Leads> leads = new ArrayList<Leads>();
		Iterator<JSONObject> iter = rawLeads.iterator();
		while (iter.hasNext()) {
			
			Leads lead = new Leads(iter.next());
			addIfNotDup(leads, lead);
			
		}
		return leads;
	}
	
	private static void writeOutput(List<Leads> leadsList) {
		try {
			JSONObject head = new JSONObject();
			JSONArray leadsArray = new JSONArray();
			for (Leads lead : leadsList) {
				leadsArray.add(lead.json);
			}
			head.put("leads", leadsArray);
			FileWriter writer = new FileWriter(new File("resources/output.json"));
			writer.write(head.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void addIfNotDup(List<Leads> leads,Leads lead) {
		for (Leads mainLead : leads) {
			if (mainLead.id.equalsIgnoreCase(lead.id)
					|| mainLead.email.equalsIgnoreCase(lead.email)) {
				if (mainLead.entryDate.after(lead.entryDate)) {

				} else {
					leads.remove(mainLead);
					leads.add(lead);
					return;
				}
			}
		}
		leads.add(lead);
	}
}
