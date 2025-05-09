# -*- coding: utf-8 -*-
"""
Created on Tue Apr 23 11:23:51 2024

@author: andre
"""

#diccionarios
#delimitacion de valores
dicc_a={'Santi':1.72,'Maria':1.76,'Ana':1.62}
llaves = dicc_a.keys()

valores = dicc_a.values()

print(llaves , valores)

print (type(llaves)) #es un diccionario de keys y el de valores es de valores
#si quisiera pasarlo a una lista.
print(type(list(valores))) #hacemos un cast y la convertimos a lista that easy the fuck

valores_lista = list(valores)
valores_lista[0] #casteamos valores a lista y obtenemos valor del indice 0
print(dicc_a['Ana']) #accedemos al vaor por medio del key

import statistics as st

st.mean(valores_lista) #mean es la media de los valores por medio del import statistics
st.mean((list(dicc_a.values())))


dicc_b = {'Arturo':{'ap':9,'Eda':8,'DAI':9},'Paco':{'ap':8},'Maria':{'ap':10,'Eda':10}}
print(dicc_b['Arturo'])
print(dicc_b['Arturo']['DAI'])#aqui puedo especificar la key de la persona y la key de su materia

[st.mean(list(dicc_b[llave].values())) for llave in dicc_b.keys()] #esto lo que hace es sacar la media de cada
#uno de los participantes
st.mean([st.mean(list(dicc_b[llave].values())) for llave in dicc_b.keys()])#calcula la media de todas las medias
#para obtener una media general de todo