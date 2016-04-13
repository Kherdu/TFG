import sys
def pregunta11(codigo):
    print("Esto es la pregunta 11")
    print(codigo)
    pass

def pregunta21(codigo):
    print("Esto es la pregunta 21")
    print(codigo)
    pass

def pregunta22(codigo):
    print("Esto es la pregunta 22")
    print(codigo)
    pass

pregunta = sys.argv[1]
codigo = sys.argv[2]

if pregunta == "pregunta11":
    pregunta11(codigo)
elif pregunta == "pregunta21":
    pregunta21(codigo)
elif pregunta == "pregunta22":
    pregunta22(codigo)
