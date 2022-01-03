package Dominio;

import java.util.PriorityQueue;

public class Frontera {
	PriorityQueue<NodoArbol> frontera= new PriorityQueue<NodoArbol>();;
	private int contador=0;
	
	public Frontera() {
		
	}
	
	public PriorityQueue<NodoArbol> getFrontera() {
		return frontera;
	}

	public void setFrontera(PriorityQueue<NodoArbol> frontera) {
		this.frontera = frontera;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	public void insertar(NodoArbol nodo){
		frontera.add(nodo);
		contador++;
		
	}
	public NodoArbol eliminar(){
		NodoArbol n=frontera.poll();
		return n;
	}
	public boolean esVacia() {
		return frontera.size()==0;
	}
	
	

}
