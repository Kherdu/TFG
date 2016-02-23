# -*- coding: UTF-8 -*-
import sys

def pregunta11(codigo):
    print("Esto es la pregunta 11")
    print(codigo)
    try:
        exec(codigo)
        if locals()['i']== 4:
            print("todo correcto")
            return True
        else:
            print("la variable no contiene el valor correcto")
            return False
    except Exception:
        print("ROTO")
        return False

def pregunta21(codigo):
    print("Esto es la pregunta 21")
    print(codigo)
    try:
        exec(codigo)
        if locals()['cadena']=="Hola mundo":
            print("Todo correcto")
            return True
        else:
            print("Fallo en el valor")
            return False
    except Exception:
            print("Variable no correcta")
            return False
        
   
def pregunta22(codigo):
    print("Esto es la pregunta 22")
    print(codigo)
    try:
        exec(codigo)
        lista = ["gato", "perro", "burro"]
        lista.sort
        print(locals()['lista_animales'].sort)
        if locals()['lista_animales'].sort==lista:
            print("Todo corecto")
            return True;
        else:
            print("Fallo de valor")
            return False;
    except Exception:
        print ("Variable no buena")
        return False

pregunta = sys.argv[1]
codigo = sys.argv[2]

if pregunta == "pregunta11":
    pregunta11(codigo)
elif pregunta == "pregunta21":
    pregunta21(codigo)
elif pregunta == "pregunta22":
    pregunta22(codigo)


