#ifndef EMPRESA_H
#define EMPRESA_H

#include <iostream>
#include "Contrato.h"
#include "Cliente.h"
#include "ContratoTP.h"
#include "ContratoMovil.h"
#include <stdlib.h>
#include <typeinfo>

using namespace std;

class Empresa
{
    private:
        Cliente *clientes[100]; //array estático de punteros a Clientes
        Contrato **contratos; //primer asterisco puntero a contrato, y el segundo por el array
        int numCliente; //Contador clientes (numero de clientes que tengo)
        int numContrato; //Contador contratos (numero de contratos que tengo)
        int nmaxcli;
        int nmaxcon;

    public:
        Empresa();
        ~Empresa();
        void crearContrato();
        bool cancelarContrato(int idContrato);
        bool bajaCliente(long int dni);
        void cargarDatos();
        int nContratosTP() const;
        int descuento(float porcentaje) const;

        int altaCliente(Cliente *c);
        int buscarCliente(long int dni) const;

        void ver() const;

};

#endif // EMPRESA_H
