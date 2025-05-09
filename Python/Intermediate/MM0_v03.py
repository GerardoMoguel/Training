# -*- coding: utf-8 -*-
"""
Created on Mon Apr  5 15:48:03 2021

@author: Rafael
"""

# =============================================================================
#                                Utilerías Generales
# =============================================================================
import sys
import time
import datetime
import numpy as np

cadsep = "=" * 60
# -----------------------------------------------------------------------------
CU     = "CLAVE UNICA"
NOMBRE = "NOMBRE(S) PRIMER_APELLIDO SEGUNDO_APELLIDO"
str_dateTime = datetime.datetime.now().strftime('%d-%m-%Y %H:%M:%S')
# -----------------------------------------------------------------------------
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
        cola.forma(clte)


class Cliente:
    #
    # el objeto cliente es solamente un carrier o placeholder para otros datos (estadísticas, etc.)
    #
    def __init__(self,numClte):
        self.num_clte  = numClte 
        
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
        # forma al cliente en la fila
        self.lista_fila.append(elto)
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
       self.temporizador = temporizador
       self.edo = 'DESOCUPADA'
       self.deltaT_prom = delta_t_prom_atencion # en minutos
       self.clte_en_atencion = None
       
       
   def asignaFila(self,fila):    
       self.cola = fila
       
   def revisaCola(self):
       #
       # Si estas DESOCUPADA:
       #    Si hay un cliente en la cola:
       #         tomar el cliente y "atenderlo" (genera su tiempo de atención)
       #         y siembra el evento de LIBERACION_DE_ESTACION
       #
       global t_actual
       if self.edo == 'DESOCUPADA':
          clte = self.cola.sigElto()
          if clte is not None: 
             self.clte_en_atencion = clte
             deltaT = generaTiempoExponencial(self.deltaT_prom)
             reportaEvento(' se ocupa la ventanilla por el ',clte)
             self.edo = 'OCUPADA'
             temporizador.agregaEvento(Evento("LIBERACION_ESTACION",tiempo_actual + deltaT))
             
   def libera(self):
       #
       # El cliente es desalojado
       # El estado de la estación pasa a ser DESOCUPADA
       # Se dispara el método de revisaCola()
       #
       reportaEvento(' se libera la ventanilla por el ',self.clte_en_atencion)
       self.cliente_en_atencion = None
       self.edo = 'DESOCUPADA'
       self.revisaCola()

def generaTiempoExponencial(deltaT_prom):
    deltaT = - deltaT_prom * np.log(np.random.uniform())
    return deltaT

def reportaEvento(letrero_de_evento,clte):
    global tiempo_actual
    print('{:10.3f}'.format(tiempo_actual) + ': ' + letrero_de_evento + ' --> ' + str(clte))
    
    
# =============================================================================
#
# =============================================================================

usuario()

np.random.seed(1234)
tiempo_actual = 0
T_MAX    = 100*60 # en segundos)

temporizador = Temporizador('Temporizador de la simulacion')
fila =  Fila('Cola del Banco')
ventanilla = EstacioDeServicio(temporizador,fila,300)
fila.asignaEstacionDeServicio(ventanilla)
ventanilla.asignaFila(fila)

genCltes = GenCltes(temporizador, 300)
temporizador.fijaEstructuras(fila, ventanilla, genCltes)
#
# Evento primigenio
#
temporizador.agregaEvento(Evento('LLEGADA_DE_CLTE',generaTiempoExponencial(400))) 

while temporizador.procesaSigEvento():
    #print(tiempo_actual,' ... simulando...')
    pass

print('Fin de la simulación')
usuario()
