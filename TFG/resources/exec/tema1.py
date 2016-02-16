import sys

def pregunta11(code):
    print(code)
    try:
        exec(code)
        exec("print(x)")
    except Exception as e:
        print ("se ha roto")
    
    pass
