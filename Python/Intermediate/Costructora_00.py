# -*- coding: utf-8 -*-
"""
Created on Mon Mar 27 12:26:05 2023

@author: psdist
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
#            Estructura Topológica para los proyectos con actividades
# =============================================================================
class EstructuraTopo:
    def __init__(self):
        self.colNodos      = {}
        self.listaOrdenada = []
        self.ordenado      = False
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    def agregaAct(self,idPred,idSuc, nomActividad,tipoRecurso,numRecursos,costo,duracion_dias):
        nodoPred = self.colNodos.get(idPred,None)
        if nodoPred == None:
            nodoPred = NodoTopo(idPred)
            self.colNodos[idPred] = nodoPred

        nodoSuc = self.colNodos.get(idSuc,None)
        if nodoSuc == None:
            nodoSuc = NodoTopo(idSuc)
            self.colNodos[idSuc] = nodoSuc            
        nodoPred.agregaAct(nodoSuc,nomActividad,tipoRecurso,numRecursos,costo,duracion_dias)   
        nodoSuc.num_antecesores += 1                
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------        
    def ordena_topo(self):
        ban = True
        while ban and len(self.colNodos) > 0:
          nodo_trab = None  
          for nodo in self.colNodos.values():
             if nodo.num_antecesores == 0:
                nodo_trab = nodo
                self.colNodos.pop(nodo.id)
                break
          if nodo_trab == None:
              raise Exception("Hay ciclos con el nodo " + str(self))
          self.listaOrdenada.append(nodo_trab)
          for act in nodo_trab.colActividades:
              act.nodoSuc.num_antecesores -= 1
        self.ordenado = True      
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    def restituye_num_antecesores(self):
        for nodo_status in self.listaOrdenada:
            for act in nodo_status.colActividades:
                act.nodoSuc.num_antecesores += 1
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------                
    def calcula_t_finNodo(self,d_inicial):
        self.restituye_num_antecesores()
        for nodo_status in self.listaOrdenada:
            if nodo_status.num_antecesores == 0:
              nodo_status.completo_al_dia = d_inicial
            for act in nodo_status.colActividades:
                act.nodoSuc.completo_al_dia = max(act.nodoSuc.completo_al_dia,
                                                  nodo_status.completo_al_dia +
                                                  act.duracion_dias)
        for nodo_status in self.listaOrdenada:
            for act in nodo_status.colActividades:
                act.holgura = act.nodoSuc.completo_al_dia -\
                              nodo_status.completo_al_dia -\
                              act.duracion_dias
                act.inicio_temprano = nodo_status.completo_al_dia
                act.final_obligado  = act.nodoSuc.completo_al_dia                      
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------                                                      
    def obtenUsoRecurso(self,dict_rec_x_dia):
        for nodo_status in self.listaOrdenada:
            dia_ini = nodo_status.completo_al_dia
            for act in nodo_status.colActividades:
                tipoRec = act.tipoRecurso
                dia_fin = dia_ini + act.duracion_dias
                for dia in range(dia_ini,dia_fin): # el range le quita el final (OK)
                  dd = dict_rec_x_dia.get(dia,None) # la entrada es un diccionario
                  if dd == None:
                      dict_rec_x_dia[dia] = {tipoRec:act.numRecursos}
                  else:
                      dr = dd.get(tipoRec,None)
                      if dr == None:
                          dict_rec_x_dia[dia][tipoRec] = act.numRecursos
                      else:
                          dict_rec_x_dia[dia][tipoRec] += act.numRecursos
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    def __str__(self):
        strRes = "EstructuraTopo" + '\n'
        if self.ordenado:
            lista_imp = self.listaOrdenada
        else:    
            lista_imp = list(self.colNodos.values)
        if len(lista_imp) == 0:
            strRes += "Colección de nodos vacía"
        else:    
            for nodo in lista_imp:
                strRes += str(nodo)
        return strRes
    
# =============================================================================
#                             Nodo Topológico
# =============================================================================
class NodoTopo:
    def __init__(self,Id):
        self.id = Id
        self.num_antecesores = 0
        self.colActividades  = []
        self.yaSalio         = False
        self.costo_inicio    = 0.0
        self.completo_al_dia = 0
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------        
    def agregaAct(self,nodoSuc,nomActividad,tipoRecurso,numRecursos,costo,duracion_dias):
        actividad = NodoActividad(self,nodoSuc,
                                  nomActividad,
                                  tipoRecurso, 
                                  numRecursos, 
                                  costo,
                                  duracion_dias) 
        self.colActividades.append(actividad) 
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------        
    def __str__(self):
        strRes  = "NodoTopo " + str(self.id) + " Completado el dia " + '%3d'%self.completo_al_dia + '\n'
        strRes += "num antecesores:" + str(self.num_antecesores) + '\n'
        if len(self.colActividades) == 0:
            strRes += "...sin sucesores..." + '\n'
        else:
            for nodo_act in self.colActividades:
                strRes += " "*3 + str(nodo_act) + '\n'
        return strRes        

# =============================================================================
#                             NodoActividad
# =============================================================================
class NodoActividad():
    def __init__(self,nodoTopo,nodoSuc,nomActividad,tipoRecurso,numRecursos,costo,duracion_dias):
        self.nt              = nodoTopo  # nodo predecesor de la actividad
        self.nodoSuc         = nodoSuc
        self.nomActividad    = nomActividad
        self.tipoRecurso     = tipoRecurso
        self.numRecursos     = numRecursos
        self.costo           = costo
        self.duracion_dias   = duracion_dias
        self.holgura         = 0
        self.inicio_temprano = np.Inf
        self.final_obligado  = np.Inf
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    def __str__(self):
        strRes = '%3d'%self.nodoSuc.id + " " + '%-60s'%self.nomActividad.strip() +\
                 ' t_rec:%4d'%self.tipoRecurso + ',numRec %4d'%self.numRecursos + " " +\
                 '{:10,.2f}'.format(self.costo) +\
                 ' %4d dias'%self.duracion_dias + ", holgura %3d dias,"%self.holgura +\
                 'ini temp %4d'%self.inicio_temprano + ' fin oblig %4d'%self.final_obligado   
        return strRes         
        
# =============================================================================
#                             Constructora
# =============================================================================
class Constructora:
    def __init__(self,nomCia):
        self.nombre       = nomCia
        self.colProyectos = {}
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------

    def informa(self,blnInforma ):
        self.informa = blnInforma
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
        
    def agregaProyecto(self,idProy,nomProy,diaInicial):
        if self.colProyectos.get(idProy,None) == None:
            self.colProyectos[idProy] = Proyecto(idProy,nomProy,diaInicial)
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    def agregaAct(self,
                  idProy,
                  idPred, idSuc, nomAct,
                  tipoRecurso, numRecursos, costo, duracion_dias):
        proy = self.colProyectos.get(idProy,None)
        if proy == None:
            raise Exception("El proyecto " + str(idProy) + " NO existe")
        proy.agregaAct(idPred, idSuc, nomAct,
                       tipoRecurso, numRecursos, costo, duracion_dias)
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    def ordenaProyectos(self):
        for p in self.colProyectos.values():
            p.ordena()
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    def usoDeRecursosPorDia(self):
        dict_usoRecPorDia = {}
        for proy in self.colProyectos.values():
            proy.usoDeRecursosPorDia(dict_usoRecPorDia)
        return dict_usoRecPorDia       
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    def __str__(self):
        strRes = str(type(self))[17:-2] + ":" + self.nombre + "\n"
        for k,p in self.colProyectos.items():
            strRes += str(p) + '\n'
        return strRes
    
# =============================================================================
#                           Proyecto
# =============================================================================
class Proyecto:
    def __init__(self,idProy, nomProy, diaInicial):
        self.id        = idProy
        self.nom       = nomProy
        self.diaInicio = diaInicial
        self.colNodos  = {}
        self.informa   = True
        self.st        = EstructuraTopo()
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
   
    def comparaCon(self,otroProyecto):
        return self.idProy - otroProyecto.idProy
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
   
    def agregaAct(self,pred,suc, nomActividad,
                       tipoRecurso,numRecrsos,costo,duracion_dias):
        self.st.agregaAct(pred,suc, nomActividad,
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
                          tipoRecurso,numRecrsos,costo,duracion_dias)
        
    def ordena(self):
        self.st.ordena_topo()
        self.st.calcula_t_finNodo(self.diaInicio)
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
    
    def usoDeRecursosPorDia(self,dict_usoRecPorDia):
        self.st.obtenUsoRecurso(dict_usoRecPorDia)
# -----------------------------------------------------------------------------
#
# -----------------------------------------------------------------------------
        
    def __str__(self):
        strRes = str(type(self))[17:-2] + ":" +\
                 str(self.id) + " " +\
                 str(self.nom) +\
                 " ... día inicial:" +\
                 str(self.diaInicio) + '\n'
        strRes += str(self.st)
        return strRes
    
# =============================================================================
#        
# =============================================================================

# =============================================================================
#                             Programa Principal
# =============================================================================

if __name__ == "__main__":
       
       INFORMA = True

       ica = Constructora("ICA S.A.")

       ica.agregaProyecto(1,"Casa No. 1",1)
       ica.agregaAct(1,1,2,"Excavación de zanjas y colocar cimientos y drenaje interior",21,4,50567,12)
       ica.agregaAct(1,2,4,"Levantar muros y colar firme del piso",26,4,24325,5)
       ica.agregaAct(1,4,6,"Cimbra y armado de techo",25,3,9500,2)
       ica.agregaAct(1,2,10,"Colocar registros y drenaje exterior",21,2,57800,4)
       ica.agregaAct(1,4,8,"Instalación hidráulica y sanitaria",23,1,17325,5)
       ica.agregaAct(1,6,3,"Colar loza techo",50,2,12500,12)
       ica.agregaAct(1,1,3,"Acometida eléctrica",32,1,500,5)
       ica.agregaAct(1,3,5,"Ventanas y puertas",35,2,17500,14)
       ica.agregaAct(1,5,8,"Pintar muros y techo",36,3,3500,2)
       ica.agregaAct(1,7,5,"Permiso de color",0,1,0,3)
       ica.agregaAct(1,7,9,"Acometida de agua",23,1,2500,1)
       ica.agregaAct(1,9,4,"Conexión de agua",23,1,1500,1)
       ica.agregaAct(1,9,10,"Acometida de drenaje",21,2,2000,2)


       ica.agregaProyecto(2,"Casa No. 2",10)
       ica.agregaAct(2,2,4,"Excavación de zanjas y colocar cimientos y drenaje interior",21,4,50567,12)
       ica.agregaAct(2,4,8,"Levantar muros y colar firme del piso",26,4,24325,5)
       ica.agregaAct(2,8,12,"Cimbra y armado de techo",25,3,9500,2)
       ica.agregaAct(2,4,20,"Colocar registros y drenaje exterior",21,2,57800,4)
       ica.agregaAct(2,8,16,"Instalación hidráulica y sanitaria",23,1,17325,5)
       ica.agregaAct(2,12,6,"Colar loza techo",50,2,12500,12)
       ica.agregaAct(2,2,6,"Acometida eléctrica",32,1,500,5)
       ica.agregaAct(2,6,10,"Ventanas y puertas",35,2,17500,14)
       ica.agregaAct(2,10,16,"Pintar muros y techo",36,3,3500,2)
       ica.agregaAct(2,14,10,"Permiso de color",0,1,0,3)
       ica.agregaAct(2,14,18,"Acometida de agua",23,1,2500,1)
       ica.agregaAct(2,18,8,"Conexión de agua",23,1,1500,1)
       ica.agregaAct(2,18,20,"Acometida de drenaje",21,2,2000,2)

       ica.agregaProyecto(3,"Casa No. 3",15)
       ica.agregaAct(3,3,6,"Excavación de zanjas y colocar cimientos y drenaje interior",21,4,50567,12)
       ica.agregaAct(3,6,12,"Levantar muros y colar firme del piso",26,4,24325,5)
       ica.agregaAct(3,12,18,"Cimbra y armado de techo",25,3,9500,2)
       ica.agregaAct(3,6,30,"Colocar registros y drenaje exterior",21,2,57800,4)
       ica.agregaAct(3,12,24,"Instalación hidráulica y sanitaria",23,1,17325,5)
       ica.agregaAct(3,18,9,"Colar loza techo",50,2,12500,12)
       ica.agregaAct(3,3,9,"Acometida eléctrica",32,1,500,5)
       ica.agregaAct(3,9,15,"Ventanas y puertas",35,2,17500,14)
       ica.agregaAct(3,15,24,"Pintar muros y techo",36,3,3500,2)
       ica.agregaAct(3,21,15,"Permiso de color",0,1,0,3)
       ica.agregaAct(3,21,27,"Acometida de agua",23,1,2500,1)
       ica.agregaAct(3,27,12,"Conexión de agua",23,1,1500,1)
       ica.agregaAct(3,27,30,"Acometida de drenaje",21,2,2000,2)

       ica.agregaProyecto(4,"Casa No. 4",30)
       ica.agregaAct(4,1,2,"Excavación de zanjas y colocar cimientos y drenaje interior",21,4,50567,12)
       ica.agregaAct(4,2,4,"Levantar muros y colar firme del piso",26,4,24325,5)
       ica.agregaAct(4,4,6,"Cimbra y armado de techo",25,3,9500,2)
       ica.agregaAct(4,2,10,"Colocar registros y drenaje exterior",21,2,57800,4)
       ica.agregaAct(4,4,8,"Instalación hidráulica y sanitaria",23,1,17325,5)
       ica.agregaAct(4,6,3,"Colar loza techo",50,2,12500,12)
       ica.agregaAct(4,1,3,"Acometida eléctrica",32,1,500,5)
       ica.agregaAct(4,3,5,"Ventanas y puertas",35,2,17500,14)
       ica.agregaAct(4,5,8,"Pintar muros y techo",36,3,3500,2)
       ica.agregaAct(4,7,5,"Permiso de color",0,1,0,3)
       ica.agregaAct(4,7,9,"Acometida de agua",23,1,2500,1)
       ica.agregaAct(4,9,4,"Conexión de agua",23,1,1500,1)
       ica.agregaAct(4,9,10,"Acometida de drenaje",21,2,2000,2)

       ica.agregaProyecto(5,"Casa No. 5",25)
       ica.agregaAct(5,2,4,"Excavación de zanjas y colocar cimientos y drenaje interior",21,4,50567,12)
       ica.agregaAct(5,4,8,"Levantar muros y colar firme del piso",26,4,24325,5)
       ica.agregaAct(5,8,12,"Cimbra y armado de techo",25,3,9500,2)
       ica.agregaAct(5,4,20,"Colocar registros y drenaje exterior",21,2,57800,4)
       ica.agregaAct(5,8,16,"Instalación hidráulica y sanitaria",23,1,17325,5)
       ica.agregaAct(5,12,6,"Colar loza techo",50,2,12500,12)
       ica.agregaAct(5,2,6,"Acometida eléctrica",32,1,500,5)
       ica.agregaAct(5,6,10,"Ventanas y puertas",35,2,17500,14)
       ica.agregaAct(5,10,16,"Pintar muros y techo",36,3,3500,2)
       ica.agregaAct(5,14,10,"Permiso de color",0,1,0,3)
       ica.agregaAct(5,14,18,"Acometida de agua",23,1,2500,1)
       ica.agregaAct(5,18,8,"Conexión de agua",23,1,1500,1)
       ica.agregaAct(5,18,20,"Acometida de drenaje",21,2,2000,2)


       ica.ordenaProyectos()
      
       if INFORMA:
       
         print("---------------------------------------------\n")
         print(ica)
         
       print("---------------------------------------------\n")
       
       d_uso_recPorDia = ica.usoDeRecursosPorDia()
 
       print(cadsep)
       print("=====>>>>> Fin de la ejecución <<<<<=====")
       usuario()

# =============================================================================
#                            Fin del código
# =============================================================================
