package Dominio;


import java.util.ArrayList;
import java.util.Vector;

import Utilidad.Util;

public class Cubo  {

	private Byte[][]  back;
	private Byte[][] down;
	private Byte[][] front;
	private Byte[][]  left;
	private Byte[][]  right;
	private Byte[][]   up;

	

	public Cubo(Byte[][] back, Byte[][] down, Byte[][] front, Byte[][] left, Byte[][] right, Byte[][] up) {
		super();
		this.back = back;
		this.down = down;
		this.front = front;
		this.left = left;
		this.right = right;
		this.up = up;
	}

	public Byte[][] getBack() {
		return back;
	}

	public void setBack(Byte[][] back) {
		this.back = back;
	}

	public Byte[][] getDown() {
		return down;
	}

	public void setDown(Byte[][] down) {
		this.down = down;
	}

	public Byte[][] getFront() {
		return front;
	}

	public void setFront(Byte[][] front) {
		this.front = front;
	}

	public Byte[][] getLeft() {
		return left;
	}

	public void setLeft(Byte[][] left) {
		this.left = left;
	}

	public Byte[][] getRight() {
		return right;
	}

	public void setRight(Byte[][] right) {
		this.right = right;
	}

	public Byte[][] getUp() {
		return up;
	}

	public void setUp(Byte[][] up) {
		this.up = up;
	}

	public String cuboString() {
		String cadena="";
		cadena+=Util.crearCadena(back);
		cadena+=Util.crearCadena(down);
		cadena+=Util.crearCadena(front);
		cadena+=Util.crearCadena(left);
		cadena+=Util.crearCadena(right);
		cadena+=Util.crearCadena(up);
		return cadena;

	}
	
	public void imprimirCubo() {
		System.out.println("BACK");
		Util.imprimirMatriz(back);
		
		System.out.println("DOWN");
		Util.imprimirMatriz(down);
		
		System.out.println("FRONT");
		Util.imprimirMatriz(front);
		
		System.out.println("LEFT");
		Util.imprimirMatriz(left);
		
		System.out.println("RIGHT");
		Util.imprimirMatriz(right);
		
		System.out.println("UP");
		Util.imprimirMatriz(up);
		
	}
	
	public void  movimientoL(int x) {
		Byte[] m1=Util.cogerL(front, x);
		Byte[] m2=Util.cogerL(down, x);
		Byte[] m3=Util.cogerL(back, x);
		Byte[] m4=Util.cogerL(up,Util.espejo(up, x) );
		
		if(x==0) 
			setLeft(Util.girar(left));
		else if(x==back.length-1)
			setRight(Util.girar(right));
			
			setDown(Util.escribirL(down, m1,x));
			setBack(Util.escribirL(back, m2, x));
			setUp(Util.escribirL(up, Util.vectorInverso(m3), Util.espejo(up, x)));
			setFront(Util.escribirL(front, Util.vectorInverso(m4), x));
		
	}
	public void movimientol(int x) {
		
		Byte[] m1=Util.cogerL(front, x);
		Byte[] m2=Util.cogerL(up, Util.espejo(up, x));
		Byte[] m3=Util.cogerL(back, x);
		Byte[] m4=Util.cogerL(down, x);
		
		if(x==0) 
			setLeft(Util.girarNegativo(left));
		else if(x==back.length-1)
			setRight(Util.girarNegativo(right));
		
		setUp(Util.escribirL(up,Util.vectorInverso(m1),Util.espejo(up, x)));
		setBack(Util.escribirL(back, Util.vectorInverso(m2), x));
		setDown(Util.escribirL(down, m3, x));
		setFront(Util.escribirL(front, m4,x));
	}
	public void movimientoD(int x) {
		if(x==0)
			setDown(Util.girar(down));
		else if(x==back.length-1)
			setUp(Util.girar(up));
		
		Byte[] m1=Util.cogerB(back, Util.espejo(left, x));
		Byte[] m2=Util.cogerL(right, x);
		Byte[] m3=Util.cogerB(front, x);
		Byte[] m4=Util.cogerL(left, Util.espejo(left, x));
		
		setRight(Util.escribirL(right, m1, x));
		setFront(Util.escribirB(front, Util.vectorInverso(m2),x));
		setLeft(Util.escribirL(left, m3, Util.espejo(back, x)));
		setBack(Util.escribirB(back, Util.vectorInverso(m4), Util.espejo(back, x)));	
	}
	public void movimientod(int x) {
		
		if(x==0)
			setDown(Util.girarNegativo(down));
		else if(x==back.length-1)
			setUp(Util.girarNegativo(up));
		
		Byte[] m1=Util.cogerB(front, x);
		Byte[] m2=Util.cogerL(right, x);
		Byte[] m3=Util.cogerB(back, Util.espejo(back, x));
		Byte[] m4=Util.cogerL(left, Util.espejo(left, x));
		
		
		setRight(Util.escribirL(right,Util.vectorInverso(m1) , x));
		setBack(Util.escribirB(back, m2, Util.espejo(back, x)))	;	
		setLeft(Util.escribirL(left, Util.vectorInverso(m3), Util.espejo(left, x)));
		setFront(Util.escribirB(front, m4, x));
		
		
		
		
	}
	
	public void movimientoB(int x) {
		if(x==0) 
			setBack(Util.girar(back));
		else if(x==back.length-1)
			setFront(Util.girar(front));
		
		Byte[] m1=Util.cogerB(left, x);
		Byte[] m2=Util.cogerB(down, x);
		Byte[] m3=Util.cogerB(right, x);
		Byte[] m4=Util.cogerB(up, x);
		
	
		setDown(Util.escribirB(down, m1, x));
		setRight(Util.escribirB(right, m2, x));
		setUp(Util.escribirB(up,m3, x));
		setLeft(Util.escribirB(left, m4, x));
	}
	
	
	public void movimientob(int x) {
		Byte[] m1=Util.cogerB(up, x);
		Byte[] m2=Util.cogerB(left, x);
		Byte[] m3=Util.cogerB(down, x);
		Byte[] m4=Util.cogerB(right, x);
		
		if(x==0) 
			setBack(Util.girarNegativo(back));
		else if(x==back.length-1)
			setFront(Util.girarNegativo(front));
		
		setRight(Util.escribirB(right, m1,x));
		setUp(Util.escribirB(up, m2, x));
		setLeft(Util.escribirB(left, m3, x));
		setDown(Util.escribirB(down, m4, x));
		
		
	}
	public Cubo clonarCubo() {
		Byte[][]  nback = new Byte [left.length][left.length];
		Byte[][] ndown = new Byte [left.length][left.length];
		Byte[][] nfront= new Byte [left.length][left.length];
		Byte[][]  nleft=new Byte [left.length][left.length];
		Byte[][]  nright=new Byte [left.length][left.length];
		Byte[][]   nup=new Byte [left.length][left.length];
		 
		 int z=0;
		 Byte[][]   aux=new Byte [left.length][left.length];
		 Byte[][] aux2=new Byte [left.length][left.length];
		 
		Vector <Byte [][]> list = new Vector<Byte[][]>();
		Vector <Byte [][]> newlist = new Vector<Byte[][]>();
		list.add(back);
		newlist.add(nback);
		list.add(down);
		newlist.add(ndown);
		list.add(front);
		newlist.add(nfront);
		list.add(left);
		newlist.add(nleft);
		list.add(right);
		newlist.add(nright);
		list.add(up);
		newlist.add(nup);
		
		while(z<newlist.size()) {
			aux=list.get(z);
			aux2=newlist.get(z);
			for(int i=0;i<aux.length;i++)
				for(int j=0;j<aux.length;j++)
					aux2[i][j]=aux[i][j];
					
			z++;
		}
		Cubo cubo=new Cubo(newlist.get(0), newlist.get(1), newlist.get(2), newlist.get(3), newlist.get(4), newlist.get(5))	;	
		
		
		return cubo;	
	}
	public boolean esCuboPerfecto() {
	 Vector<Byte [][]> vector=new Vector<Byte[][]>();
	 vector.add(back);
	 vector.add(down);
	 vector.add(front);
	 vector.add(left);
	 vector.add(right);
	 vector.add(up);
	 Byte [][] aux=new Byte [back.length][back.length];
	 int z=0;
	 while(z<vector.size()) {
	 	aux=vector.elementAt(z);
	 	for(int i=0;i<aux.length;i++)
	 		for(int j=0;j<aux.length;j++)
	 			if(aux[0][0]!=aux[i][j]) 
	 				return false;
	 				
	 	z++;		
	 }
	return true;
	}
	public String md5() {
		String md5 =Util.convertirMD5(cuboString());
		return md5;
	}
	public void movimiento(String orden) {
		String [] vector=orden.split("");
		switch (vector[0]) {
		case "l":
			movimientol(Integer.parseInt(vector[1]));
			break;
		case "L":
			movimientoL(Integer.parseInt(vector[1]));
			break;
		case "b":
			movimientob(Integer.parseInt(vector[1]));
			break;
		case "B":
			movimientoB(Integer.parseInt(vector[1]));
			break;
		case "d":
			movimientod(Integer.parseInt(vector[1]));
			break;
		case "D":
			movimientoD(Integer.parseInt(vector[1]));
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + vector[0]+ ""+ vector[1] );
		
	
		}
			
	}
	public ArrayList<String> posiblesMovimientos(){
		String [] sentidos= {"B","b","D","d","L","l"};
		ArrayList<String> lista=new ArrayList<String>();
		
		for(int i=0;i<sentidos.length;i++) 
			for(int j=0;j<back.length;j++)
				lista.add(sentidos[i]+String.valueOf(j));
		
		return lista;
	}
	
	
   }
	

	
	
	
	
	


