package libClases;

import java.util.Scanner;

public class Fecha implements Cloneable, Proceso{
	//Cloneable hace que la clase sea Mutable, es decir,
	//que no tenga clases derivadas pero que tenga metodos
	//setters y getters los cuales permite consultar y establecer

	private int dia, mes, anio;
	
	public int getDia() {return this.dia;}
	public int getMes() {return this.mes;}
	public int getAnio() {return this.anio;}
	
	public Fecha(int dia, int mes, int anio) {
		setFecha(dia, mes, anio);
	}
	
	//Constructor de copia
	public Fecha(Fecha f) {
		dia = f.dia;
		mes = f.mes;
		anio = f.anio;
	}
	
	public void setDia(int d) {this.dia = d;}
	public void setMes(int m) {this.mes = m;}
	public void setAnio(int a) {this.anio = a;}
	
	public boolean bisiesto() {
		if(this.anio % 4 == 0 && (this.anio % 100 != 0 || this.anio % 400 == 0)) 
			return true;
		else
			return false;
	}
	
	public void setFecha(int d, int m, int a) {
		
		//ANIOS
		//Primero, asignamos los anios ya que es lo menos problematico
		this.anio = a;
		
		//MES
		//Seguidamente, asignamos los meses. Aquí, tenemos que evitar que el
		//usuario meta el mes de forma incorrecta
		if(m<1)
			m = 1;
		else if(m>12)
			m = 12;
		this.mes = m;
		
		//DIAS
		//Por ultimo, tenemos que tratar los días. Tendremos que evitar que se introduzcan de manera incorrecta
		//de forma que permita manejar los dias de forma correcta.
		int diaMes[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		if(d<1)
			this.dia = 1;
		else if(d <= 28) //Si hay un ayo que no sea bisiesto, aplicamos los dias correctamente
			this.dia = d;
		else {
			if(this.bisiesto()) //Si el anio es bisiesto, asignamos el 29
				diaMes[2] = 29;
			if(d <= diaMes[m]) //Si tiene el numero maximo correcto del mes, lo aplicamos
				this.dia = d;
			else //Si supera el dia maximo del mes, asignamos el por defecto
				this.dia = diaMes[m];
		}
	}
	
	public String toString(){
		String s = "";
		if(dia<10) {
			s = s + 0; //Le ayadimos el 0 delante al dia
		}
		
		s = s + dia + "/";
		if(mes < 10) {
			s = s + 0; //Le ayadimos el 0 delante al mes
		}
		s = s + mes + "/" + anio;

		return s;
		
	}
	
	public void ver() {
		//Podemos visualizar directamente el objeto de la clase con el this
		System.out.println(this);
	}
	
	public static Fecha pedirFecha() {
		Fecha fecha = null;
		boolean valida = false;
		int dia, mes, anio;
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		
		do {
			System.out.println("Introduce la fecha (dd/mm/aaaa): ");
			
			String fechaTeclado = teclado.next();
			//Aqui se hace la transformacion de la fecha
			String[] parteFecha = fechaTeclado.split("/");
			
			try {
				if(parteFecha.length != 3)
					throw new NumberFormatException();
				
				dia = Integer.parseInt(parteFecha[0]); //parseInt lanza la excepcion
				mes = Integer.parseInt((parteFecha[1])); //NumberFormatException si no
				anio = Integer.parseInt((parteFecha[2])); //puede convertir el String a int
				
				fecha = new Fecha(dia, mes, anio);
				
				if(fecha.getDia() != dia || fecha.getMes() != mes)
					throw new NumberFormatException();
				
				//Si todo se cumple, la damos por valida 
				valida = true;
			}catch(NumberFormatException e) {
				System.out.println("La fecha introducida no es valida");
			}
		}while(!valida);
			
		return fecha;
	}
	
	static public boolean mayor(Fecha f1, Fecha f2) {
		if(f1.anio*10000+f1.mes*100+f1.dia>f2.anio*10000+f2.mes*100+f2.dia)
			return true;
		else
			return false;
	}
	
	public Fecha diaSig(){
		
		Fecha f = (Fecha)clone();
        
        f.dia++;

        int maxDias[] = {0,31,bisiesto()?29:28,31,30,31,30,31,31,30,31,30,31};
        //La sintaxis de bisiesto()?29:28 es:
        
        /*
         * if(bisiesto())
         *    numDias[2] = 29;
         * else
         * 	  numDias[2] = 28;
         */

        if(f.dia > maxDias[f.mes]){
            f.dia = f.dia - maxDias[f.mes];
            f.mes++;
            if(f.mes > 12){
                f.mes = 1;
                f.anio++;
            }
        }
        return f;
	}
	
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone(); //se llama al clone() de la clase base (Object)
								//que hace copia binaria de los atributos
		} catch(CloneNotSupportedException ex) {
			System.out.println("No se puede duplicar");
		}
		return obj;
	}
	
	public boolean equals(Object obj) { //true si son iguales
		if(this == obj) return true; //si apuntan al mismo sitio, son iguales
		if(obj == null) return false; //Si el objeto tiene valor null (vacio), no son iguales
		if(getClass() != obj.getClass()) //if (!(obj instanceof Cliente))
			return false;				// si los 2 no son de la misma clase, no son iguales
		Fecha c = (Fecha) obj;
		return (dia == c.dia && mes == c.mes && anio == c.anio);
	}
	
	//Con el main, la clase Fecha funciona perfectamente
	/*
	public static void main(String[] args) {
		 Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1), f3 = new Fecha(29,2,2004);
		 final Fecha f4=new Fecha(05,12,2003); //es constante la referencia f4
		 System.out.println("Fechas: " + f1.toString() + ", " + f2 + ", " + f3 + ", " + f4);
		 f1=new Fecha(31,12,2016); //31/12/2016
		 f4.setFecha(28, 2, 2008); //pero no es constante el objeto al que apunta
		 System.out.println(f1 +" "+ f2.toString() +" " + f3 + " "+ f4 + " "+ f1);
		 f1=new Fecha(f4.getDia()-10, f4.getMes(), f4.getAnio()-7); //f1=18/02/2001
		 f3=Fecha.pedirFecha(); // pide una fecha por teclado
		 if (f3.bisiesto() && Fecha.mayor(f2,f1))
		 System.out.println("El " + f3.getAnio() + " fue bisiesto, " + f1 + ", " + f3);
		 System.out.print("Fin\n");
		}
	*/
}