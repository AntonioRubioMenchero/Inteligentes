package Dominio;

public class Sucesor {
	

		private String movimiento;
		private Cubo estadoNuevo;
		private int coste;
		public Sucesor(String movimiento, Cubo estadoNuevo, int coste) {
			super();
			this.movimiento = movimiento;
			this.estadoNuevo = estadoNuevo;
			this.coste = coste;
		}
		public String getMovimiento() {
			return movimiento;
		}
		public void setMovimiento(String movimiento) {
			this.movimiento = movimiento;
		}
		public Cubo getEstadoNuevo() {
			return estadoNuevo;
		}
		public void setEstadoNuevo(Cubo estadoNuevo) {
			this.estadoNuevo = estadoNuevo;
		}
		public int getCoste() {
			return coste;
		}
		public void setCoste(int coste) {
			this.coste = coste;
		}
		
		
	
		
		
		

}