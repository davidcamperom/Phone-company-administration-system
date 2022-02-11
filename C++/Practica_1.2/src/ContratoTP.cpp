#include "ContratoTP.h"

//Los atributos estáticos se inicializan aquí
int ContratoTP::minutosTP = 300;
float ContratoTP::precioTP = 10;
const float ContratoTP::precioExcesoMinutos = 0.15;


ContratoTP::ContratoTP(long int dni, Fecha f, int minutosh):Contrato(dni, f){

    this->minutosHablados = minutosh;
}

float ContratoTP::factura(){
    if(minutosHablados>minutosTP){
        int excesoLimiteMinutos = minutosHablados - minutosTP;
        return (precioTP + (excesoLimiteMinutos * precioExcesoMinutos));
    }
    else
        return precioTP;
}

void ContratoTP::setTarifaPlana(int m, int p){
     minutosTP = m;
     precioTP = p;
}

void ContratoTP::ver() const{
    Contrato::ver();
    cout << fixed << setprecision(2) << " " << minutosHablados << "m, " << getLimiteMinutos() << "(" << getPrecio() << ")";
    cout << ContratoTP::factura();
}

ostream& operator<<(ostream &s, ContratoTP &c){

    s << c.getDniContrato() << " (" << c.getIdContrato() << " - " << c.getFechaContrato() << ") ";
    s << c.getMinutosHablados() << "m, ";
    s << c.getLimiteMinutos() << "(" << c.getPrecioTP() << ")" << " - ";
    s << c.factura() << "$";

    return s;
}
