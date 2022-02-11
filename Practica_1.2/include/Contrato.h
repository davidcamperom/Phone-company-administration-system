#ifndef CONTRATO_H
#define CONTRATO_H

#include "Fecha.h"
#include <iostream>

using namespace std;

class Contrato
{
    private:
        //Como tiene que ser unico para cada cliente, lo hacemos constante y con cada
        //creacion de contrato, hacemos en el constructor un incremento del contadorContratos
        //que será el idContrato, de cada uno de los Contratos.
        const int idContrato;
        long int dniContrato;
        Fecha fechaContrato;
        static int contadorContratos; //Compartido por todos los objetos Contrato que cree
        //¡¡UN STATIC SE INICIALIZA EN EL .CPP!!

    public:
        Contrato(long int dni, Fecha f);
        Contrato(const Contrato &c);

        //getIdContrato se hace aqui por que la hereda ContratoMovil y ContratoTP
        int getIdContrato() const {return this->idContrato;};
        void setDniContrato(long int dni) {this->dniContrato = dni;};
        int getDniContrato() const {return this->dniContrato;};
        void setFechaContrato(Fecha f){this->fechaContrato = f;};
        Fecha getFechaContrato() const {return this->fechaContrato;};

        void ver() const;
};

ostream& operator<<(ostream &s, const Contrato &c);

#endif // CONTRATO_H
