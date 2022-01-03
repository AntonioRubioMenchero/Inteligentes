package Utilidad;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;

import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Dominio.Cubo;


public class Util {
	public static Cubo readJson(String fichero)  {
		JSONParser parser=new JSONParser();
		
		try(Reader reader=new FileReader(fichero)){
		Object objeto=(JSONObject) parser.parse(reader);
		JSONObject objetojson=(JSONObject) objeto;
		
		JSONArray back=(JSONArray) objetojson.get("BACK");
		JSONArray down=(JSONArray) objetojson.get("DOWN");
		JSONArray front=(JSONArray) objetojson.get("FRONT");
		JSONArray left=(JSONArray) objetojson.get("LEFT");
		JSONArray right=(JSONArray) objetojson.get("RIGHT");
		JSONArray up=(JSONArray) objetojson.get("UP");
		
		
		Cubo c=new Cubo(PasaraByte(convertirVector(back.toString().replaceAll("[^\\dA-Za-z]","").split(""))),
				PasaraByte(convertirVector(down.toString().replaceAll("[^\\dA-Za-z]","").split(""))),
				PasaraByte(convertirVector(front.toString().replaceAll("[^\\dA-Za-z]","").split(""))),
				PasaraByte(convertirVector(left.toString().replaceAll("[^\\dA-Za-z]","").split(""))),
				PasaraByte(convertirVector(right.toString().replaceAll("[^\\dA-Za-z]","").split(""))),
				PasaraByte(convertirVector(up.toString().replaceAll("[^\\dA-Za-z]","").split("")))
				);
		
		return c;
		}
		catch (IOException  e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
            e.printStackTrace();
        }
		return null;
		
	}
	public static String convertirMD5(String source) {
		   String md5 = null;
		   try {
		         MessageDigest mdEnc = MessageDigest.getInstance("MD5"); //Encryption algorithm
		         mdEnc.update(source.getBytes(), 0, source.length());
		         md5 = new BigInteger(1, mdEnc.digest()).toString(16); //Encrypted string
		        } 
		    catch (Exception ex) {
		         return null;
		    }
		    return md5;
		}
	public static String [][] convertirVector(String[] vector){
		List<String> lista=new ArrayList<String>();
		
		for(int i=0;i<vector.length;i++)
			lista.add(vector[i]);
		
		String [][] matrix=new String[(int)Math.sqrt(vector.length)][(int)Math.sqrt(vector.length)];
		
			for(int i=0;i<matrix.length;i++) {
				for(int j=0;j<matrix.length;j++) {
					matrix[i][j]=lista.get(0);
					lista.remove(0);
				}
			}
		return matrix;
	}
	public static void imprimirMatriz(Byte [][] matrix) {
		
		for(int i=0;i<matrix.length;i++) {
		for(int j=0;j<matrix.length;j++) {
			System.out.print(matrix[i][j] + " ");
		}
		System.out.println();
	}
		System.out.println("---");
		
	}
	public static String crearCadena(Byte [][] matrix) {
		String cadena="";
		for(int i=0;i<matrix.length;i++) {
		for(int j=0;j<matrix.length;j++) {
			cadena+=matrix[i][j];
		}
		
	}
		return cadena;
			
	}
	
	public static Byte [] cogerL(Byte [][] matrix,int x) {
		Byte[] vector=new Byte [matrix.length];
		for(int i=0;i<matrix.length;i++) {
			vector[i]=matrix[i][x];
		}
		return vector;
	}
	public static Byte [][] escribirL(Byte [][] matrix,Byte [] vector,int x){
		for(int i=0;i<vector.length;i++) 
			matrix[i][x]=vector[i];
		
		return matrix;
	}
	public static Byte[][] escribirB(Byte [][] matrix,Byte [] vector,int x){
		for(int i=0;i<vector.length;i++) 
			matrix[x][i]=vector[i];
		
		return matrix;
	}
	public static Byte[] cogerB(Byte[][] matrix,int x) {
		Byte[] vector=new Byte [matrix.length];
		for(int i=0;i<matrix.length;i++) {
			vector[i]=matrix[x][i];
		}
		return vector;
		
	}
	public static Byte[][] girar(Byte[][] matrix){
		Byte [][] resultado=new Byte [matrix.length][matrix.length];
		int x= matrix.length-1;
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix.length;j++) {
			escribirL(resultado, cogerB(matrix, i),x);	
			}
			x--;
		}	
		return resultado;
	}
	
	public static Byte[][] girarNegativo(Byte[][] matrix){
		Byte [][] resultado=new Byte [matrix.length][matrix.length];
		int x=matrix.length-1;
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix.length;j++) {
			escribirB(resultado, cogerL(matrix, i),x);	
			}
			x--;
		}	
		return resultado;
	}
	public static Byte[] vectorInverso(Byte [] vector) {
		Byte [] inverso=new Byte [vector.length];
		int j=0;
		for(int i=vector.length-1;i>=0;i--) {
			inverso[j]=vector[i];
					j++;
		}
		return inverso;
	}
	
	public static int espejo(Byte [] [] matrix,int x) {
		int resultado;
		resultado=(matrix.length-1)-x;
		return resultado;
		
	}
	public static Byte [][] PasaraByte(String [][] cadena) {
		Byte [] []cadenabyte=new Byte [cadena.length][cadena.length];
		for(int i=0;i<cadena.length;i++)
			for(int j=0;j<cadena.length;j++)
			cadenabyte[i][j]=Byte.parseByte(cadena[i][j]);
				
		return cadenabyte;
	}
//	public double calcularEntropiaCara(byte [][] cara) {
//	    double entropia = 0;
//	    double n = cara.length;
//	    int contador[] = new int[6];
//	    
//	    for (int i = 0; i < cara.length; i++) {
//	      for (int j = 0; j < cara[i].length; j++) {
//	        
//	        contador[cara[i][j]] +=1;
//	      }
//	    }
//	    
//	    for (int i = 0; i < contador.length; i++) {
//	      if(contador[i]>0) {
//	    
//	        entropia +=contador[i]/(n*n)* Math.log((double) contador[i]/(n*n))/Math.log(6);
//
//	      }
//	    }
//	    
//	    if(Double.isNaN(entropia)) {
//	      System.out.println(entropia);
//	    }
//	    
//	    return Math.abs(entropia);
//	  }
		
	
	
	
	
}
