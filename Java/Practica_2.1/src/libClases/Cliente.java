package libClases;

public class Cliente implements Cloneable, Proceso{

	private static int contador = 1;
	private static final Fecha POR_DEFECTO = new Fecha(1,1,2018);
	
	private final String nif;
	private final int codCliente;
	private String nombre;
	private final Fecha fechaNac;
	private final Fecha fechaAlta;
	
	public Cliente(String dni, String nom, Fecha fNac, Fecha fAlt) {	
		this.nif = dni;
		this.nombre = nom;
		//Esto se escribe así para que el atributo fNac, tenga los mismos atributos
		//que la clase Fecha.
		//Si la clase no fuera mutable, seria:
		//this.fechaNac = f;
		this.fechaNac = (Fecha)fNac.clone();
		this.fechaAlta = (Fecha)fAlt.clone();
		this.codCliente = contador++;
	}
	
	public Cliente(String dni, String nom, Fecha fNac) {
		this.nif = dni;
		this.nombre = nom;
		this.fechaNac = (Fecha)fNac.clone();
		this.fechaAlta = (Fecha)POR_DEFECTO.clone();
		codCliente = contador++;
	}
	
	//Constructor de copia
	public Cliente (Cliente c) {
		this(c.nif, c.nombre, (Fecha)c.fechaNac.clone(), (Fecha)c.fechaAlta.clone());
	}

	public static Fecha getFechaPorDefecto() {
		return (Fecha)POR_DEFECTO.clone();
	}
	public static void setFechaPorDefecto(Fecha f) {
		POR_DEFECTO.setFecha(f.getDia(), f.getMes(), f.getAnio());
	}
	
	public Fecha getFechaNac() {
		return new Fecha(this.fechaNac.getDia(), this.fechaNac.getMes(), this.fechaNac.getAnio());
	}
	public void setFechaNac(Fecha fNac) {
		fechaNac.setFecha(fNac.getDia(), fNac.getMes(), fNac.getAnio());
	}
	
	public Fecha getFechaAlta() {
		return new Fecha(this.fechaAlta.getDia(), this.fechaAlta.getMes(), this.fechaAlta.getAnio());
	}
	public void setFechaAlta(Fecha fAlt) {
		fechaAlta.setFecha(fAlt.getDia(), fAlt.getMes(), fAlt.getAnio());
	}
	
	public int getCodClientes() {return this.codCliente;}
	
	public String getNombre() {return this.nombre;}
	public void setNombre(String nom) {
		this.nombre = nom;
	}
	
	public String getNif() {return this.nif;}
	
	@Override
	public void ver() {
		System.out.println(this);
	}
	
	public String toString() {
		return nif + " " + fechaNac + ": " + nombre + " (" + codCliente + " - " + fechaAlta + ")";
	}
	
	public Object clone() {
		return (Object) new Cliente(nif, nombre, fechaNac, fechaAlta);
	}
	
	public boolean equals(Object o) {
		return o instanceof Cliente && nif.equals(((Cliente)o).nif);
	}
}
