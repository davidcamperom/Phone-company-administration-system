#ifndef CONTRATOMOVIL_H
#define CONTRATOMOVIL_H

#include <iostream>
#include <string.h>
#include <cstring>
#include <iomanip>
#include "Fecha.h"
#include "Contrato.h"

using namespace std;

class ContratoMovil:public Contrato //Esto hace que herede de Contrato
{
    private:
        char* nacionalidad;
        int minutosHablados;
        float precioMinuto;

    public:
        ContratoMovil(long int dn, Fecha f, float pM, int mH, char* nac);
        //Al tener un puntero (memoria dinamica), hay que hacer un constructor de copia
        //y un destructor.
        ContratoMovil(const ContratoMovil &c);
        ~ContratoMovil();

        void setNacionalidad(char* nuevaNacionalidad);
        char* getNacionalidad() const {return this->nacionalidad;};
        void setPrecioMinuto(float pMinuto) {this->precioMinuto = pMinuto;};
        float getPrecioMinuto() const {return this->precioMinuto;};
        void setMinutosHablados(int mHablados) {this->minutosHablados = mHablados;};
        int getMinutosHablados() const {return this->minutosHablados;};

        float factura() const;
        void ver() const;
};

ostream& operator<<(ostream &s, const ContratoMovil &c);

#endif // CONTRATOMOVIL_H
