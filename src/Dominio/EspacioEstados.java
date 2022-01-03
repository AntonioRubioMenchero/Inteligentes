package Dominio;

import java.util.ArrayList;

public class EspacioEstados {
	
	public ArrayList<Sucesor> sucesores(Cubo c){
		
		ArrayList<Sucesor> s = new ArrayList<Sucesor>();
		
		for (int i = 0;  i<c.posiblesMovimientos().size(); i++) {
			Cubo clon=c.clonarCubo();
			clon.movimiento(c.posiblesMovimientos().get(i));
			
			s.add(new Sucesor(c.posiblesMovimientos().get(i),clon, 1));
			
			
			
		}
		return s;
	}

	



}
