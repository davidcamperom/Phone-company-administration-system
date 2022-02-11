package libClases;

import java.util.ArrayList;
import java.util.Scanner;

public class Empresa implements Cloneable, Proceso{
	
	private Cliente[] Clientes;
	//Pongo el número 4, para ir creando de 4 en 4.
	private static int INCREMENTO = 4;
	private int numClientes;
	
	//Constructores/
	public Empresa() {
		this.Clientes = new Cliente[INCREMENTO];
		this.numClientes = 0;
	}
	
	public int BuscarCliente(Cliente c) {
		int i; //variable para posicionar la busqueda
		int pos = -1; //variable donde guarda la posicion del cliente, y la cual
		//hay que devolver. Si devuelve -1 es que no se encuntra en el sistema
		boolean encontrado = false;
		
		i = 0;
		while(i<numClientes && !encontrado) {
			if(c.getNif().equals(Clientes[i].getNif())) {
				encontrado = true;
				pos = i;
			}
			else
				i++;
		}
		return pos;
	}
	
	public int BuscarClienteNif(String nf) {
		int i; //variable para posicionar la busqueda
		int pos = -1; //variable donde guarda la posicion del cliente, y la cual
		//hay que devolver.
		boolean encontrado = false;
		
		i = 0;
		while(i<numClientes && !encontrado) {
			if(nf.equals(Clientes[i].getNif())) {
				encontrado = true;
				pos = i;
			}
			else
				i++;
		}
		return pos;		
	}
	
	//Metodo que hace el Alta a un cliente pasado por parametro
	public void alta(Cliente c) {
		int pos;
		Cliente[] aux;
		
		pos = BuscarCliente(c);
		if(pos == -1) { //Si el Cliente no existe, procedemos a ayadirlo
			if(numClientes == INCREMENTO) { //Si el numero de clientes es el mismo que
			//el el espacio total...
				aux = new Cliente[INCREMENTO + 4]; //Aumentamos el numero a 4 más.
				for(int i=0; i<numClientes; i++) //Recorremos todos los clientes
					aux[i] = Clientes[i]; //y los metemos en la tabla aux
				Clientes = aux; //Luego, metemos los datos de aux en Clientes (de nuevo)
			}
			Clientes[numClientes] = c;
			numClientes++;
		}
	}
	
	//Visualizacion Alta a un cliente (Comprobacion por su dni)
	public void alta() {
		Cliente c; //Inicializado mas abajo
		String dni, nomb, nacion;
		Fecha fNacim, fAlta, fPerma;
		float minutosH, precioM;
		int tipoCliente;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Introduzca DNI: ");
		dni = s.nextLine();

		//Comprobamos que ese Cliente exista o no
		int posCliente = BuscarClienteNif(dni);
		if(posCliente != -1) { //Si el cliente existe, decimos que esta
			System.out.println("Ya existe un Cliente con ese dni: ");
			c = Clientes[posCliente];
			System.out.println(c);
			System.out.println("\n");
		}else {
			System.out.println("Introduzca Nombre: ");
			nomb = s.nextLine();
			System.out.println("Fecha de Nacimiento: ");
			fNacim = Fecha.pedirFecha();
			System.out.println("Fecha de Alta: ");
			fAlta = Fecha.pedirFecha();
			System.out.println("Minutos que habla al mes: ");
			minutosH = s.nextFloat();
			System.out.println("Indique tipo de Cliente (1-Movil, 2-Tarifa Plana): ");
			do {
				tipoCliente = s.nextInt();
				if(tipoCliente != 1 && tipoCliente != 2)
					System.out.println("ERROR. Introduce una opcion correcta");
			}while(tipoCliente != 1 && tipoCliente != 2);

			if(tipoCliente == 1) {
				System.out.println("Precio por minuto: ");
				precioM = s.nextFloat();
				System.out.println("Fecha fin de permanencia: ");
				fPerma = Fecha.pedirFecha();
				
				c = new ClienteMovil(dni, nomb, fNacim, fAlta, fPerma, minutosH, precioM);
			}
			else {
				System.out.println("Nacionalidad: ");
				nacion = s.nextLine();
				
				c = new ClienteTarifaPlana(dni, nomb, fNacim, fAlta, minutosH, nacion);
			}
			alta(c);
		}
	}
	
	//Metodo que hace la Baja a un cliente pasado por parametro
	public void baja(String dni) {
		int posCliente;
		posCliente = BuscarClienteNif(dni);
		
		if(posCliente != -1) {
			for(int j = posCliente; j<numClientes-1; j++) { //A partir de la poscicion
				//del Cliente hacia el numero maximo de Clientes-1, lo copiamos todo
				//sin incluir la posición de i inicial y sobrescribimos
				Clientes[j] = Clientes[j+1];
			}
			numClientes--;
		}
	}
	
	//Visualizacion Baja a un cliente (Comprobacion por su dni)
	public void baja() {
		String dni, opcion;
		int posCliente;
		Cliente c; //Inicializado mas abajo
		Scanner s = new Scanner(System.in);
		
		System.out.println("Introduzca nif de cliente a dar de baja: ");
		dni = s.nextLine();
		posCliente = BuscarClienteNif(dni);
		if(posCliente != -1) { //Si el Cliente se encuentra en el sistema
			c = Clientes[posCliente];
			System.out.println(c);
			do {
				System.out.println("¿Seguro que desea eliminarlo(s/n)?: ");
				opcion = s.nextLine();
			}while(opcion.equals("s") && opcion.equals("n"));		
			
			if(opcion.equals("s")) {
				System.out.println("El Cliente " + c.getNombre() + " con nif " + c.getNif() + " ha sido eliminado\n");
				baja(dni);
			}
			if(opcion.equals("n")) {
				System.out.println("El Cliente con nif " + c.getNif() + " no ha sido eliminado\n");
			}
			
		}
		else
			System.out.println("ERROR. No se ha encontrado el Cliente");
		
	}
	
	public float factura() {
		float totalFact = 0;
		
		for(int i=0; i<numClientes; i++)
			totalFact = totalFact + Clientes[i].factura();
		return totalFact;
	}
	
	public void descuento(int porcen){
		
		float desc;
		ClienteMovil c;
		
		desc = (float)((100 - porcen))/100.0f;
		
		for(int i=0; i<numClientes; i++){
			if(Clientes[i] instanceof ClienteMovil){
				c = (ClienteMovil)Clientes[i];
				c.setPrecioMinuto(c.getPrecioMinuto()*desc);
			}
		}
	}
	
	public int nClienteMovil(){
		
		int num = 0;
		
		for(int i=0; i<numClientes; i++){
			if(Clientes[i] instanceof ClienteMovil){
				num++;
			}
		}
		return num;
	}
	
	public int getN() {return this.numClientes;}
	
	public String toString(){
		
		String tex = "";
		
		for(int i=0; i<numClientes; i++){
			tex = tex + Clientes[i] + "\n";
		}
		
		return tex;
	}
	
	public void ver(){
		System.out.println(this);
	}
	
	public Object clone(){
		
		Empresa obj = null;
		
		try{
			obj = (Empresa)super.clone();
			obj.Clientes = (Cliente[])Clientes.clone();
			for(int i=0; i<numClientes; i++){
				obj.Clientes[i] = (Cliente)Clientes[i].clone();
			}
		}catch(CloneNotSupportedException ex){
			
		}
		return (Object)obj;
	}
	
	// ************************* MODIFICACIONES ********************************************

	//1.a)Eliminar clientes movil cn una factura menor a la factura media d los clientes movil
    public int eliminarMenosFactura(){
        float media = 0;
        int nMovil = 0;
        for(int i = 0; i < numClientes; i++){
            if(Clientes[i].getClass().equals(ClienteMovil.class)){
                media += Clientes[i].factura();
                nMovil ++;
            }
        }
        media /= nMovil;
        
        int i = 0;
        int eliminados = 0;
        while(i < numClientes){
            if(Clientes[i].getClass().equals(ClienteMovil.class) && Clientes[i].factura() < media){
                baja(Clientes[i].getNif());
                eliminados++;
            }else i++;
        }
        return eliminados;
    }
    
    //1.b)Mostrar los clientes mas jovenes d la empresa (fecha d nacimiento mas alta)
    public void clienteMasJoven(){
        Fecha mayor = null;
        for(int i = 0; i < numClientes; i++)
            if(mayor == null || Fecha.mayor(Clientes[i].getFechaNac(), mayor))
                mayor = Clientes[i].getFechaNac();
        
        for(int i = 0; i < numClientes; i++)
            if(Clientes[i].getFechaNac().equals(mayor))
                System.out.println(Clientes[i]);
    }
            
    //1.c)Eliminar los clientes tarifa plana cn la nacionalidad y año d alta q t pasan x parametro
    public void eliminarSelectivo(String nac, int anio){
        int i = 0;
        while(i < numClientes)
            if(Clientes[i].getClass().equals(ClienteTarifaPlana.class) &&
                    ((ClienteTarifaPlana)Clientes[i]).getNacionalidad().equals(nac) &&
                    Clientes[i].getFechaAlta().getAnio() == anio)
                baja(Clientes[i].getNif());
            else i++;
    }
    
    
    //2.a) Mostrar al cliente de cada tipo que tiene mayor factura.
    public void mostrarMejores(){
        float mejorM = -1, mejorTP = -1;
        
        for(int i = 0; i < numClientes; i++)
            if(Clientes[i].getClass().equals(ClienteMovil.class) && (mejorM < 0 || Clientes[i].factura() > mejorM))
                mejorM = Clientes[i].factura();
            else if(Clientes[i].getClass().equals(ClienteTarifaPlana.class) && (mejorTP < 0 || Clientes[i].factura() > mejorTP))
                mejorTP = Clientes[i].factura();
        
        System.out.println("Mejor cliente movil:");
        for(int i = 0; i < numClientes; i++)
            if(Clientes[i].getClass().equals(ClienteMovil.class) && Clientes[i].factura() == mejorM)
                System.out.println(Clientes[i]);
        System.out.println("Mejor cliente tarifa plana:");
        for(int i = 0; i < numClientes; i++)
            if(Clientes[i].getClass().equals(ClienteTarifaPlana.class) && Clientes[i].factura() == mejorTP)
                System.out.println(Clientes[i]);
    }
    
    //2.b) Un metdo estatico para empresa al que se le pasan dos objetos empresa. A la primera empresa se le agregan los clientes que hablen más de los minutos que tienen contratados y se quitan de la segunda empresa. (Se los roba)
    public static void rovarExtraHabladoresTP(Empresa destino, Empresa origen){
        ArrayList<Cliente> rovados = new ArrayList<>();
        for(int i = 0; i < origen.numClientes; i++)
            if(origen.Clientes[i].getClass().equals(ClienteTarifaPlana.class) && ((ClienteTarifaPlana)origen.Clientes[i]).getMinutos() > ClienteTarifaPlana.getLimite())
                rovados.add(origen.Clientes[i]);
        for(Cliente c : rovados){
            destino.alta(origen.Clientes[origen.BuscarClienteNif(c.getNif())]);
            origen.baja(c.getNif());
        }
    }

    //2.c) Hacer el equals se Empresa. Se considera que dos empresas son iguales si tienen los mismos clientes, aunque no esten en el mismo orden.
    @Override
    public boolean equals(Object o){
        if(o.getClass().equals(Empresa.class)){
            if(o == this) return true;
            Empresa e = (Empresa)o;
            if(e.getN() == numClientes){
                int i = 0;
                boolean encontrado = false;
                boolean todoBien = true;
                while(i < numClientes && todoBien){
                    int j = 0;
                    while(j < e.getN() && !encontrado){
                        encontrado = Clientes[i].equals(e.Clientes[j]);
                        j++;
                    }
                    if(!encontrado)
                        todoBien = false;
                    i++;
                }
                return todoBien;
            }
        }
        return false;
    }
    
    /*
    //3.a)
    public static void subirPrecioAMP(Empresa e, int anios){
        for(int i = 0; i < e.numClientes; i++){
            if(e.Clientes[i].getClass().equals(ClienteMovil.class)){
                ClienteMovil cm = (ClienteMovil)e.Clientes[i];
                //Hay que definir, como pone abajo, este método en la clase Fecha
                if(Fecha.diffAnios(cm.getFPermanencia(), cm.getFechaAlta()) >= anios){
                    cm.setPrecioMinuto(cm.getPrecioMinuto()*1.05f);
                }
            }
        }
    }
    */
    
    /*Siendo la clase diffAnios en la Clase Fecha:
     * 
     *public static int diffAnios(Fecha a, Fecha b){
        int n = a.anio - b.anio;
        if(a.mes < b.mes || (a.mes == b.mes && a.dia < b.dia))
            n --;
        return n;
     *}
     */
    
    //3.b)Sacar un float con la facturacion total de todos los contratosTP
    public float resumenFacturaTP(){
        float total = 0;
        for(int i = 0; i < numClientes; i++){
            if(Clientes[i].getClass().equals(ClienteTarifaPlana.class))
                total += Clientes[i].factura();
        }
        return total;
    }
    
    //3.c)Eliminar peores, el/los que facturan menos clientes de contrato TP y una nacionalidad en concreto
    public void eliminarPeoresTP(String nacionalidad){
        Object o = null;
        o.equals(null);
        
        float peor = -1;
        for(int i = 0; i < numClientes; i++)
            if(Clientes[i].getClass().equals(ClienteTarifaPlana.class))
                if(((ClienteTarifaPlana)Clientes[i]).getNacionalidad().equals(nacionalidad) && (peor < 0 && Clientes[i].factura() < peor))
                    peor = Clientes[i].factura();
        
        int i = 0;
        while(i < numClientes){
            if(Clientes[i].factura() == peor)
                baja(Clientes[i].getNif());
            else i++;
        }
    }
    
  //4.a) Elimina la permanencia de los ClientesMovil con las tarifas que hablen los minutos pasado por parámetro
    public void eliminarPermanencia(float minutos){
        for(int i = 0; i < numClientes; i++){
            if(Clientes[i].getClass().equals(ClienteMovil.class)){
                ClienteMovil cm = (ClienteMovil)Clientes[i];
                if(cm.getMinutos() > minutos)
                    cm.setFPermanencia(cm.getFechaAlta()); //Se pone la fecha de alta queriendo decir que no tiene permanencia
            }
        }
    }
    
    //4.b) Hacer un método que contabilice las nacionalidades de los Clientes de la empresa
    public static void nacionalidadUnica(Empresa e){
        for(int i = 0; i < e.numClientes; i++)
            if(e.Clientes[i].getClass().equals(ClienteTarifaPlana.class) &&
                    e.contarNacionalidad(((ClienteTarifaPlana)e.Clientes[i]).getNacionalidad()) == 1)
                System.out.println("Solo hay un cliente con la nacionalidad "+((ClienteTarifaPlana)e.Clientes[i]).getNacionalidad());
    }
    private int contarNacionalidad(String nacionalidad){
        int n = 0;
        
        for(int i = 0; i < numClientes; i++)
            if(Clientes[i].getClass().equals(ClienteTarifaPlana.class) &&
                    ((ClienteTarifaPlana)Clientes[i]).getNacionalidad().equals(nacionalidad))
                n++;
                    
        return n;
    }
    
    //4.c)Robar o llevar de una empresa origen, a una destino y luego borrar en la origen, esos clientes
    public static int portar(Empresa destino, Empresa origen){
        int n = 0;
        
        int i = 0;
        while(i < origen.numClientes){
            if(destino.BuscarClienteNif(origen.Clientes[i].getNif()) == -1){
                destino.alta(origen.Clientes[i]);
                origen.baja(origen.Clientes[i].getNif());
                n++;
            }else i++;
        }
        
        return n;
    }
    
}
