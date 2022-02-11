#include "Cliente.h"

Cliente::Cliente(long int dn, char *nom, Fecha f):fechaAlta(f.getDia(), f.getMes(), f.getAnio()){

    this->dni = dn;

    this->nombre = new char[strlen(nom+1)];
    strcpy(this->nombre, nom);
}

Cliente::~Cliente(){

    delete[] nombre;
}

void Cliente::setNombre(char *nom){

    delete[] this->nombre; //Por precaucion, eliminamos lo que
    //previamente habia

    this->nombre = new char[strlen(nom+1)];
    strcpy(this->nombre, nom);
}

const char* Cliente::getNombre() const{

    return this->nombre;
}

bool Cliente::operator==(Cliente &c) const{

    if(this != &c){
        //Variables de las fechas
        //donde almacenar los datos de fechas de ambos Clientes
        int d1, d2, m1, m2, a1, a2;
        d1 = this->getFecha().getDia(), m1 = this->getFecha().getMes(), a1 = this->getFecha().getAnio();
        d2 = c.getFecha().getDia(), m2 = c.getFecha().getMes(), a2 = c.getFecha().getAnio();

        //Procedemos a comparar ambos Clientes
        if(this->getDni() != c.getDni() || strcmp(this->nombre, c.getNombre())!=0 || (d1!=d2 || m1!=m2 || a1!=a2))
            return false;
        else
            return true;
    }
    else
        return true;
}

Cliente& Cliente::operator=(const Cliente &c){

    if(this != &c){
        this->dni = c.getDni();

        delete[] this->nombre;
        this->nombre = new char[strlen(c.getNombre())+1];
        strcpy(this->nombre, c.getNombre());

        this->fechaAlta = c.getFecha();
    }

    return *this;
}

ostream& operator<<(ostream &s, const Fecha &f)
{
    int day=f.getDia(), month=f.getMes(), year=f.getAnio();

    if(day<10)
        s << "0" << day;
    else
        s << day;

    switch(month)
    {
        case 1:
            {
                s << " ene ";
                break;
            }
        case 2:
            {
                s << " feb ";
                break;
            }
        case 3:
            {
                s << " mar ";
                break;
            }
        case 4:
            {
                s << " abr ";
                break;
            }
        case 5:
            {
                s << " may ";
                break;
            }
        case 6:
            {
                s << " jun ";
                break;
            }
        case 7:
            {
                s << " jul ";
                break;
            }
        case 8:
            {
                s << " ago ";
                break;
            }
        case 9:
            {
                s << " sep ";
                break;
            }
        case 10:
            {
                s << " oct ";
                break;
            }
        case 11:
            {
                s << " nov ";
                break;
            }
        case 12:
            {
                s << " dic ";
                break;
            }
    }

    s << year;

    return s;
}

ostream& operator<<(ostream &s, const Cliente &c)
{
    s << c.getNombre() << " (" << c.getDni() << " - " << c.getFecha() << ")";
    return s;
}
