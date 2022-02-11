#ifndef CONTRATOTP_H
#define CONTRATOTP_H

#include "ContratoTP.h"
#include <iostream>
#include <iomanip>
#include "Contrato.h"

class ContratoTP:public Contrato //Esto hace que herede de Contrato
{
    private:
        //Usamos static ya que los minutos, el precio de ellos, y el precio por exceso
        //son fijos.
        //static también se usa para darle un valor global al atributop, y en caso de
        //cambio, se aplica a todos.
        static int minutosTP;
        static float precioTP;
        int minutosHablados;
        static const float precioExcesoMinutos;

    public:
        ContratoTP(long int dni, Fecha f, int minutosh);
        //ContratoTP(const ContratoTP &c)
        //No hace falta un constructor de copia ya que el padre
        //se encarga (Clase Contrato)

        float factura() const;
        static int getLimiteMinutos() {return ContratoTP::minutosTP;};
        static float getPrecio()  {return ContratoTP::precioTP;};
        static float getPrecioTP() {return ContratoTP::precioTP;};
        static void setTarifaPlana(int m, int p);
        void setMinutosHablados(int minHablados){this->minutosHablados = minHablados;};
        int getMinutosHablados() const {return this->minutosHablados;};
        void ver() const;

};

ostream& operator<<(ostream &s, ContratoTP &c);

#endif // CONTRATOTP_H
