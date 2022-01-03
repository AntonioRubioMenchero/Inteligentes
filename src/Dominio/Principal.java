package Dominio;

import java.util.ArrayList;
import java.util.Scanner;

import Utilidad.Util;

public class Principal {
	public static void main(String [] args)  {
		menu();
	}	
	public static void menu() {
		System.out.println("----------------------Rubik's Cube Solver--------------------------------");
		String fichero;
		int estrategia,prof,valorpoda;
		boolean poda=false;
		Scanner sc=new Scanner(System.in);
		System.out.print("Introduzca nombre de archivo \n");
		fichero=sc.nextLine();
		System.out.print("Introduce estrategia seleccionada \n 0.Anchura \n 1.Profundidad \n 2.Profundidad Acotada\n 3.Profundidad Iterativa\n 4.Coste Uniforme \n 5.Voraz\n 6.A*\n");
		estrategia=sc.nextInt();
		System.out.println("Introduce la profundidad maxima");
		prof=sc.nextInt();
		System.out.println("Quieres realizar poda \n 1.Si \n 2.No");
		valorpoda=sc.nextInt();
		if(valorpoda==1)
			poda=true;
		
		System.out.println("Fichero:"+fichero);
		System.out.println("Estrategia:"+estrategia);
		System.out.println("Profundidad:"+prof);
		System.out.println("Poda:"+poda);
		
       Problema p=new Problema(fichero);
	   Solucion s=new Solucion();
	   s.Busqueda(p, estrategia, prof, prof, poda);
	   System.out.println("Se ha escrito en el fichero de resultados.");
		
	
		
		
		
		
	}
}