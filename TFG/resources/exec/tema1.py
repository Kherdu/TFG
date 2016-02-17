import sys

class Tema1:

	def pregunta11(code):
		print(code)
    	r=""
    	try:
	        exec(code)
	        exec("print(x)")
	        r="ok"
	    except Exception as e:
	        print ("se ha roto")
	    	r="roto"
	    return r