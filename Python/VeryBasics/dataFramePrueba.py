# -*- coding: utf-8 -*-
"""
Created on Fri May  3 10:54:33 2024

@author: emili
Programa para ordenar los datos del proyecto
"""
import pandas as pd 
import matplotlib.pyplot as plt


data=pd.read_csv("C:\\Users\\andre\\OneDrive\\Escritorio\\DAI\Python\\proyecto_2\\nonComunicableDiseasesByCountry.csv")
data2=pd.read_csv("C:\\Users\\andre\\OneDrive\\Escritorio\\DAI\\Python\\proyecto_2\\nonComunicableDiseasesByCauseByCountry-1.csv")
#Renombrar las columnas de data
data=data.rename(columns={'Unnamed: 0':'Country'})
data=data.rename(columns={'Unnamed: 1':'Year'})
data=data.rename(columns={'Noncommunicable diseases':'StdBoth sexes'}) #Representa la edad estandarizada 
data=data.rename(columns={'Noncommunicable diseases.1':'StdMale'})
data=data.rename(columns={'Noncommunicable diseases.2':'StdFemale'})
data=data.rename(columns={'Noncommunicable diseases.3':'TBothSexes'})
data=data.rename(columns={'Noncommunicable diseases.4':'TMale'})
data=data.rename(columns={'Noncommunicable diseases.5':'TFemale'})
#Renombrar las columnas de data2 
data2=data2.rename(columns={'Unnamed: 0':'Country'})
data2=data2.rename(columns={'Unnamed: 1':'Year'})
data2=data2.rename(columns={'Unnamed: 2':'Causes'})
data2=data2.rename(columns={'Number of deaths attributed to non-communicable diseases, by type of disease and sex':'NDBothSexes'})
data2=data2.rename(columns={'Number of deaths attributed to non-communicable diseases, by type of disease and sex.1':'NDMale'})
data2=data2.rename(columns={'Number of deaths attributed to non-communicable diseases, by type of disease and sex.2':'NDFemale'})
#Revisar cuantos datos nan hay en el dataframe 
data.isnull().sum()
data2.isnull().sum()
#Eliminar los datos nulos y nombres repetidos 
data=data.drop(0)
data=data.drop(1)
data2=data2.drop(0)
#Ordenar los datos 
data=data.sort_values(['Country','Year'])
data2=data2.sort_values(['Country','Year'])
#Revisar que tipo de datos hay 
data.dtypes
data2.dtypes
#Castear los datos de data (Country,Year,Std, T) a str,int y float y revisar nuevamente los tipos de datos 
data['Country']=data['Country'].astype(str)
data['Year']=data['Year'].astype(str).astype(int)
data['StdBoth sexes']=data['StdBoth sexes'].astype(str).astype(float)
data['StdMale']=data['StdMale'].astype(str).astype(float)
data['StdFemale']=data['StdFemale'].astype(str).astype(float)
data['TBothSexes']=data['TBothSexes'].astype(str).astype(float)
data['TMale']=data['TMale'].astype(str).astype(float)
data['TFemale']=data['TFemale'].astype(str).astype(float)
#Castear los datos de data2(Country, Year, Causes, ND) a str, int, str, int y revisar nuevamente los tipos de datos
data2['Country']=data2['Country'].astype(str)
data2['Year']=data2['Year'].astype(int)
data2['Causes']=data2['Causes'].astype(str)
data2['NDBothSexes']=data2['NDBothSexes'].astype(int)
data2['NDMale']=data2['NDMale'].astype(int)
data2['NDFemale']=data2['NDFemale'].astype(int)
#Revisar cómo quedaron
data.head(20)
data2.head(20)

data.shape

#1. En general mueren mas hombres o mujeres por enfermedades no transmitibles en el mundo?
# Sumar el número total de muertes por enfermedades no transmisibles para ambos sexos, hombres y mujeres
total_deaths_both_sexes = data['TBothSexes'].sum()
total_deaths_male = data['TMale'].sum()
total_deaths_female = data['TFemale'].sum()

# Generar un gráfico de barras para comparar el total de muertes entre hombres y mujeres
gender_deaths = pd.DataFrame({'Sexo': ['Hombres', 'Mujeres'], 'Total de Muertes': [total_deaths_male, total_deaths_female]})
gender_deaths.plot(kind='bar', x='Sexo', y='Total de Muertes', color=['blue', 'pink'])
plt.title('Comparación de Muertes por Enfermedades No Transmisibles entre Hombres y Mujeres')
plt.xlabel('Sexo')
plt.ylabel('Total de Muertes')
plt.show()

#2. Cuales son las 5 causas de muerte por enfermedades no transmitibles mas comunes en el mundo?
# Agrupar por causa y sumar el número total de muertes para cada causa
top_causes = data2.groupby('Causes')[['NDBothSexes']].sum().sort_values(by='NDBothSexes', ascending=False).head(5)
print("Las 5 causas de muerte por enfermedades no transmisibles más comunes son:")
print(top_causes)
top_causes.plot(kind='barh', color='skyblue')
plt.title('5 Causas de Muerte por Enfermedades No Transmisibles Más Comunes')
plt.xlabel('Número de Muertes')
plt.ylabel('Causa')
plt.show()


#3. Cual es la enfemedad por la que murieron mas hombres por ENT en el anho 2000?
# Filtrar los datos para el año 2000 y para hombres
data_2000_male = data2[(data2['Year'] == 2000)]

# Encontrar la enfermedad por la que murieron más hombres en el año 2000
max_deaths_2000_male = data_2000_male[data_2000_male['NDMale'] == data_2000_male['NDMale'].max()]
# Encontrar la enfermedad con el numero maximo de muertes
print("La enfermedad por la que murieron más hombres por ENT en el año 2000 es:")
print(max_deaths_2000_male)
# Obtener el nombre de la enfermedad por la que murieron más hombres en 2000
max_disease = max_deaths_2000_male['Causes'].iloc[0]

# Crear el gráfico de barras
plt.figure(figsize=(8, 6))
plt.barh(max_deaths_2000_male['Causes'], max_deaths_2000_male['NDMale'], color='skyblue')
plt.xlabel('Número de muertes')
plt.ylabel('Enfermedad')
plt.title(f'Enfermedad con más muertes en hombres en el año 2000: {max_disease}')
plt.gca().invert_yaxis()  # Invertir el eje y para que la enfermedad con más muertes aparezca en la parte superior
plt.show()

#4. Cual es el pais en el que han muerto mas mujeres por ENT en el anho 2015?
# Filtrar los datos para el año 2015 y para ENT
data_2015_ent = data2[(data2['Year'] == 2015)]

# Ordenar los datos por el número de muertes de mujeres de mayor a menor
data_2015_ent_sorted = data_2015_ent.sort_values(by='NDFemale', ascending=False)

# Seleccionar los primeros 10 países con el mayor número de muertes de mujeres por ENT
top_10_countries_ent_2015 = data_2015_ent_sorted.head(10)

# Crear la gráfica de barras
plt.figure(figsize=(12, 8))
plt.bar(top_10_countries_ent_2015['Country'], top_10_countries_ent_2015['NDFemale'], color='purple')

# Añadir títulos y etiquetas
plt.title('Top 5 países con más muertes de mujeres por ENT en 2015')
plt.xlabel('País')
plt.ylabel('Número de Muertes')
plt.xticks(rotation=45, ha='right')  # Rotar las etiquetas del eje x para una mejor legibilidad

# Mostrar la gráfica
plt.tight_layout()  # Ajusta el diseño para que todo se vea bien
plt.show()

#5. Cual el pais en el que murieron mas personas por diabetes en el 2010?
# Filtrar los datos para el año 2010 y para la causa de diabetes mellitus
data_2010_diabetes = data2[(data2['Year'] == 2010) & (data2['Causes'] == 'Diabetes mellitus')]

# Encontrar el país con el número máximo de muertes por diabetes en 2010
# Filtrar el DataFrame para incluir solo los 10 países con más muertes por diabetes en 2010
#por que si no ponia 200 datos y no se veia nada jeje
top_10_countries_diabetes_2010 = data_2010_diabetes.nlargest(10, 'NDBothSexes')

# Encontrar el país con el número máximo de muertes por diabetes en 2010
country_max_deaths_diabetes_2010 = top_10_countries_diabetes_2010['Country'][top_10_countries_diabetes_2010['NDBothSexes'].idxmax()]
max_deaths_diabetes_2010 = top_10_countries_diabetes_2010['NDBothSexes'].max()

print(f"El país en el que murieron más personas por diabetes en 2010 es: {country_max_deaths_diabetes_2010}, con un total de {max_deaths_diabetes_2010} muertes.")

# Generar la gráfica solo con los 10 países con más muertes
top_10_countries_diabetes_2010.plot(kind='bar', x='Country', y='NDBothSexes', color='orange')
plt.title('Muertes por Diabetes en 2010 por País (Top 10)')
plt.xlabel('País')
plt.ylabel('Número de Muertes')
plt.show()

#6¿Cuál es la tendencia de las muertes por enfermedades no transmisibles a lo largo de los años?
# Agrupar los datos por año y sumar el número total de muertes
total_deaths_yearly = data2.groupby('Year')['NDBothSexes'].sum()

# Generar el gráfico de línea
plt.plot(total_deaths_yearly.index, total_deaths_yearly.values, marker='o', color='green')
plt.title('Tendencia de Muertes por Enfermedades No Transmisibles a lo largo de los Años')
plt.xlabel('Año')
plt.ylabel('Total de Muertes')
plt.grid(True)  # Agregar una cuadrícula al gráfico
plt.show()


#7. Diferencia entre muertes entre hombres y mujeres en el anho 2000
data_year_specific = data2[data2['Year'] == 2000]

# Sumar el número total de muertes para hombres y mujeres
total_deaths_male_year = data_year_specific['NDMale'].sum()
total_deaths_female_year = data_year_specific['NDFemale'].sum()

# Generar el gráfico de barras apiladas
plt.bar(['Hombres', 'Mujeres'], [total_deaths_male_year, total_deaths_female_year], color=['blue', 'pink'])
plt.title(f'Comparación de Muertes por Enfermedades No Transmisibles en 2000 entre Hombres y Mujeres')
plt.xlabel('Sexo')
plt.ylabel('Total de Muertes')
plt.show()

#8. Diferencia entre muertes entre hombres y mujeres en el anho 2015
data_year_specific = data2[data2['Year'] == 2015]

# Sumar el número total de muertes para hombres y mujeres
total_deaths_male_year = data_year_specific['NDMale'].sum()
total_deaths_female_year = data_year_specific['NDFemale'].sum()

# Generar el gráfico de barras apiladas
plt.bar(['Hombres', 'Mujeres'], [total_deaths_male_year, total_deaths_female_year], color=['blue', 'pink'])
plt.title(f'Comparación de Muertes por Enfermedades No Transmisibles en 2015 entre Hombres y Mujeres')
plt.xlabel('Sexo')
plt.ylabel('Total de Muertes')
plt.show()


#9. Cual el pais en el que murieron mas personas por obstruccion pulmonar en el 2010?
# Filtrar los datos para el año 2010 y para la causa obstruccion pulmonar
data_2010_diabetes = data2[(data2['Year'] == 2010) & (data2['Causes'] == 'Chronic obstructive pulmonary disease')]

# Encontrar el país con el número máximo de muertes por obstruccion pulmonar en 2010
# Filtrar el DataFrame para incluir solo los 10 países con más muertes por obstruccion pulmonar en 2010
#por que si no ponia 200 datos y no se veia nada jeje
top_10_countries_diabetes_2010 = data_2010_diabetes.nlargest(10, 'NDBothSexes')

# Encontrar el país con el número máximo de muertes por obstruccion pulmonar en 2010
country_max_deaths_diabetes_2010 = top_10_countries_diabetes_2010['Country'][top_10_countries_diabetes_2010['NDBothSexes'].idxmax()]
max_deaths_diabetes_2010 = top_10_countries_diabetes_2010['NDBothSexes'].max()

print(f"El país en el que murieron más personas por obstruccion pulmonar en 2010 es: {country_max_deaths_diabetes_2010}, con un total de {max_deaths_diabetes_2010} muertes.")

# Generar la gráfica solo con los 10 países con más muertes
top_10_countries_diabetes_2010.plot(kind='bar', x='Country', y='NDBothSexes', color='purple')
plt.title('Muertes por obstruccion pulmonar en 2010 por País (Top 10)')
plt.xlabel('País')
plt.ylabel('Número de Muertes')
plt.show()

#10. Cual el pais en el que murieron mas personas por enfermedades cardiovaculares en el 2005?
# Filtrar los datos para el año 2005 y para la causa de enfermedades cardiovaculares
data_2010_diabetes = data2[(data2['Year'] == 2005) & (data2['Causes'] == 'Cardiovascular diseases')]

# Encontrar el país con el número máximo de muertes por diabetes en 2010
# Filtrar el DataFrame para incluir solo los 10 países con más muertes por enfermedades cardiovaculares en 2005
#por que si no ponia 200 datos y no se veia nada jeje
top_10_countries_diabetes_2010 = data_2010_diabetes.nlargest(10, 'NDBothSexes')

# Encontrar el país con el número máximo de muertes enfermedades cardiovaculares en el 2005
country_max_deaths_diabetes_2010 = top_10_countries_diabetes_2010['Country'][top_10_countries_diabetes_2010['NDBothSexes'].idxmax()]
max_deaths_diabetes_2010 = top_10_countries_diabetes_2010['NDBothSexes'].max()

print(f"El país en el que murieron más personas por obstruccion pulmonar en 2010 es: {country_max_deaths_diabetes_2010}, con un total de {max_deaths_diabetes_2010} muertes.")

# Generar la gráfica solo con los 10 países con más muertes
top_10_countries_diabetes_2010.plot(kind='bar', x='Country', y='NDBothSexes', color='red')
plt.title('Muertes por enfermedades cardiovaculares en 2005 por País (Top 10)')
plt.xlabel('País')
plt.ylabel('Número de Muertes')
plt.show()







