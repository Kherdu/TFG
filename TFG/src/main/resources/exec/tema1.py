# -*- coding: UTF-8 -*-
import sys
import json
import tempfile
# el json tiene que tener 3 campos
# campo 1: boolean isCorrect: true si está bien, false si está mal
# campo 2: string typeError: titulo del fallo para poner el mensaje en el label de java
# campo 3: lista de string Hints: pistas adicionales para mostrar
def pregunta11(code):
    
    try:
        exec(code)
        dicc = {}
        things = locals()
        if 'i' in things:
            if things['i'] == 4:
                value1 = True
                value2 = ''
                value3 = ['']
            else:
                value1 = False
                value2 = '¿te suena de algo el numero 4?'
                value3 = ['read again', 'or sth like that']
        else:
            value1 = False
            value2 = 'esta fatal'
            value3 = ['prueba a leer el enunciado otra vez', 'o cortarte las venas']
        dicc['isCorrect'] = value1
        dicc['typeError'] = value2
        dicc['Hints'] = value3
        with open(file, 'w') as outfile:
            json.dump(dicc, outfile)
        sys.exit(0);    
    except Exception as e:
        e.print_exc()
        sys.exit(1)
        #return False
    
def pregunta21(code):
    
    try:
        exec(code)
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
        
   
def pregunta22(code):
    try:
        exec(code)
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

question = sys.argv[1]
code = sys.argv[2]
temp_name = next(tempfile._get_candidate_names())
default_tmp_dir = tempfile._get_default_tempdir()
file = default_tmp_dir + '/' + sys.argv[3]

if question == "pregunta11":
    pregunta11(code)
elif question == "pregunta21":
    pregunta21(code)
elif question == "pregunta22":
    pregunta22(code)


