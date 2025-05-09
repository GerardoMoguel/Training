# -*- coding: utf-8 -*-
"""
Created on Tue Apr 23 11:52:36 2024

@author: andre
"""

#funciones
def suma (a,b):
    return a+b

#los argumentos por valor
res = suma(5,3)

#los argumentos se pueden llamar por nombre
res = suma(b=15 , a=2)

print (res)
def otra(x,y):
    return x*2+y*100

r=otra(y=2,x=3) #no importaa el orden en que pones los argumentos, con que matchee el nombre
# de la variable

def resta (x=1,y=None):
    if y==None:
        return x
    else:
        return x-y
    
print(resta(y=2))

#funcion que regresa mas de un valor

import math
#metodo que regresa dos resultados
def circulo(radio):
    return math.pi*radio**2, math.pi*2*radio #internamente es como un arreglo
    
area , perimetro = circulo(3) #de esta forma podemos asignar los resultados de las variables

print (area)
print (perimetro)
print (type(circulo(3)))
circulo(3)[0] #tambien lo podemos imprimir como si fuera una tupla

nombre = 'Ana Lidia    '
def quitaEspacios(palabra):
    """
        elimina los espacios al final de una palabra
        in: una palabra con espacios al final
        out: la palabra sin espacios al final y el largo de la palabra
    """
    palabra = palabra.strip()
    return palabra, len(palabra)

print (len(nombre))

pal, largo = (quitaEspacios(nombre))
print (pal, largo)

#funcion de orden superior o sea f o g, funciones anidadas dentro de otras
def saludar(idioma):
    """
    calcula el saludo en un idioma dado
    in:idioma
    out:el saludo en ese idioma
    """
    def esp():
        print("hola")
    def ing():
        print ("hello")
    def fra():
        print("salut")
    idiomaFun = {'es':esp,'in':ing,'fr':fra}
    return idiomaFun[idioma]

saludar('fr')() #el () es porque es una funcion dentro de una funcion

#funcion lambda es anonima  y desechable
#tambien se llaman abreviadas
# solo pueden tener una instruccion
#pueden tener varios parametros
#agilizan la proframacion
#solo se usa donde fue creada

suma = lambda a,b : a+b
suma(3,5)

#iteraciones de lambda con funciones de orden superior
#map(funcion, iterable)
#map requiere una funcion y un iterable
def cubo (a):
    return a**3

lista = [1,2,3,4,5]
[cubo(i) for i in lista]

#esto no debemos de hacerlo
l = []
for i in lista:
    l.append(cubo(i))
    
print(list(map(cubo,lista)))
#mismo pero con lambda
print(list(map(lambda x : x**3, lista)))

#filter funcion para filtrar
print (list(filter(lambda  x : x%2 == 0 , lista))) #lista en la que unicamente se pide los pares

#reduce
from functools import reduce
resultado = reduce(lambda x,y : x+y, lista)
print (resultado)

#archivos, esta es la forma de 
f = open("C:\\Users\\andre\\OneDrive\\Escritorio\\DAI\\Python\\texto1.txt","r")
print(f.read())
f.close()

def lee_archivo(ruta):
    archivo = open(ruta,"r")
    return archivo.read()

f1=lee_archivo(f)
print(f1)

def lee_archivo2(ruta):
    id=open(ruta , 'r')
    contenido = id.read()
    lista = contenido.split('\n')
    id.close() 
    return lista

f2 = lee_archivo2("C:\\Users\\andre\\OneDrive\\Escritorio\\DAI\\Python\\MuchasCURPS.csv")
print(f2[:10])

print (f2[0][10]) #imprime la letra 11 del indice 0

help('str.split')
    
#crea una una funcion que reciba un alista de CURP y devuelva el 
# % de M y el % H 
print (len(f2))
lstGenero = [curp[10] for curp in f2]
print(lstGenero.count('H'))

def porcentaje_H_M(lista):
    listaGEN = [curp[10] for curp in lista] # para que nos saque todo
    diccGenero['M'] = listaGEN.count('M')/len(lista)*100
    diccGenero['H'] = listaGEN.count('H')/len(lista)*100
    return diccGenero

print (porcentaje_H_M(lstGenero))

# crea una función que reciba una lista de CURP una lista con la cantidad de
#personas nacida x mes
# IN lista de CURP
# OUT lst de personas nacidas x mes [1] nacidas en enero, etc

def cuentaMes(lstCURP):
    lstMes = [int(curp[6:8]) for curp in lstCURP] # para que nos saque todos los mes 1
    lstCuantos = [0]
    for mes in range (1,13):
        lstCuantos.append(lstMes.count(mes))
    return lstCuantos

print(cuentaMes(f2))

# QUE cuenta la cantidad de personas para cada estado existen en la lista
#de CURPs
# IN lista CURP
# OUT diccionario llave = estado valor = cantidad de personas
def cantEstado(lstCURP):
    lstEdo = [int(curp[11:13]) for curp in lstCURP] # para que nos saque todos los mes 1
    setEdo = set(lstEdo)
    lstCant = [[edo,lstEdo.count(edo)]for edo in setEdo]
    dictEdo = dict(lstCant)
    return dictEdo

print(cantEstado(f2)) 