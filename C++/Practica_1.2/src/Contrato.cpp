#include "Contrato.h"

int Contrato::contadorContratos = 1;

Contrato::Contrato(long int dni, Fecha f):idContrato(contadorContratos), fechaContrato(f){

    this->dniContrato = dni;
    //Cada vez que ejecute el constructor, creará un idContrato unico, a partir del contadorContratos
    contadorContratos++;

}

Contrato::Contrato(const Contrato &c):idContrato(contadorContratos), fechaContrato(c.fechaContrato){
    this->dniContrato = c.dniContrato;
    //Cada vez que ejecute el constructor, creará un idContrato unico, a partir del contadorContratos
    contadorContratos++;
}

void Contrato::ver() const{

    cout << this->dniContrato << " (" << this->idContrato << " - ";
    this->fechaContrato.ver(); //llamo al ver del objeto fecha para que lo muestre con el
    //formato correcto y correspondiente
    cout << ")";
}

ostream& operator<<(ostream &s, const Contrato &c){

    s << c.getDniContrato() << " (" << c.getIdContrato() << " - " << c.getFechaContrato() << ")";
    return s;
}
