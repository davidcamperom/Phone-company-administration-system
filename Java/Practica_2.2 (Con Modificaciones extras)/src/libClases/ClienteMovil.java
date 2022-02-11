package libClases;

public class ClienteMovil extends Cliente{

	private Fecha fechaPermanencia;
	private float minutosHablados, precioMinuto;
	
	//Constructor de un ClienteMovil que indique su permanencia
	public ClienteMovil(String dni, String nom, Fecha fNac, Fecha fAlt, Fecha fPer, float minutosH, float precioM) {
		//Como ClienteMovil es hija de Cliente, usamos el constructor de Cliente y luego ayadimos el
		//resto de datos que no tiene el Constructor de la clase madre.
		super(dni, nom, fNac, fAlt);
		this.fechaPermanencia = new Fecha(fPer.getDia(), fPer.getMes(), fPer.getAnio());
		this.minutosHablados = minutosH;
		this.precioMinuto = precioM;
	}
	
	//Constructor de un ClienteMovil que no indique permanencia.
	//Será de 1 anio de permanencia de forma automatica
	public ClienteMovil(String dni, String nom, Fecha fNac, float minutosH, float precioM) {
		//Como ClienteMovil es hija de Cliente, usamos el constructor de Cliente y luego ayadimos el
		//resto de datos que no tiene el Constructor de la clase madre.
		super(dni, nom, fNac);
		Fecha f = new Fecha(this.getFechaAlta());
		this.fechaPermanencia = new Fecha(f.getDia(), f.getMes(), f.getAnio()+1);
		this.minutosHablados = minutosH;
		this.precioMinuto = precioM;
	}
	
	public ClienteMovil(ClienteMovil c) {
		super(c);
		this.fechaPermanencia = (Fecha)fechaPermanencia.clone();
		this.minutosHablados = c.minutosHablados;
		this.precioMinuto = c.precioMinuto;
	}
	
	public Fecha getFPermanencia() {
		return (Fecha)fechaPermanencia.clone();
	}
	
	public void setFPermanencia(Fecha f1) {
		fechaPermanencia = (Fecha)f1.clone();
	}
	
	public float factura() {
		return precioMinuto * minutosHablados;
	}
	
	public float getMinutos() {return minutosHablados;}
	
	public float getPrecioMinuto() {return precioMinuto;}
	
	public String toString(){
		//El super.toString(), muestra la info del constructor de Cliente y su representacion del toString().
		//Luego, ayadimos el resto que tiene que tener la visualizacion de ClienteMovil
		return super.toString() + " " + fechaPermanencia + " " + minutosHablados + " x " + precioMinuto + " --> " + factura();
	}
	
	public Object clone(){
		
		return new ClienteMovil(getNif(), getNombre(), getFechaNac(), getFechaAlta(), getFPermanencia(), getMinutos(), getPrecioMinuto());
	}
	
	public boolean equals(Object c){
		
		return c instanceof ClienteMovil && getNif().equals(((Cliente)c).getNif());
	}

	//Implementación Practica 2.2
	public void setPrecioMinuto(float prec) {
		this.precioMinuto = prec;
	}
}
