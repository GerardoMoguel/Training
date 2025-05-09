# -*- coding: utf-8 -*-
"""
Created on Thu Apr 18 12:09:10 2024

@author: hca
"""

print ("Hola mundo")
help("keywords")

x=10
y=12.5
x_y=x+y

z='100'

x_z = str(x)+z
x_z = x+int(z)

a=17
b=3
division=a/b
cociente = a//b
residuo=a%b 

mi_x = 12345

print(mi_x, ' ',id(mi_x))

mi_x=mi_x+1 #aqui lo que hace es crear un objeto nuevo, porque es un distinto ID

print(mi_x,' ',id(mi_x)) #id es la direccion de memoria que se le asigna, estamos
#viendo como la direccion de memoria es diferente en las dos variables.

import math

a=123456789
b=100

print (a,b,math.pi)
print('El valor de pi es:%6.3f '%math.pi)#lo que esta despues del porcentaje significa
#6 enteros y 3 decimales
print('b={2}, el valor de pi es = {0:6.3f}, a={1}'.format(math.pi,a,b)) #el 0:6.3f es lo mismo 
#que el 6 enteros 3 decimales de arriba pero el 0 de a lado representa el indice del que toma el argumento


#ejemplo de cadenas que se delimitan con "" o con '',son inmutables por lo que no se 
#puede modificar su valor
cad_1= 'Hola'
cad_2='Adios'
print(cad_1,cad_2)
print(cad_1+cad_2)
print("hola's") #esta es una forma para poder tomar las apostrolfes, se usa comillas dobles
print(3*cad_1)
#inmutables
mi_string_a = 'Hola ITAM'
mi_string_b = mi_string_a
mi_string_a = 'Hola DAI'
print(mi_string_a,mi_string_b)

#slicing
#significa acceder al contenido
cad_3 = "Murcielago"
print (len(cad_3))
print (cad_3[0])
print (cad_3[-10])
print (cad_3[2:5]) #[2,5]
print (cad_3[5:]) #desde el 5 hasta el final
print(cad_3[::2])
print(cad_3[::-1]) #orden inverso

#listas se delimita [con estos corcheters]

str_a = 'AAAA'
lista_a = ['a','e','i','o','u']
otra_lista = [0, 'Luis',34.5]

list_b = [1000, lista_a, 10,str_a]
print(list_b)
str_a='-----AAAAA'
lista_a[3]='Python'
print(list_b)

