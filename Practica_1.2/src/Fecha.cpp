#include "Fecha.h"

Fecha::Fecha(int d, int m, int a){
    //llamamos directamente al setFecha, ya que controla si es bisiesto, etc
    //sabemos esto por que le llegan los mismos parametros al constructor que
    //al propio metodo
    this->setFecha(d, m, a);
}

void Fecha::setFecha(int d, int m, int a){

    //ANIOS
    this->anio = a;

    //MESES
    //El unico problema que encontramos aqui es que hay que tener cuidado que
    //el usuario meta un valor menor que 1 o mayor que 12.
    if(m < 1)
        m = 1;
    else if(m > 12)
        m = 12;

    this->mes = m;

    //DIAS
    //Los dias, tienen 3 problemas:
    //1. El usuario puede introducir un valor menor que 1.
    //2. Cada mes, tiene un numero diferente de dias, por lo que depende del mes,
    //hay que corregir esto
    //3. El mes bisiesto, del anio en cuestion

    //Creamos un array con todos los meses(y sus dias) para agilizar
    int diaMes[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if(d < 1)
        this->dia = 1;
    else if(d <= 28)
        this->dia = d;
    else{
        if(this->bisiesto()) //Si el anio es bisiesto, entonces le damos el valor 29
            diaMes[2] = 29;
        if(d <= diaMes[m]) //Si el dia es menor o igual al maximo que le pertenece, lo aplicamos
            this->dia = d;
        else //Que el usuario lo supera, pues le asignamos el numero maximo del mes
            this->dia = diaMes[m];
    }
}

void Fecha::ver() const{

    if(this->dia<10)
        cout << "0";
    cout << this->dia << "/";

    if(this->mes<10)
        cout << "0";
    cout << this->mes << "/";

    cout << this->getAnio();
}

bool Fecha::bisiesto() const{

    return (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0));
}

Fecha Fecha::operator++(int notused){ //f++;

    //Este metodo es para incrementar en 1, la fecha en cuestion

    Fecha copia(*this); //Esto es necesario con el operator++(int notused)
    this->dia++;
    int diaMax[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if(this->bisiesto())
        diaMax[2] = 29;

    //Si el dia del mes es mayor al maximo, y el mes es el 12, reiniciamos
    if(this->dia > diaMax[this->mes] && this->mes==12){
        this->dia = 1;
        this->mes = 1;
        this->anio++;
    }

    //Si el dia del mes es mayor al maximo, pero no estamos en el mes 12, incrementamos
    //el mes y ponemos el dia en 1.
    if(this->dia > diaMax[this->mes]){
        this->dia = 1;
        this->mes++;
    }

    return copia;
}

Fecha Fecha::operator++(){ //++f;

    //Este metodo es para incrementar en 1, la fecha en cuestion

    this->dia++;
    int diaMax[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if(this->bisiesto())
        diaMax[2] = 29;

    //Si el dia del mes es mayor al maximo, y el mes es el 12, reiniciamos
    if(this->dia > diaMax[this->mes] && this->mes==12){
        this->dia = 1;
        this->mes = 1;
        this->anio++;
    }

    //Si el dia del mes es mayor al maximo, pero no estamos en el mes 12, incrementamos
    //el mes y ponemos el dia en 1.
    if(this->dia > diaMax[this->mes]){
        this->dia = 1;
        this->mes++;
    }

    return *this;
}

Fecha Fecha::operator+(const int &i) const{ //f+i (siendo i un numero entero)

    Fecha fe(*this);
    for(int j=0; j<i; j++)
        fe++;
    return fe;
}

Fecha operator+(const int &i, const Fecha &f) //i+f (siendo i un entero)
{
    Fecha fe(f);
    for(int j=0; j<i; j++)
        fe++;
    return fe;
}

