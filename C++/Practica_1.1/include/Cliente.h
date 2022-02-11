#ifndef CLIENTE_H
#define CLIENTE_H

#include <iostream>
#include <cstring>
#include "Fecha.h"

using namespace std;

class Cliente{

    private:
        long int dni;
        char *nombre;
        Fecha fechaAlta;

    public:
        Cliente(long int dn, char *nom, Fecha f);
        //Al tener un puntero de tipo Cliente, hay que implemntar
        //constructor de copia y destructor.
        Cliente(const Cliente &c); //Constructor de copia, ya que hay un puntero
        ~Cliente(); //Destructor, ya que hay un puntero

        void setNombre(char *nom);
        void setFecha(Fecha f){this->fechaAlta = f;};

        const char* getNombre() const;
        long int getDni() const{return this->dni;};
        Fecha getFecha() const{return this->fechaAlta;};

        //Programamos el operator == del if
        bool operator==(Cliente &c) const;
        //operador asignacion (c = *p)
        Cliente& operator=(const Cliente &c);
};

//Para mostrar el contenido de los objetos con el cout
ostream& operator<<(ostream &s, const Fecha &f);
ostream& operator<<(ostream &s, const Cliente &c);

#endif // CLIENTE_H
