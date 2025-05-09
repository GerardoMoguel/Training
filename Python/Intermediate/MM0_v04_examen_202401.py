# -*- coding: utf-8 -*-
"""
Created on Mon Apr  5 15:48:03 2021

@author: Rafael

Proceso 1) Obtener las estadísticas sobre:
    a) Tiempo medio que los clientes en el sistema;
    b) Tiempo medio de espera en cola por el total de los clientes;
    c) Cantidad de clientes que estuvieron esperando en cola;
    d) Cantidad de clientes que no hicieron fila;
    e) Tiempo medio en cola de los clientes que estuvieron esperando en la fila;
    f) Tiempo de ocupación de la estación de servicio y su proporción
       al tiempo total.
       
       
  Para la solución de este ejercicio se agregan a 
  i) Cliente, atributos del valor del tiempo para los eventos:
        LLegada al sistema;
        Salida de cola;
        Salida de la estación;
  
  ii) En la Clase GenCltes se agrega una lista para almacenar los clientes y 
      un método para el procesamiento del reporte.
         
  Al finalizar la simulación se obtiene el reporte solicitado con tales datos.

Proceso 2) Controlar con una variable booleana que un porcentaje de
              los clientes (20%) tarde en media un 75% mas que lo "normal".
              Al entrer a la estación de servicio y calcular el tiempo de atención
              se genera el tiempo de atención en función del valor de la variable
              booleana global solicitada.


"""
"""
[2.0] Ejercicio 1) Defina una variable en el programa principal SOLO_REPORTE
             para que se obtenga una salida reducida que contenga:
                 
             DELTA_T_PROM_LLEGADAS_CLTES, DELTA_T_PROM_ATENCION, PORC_CLTES_COMPLEJOS, PORC_DELTA_T_ATENCION, Cantidad_de_clientes_atendidos  

[2.0] Ejercicio 2) Tome de los argumentos en la línea de comandos los valores para
             
             DELTA_T_PROM_LLEGADAS_CLTES, DELTA_T_PROM_ATENCION, PORC_CLTES_COMPLEJOS, PORC_DELTA_T_ATENCION

[2.0] Ejercicio 3) Cree un documento de word y agregue la evidencia de la ejecución con la salida reducida

[2.0] Ejercicio 4) Libere la Aleatorización y ejecute 5 corridas para cada combinación de
             PORC_CLTES_COMPLEJOS = 10,15,20,25 y 30
             cree el .csv correspondiente con los títulos adecuados.

[2.0] Ejercicio 5) Obtenga la gráfica en excel con el PORC_CLTES_COMPLEJOS vs Cantidad_de_clientes_atendidos.             
            
"""

# =============================================================================
#                                Utilerías Generales
# =============================================================================
import sys
import time
import datetime
import numpy as np
import random
import argparse
import sys






cadsep = "=" * 60
# -----------------------------------------------------------------------------
CU     = "209304"
NOMBRE = "GERARDO_ANDRES_MOGUEL_ROVELO"
str_dateTime = datetime.datetime.now().strftime('%d-%m-%Y %H:%M:%S')
# -----------------------------------------------------------------------------

SOLO_REPORTE = True

def usuario():
    print(cadsep)
    print("CU:" + str(CU) + " " + NOMBRE)
    print(" "*20 + str_dateTime)
    print(cadsep)

def letrero(strLetrero):
    print(cadsep)
    print("          " + strLetrero)
    print(cadsep)

def salir(strLetrero):
    letrero(strLetrero)
    sys.exit()	



# =============================================================================
#
# =============================================================================
class Evento:
    def __init__(self,tipo,cuando):
        self.tipoEvento       = tipo
        self.tiempo_ejecucion = cuando

    def tipo(self):
        return self.tipoEvento

    def __str__(self):
        return "Evento de tipo: " + str(self.tipoEvento) + " al tiempo " + str(self.tiempo_ejecucion)

class Temporizador:
    
   def __init__(self,nombreTempo):
       self.nombre = nombreTempo
       self.cola_eventos = []

   def fijaEstructuras(self,fila,estacion,gen_cltes):
       self.fila      = fila
       self.estacion  = estacion
       self.gen_cltes = gen_cltes
#
#  rutina de burocracia interna
#       
   def ordenEvento(self,evento):
       return evento.tiempo_ejecucion
       
   def agregaEvento(self,evento):
       self.cola_eventos.append(evento)
       self.cola_eventos.sort(key=self.ordenEvento)

   def procesaSigEvento(self):
       #
       # toma el siguiente evento,si no hay evento regresa False
       #
       # Si se tiene un evento:
       # Si es una LLEGADA_DE_CLTE:
       #    Solicita al Generador que cree un clte (y lo forme en cola).
       #
       # Si es una LIBERACION_DE_ESTACION:
       #    Solicita a la Estación de Servicio que se libere.
       # fin.
       global tiempo_actual
       evento = None
       if len(self.cola_eventos) > 0:
         evento = self.cola_eventos.pop(0)
        
       if evento is not None:
           tiempo_actual = evento.tiempo_ejecucion
           tipo_evento = evento.tipo()
           if tipo_evento == "LLEGADA_DE_CLTE":
             self.gen_cltes.llegadaDeClte(self.fila)  
           if tipo_evento == "LIBERACION_ESTACION":
            self.estacion.libera()   
       
       return evento is not None
    
   def __str__(self):
       strRes = "Temporizador " + str(self.nombre) + '\n'
       if self.cola_eventos:
           for x in self.cola_eventos:
               strRes += str(x) + '\n'
       else:
           strRes += "...sin eventos..."
       return strRes    

class GenCltes():
    cuantosClientesVan = 0
    def __init__(self, temporizador,delta_t_prom):
        self.temporizador = temporizador
        self.deltaT_prom  = delta_t_prom
        self.colCltes     = []
        
    def llegadaDeClte(self,cola):
        #
        # "Sembrar" la hora de la llegada del siguiente clte;
        # Crear un nuevo cliente;
        # Mandarlo a formarse a la fila.
        #
        global tiempo_actual
        global T_MAX
        deltaT = generaTiempoExponencial(self.deltaT_prom)
        if tiempo_actual + deltaT <= T_MAX:
          temporizador.agregaEvento(Evento("LLEGADA_DE_CLTE",tiempo_actual + deltaT))
          GenCltes.cuantosClientesVan += 1
          clte = Cliente(GenCltes.cuantosClientesVan)
          clte.llegada_al_sistema = tiempo_actual
          self.colCltes.append(clte)
          cola.forma(clte)
          
          
          

    def repEstadisticasCltes(self):
        
        global tiempo_actual
        
        tiempo_espera_fila     = 0.0
        tiempo_en_sistema      = 0.0
        cantidad_cltes_bypass  = 0
        cantidad_total_cltes   = len(self.colCltes)
        tiempo_atencion        = 0.0
        
        for clte in self.colCltes:
            tiempo_espera_fila    += (clte.salida_de_cola     - clte.llegada_al_sistema) 
            tiempo_en_sistema     += (clte.salida_de_estacion - clte.llegada_al_sistema) 
            tiempo_atencion       += (clte.salida_de_estacion - clte.salida_de_cola) 
            cantidad_cltes_bypass += 1 * (tiempo_espera_fila == 0)
            
        promedio_tiempo_en_sistema            = tiempo_en_sistema   / cantidad_total_cltes
        promedio_tiempo_espera_fila           = tiempo_espera_fila / cantidad_total_cltes
        promedio_tiempo_atencion              = tiempo_atencion    / cantidad_total_cltes
        cltes_espera_efectiva                 =  cantidad_total_cltes - cantidad_cltes_bypass
        
        if cltes_espera_efectiva > 0:
            promedio_tiempo_espera_fila_no_baypass = tiempo_espera_fila / cltes_espera_efectiva
        else:
            promedio_tiempo_espera_fila_no_bypass = None
            
        tiempo_ocio_estacion = tiempo_actual - tiempo_atencion
        porc_tiempo_ocio     = tiempo_ocio_estacion / tiempo_actual
        
        
        if(SOLO_REPORTE == False):
            print(cadsep)
            print(" " * 10 + "Estadísticas de la simulación:")
            print("              Tiempo total de la simulación:   {:>9,.2f}".format(tiempo_actual))
            print("             Cantidad de clientes atendidos:   {:>6d}".format(cantidad_total_cltes))       
            print("  Cantidad de clientes que no hicieron cola:   {:>6d}".format(cantidad_cltes_bypass)) 
            print("         Tiempo medio general en el sistema:   {:>9,.2f}".format(promedio_tiempo_en_sistema)) 
            print("     Tiempo medio general de espera en cola:   {:>9,.2f}".format(promedio_tiempo_espera_fila)) 
            print("            Tiempo medio de espera efectiva:   {:>9,.2f}".format(promedio_tiempo_espera_fila_no_baypass)) 
            print("                   Tiempo medio de atención:   {:>9,.2f}".format(promedio_tiempo_atencion))       
            print("              Tiempo de ocio de la estación:   {:>9,.2f}".format(tiempo_ocio_estacion)) 
            print("           Porcentaje de uso de la estacion:   {:>10.2%}".format(1.0-porc_tiempo_ocio))
            print("Porcentaje de tiempo de ocio de la estacion:   {:>10.2%}".format(porc_tiempo_ocio))
            print(cadsep)
            
    
    def dameClteTotal(self):
        clientes = self.cuantosClientesVan
        return clientes


class Cliente:
    #
    # el objeto cliente es solamente un carrier o placeholder para otros datos (estadísticas, etc.)
    #
    def __init__(self,numClte):
        self.num_clte           = numClte 
        self.llegada_al_sistema = 0
        self.salida_de_cola     = 0
        self.salida_de_estacion = 0

        
    def __str__(self):
        return "Clte No." + str(self.num_clte)
    
class Fila:
    def __init__(self, nombreFila):
        self.nombre = nombreFila
        self.lista_fila = []
        
    def asignaEstacionDeServicio(self,estacion_de_servicio):
        self.estacionDeServicio = estacion_de_servicio

    def forma(self,elto):
        global ventanilla
        global tiempo_actual
        # forma al cliente en la fila
        self.lista_fila.append(elto)
        elto.lLegada_al_sistema = tiempo_actual 
        reportaEvento(' se forma el ',elto)
        # Si es el único cliente avisa a la Estación de servicio que revise la cola.
        if len(self.lista_fila) == 1:
           ventanilla.revisaCola()            
    
    def sigElto(self):
        if not self.vacia():
            elto = self.lista_fila.pop(0)
        else:
            elto = None
        return elto    
        
    def __str__(self):
        strRes = self.nombre + '\n'
        if len(self.lista_fila):
            for x in self.lista_fila:
              strRes += str(x) + '\n'
        else:
            strRes += "Fila vacía" + '\n'
        return strRes    
    
    def vacia(self):
        return len(self.lista_fila) == 0
    
class EstacioDeServicio():
   
   def __init__(self,temporizador,cola,delta_t_prom_atencion):
       self.temporizador     = temporizador
       self.edo              = 'DESOCUPADA'
       self.deltaT_prom      = delta_t_prom_atencion # en minutos
       self.clte_en_atencion = None
       self.acum_deltaT      = 0.0
       
       
   def asignaFila(self,fila):    
       self.cola = fila
       
   def revisaCola(self):
       #
       # Si estas DESOCUPADA:
       #    Si hay un cliente en la cola:
       #         tomar el cliente y "atenderlo" (genera su tiempo de atención)
       #         y siembra el evento de LIBERACION_DE_ESTACION
       #
       global tiempo_actual
       global PORC_CLTES_COMPLEJOS
       global PORC_DELTA_T_ATENCION
       if self.edo == 'DESOCUPADA':
          clte = self.cola.sigElto()
          if clte is not None: 
             clte.salida_de_cola = tiempo_actual
             self.clte_en_atencion = clte
             deltaT_a_usar = self.deltaT_prom
             if np.random.uniform() <= 0.1 * PORC_CLTES_COMPLEJOS:
                deltaT_a_usar *= (1.0 + 0.01 * PORC_DELTA_T_ATENCION) 
             deltaT = generaTiempoExponencial(deltaT_a_usar)
             if SOLO_REPORTE == False:
                 reportaEvento(' se ocupa la ventanilla por el ',clte)
             self.edo = 'OCUPADA'
             self.acum_deltaT += deltaT
             temporizador.agregaEvento(Evento("LIBERACION_ESTACION",tiempo_actual + deltaT))
             
   def libera(self):
       #
       # El cliente es desalojado
       # El estado de la estación pasa a ser DESOCUPADA
       # Se dispara el método de revisaCola()
       #
       global tiempo_actual
       if SOLO_REPORTE == False:
           reportaEvento(' se libera la ventanilla por el ',self.clte_en_atencion)
       self.clte_en_atencion.salida_de_estacion = tiempo_actual
       self.clte_en_atencion = None
       self.edo              = 'DESOCUPADA'
       self.revisaCola()

def generaTiempoExponencial(deltaT_prom):
    deltaT = - deltaT_prom * np.log(np.random.uniform())
    return deltaT

def reportaEvento(letrero_de_evento,clte):
    global tiempo_actual
    if SOLO_REPORTE == False:
        print('{:10.3f}'.format(tiempo_actual) + ': ' + letrero_de_evento + ' --> ' + str(clte))
    
    
# =============================================================================
#
# =============================================================================

if SOLO_REPORTE == False:
    usuario()

#DELTA_T_PROM_LLEGADAS_CLTES = 300
#DELTA_T_PROM_ATENCION       = 300

#PORC_CLTES_COMPLEJOS  =  0.0 
#PORC_DELTA_T_ATENCION = 75.0  

parser = argparse.ArgumentParser(description='Simulación de una estación de servicio')
parser.add_argument('DELTA_T_PROM_LLEGADAS_CLTES', type=float, help='Tiempo promedio entre llegadas de clientes')
parser.add_argument('DELTA_T_PROM_ATENCION', type=float, help='Tiempo promedio de atención')
parser.add_argument('PORC_CLTES_COMPLEJOS', type=float, help='Porcentaje de clientes complejos')
parser.add_argument('PORC_DELTA_T_ATENCION', type=float, help='Porcentaje del delta de tiempo de atención')
args = parser.parse_args()

DELTA_T_PROM_LLEGADAS_CLTES = args.DELTA_T_PROM_LLEGADAS_CLTES
DELTA_T_PROM_ATENCION = args.DELTA_T_PROM_ATENCION
PORC_CLTES_COMPLEJOS = args.PORC_CLTES_COMPLEJOS
PORC_DELTA_T_ATENCION = args.PORC_DELTA_T_ATENCION




ALEATORIZA = False

if ALEATORIZA:
    SEMILLA = int(1000.0*random.random())
else:
    SEMILLA = 1234
    
np.random.seed(SEMILLA)

if SOLO_REPORTE == False:
    print("SEMILLA:",SEMILLA)

tiempo_actual = 0
T_MAX         = 100*120 # en segundos)

temporizador = Temporizador('Temporizador de la simulacion')
fila =  Fila('Cola del Banco')
ventanilla = EstacioDeServicio(temporizador,fila,DELTA_T_PROM_ATENCION)
fila.asignaEstacionDeServicio(ventanilla)
ventanilla.asignaFila(fila)

genCltes = GenCltes(temporizador, DELTA_T_PROM_LLEGADAS_CLTES)
temporizador.fijaEstructuras(fila, ventanilla, genCltes)
#
# Evento primigenio
#
temporizador.agregaEvento(Evento('LLEGADA_DE_CLTE',generaTiempoExponencial(DELTA_T_PROM_LLEGADAS_CLTES))) 

while temporizador.procesaSigEvento():
    #print(tiempo_actual,' ... simulando...')
    pass

if SOLO_REPORTE == False:
    print(cadsep)
    print("Delta T promedio entre llegadas de clientes: {:>8,.2f}".format(DELTA_T_PROM_LLEGADAS_CLTES))
    print("    Delta T promedio atención en ventanilla: {:>8,.2f}".format(DELTA_T_PROM_ATENCION))
    print(cadsep)
    if PORC_CLTES_COMPLEJOS > 0.0:
      print("Porcentaje de clientes complejos:  {:>4.2%}".format(0.01*PORC_CLTES_COMPLEJOS))
      print("Sobrecarga del tiempo de atención: {:>4.2%}".format(0.01*PORC_DELTA_T_ATENCION))
      print(cadsep)
      
    genCltes.repEstadisticasCltes()
    
    print("SEMILLA:",SEMILLA)
    
    print('Fin de la simulación')
    
    usuario()
else:
    genCltes.repEstadisticasCltes()
    print(DELTA_T_PROM_LLEGADAS_CLTES,',',DELTA_T_PROM_ATENCION,',',PORC_CLTES_COMPLEJOS,',',PORC_DELTA_T_ATENCION,',',genCltes.dameClteTotal())
    
# =============================================================================
#
# =============================================================================
