package Dominio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;



public class Solucion {
	Hashtable<String,Double> nodoUsados=new Hashtable<String, Double>();
	
	
	public Solucion() {
		
	}
	public boolean Busqueda(Problema problema, int estrategia, int prof_Max, int inc_Prof, boolean realizarPoda) {
		int prof_Act=inc_Prof;
		boolean acabar=false;
		
		while (!acabar && prof_Act <= prof_Max) {
			acabar = busquedaAcotada(problema, estrategia, prof_Max, realizarPoda);
			prof_Act = prof_Act+inc_Prof;
			
		}
		return acabar;
	}
	public ArrayList<NodoArbol> GenerarListaNodos(Frontera front,Problema problema,ArrayList<Sucesor> list_s,NodoArbol nActual,
			int profMax,int estrategia,boolean realizarPoda) {
		
		double funcion=0;
		
		ArrayList<NodoArbol> list_na=new ArrayList<NodoArbol>();
		
		for(int i=0;i<list_s.size();i++) {
			switch (estrategia) {
			case 0:
				funcion=nActual.getProfundidad()+1;
				break;
			case 1:
				funcion=redondeo((double)1/(nActual.getProfundidad()+2));
				break;
			case 2:
				if(nActual.getProfundidad()<= profMax)
					funcion=redondeo((double)1/(nActual.getProfundidad()+2));
				break;
			case 3:
				funcion=redondeo((double)1/(nActual.getProfundidad()+2));
				break;
			case 4:
				funcion=nActual.getCosto() + list_s.get(i).getCoste();
				break;
			case 5:
				funcion=redondeo(calcularHeuristica(list_s.get(i).getEstadoNuevo()));
				break;
			case 6:
				funcion=redondeo(calcularHeuristica(list_s.get(i).getEstadoNuevo())+nActual.getCosto() + list_s.get(i).getCoste());
				break;
				

			default:
				throw new IllegalArgumentException("Unexpected value: " + estrategia);
			}
			//PODA
			if(realizarPoda) {
				if(!nodoUsados.containsKey(list_s.get(i).getEstadoNuevo().md5())) {
					nodoUsados.put(list_s.get(i).getEstadoNuevo().md5(), funcion);
						list_na.add(new NodoArbol(nActual, new Estado(list_s.get(i).getEstadoNuevo()), list_s.get(i).getCoste()+nActual.getCosto(),  list_s.get(i).getMovimiento(), nActual.getProfundidad()+1,estrategia,redondeo(calcularHeuristica(list_s.get(i).getEstadoNuevo())),funcion));
				}else {
					//Profundidad
					if(estrategia==1 || estrategia==2 || estrategia==3) {
						if(nodoUsados.get(list_s.get(i).getEstadoNuevo().md5()) > nActual.getFuncion()) {
							nodoUsados.remove(nActual.getEstado().getID());
							nodoUsados.put(nActual.getEstado().getID(),nActual.getFuncion());
							
						}
						
					}else {
						if(nodoUsados.get(list_s.get(i).getEstadoNuevo().md5())< nActual.getFuncion()) {
							nodoUsados.put(nActual.getEstado().getID(),nActual.getFuncion());
						}
					}
				}
				
				}else {
					list_na.add(new NodoArbol(nActual, new Estado(list_s.get(i).getEstadoNuevo()), list_s.get(i).getCoste()+nActual.getCosto(), 
							list_s.get(i).getMovimiento(), nActual.getProfundidad()+1,nActual.getEstrategia(),redondeo(calcularHeuristica(list_s.get(i).getEstadoNuevo())), funcion));
				}
			
			}
		
		return list_na;
		
	}
	private boolean busquedaAcotada(Problema problema, int estrategia, int profMax, boolean realizarpoda) {
		Frontera front = new Frontera();
		NodoArbol raiz;
		
		if (estrategia == 6 || estrategia == 5) 
			raiz = new NodoArbol(null, problema.getEstado_Inicial(), 0, "None", 0,estrategia,redondeo(calcularHeuristica(problema.getEstado_Inicial().getCubo())), 0.0);
		if (estrategia==1 || estrategia==2|| estrategia==3)
			raiz = new NodoArbol(null, problema.getEstado_Inicial(), 0, "None", 0,estrategia, 0.0,0.0);
		else
		    raiz = new NodoArbol(null, problema.getEstado_Inicial(), 0, "None", 0,estrategia, 0.0,0.0);
		

		
		front.insertar(raiz);
		boolean sol = false;
		NodoArbol nActual = null;

		while (!sol && !front.esVacia() ) {

			nActual = front.eliminar();

			if (problema.esObjetivo(nActual.getEstado().getCubo())) {
				sol = true;
			}
			else if(nActual.getProfundidad()<profMax) {
				ArrayList<Sucesor> ls = problema.getEde().sucesores(nActual.getEstado().getCubo());
				ArrayList<NodoArbol> ln = GenerarListaNodos(front, problema, ls, nActual, profMax, estrategia, realizarpoda);

				
				for (int i = 0; i < ln.size(); i++) {
					
					front.insertar(ln.get(i));
					
				}
				
			}
			
		}
		
		if (sol) {
			System.out.println("Nodos Generados " + front.getContador());
			System.out.println("Profundidad " + nActual.getProfundidad());
			System.out.println("Costo " + nActual.getCosto());	
			ficheroSolucion(nActual, front, estrategia);
		}
		
		return sol;
	}
	public void ficheroSolucion(NodoArbol nodo, Frontera f, int estrategia) {

		ArrayList<NodoArbol> camino = new ArrayList<NodoArbol>();

		while ( nodo != null) {
			camino.add(nodo);
			nodo = nodo.getPadre();
		}

		Collections.reverse(camino);

		try {
			File file = new File("resultados.txt");
			FileWriter fichero = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bufwrite = new BufferedWriter(fichero);

			switch (estrategia) {
			case 0:
				bufwrite.write("Estrategia: Anchura " + "\n");
				break;
			case 1:
				bufwrite.write("Estrategia: Profundidad" + "\n");
				break;
			case 2:
				bufwrite.write("Estrategia: Profundidad Acotada" + "\n");
				break;
			case 3:
				bufwrite.write("Estrategia: Profundidad Iterativa" + "\n");
				break;
			case 4:
				bufwrite.write("Estrategia: Coste Uniforme" + "\n");
				break;
			case 5:
				bufwrite.write("Estrategia: Voraz" + "\n");
				break;
			case 6:
				bufwrite.write("Estrategia: A*" + "\n");
				break;


			}

			bufwrite.write("Nodos Generados: " + f.getContador() + "\n");
			bufwrite.write("Profundidad: " + (camino.get(camino.size() - 1).getProfundidad() ) + "\n");
			bufwrite.write("Coste: " + camino.get(camino.size() - 1).getCosto() + "\n");
			
			
			for (int i=0;i<camino.size();i++) {

				bufwrite.write("["+camino.get(i).getID() + "]["+camino.get(i).getAccion()+"]("+camino.get(i).getEstado().getID()+",c="+camino.get(i).getCosto()+",p="+camino.get(i).getProfundidad()+",h="+camino.get(i).getHeuristica()+"v="+redondeo(camino.get(i).getFuncion())+")");
				bufwrite.newLine();
			}

			bufwrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public double calcularEntropiaCara(Byte [][] cara) {
	    double entropia = 0;
	    double n = cara.length;
	    int contador[] = new int[6];
	    
	    for (int i = 0; i < cara.length; i++) {
	      for (int j = 0; j < cara[i].length; j++) {
	        
	        contador[cara[i][j]] +=1;
	      }
	    }
	    
	    for (int i = 0; i < contador.length; i++) {
	      if(contador[i]>0) {
	    
	        entropia +=contador[i]/(n*n)* Math.log((double) contador[i]/(n*n))/Math.log(6);

	      }
	    }
	    
	    if(Double.isNaN(entropia)) {
	      System.out.println(entropia);
	    }
	    
	    return Math.abs(entropia);
	  }
	public double calcularHeuristica(Cubo o) {
		double heuristica=0.0;
		heuristica+=calcularEntropiaCara(o.getBack());
		heuristica+=calcularEntropiaCara(o.getDown());
		heuristica+=calcularEntropiaCara(o.getFront());
		heuristica+=calcularEntropiaCara(o.getLeft());
		heuristica+=calcularEntropiaCara(o.getRight());
		heuristica+=calcularEntropiaCara(o.getUp());
		
		return heuristica;
	}
	public double redondeo(double numero) {
		
		return Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2);
	}
	
}
			
			
		
		
				
				
				

