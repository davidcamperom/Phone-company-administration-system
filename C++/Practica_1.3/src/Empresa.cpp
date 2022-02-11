#include "Empresa.h"

Empresa::Empresa():nmaxcli(100){

    this->contratos = new Contrato*[10];
    this->nmaxcon = 10;
    this->numContrato = 0;
    this->numCliente = 0;
}

Empresa::~Empresa(){

    for(int i=0; i<this->numContrato; i++) //Recoremos el numero total de contratos
        delete this->contratos[i]; //Eliminamos sus objetos (los de dentro)
    delete [] this->contratos; //Eliminamos el array contratos (es array dinamico)

    for(int i=0; i<this->numCliente; i++)
        delete this->clientes[i];

}

void Empresa::cargarDatos(){

    Fecha f1(29,2,2001), f2(31,1,2002), f3(1,2,2002);
    this->clientes[0] = new Cliente(75547001, "Peter Lee", f1);
    this->clientes[1] = new Cliente(45999000, "Juan Perez", Fecha(29,2,2000));
    this->clientes[2] = new Cliente(37000017, "Luis Bono", f2);
    this->numCliente=3;

    this->contratos[0] = new ContratoMovil(75547001, f1, 0.12, 110, "DANES"); //habla 110m a 0.12e/m
    this->contratos[1] = new ContratoMovil(75547001, f2, 0.09, 170, "DANES"); //habla 170m a 0.09e/m
    this->contratos[2] = new ContratoTP(37000017, f3, 250); //habla 250m (300m a 10e, exceso 0.15e/m)
    this->contratos[3] = new ContratoTP(75547001, f1, 312); //habla 312m (300m a 10e, exceso 0.15e/m)
    this->contratos[4] = new ContratoMovil(45999000, f2, 0.10, 202, "ESPANOL"); //habla 202m a 0.10e/m
    this->contratos[5] = new ContratoMovil(75547001, f2, 0.15, 80, "DANES"); //habla 80m a 0.15e/m
    this->contratos[6] = new ContratoTP(45999000, f3, 400); //habla 400m (300m a 10e, exceso 0.15e/m)
    this->numContrato=7;
}

int Empresa::altaCliente(Cliente *c){ //Método auxiliar para comprobar que cabe

    //Su funcionamiento es que si devuelve un -1, no cabe, y si no, lo inserta en la posicion
    //correspondiente
    int pos = -1;
    if(this->numCliente<nmaxcli){

        this->clientes[this->numCliente] = c;
        pos = this->numCliente;
        this->numCliente++;
    }
    else{
        cout << "\n\tLo sentimos, no se admiten mas clientes en estos momentos" << endl;
        pos = -1;
    }
    return pos;
}

int Empresa::buscarCliente(long int dni) const{

    //Su funcionamiento es que si devuelve un -1, no existe el cliente en el array
    bool encontrado = false;
    int i = 0;
    int pos = -1;

    while(i<numCliente && !encontrado){
        if(dni == clientes[i]->getDni()){
            pos = i;
            encontrado = true;
        }
        else
            i++;
    }
    return pos;
}

void Empresa::crearContrato(){

    long int dni;
    float precioMinuto;
    char nombre[100];
    char nacionalidad[100];
    int dia, mes, anio;
    int tipo;
    int minutosHablados;

    cout << "\nIntroduzca un DNI, por favor: ";
    cin >> dni;

    int busqueda = this->buscarCliente(dni);
    if(busqueda == -1){ //Si el cliente no existe...

        Cliente *c; //Para crear un nuevo cliente
        cout << "Introduzca el nombre del cliente: ";
        cin >> nombre;
        cout << "Introduzca fecha " << endl;
        cout << "Dia: "; cin >> dia;
        cout << "Mes: "; cin >> mes;
        cout << "Anio: "; cin >> anio;
        c = new Cliente(dni, nombre, Fecha(dia, mes, anio));

        //Ahora comprobamos que quepa el cliente
        int alta = this->altaCliente(c);
        cout << endl;
    }

    if(busqueda != 1){ //Si existe...
        do{
            cout << "\nTipo de Contrato a abrir (1-Tarifa Plana, 2-Movil): ";
            cin >> tipo;
        }while(tipo!=1 && tipo!=2);

        cout << "Introduzca fecha del contrato:" << endl;
        cout << "Dia: "; cin >> dia;
        cout << "Mes: "; cin >> mes;
        cout << "Anio: "; cin >> anio;
        cout << "Minutos hablados: "; cin >> minutosHablados;

        if(tipo == 2){

            cout << "Precio minuto: "; cin >> precioMinuto;
            cout << "Nacionalidad: "; cin >> nacionalidad;
            this->contratos[this->numContrato] = new ContratoMovil(dni, Fecha(dia, mes, anio), precioMinuto, minutosHablados, nacionalidad);
        }
        else
            this->contratos[this->numContrato] = new ContratoTP(dni, Fecha(dia, mes, anio), minutosHablados);
    }
    this->numContrato++;
}

bool Empresa::cancelarContrato(int idContrato){

    bool encontrado = false;
    bool eliminado = false;
    int i = 0;

    while(i<numContrato && !encontrado){

        if(contratos[i]->getIdContrato() == idContrato)
            encontrado = true;
        else
            i++;
    }

    if(encontrado){ //Procedemos a eliminarlo

        for(int j=i; j<numContrato; j++)
            contratos[j] = contratos[j+1]; //Aqui realiza la accion de eliminar
        numContrato--; //Quitamos un contrato
        eliminado = true; //Damos la señal de que ha sido eliminado por completo
    }
    return eliminado;
}

bool Empresa::bajaCliente(long int dni){

    //Su funcionamiento es que primero quitemos sus contratos y luego el propio cliente

    bool encontrado = false;
    int i = 0;

    //Primero, vamos a eliminar los contratos
    while(i<numContrato){

        if(contratos[i]->getDniContrato() == dni)
            cancelarContrato(contratos[i]->getIdContrato());
        else
            i++;
    }

    //Una vez eliminado los contratos, pasamos a buscar al Cliente por su dni
    i=0; //Tenemos que resetear el valor de i, para la siguiente busqueda

    while(i<numCliente && !encontrado){

        if(clientes[i]->getDni() == dni)
            encontrado = true;
        else
            i++;
    }

    //Y ahora si, procemos a eliminar al Cliente
    if(encontrado){
        for(int j=i; j<numCliente; j++)
            clientes[j] = clientes[j+1];
        numCliente--;
    }
    return encontrado;
}

int Empresa::descuento(float porcentaje) const{

    ContratoMovil *c;
    porcentaje = 1 - porcentaje/100;

    for(int i=0; i<numContrato; i++)
        if(c = dynamic_cast<ContratoMovil*>(contratos[i]))
            c->setPrecioMinuto(c->getPrecioMinuto()*porcentaje);

}

int Empresa::nContratosTP() const{

    int numTP = 0;
    for(int i=0; i<numContrato; i++)
        if(typeid(ContratoTP) == typeid(*contratos[i]))
            numTP++;

    return numTP;
}

void Empresa::ver() const
{

    cout << "\n\nLa empresa tiene " << numCliente << " clientes" << " y " << numContrato << " contratos " << endl;

    cout << "Clientes: " << endl;
    for(int i=0; i<numCliente; i++){
        cout << *clientes[i] << endl;
    }
    cout << endl;

    cout << "Contratos: " << endl;
    for(int i=0; i<numContrato; i++){
        contratos[i]->ver();
        cout << endl;
    }
    cout << endl;
    cout << endl;
}
