package libClases;

public class ClienteTarifaPlana extends Cliente {

	private static float tarifa = 20f;
	private static float precioExcesoMinutos = 0.15f;
	private static float limiteMinutos = 300;
	
	private float minutosHablados;
	private String Nacionalidad;
	
	public ClienteTarifaPlana(String dni, String nom, Fecha fNac, Fecha fAlt, float minutosH, String nacion) {
		//Como ClienteTarifaPlana es hija de Cliente, usamos el constructor de Cliente y luego ayadimos el
		//resto de datos que no tiene el Constructor de la clase madre.
		super(dni, nom, fNac, fAlt);
		this.minutosHablados = minutosH;
		this.Nacionalidad = nacion;
	}
	
	public ClienteTarifaPlana(String dni, String nom, Fecha fNac, float minutosH, String nacion) {
		//Como ClienteTarifaPlana es hija de Cliente, usamos el constructor de Cliente y luego ayadimos el
		//resto de datos que no tiene el Constructor de la clase madre.
		super(dni, nom, fNac);
		this.minutosHablados = minutosH;
		this.Nacionalidad = nacion;
	}
	
	//Setter y getter de minutosHablados
	public float getMinutos() {return this.minutosHablados;}
	public void setMinutos(float minH) {
		this.minutosHablados = minH;
	}
	
	public String getNacionalidad() {return this.Nacionalidad;}
	public void setNacionalidad(String nac) {
		this.Nacionalidad = nac;
	}
	
	//Cuando queramos un dato tipo static, hay que devolverlo con la llamada a la clase. 
	public static float getLimite() {return ClienteTarifaPlana.limiteMinutos;}
	public static float getTarifa() {return ClienteTarifaPlana.tarifa;}
	//Aquí, al ser un método static, hay que hacer lo mismo para establecer un dato.
	public static void setTarifa(float limiteMin, float tarifaPrecio) {
		ClienteTarifaPlana.limiteMinutos = limiteMin;
		ClienteTarifaPlana.tarifa = tarifaPrecio;
	}
	
	public float factura() {
		float fact;
		if(minutosHablados > limiteMinutos)
			fact = tarifa + ((minutosHablados - limiteMinutos) * precioExcesoMinutos);
		else
			fact = tarifa;
		
		return fact;
	}
	
	public String toString() {
		return super.toString() + " " + Nacionalidad + " [" + limiteMinutos + " por " + tarifa + "] " + minutosHablados + " --> " + factura();
	}
	
	public Object clone() {
		return new ClienteTarifaPlana(getNif(), getNombre(), getFechaNac(), getFechaAlta(), getMinutos(), getNacionalidad());
	}
	
	public boolean equals(Object o) {
		return o instanceof ClienteTarifaPlana && getNif().equals(((Cliente)o).getNif());
	}
}