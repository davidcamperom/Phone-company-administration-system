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

        //Implementacion 1 - PARTE 3. Hacer que Contrato muestre un comportamiento Polimorfico
        //Añadimos virtual en ver()
        //virtual void ver() const;

        //Implementacion 2 - PARTE 3. Hacer que Contrato sea una clase Abstracta (no puede generar
        //objetos de la Clase Contrato)
        //Añadimos virtual en ver() y lo igualamos a 0, para que sea un virtual puro, y
        //por tanto, la clase sea Abstracta
        virtual void ver() const = 0;
        //Tendremos que comentar en el main prueba2.cpp, todas las lineas a donde se use
        //un objeto tipo Contrato.

};

ostream& operator<<(ostream &s, const Contrato &c);

#endif // CONTRATO_H
