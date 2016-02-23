# -*- coding: UTF-8 -*-
import sys

def pregunta11(codigo):
    print("Esto es la pregunta 11")
    print(codigo)
    try:
        exec(codigo) #desde aquí poner el código de corrección 
        r=["Rafa","Carlos", 32,27]
        if set(locals()['l']) == set(r) :
            print("todo correcto")
            return True
        else:
            print("la variable no contiene el valor correcto")
            return False
    except Exception:
        print("ROTO")
        return False
        
    
