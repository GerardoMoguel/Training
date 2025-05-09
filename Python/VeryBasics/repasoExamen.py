# -*- coding: utf-8 -*-
"""
Created on Thu May  9 11:47:05 2024

@author: andre
"""

#esto es para hacer listas
x = [1,2,3,4]
x.append([4,5])#esto agrega la lista dentro de la lista
print(x)
x.extend([6,7]) #esto lo aggrega a la lista sin meter una lista, sino que lo agrega como contenido
print(x)

#vamos a ver cuantos nombres de una lista hay con mas de dos A's
datos=[['Mariel','Maria','Natalia','Miguel'],['Ana','Luis','Paco']]
#como tenemos dos listas, necesitamos un for para sacar las listas
#luego tenemos que sacar el nombre dentro de la lista para despues ya analizarlo
#queremos regresar una lista

res=[]
for lista in datos:
    for nombre in lista:
        if nombre.count('a')>=2:
            res.append(nombre)
            
print (res)

result = [nombre for lista in datos for nombre in lista if nombre.count('a')>=2]
print(result)

paises = ['China','India','Indonesia','Estados Unidos']
poblaciones = [123,345,567,789]

#aqui convertimos a lista, pero el zip lo que hace es una tupla de las cosas que le pedimos convertir a tupla
list(zip(paises,poblaciones))

#de forma bonita
for pais, poblaciones in zip(paises,poblaciones):
    print("{} : {} millones de habitantes.").format(pais,poblaciones)
    
#diccionario con palabras de una frase como llave y el largo como valor
def cuantas(frase):
    palabras = frase.split()
    tamanho = map(len,palabras)
    return dict(zip(palabras,tamanho))
print(cuantas('esta es la clase de DAI'))

def cuantas2(frase):
    return {palabra:len(palabra) for palabra in frase.split()}
print(cuantas2("esta es la clase de DAI"))

 















