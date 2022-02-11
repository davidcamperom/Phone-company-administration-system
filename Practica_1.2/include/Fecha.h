#ifndef FECHA_H
#define FECHA_H

#include <iostream>

using namespace std;

class Fecha{

    private:
        int dia, mes, anio;

    public:
        Fecha(int d, int m, int a);

        void setFecha(int d, int m, int a);
        int getDia() const {return this->dia;};
        int getMes() const {return this->mes;};
        int getAnio() const {return this->anio;};
        void ver() const;
        bool bisiesto() const;

        Fecha operator++(); //++f;
        Fecha operator++(int notused); //f++;
        Fecha operator+(const int &i) const; //f+i (entero)

        //Llamada del operator+ declarado fuera de la clase
        friend Fecha operator+(const int &i, const Fecha &f); //i+f;
};

Fecha operator+(const int &i, const Fecha &f); //Declaracion del operator+ de fecha

//IMPLEMENTADO EL OPERADOR << PARA LA PARTE 1.2
ostream& operator<<(ostream &s, const Fecha &f);

#endif // FECHA_H
