package Dominio;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Util {
	//ME quedo por aqui 
	public static void CrearCubo() {
		JSONObject objeto=ReadJson();
		Object[] values =objeto.values().toArray();
		String finalString = "";
		for (int i = 0; i< values.length - 1; i++) 
		{
		
			finalString += values[i].toString().replaceAll("[^\\dA-Za-z]", "");
		}
		
		System.out.println(finalString);
		
		
		JSONArray msg = (JSONArray) objeto.get("DOWN");
        Object[] iterator = msg.toArray();
        for (int i = 0; i< iterator.length - 1; i++); 
        
		
	}
			
		
	
	public static JSONObject ReadJson()  {
		JSONParser parser=new JSONParser();
		JSONObject jsonobj=null;
		
		
		try(Reader reader=new FileReader("Cubo.json")){
		jsonobj=(JSONObject) parser.parse(reader);
		// System.out.println(jsonobj);
		
		}
		
		catch (IOException  e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
            e.printStackTrace();
        }
		
		return jsonobj;
	}
	public static void main(String [] args) {
		CrearCubo();
		
	}
	
	
	
	

}
