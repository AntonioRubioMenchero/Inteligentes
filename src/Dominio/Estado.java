package Dominio;

public class Estado {
	private Cubo Cubo;
	private String ID;
	
	public Estado(Cubo Cubo) {
		this.Cubo = Cubo;
		this.ID=Cubo.md5();
	}

	public Cubo getCubo() {
		return Cubo;
	}

	public void setCubo(Cubo cubo) {
		Cubo = cubo;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "Estado [" + Cubo.md5() + "]";
	}
	
}
	
	
	
	