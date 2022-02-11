#include "ContratoMovil.h"

ContratoMovil::ContratoMovil(long int dni, Fecha f, float pM, int mH, char* nac):Contrato(dni, f){
    this->precioMinuto = pM;
    this->minutosHablados = mH;

    this->nacionalidad = new char [strlen(nac)+1];
    strcpy(this->nacionalidad, nac);
}

ContratoMovil::ContratoMovil(const ContratoMovil &c):Contrato(c){
    this->precioMinuto = c.precioMinuto;
    this->minutosHablados = c.minutosHablados;

    this->nacionalidad = new char[strlen(c.nacionalidad)+1];
    strcpy(this->nacionalidad, c.nacionalidad);
}

ContratoMovil::~ContratoMovil(){
    delete [] nacionalidad;
}

void ContratoMovil::setNacionalidad(char* nuevaNacionalidad){
    delete [] nacionalidad;

    this->nacionalidad = new char[strlen(nuevaNacionalidad)+1];
    strcpy(this->nacionalidad, nuevaNacionalidad);
}

float ContratoMovil::factura() const{
    return (precioMinuto*minutosHablados);
}

void ContratoMovil::ver() const
{
    Contrato::ver();
    cout << " " << this->minutosHablados << "m, " << this->nacionalidad << " " << this->precioMinuto;
    cout << ContratoMovil::factura();
}

ostream& operator<<(ostream &s, const ContratoMovil &c){

    s << c.getDniContrato() << " (" << c.getIdContrato() << " - " << c.getFechaContrato() << ") ";
    s << c.getMinutosHablados() << "m, ";
    s << c.getNacionalidad() << " " << c.getPrecioMinuto() << " - ";
    s << c.factura() << "$";

    return s;
}
