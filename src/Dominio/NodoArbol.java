package Dominio;

public class NodoArbol implements Comparable<NodoArbol>{
	private static int contador;
	private NodoArbol padre;
	private Estado estado;
	private int costo;
	private int ID;
	private String accion;
	private int profundidad;
	private int estrategia;
	private double heuristica;
	private double funcion;
	
	
	
	public NodoArbol(NodoArbol padre, Estado estado, int costo, String accion, int profundidad,int estrategia,
			double heuristica,double funcion) {
		super();
		
		this.padre = padre;
		this.estado = estado;
		this.costo = costo;
		this.ID=contador++;
		this.accion = accion;
		this.heuristica=heuristica;
		this.profundidad = profundidad;
		this.estrategia=estrategia;
		this.funcion = funcion;
	}
	public NodoArbol getPadre() {
		return padre;
	}
	public void setPadre(NodoArbol padre) {
		this.padre = padre;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public int getProfundidad() {
		return profundidad;
	}
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	public int getEstrategia() {
		return estrategia;
	}
	public void setEstrategia(int estrategia) {
		this.estrategia = estrategia;
	}
	public double getFuncion() {
		return funcion;
	}
	public void setFuncion(double funcion) {
		this.funcion = funcion;
	}
	public double getHeuristica() {
		return heuristica;
	}
	public void setHeuristica(double heuristica) {
		this.heuristica = heuristica;
	}
	
	 public int compareTo(NodoArbol o) {
		 int aux = 0;
		    if(estrategia==0 || estrategia==4 || estrategia==5 || estrategia==6) {
		      
		      if(o.getFuncion() < this.getFuncion()) {
		        aux = 1;
		      }else {
		        if(o.getFuncion() > this.getFuncion()) {
		          aux = -1;
		        }else if(o.getID() < this.getID()) {
		          aux=1;
		        }else {
		          aux = -1;
		        }
		      }
		      
		    }else {
		      
		      if(o.getFuncion() < this.getFuncion()) {
		        aux = -1;
		      }else {
		        if(o.getFuncion() > this.getFuncion()) {
		          aux = 1;
		        }else if(o.getID() < this.getID()) {
		          aux=1;
		        }else {
		          aux = -1;
		        }
		      }
		    }
		    
		    return aux;
		  
		 
	 }
		    
		    

	@Override
	public String toString() {
		return "NodoArbol [padre=" + padre + ", estado=" + estado.toString() + ", costo=" + costo + ", ID=" + ID + ", accion="
				+ accion + ", profundidad=" + profundidad + ", funcion=" + funcion + "]";
	}
	
	
	
	
	
	
	
	

}