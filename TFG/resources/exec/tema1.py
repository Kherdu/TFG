# -*- coding: UTF-8 -*-
import sys

class Tema1:
	def pregunta11(code):
		print(code)
    	try:
        	exec(code)
	        exec("print(x)")
	        r = "bien"
    	except Exception:
	    	print ("se ha roto")
	    	r="roto"
    	return r
    