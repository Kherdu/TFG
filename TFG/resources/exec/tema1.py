# -*- coding: UTF-8 -*-
import sys

def pregunta11(codigo):
    print("Esto es la pregunta 11")
    #print(codigo)
    try:
        exec(codigo)
        if locals()['i']== 4:
           # print("todo correcto")
            sys.exit(0);
           # return True
        else:
            #print("la variable no contiene el valor correcto")
            sys.exit(1)
           # return False
    except Exception:
        #print("ROTO")
        sys.exit(-1)
       # return False

def pregunta21(codigo):
    print("Esto es la pregunta 21")
    #print(codigo)
    try:
        exec(codigo)
        if locals()['cadena']=="Hola mundo":
            #print("Todo correcto")
            sys.exit(0)
            #return True
        else:
           # print("Fallo en el valor")
            sys.exit(1)
           # return False
    except Exception:
            #print("Variable no correcta")
            sys.exit(-1)
            #return False
        
   
def pregunta22(codigo):
    print("Esto es la pregunta 22")
    #print(codigo)
    try:
        exec(codigo)
        lista = ["gato", "perro", "burro"]
        if set(lista)==set(locals()['lista_animales']):
           # print("Todo corecto")
            sys.exit(0)
            #return True;
        else:
           # print("Fallo de valor")
            sys.eixt(1)
           # return False;
    except Exception:
        #print ("Variable no buena")
        sys.exit(-1)
       # return False

pregunta = sys.argv[1]
codigo = sys.argv[2]

if pregunta == "pregunta11":
    pregunta11(codigo)
elif pregunta == "pregunta21":
    pregunta21(codigo)
elif pregunta == "pregunta22":
    pregunta22(codigo)


