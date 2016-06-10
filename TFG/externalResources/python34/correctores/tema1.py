# -*- coding: UTF-8 -*-
import sys
import json
import tempfile
# el json tiene que tener 3 campos
# campo 1: boolean isCorrect: true si está bien, false si está mal
# campo 2: string typeError: titulo del fallo para poner el mensaje en el label de java
# campo 3: lista de string Hints: pistas adicionales para mostrar
def pregunta11(code, filename):
    
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
                value2 = 'Valor incorrecto'
                value3 = ['lee el enunciado de nuevo']
        else:
            value1 = False
            value2 = 'Variable no encontrada'
            value3 = ['prueba a leer el enunciado otra vez']
        dicc['isCorrect'] = value1
        dicc['typeError'] = value2
        dicc['Hints'] = value3
        with open(filename, 'w') as outfile:
            json.dump(dicc, outfile)
        sys.exit(0);    
    except Exception as e:
        e.print_exc()
        sys.exit(1)
        #return False
    
def pregunta21(code, filename):
    
    try:
        exec(code)
        dicc = {}
        dicc['typeError'] = ''
        if "cadena" in locals():
          dicc['isCorrect'] = (locals()['cadena']=="Hola mundo")
          dicc['Hints'] = ['El valor de la variable \'cadena\' no es el correcto',' * Valor esperado: Hola mundo' ,' * Valor obtenido: ' + str(locals()['cadena']) ]
        else:
          dicc['isCorrect'] = False
          dicc['Hints'] = ['La variable \'cadena\' no ha sido definida']
       
        with open(filename, 'w') as outfile:
            json.dump(dicc, outfile)
        sys.exit(0)
    except Exception as e:
        print(e)
        sys.exit(-1)
        #return False
        
   
def pregunta22(code, filename):
    # TODO arreglar este código, no genera JSON
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


def main():
  question = sys.argv[1]
  code = sys.argv[2]
  temp_name = next(tempfile._get_candidate_names())
  default_tmp_dir = tempfile._get_default_tempdir()
  filename = default_tmp_dir + '/' + sys.argv[3]

  if question == "pregunta11":
    pregunta11(code, filename)
  elif question == "pregunta21":
    pregunta21(code, filename)
  elif question == "pregunta22":
    pregunta22(code, filename)
    
    

if __name__ == "__main__":
  main()    
