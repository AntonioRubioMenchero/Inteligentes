package Dominio;



import Utilidad.Util;

public class Problema {
	private EspacioEstados ede;
	private Estado estado_inicial;


	public Problema(String fichero) {
		
		Cubo c=Util.readJson(fichero);
		
		this.ede=new EspacioEstados();
		this.estado_inicial=new Estado(c);	
		
	}
	
	public boolean esObjetivo(Cubo cubo) {
	  return cubo.esCuboPerfecto();
		
	}
	public Estado getEstado_Inicial() {
		
		return estado_inicial;
	}

	public void setEstado_Inicial(Estado estado_inicial) {
		this.estado_inicial = estado_inicial;
	}

	public EspacioEstados getEde() {
		return ede;
	}

	public void setEde(EspacioEstados ede) {
		this.ede = ede;
	}
	
	
}
