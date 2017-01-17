package com.marketo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonConfigReader {
	
	public static JSONObject readFile(File input) {
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) JSONValue.parse(new FileReader(input));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return (JSONObject) jsonObj;
	}
}
