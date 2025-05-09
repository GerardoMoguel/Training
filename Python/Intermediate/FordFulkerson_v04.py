# -*- coding: utf-8 -*-
"""
Created on Wed Nov  9 09:49:09 2022

@author: RGAMBOAH

Para EDA 202203

# =============================================================================
# Cálculo valores y recorridos en un DAG
# (Directed Abstract Graph o Grafo Abstracto Dirigido) y para un grafo bidireccional.
# Para aplicarlo al caso bidireccional, la matriz debe ser simétrica.  
# Cálculo de Flujo Máximo por FF DFS (recursivo) versión preliminar ineficiente.
# Cálculo de Flujo Máximo por FF BFS versión mas eficiente con revisión de nodos "Backward"
#
# =============================================================================
"""

import random
import numpy as np
import time
import sys
import copy

cadsep           = "=" * 100
cadast           = "*" * 50
ALEATORIZA       = False
SEMILLA          = 4967 *(1 - ALEATORIZA ) + ALEATORIZA * random.randint(1,10000)
MATRIZ_SIMETRICA = True

TRAZA            = False


# =============================================================================
#                 opciones de despliegue para numpy
# =============================================================================

np.set_printoptions(linewidth =  180,
                    suppress  = None,
                    edgeitems = 2000,
                    threshold = 5000)
np.set_printoptions(formatter={'float': '{: 4.0f}'.format})


def salir(letrero):
    print(cadsep)
    print("       " + letrero)
    print(cadsep)
    sys.exit()
# =============================================================================
#                        separaSecciones
# =============================================================================
def separaSecciones(strSeccion):
       print()
       print(cadast)
       print(strSeccion)
       print(cadast)


# =============================================================================
#                          gendist
# =============================================================================
def gendist(n, dmin,dmax,ftipo=np.ceil,dens = 0.5):
    #
    #   ftipo es np.ceil, np.floor, np.round
    #
    if not ALEATORIZA:
        random.seed(SEMILLA)
        print("SEMILLA=",SEMILLA)
    d = np.zeros((n,n))   
    deltaD = dmax - dmin
    for i in range(n):
          for j in range(n):
            if i == j:    
              d[i][j] = np.Inf
            else:    
              if random.random() < dens:
                  d[i][j] = int(ftipo(dmin + deltaD * random.random()))
                  #if random.random() < 0.25:
                  #    d[i][j] = -d[i][j]
              else:
                  d[i][j] = np.inf
                  
    return d            

# =============================================================================
#                         gendistSim
# =============================================================================
def gendistSim(n, dmin,dmax,ftipo=np.ceil,dens = 0.5):
    #
    
    #   ftipo es np.ceil, np.floor, np.round
    #
    if not ALEATORIZA:
        random.seed(SEMILLA)
        print("SEMILLA=",SEMILLA)
    d = np.zeros((n,n))   
    deltaD = dmax - dmin
    for i in range(n):
          for j in range(i+1):
              if random.random() < dens:
                  d[i][j] = int(ftipo(dmin + deltaD * random.random()))
              else:
                  d[i][j] = np.inf
    for i in range(n):
       d[i][i] = np.Inf 
       for j in range(i+1,n):
           d[i][j] = d[j][i]
    return d            

# =============================================================================
#                          genFlujos
# =============================================================================
    
def genFlujos(n, fmin,fmax,ftipo,dens):
    if not ALEATORIZA:
        random.seed(SEMILLA)
        print("SEMILLA=",SEMILLA)
    f = np.zeros((n,n))
    for i in range(n):
        for j in range(n):
            f[i][j] = np.Inf
            
    deltaf = fmax - fmin
    
    for i in range(n):
          for j in range(i+1,n):
              if random.random() < dens:
                  f[i][j] = int(ftipo(fmin + deltaf * random.random()))

    return f            
        

# =============================================================================
#                          distMin
# =============================================================================
def distMin(D):
    
    n = len(D)
    d = D.copy()
    for k in range(n):
        for i in range(n):
            for j in range(n):
                d[i][j] = min(d[i][j],d[i][k] +d[k][j])
    return  d 

# =============================================================================
#                             minspTr (en dos modalidades)
# =============================================================================

def minspTr(D,modo_min=False):    # D es una lista de listas de las distancias de las adyacencias
                                  # D = [[ady 0],[ady 1],...,[ady (n-1)]]
    n        = len(D)
    n_faltan = n
    V1       = [] # V1 contendrá tupkas del tipo (v_origen, v_destine, dist_arco)
    L1       = [] # Vértices en en MST
    V2       = [] # V2 contendrá tupkas del tipo (v_origen, v_destine, dist_arco)
    C        = copy.deepcopy(D)
    LC       = list(range(n))
    
    while n_faltan > 0:
        #
        # Por si no hay elementos en V2, conjunto pivote de los vertices 
        # adyacentes a V1
        #
        if len(V2) == 0:
          #print(type([min(C[i]) for i in LC]))
          if modo_min:
            #
            # Elegimos el que tenga la menor distancia a los elementos del
            # "conjunto" V1
            #
            lis_min = [min(C[i]) for i in LC]
            dr_min  = min(lis_min)
            r_min   = lis_min.index(dr_min)
            i_o     = LC[r_min]
            j_o     = C[i_o].argmin()
            d_o     = C[i_o][j_o]
            #if d_o < np.Inf:
            #  V2.append((i_o,j_o,d_o))
            print("tupla(",i_o,j_o,d_o,")")
            L1.append(i_o)
            LC.remove(i_o)
            candidatos = [(i_o,j_d,C[i_o][j_d]) 
                          for j_d in range(n) if C[i_o][j_d] < np.Inf]
            V2 += candidatos
            #
            # Se depuran los elementos de V2
            #
            V2 = [t for t in V2 if t[0] in L1 and t[1] not in L1]
            
            n_faltan -= 1
          else: 
            #
            # Elegimos el primer elemento de los que resten.
            #
            V2.append((None,LC[0],None))
        else:
           #
           # En caso de que haya elementos en V2
           # elegimos la tupla con distancia minima a V1
           #
           t_min = min(V2,key=lambda t:t[2])
           jdx_min = t_min[1]
           if t_min[0] != None: 
             V1.append(t_min)
           n_faltan -= 1
           L1.append(jdx_min)
           #print("Eliminando de LC a ", jdx_min)
           LC.remove(jdx_min)
           candidatos = [(jdx_min,j_d,C[jdx_min][j_d]) 
                         for j_d in range(n) if C[jdx_min][j_d] < np.Inf]
           V2 += candidatos
           V2 = [t for t in V2 if t[0] in L1 and t[1] not in L1]
                    
    return V1         

# =============================================================================
#                       distMinVertice:
# Obtiene la distancia mínima desde un vértice dado al resto de los vértices
# del grafo.        
# =============================================================================
def distMinVertice(D,vertice):
    n = len(D)
    dmin_vertice = np.array([np.Inf] * n)
    predec_vertice = [-1] * n
    dmin_vertice[vertice] = 0.0
    porVerificar = {vertice}
    while len(porVerificar) > 0:
        v = porVerificar.pop()
        adyacentes = [k for k in range(n) if D[v][k] < np.Inf and k != vertice]
        #print(v,adyacentes)
        for w in adyacentes:
            if dmin_vertice[w] > dmin_vertice[v] + D[v][w]:
                dmin_vertice[w] = dmin_vertice[v] + D[v][w]
                porVerificar.add(w)
                predec_vertice[w] = v
                
    return dmin_vertice,predec_vertice

# =============================================================================
#                    distMinVerticeDijstra:
# Obtiene la distancia mínima desde un vértice dado al resto de los vértices
# del grafo.    
# =============================================================================
def distMinVerticeDijstra(D,vertice):
    n = len(D)
    #
    # Distancia inicial del vérice origen al resto de los vértices.
    # Se actualizará conforme se ejecute el proceso.
    #
    dmin_vertice = D[vertice].copy()
    #
    # se marca quién es el predecesor de cada vétice 
    #
    predec_vertice = [-1] * n
    #
    # Se marcan los predecesores de los vértices inicialmente conectados al "vertice"
    #
    for k in range(n):
        if dmin_vertice[k] < np.Inf:
            predec_vertice[k] =vertice
    #
    # Y la distancia del vertice a sí mismo se hace cero.
    #         
    dmin_vertice[vertice] = 0.0
    #
    # El conjunto porVerificar contiene los vértices aún no verificados
    #
    porVerificar = set([t for t in range(n)])
    #
    # Se itera sobre los vertices no verifiados eligiendo el que tenga la distancia
    # mínima al vertice dado
    #
    while len(porVerificar) > 0:
        #
        # Las dos instrucciones siguientes sirven para localizar el vértice aún no 
        # verificado y que tenga la distancia mínima.
        #
        lpv = list(porVerificar) 
        v =lpv[dmin_vertice[lpv].argmin()]
        #
        # una vez elegido lo eliminamos v del conjunto de vértices por verificar.
        #
        porVerificar.remove(v)
        #
        # Obtenemos los adyacentes al vértice v seleccionado
        #
        adyacentes = [k for k in range(n) if (D[v][k] < np.Inf) and (k != vertice) and (k in porVerificar)]
        #print(v,adyacentes)
        #
        # y en su caso actualizamos la distancia al vértice dado utilizando
        # al vértice v como pivote.
        # 
        for w in adyacentes:
            if dmin_vertice[w] > dmin_vertice[v] + D[v][w]:
                dmin_vertice[w] = dmin_vertice[v] + D[v][w]

                predec_vertice[w] = v
                
    return dmin_vertice,predec_vertice
    

# =============================================================================
#                              genGrafo_01
# =============================================================================
def genGrafo_01():
    
    C = []
    C = np.zeros((9,9))
        
    for i in range(9):
        for j in range(9):
            C[i][j] = np.Inf
   
    C[0][1] = 2.0
    C[0][5] = 7.0
    C[0][6] = 3.0
    
    C[1][0] = 2.0
    C[1][2] = 4.0
    C[1][6] = 6.0
    
    C[2][1] = 4.0
    C[2][3] = 2.0
    C[2][7] = 2.0
    
    C[3][2] = 2.0
    C[3][4] = 1.0
    C[3][7] = 8.0
    
    C[4][3] = 1.0
    C[4][5] = 6.0
    C[4][8] = 2.0
    
    C[5][0] = 7.0
    C[5][4] = 6.0
    C[5][8] = 5.0
    
    C[6][0] = 3.0
    C[6][1] = 6.0        
    C[6][7] = 3.0
    C[6][8] = 1.0
    
    C[7][2] = 2.0
    C[7][3] = 8.0
    C[7][6] = 3.0
    C[7][8] = 4.0
    
    C[8][4] = 2.0
    C[8][5] = 5.0
    C[8][6] = 1.0
    C[8][7] = 4.0
    
    return C

# =============================================================================
#                           gengGrafoFF_01
# =============================================================================
def genGrafoFF_01():

    C = np.zeros((8,8))
                 
    s = 0
    a = 1
    b = 2
    c = 3
    d = 4
    e = 5
    f = 6
    t = 7
    
    for i in range(8):
        for j in range(8):
            C[i][j] = np.Inf
            
    C[s][a] = 2
    C[s][c] = 4
    C[s][e] = 1
    
    C[a][b] = 5
    
    C[b][t] = 3
    
    C[c][a] = 2
    C[c][f] = 3
    
    C[d][t] = 2
    
    C[e][d] = 2
    
    C[e][f] = 3
    
    C[f][t] = 1
    

    return C

def genGrafoFF_02_ciclada():
    C = genGrafoFF_01()
    C[2][3] = 10
    return C

def genGrafoFF_03():
    nv = 4
    C = np.zeros((nv,nv))
    
    for i in range(nv):
        for j in range(nv):
            C[i][j] = np.Inf
            
    C[0][1] = 10
    C[0][2] = 10
    C[1][3] = 10
    C[1][2] = 100
    C[2][3] = 10
    
    return C

def genGrafo_FF_Ejem_202401_01():
    nv = 7
    C = np.zeros((nv,nv))
    for i in range(nv):
        for j in range(nv):
            C[i][j] = np.Inf
    C[0][1] = 15
    C[0][2] = 10
    C[1][3] = 12
    C[1][2] =  3
    C[2][3] =  5
    C[2][4] = 10
    C[3][5] =  5
    C[4][3] =  3
    C[4][6] = 20
    C[5][4] =  8
    C[5][6] = 10
    
    return C    
    

# =============================================================================
#                           reportaTs
# =============================================================================
def reportaTs(letrero,t0,t1):
    print(cadsep)
    print(' ' * 25 + letrero + ":  " + "{0:12,.3f} Seg".format(t1-t0))
    print(cadsep)

# =============================================================================
#                recorrido por DFS iniciando por un vértice dado
# =============================================================================
#
# NOTA: en caso de un grafo direccional puede producir solo una parte del ST
# solamente un ST contenido en el grafo disconexo.
#
def visitaRec(D,n,visitados,camino,vertice,dist_rec):
    for j in range(n):
        if(D[vertice][j] < np.Inf) and j not in visitados:
            dist_rec[0] += D[vertice][j]
            visitados.add(j)
            camino.append((vertice,j,D[vertice][j]))
            visitaRec(D,n,visitados,camino,j,dist_rec)



def spTree(D,vertice_inicial):
    n = len(D)
    visitados = {vertice_inicial}
    camino = []
    dr = [0]
    visitaRec(D,n,visitados,camino,vertice_inicial,dr)
    
    return visitados,dr,camino

# =============================================================================
#                recorrido por BFS iniciando por un vértice dado
# =============================================================================
def recorreBFS(D):
    colaBFS    = []
    n          = len(D)
    porVisitar = set()
    for k in range(n): porVisitar.add(k)
    num_visita = [0]*n
    recorrido  = []
    i = 0
    while len(porVisitar) > 0:
        v = porVisitar.pop()
        i += 1
        num_visita[v] = i
        colaBFS.append(v)
        while len(colaBFS) > 0:
            v = colaBFS[0]
            colaBFS.remove(v)
            for k in range(n):
                if D[v][k] < np.Inf and num_visita[k] == 0:
                    i += 1
                    num_visita[k] = i
                    colaBFS.append(k)
                    recorrido.append((v,k,D[v][k]))
                    porVisitar.discard(k)
        
    return num_visita,recorrido    

# *****************************************************************************
#                            FORD FULKERSON
# *****************************************************************************                   
# =============================================================================
#                           Ford - Fulkerson BFS
# =============================================================================
def actualiza_ruta_BFS(F,fuente,terminal,cap_adic):
    incremento = cap_adic[terminal][3]
    v = terminal
    camino = [v]
    while v != fuente:
      try:  
        suc  = v
        pred = cap_adic[v][0]
        camino.append(pred)
        if TRAZA:
            print("Pred,Suc:",pred,suc)
        if cap_adic[suc][2] == FORWARD:
            F[pred][suc][1] += incremento   # flujo
            F[pred][suc][2] -= incremento   # holgura
        else:
            F[suc][pred][2] += incremento   # holgura
            F[suc][pred][1] -= incremento   # flujo
        v = pred
      except:
          print("Ocurrio una excepcion")
    camino = camino[::-1]      
    if TRAZA:
        print("FF BFS: se ha actualizado el camino ", camino, " con ", incremento)
        
    
    
def buscaRutaFF_BFS(F,n,fuente,terminal):
    #
    # Etiqueta en el diccionario: 
    #      (pred, suc, sentido, cap_adic_sucesor)
    #
    cap_adic = {}
#    for k in range(n):
#        cap_adic[k] = None
    cap_adic[fuente] = (None,fuente,FORWARD,np.Inf)    
    listaPorProspectar = [fuente] 
   
    while len(listaPorProspectar) > 0:
        vertix = listaPorProspectar.pop()
        if TRAZA:
            print("Trabajando el vertice",vertix, " con capacidad adicional ", cap_adic[vertix][3])
              
        cap_adic_vertix = cap_adic[vertix][3]
        for v_ady in range(n):
            etiqueta_generada = False
            if v_ady not in cap_adic:
                if F[vertix][v_ady] != None:       #forward
                   cap_adic_v_ady = min([cap_adic_vertix, F[vertix][v_ady][2]])
                   if cap_adic_v_ady > 0:
                       etiqueta_generada = True
                       #camino.append(v_ady)
                       cap_adic[v_ady] = (vertix,v_ady,FORWARD,cap_adic_v_ady)
                       listaPorProspectar.append(v_ady)
                       
                elif F[v_ady][vertix] != None:     # backward 
                     cap_adic_v_ady = min([cap_adic_vertix,F[v_ady][vertix][1]])
                     if cap_adic_v_ady > 0:
                         etiqueta_generada = True
                         #camino.append(v_ady)
                         cap_adic[v_ady] = (vertix,v_ady,BACKWARD,cap_adic_v_ady)
                         listaPorProspectar.append(v_ady)
        if etiqueta_generada:
          if v_ady == terminal:
            actualiza_ruta_BFS(F,fuente,terminal,cap_adic)
            #
            #   Vuelve a prospectar desde el inicio
            #
            cap_adic = {}
            cap_adic[fuente]   = (None,fuente,FORWARD,np.Inf)
            listaPorProspectar = [fuente]
                    
def FFBFS(C,fuente,terminal):
    n  = len(C)
    F = []
    for k in range(n):
        F.append([None]*n)
    for i in range(n):
        for j in range(n):
            if C[i][j] < np.Inf:
                F[i][j] = [C[i][j],0,C[i][j]]
    #ruta = []            
    buscaRutaFF_BFS(F,n,fuente,terminal)
    return F                                         

# =============================================================================
#                           Ford - Fulkerson Recursivo
# =============================================================================
# 
# =============================================================================
# -----------------------------------------------------------------------------
#                               actualiza
# -----------------------------------------------------------------------------
# Esta función actualiza la ruta con el flujo adicional que se puede tranaportar
# al vertica terminal.

FORWARD  = True
BACKWARD = False
          
def actualiza_F_rec(F,ruta):
    #
    # ruta es la lista de listas (para ser actualizable) con 
    #  [FORWARD_O_BACKWARD, vertice_destino, cap_adic_de_vertice_destino]
    #

    delta_Flujo = ruta[-1][2]   # lo que le podemos hacer llegar al terminal
    
    if delta_Flujo == 0:
        print("delta Flujo es cero")
    
    pred    = ruta[0][1]

    for t_arco in ruta[1:]:
        sentido = t_arco[0]
        suc     = t_arco[1]
        t_arco[2] -= delta_Flujo   # se actualiza la capacidad adicional del arco
        if sentido:                          # forward
            F[pred][suc][1] += delta_Flujo
            F[pred][suc][2] -= delta_Flujo            
        else:                                # backward
            F[suc][pred][1] -= delta_Flujo
            F[suc][pred][2] += delta_Flujo

        pred = suc
        
    if TRAZA:
        print("Se actualiza la ruta ", ruta, " con el incremento ", delta_Flujo)    
       

# -----------------------------------------------------------------------------
#                              buscaRutaRec
# -----------------------------------------------------------------------------
# Esta es la función principal del proceso de búsqueda recursiva
#
def buscaRutaRec(F, n, vertice, terminal, ruta,forward=True):
    #bln_alcanzo_terminal = False
    
    if TRAZA:
      print("Trabajando al vertice:",vertice)
    if len(ruta) == 0:  # se siembra la capacidad del vertice fuente
        cap_restante = np.Inf
    else:
        pred     = ruta[-1][1]
        cap_prev = ruta[-1][2]
        if forward:
           flujo_tentativo = F[pred][vertice][2]
        else:
           flujo_tentativo = F[vertice][pred][1]
        cap_restante = min([cap_prev,flujo_tentativo]) 
    #
    # Se agrega a la ruta el vertice o nodo recibido
    #    
    ruta.append([forward,vertice,cap_restante])
    
    #
    # Para tener la ruta en vertices hasta el presente y evitar ciclos
    #
    rv = [t[1] for t in ruta] 
    if TRAZA:
        print("Ruta:", ruta)
        
    if vertice == terminal:
      actualiza_F_rec(F,ruta)
      #
      # regresa, en ruta trae el vertice terminal
      #
      
    else:    
      k = 0
      while k < n and cap_restante > 0: 
        if k not in rv:  # condición para detectar y evitar ciclos y al propio vertice  
          if F[vertice][k] != None:
            if F[vertice][k][2] > 0:   # si hay holgura en el arco
               buscaRutaRec(F,n,k,terminal,ruta,FORWARD)
               cap_restante = ruta[-1][2]
                
          elif F[k][vertice] != None:   # veamos si hay arco "backward
            if F[k][vertice][1] > 0: # arco "backward" y actualmente transporta
              if TRAZA:
                  print("Analizando Backward:",k,vertice)
              buscaRutaRec(F,n,k,terminal,ruta,BACKWARD)
              cap_restante = ruta[-1][2]
                 
        k += 1   # del while...
    #
    #  Se elimina este vértice de la ruta potencial al dar el backtrack recursivo
    #       
    ruta.pop()
          
# -----------------------------------------------------------------------------
#                                FFRec    
# -----------------------------------------------------------------------------
# Esta función es el acoplamiento para el código cliente. 
# Recibe la matriz de  capacidades de flujo de los vértices en modalidad de
# renglones como vértices de origen y los índices de las columnas son los 
# vértice destino.  
#
# entradas:
#   C                matriz de capacidades de flujo.
#   fuente           id del vértice fuente del flujo
#   terminal         id del vértice termonal del flujo
# 
# salidas:               
# Regresa la matriz de flujos entre los vértices: 
#    coordenadas de la matriz [id origen][id_destino] y en cada coordenada
#    o entrada de la matriz se tiene 
#    [Capacidad del arco, flujo del arco, holgura del arco]
#
    
def FFRec(C,fuente,terminal):
    #
    # 
    # Se construye la matriz de flujos para los valores de la matriz C
    #
    n  = len(C)
    F = []
    for k in range(n):
        F.append([None]*n)
    for i in range(n):
        for j in range(n):
            if C[i][j] < np.Inf:
                F[i][j] = [C[i][j],0,C[i][j]]
    #
    # y se invoca la rutina recursiva para el càlculo del flujo
    #             
    ruta = []            
    buscaRutaRec(F,n,fuente,terminal,ruta,FORWARD)
    return F                                         



# -----------------------------------------------------------------------------
#                          imprimeFlujos                            
# -----------------------------------------------------------------------------
# imprime los flujos en la matriz de flujos y regresa el flujo total de fuente
# a terminal.
#                                      
def imprimeFlujos(F,fuente,terminal):
    n = len(F)
    print(cadsep)
    print("Flujos resultantes: [Capacidad,Flujo Cursado,Holgura]")
    print("Vértice fuente:", fuente, "   Vértice terminal:", terminal)
    print(cadsep)
    for i in range(n):
       if i != terminal: 
          print("Arcos partiendo del vertice:",i)
          for j in range(n):  
              if F[i][j] != None:
                  print("       ",i,j,F[i][j])
          print()
    flujo_total = 0.0      
    for k in range(n):
       if F[k][terminal] != None:
         flujo_total += F[k][terminal][1]
    print("Flujo total en nodo terminal:", flujo_total)
    
    return flujo_total                   

# -----------------------------------------------------------------------------
#                          compara                            
# -----------------------------------------------------------------------------
def compara(strF1,F1,strF2,F2):
    n = len(F1)
    print(cadsep)
    print("Comparación de " + strF1 + " y " + strF2)
    print(cadsep)
    for i in range(n):
        for j in range(n):
            if F1[i][j] != F2[i][j]:
                print("Flujos distintos en " + strF1 + "["+str(i) + "]["+str(j)+"]:" + str(F1[i][j]))
                print("                    " + strF2 + "["+str(i) + "]["+str(j)+"]:" + str(F2[i][j]))
    print(cadsep)  

# -----------------------------------------------------------------------------
#                       genMatCostos
# -----------------------------------------------------------------------------
    
def genMatCostos(C, cmin, cmax):
   deltaCosto = cmax - cmin
   CT = C.copy() #crea una copia, crea otro y lo copia, no es solo la direccion de memoria
   n = len(C) 
   for i in range(n):
       for j in range(n):
           if C[i][j] < np.Inf:
               CT[i][j] = np.ceil(cmin + deltaCosto * random.Random())#aqui hay que obligarlo a inicializarlo con la semilla o algo
   return CT            

# -----------------------------------------------------------------------------
#                       evaluaFlujo
# -----------------------------------------------------------------------------
def evaluaFlujo(F,Cto):
    e = 0
    n = len(F)
    s = 0.0
    for i in range(n):
       for j in range(n):
           if Cto[i][j] != np.inf:
               s += F[i][j][1] * Cto[i][j]
               if F[i][j][1] != 0:
                   e += 1
               elif F[i][j][1] <0:
                   print("Flujo negativo en ",i,j)
               print(e,i,j,F[i][j][1],Cto[i][j],s)    
    print("Evaluando costo de flujos: %8.2f, se hicieron %5d evaluaciones" % (s,e))           
    return s           
    
# =============================================================================
#                       PROGRAMA PRINCIPAL
# =============================================================================

if __name__ == "__main__":
  
    t0 = time.time()
    
    #
    #   NOTA: REDEFINIDAS AQUÍ PARA COMODIDAD DE LA EJECUCION
    #
    ALEATORIZA       = False
    SEMILLA          = 289 * (1 - ALEATORIZA ) + ALEATORIZA * random.randint(1,10000)
    MATRIZ_SIMETRICA = False

    TRAZA            = False
    
    GRAFO_ALAMBRADO = True
    GRAFO_EJEMPLO   = True
    FLUJO_MAX_REC   = False
    FLUJO_MAX_BFS   = True
    DIST_MIN        = False
    TRAZA = False

    if GRAFO_ALAMBRADO:
        if GRAFO_EJEMPLO:
          s = 0
          t = 6
          C = genGrafo_FF_Ejem_202401_01()
        else:    
          s = 0
          #
          #d = genGrafoFF_01() 
          #d = genGrafoFF_02_ciclada()
          #t = 7
          C = genGrafoFF_03()
          t = 3
          
        n = C.shape[0]
        mat_d_min = distMinVertice(C,0)
        if mat_d_min[0][n-1] == np.Inf:
            salir("No hay conectividad")
        else:
            reportaTs("Matriz a usar:",t0,time.time())
    else:
      n = 15
      fmin = 1
      fmax = 15
      ftipo = np.ceil
      dens = 0.95
      C = genFlujos(n, fmin,fmax,ftipo,dens)
      mat_d_min = distMinVertice(C,0)
      if mat_d_min[0][n-1] == np.Inf:
          salir("No hay conectividad")
      else:
          reportaTs("Matriz generada",t0,time.time())
      s = 0
      t = n-1
      
    CostosTransporte = genMatCostos(C,5,20) #aqui hay que corregir para que si use la semilla
        
    if FLUJO_MAX_BFS:
        # =============================================================================
        #  Flujo Máximo en una red de fuente a terminal por el método BFS
        # =============================================================================

        print(cadast)
        print( "   Ford Furkerson BFS")
        print(cadast)
        t0 = time.time()
        F_bfs = FFBFS(C, s, t)
        t1 = time.time()
        print()
        print(cadsep)
        print("Matriz de flujos y holguras, entrada: [Capacidad,flujo cursado,holgura]")
        print(cadsep)
        for k in range(len(C)):
           print(k,F_bfs[k])
        print(cadsep)
        ft_bfs = imprimeFlujos(F_bfs,s,t)
        print(cadsep)
        delta_t_ff_bfs = 1000.0 * ( t1 - t0) # en milisegundos
        reportaTs("Ford Fulkerson BFS para n " + str(n),t0,t1)
        cto_bfs = evaluaFlujo(F_bfs, CostosTransporte)
        print("Costo flujo bfs:",cto_bfs)

    if FLUJO_MAX_REC:
    # =============================================================================
    #  Flujo Máximo en una red de fuente a terminal por el método RECURSIVO o DFS
    # =============================================================================
    
        print(cadsep)
        print("  Matriz de Flujos")
        print(cadsep)
        print(C)
        print(cadsep)
        print()
        print(cadast)
        print( "   Ford Furkerson Recursivo")
        print(cadast)
        t0 = time.time()
        F_rec = FFRec(C, s, t)
        t1 = time.time()
        print()
        print(cadsep)
        print("Matriz de flujos y holguras, entrada: [Capacidad,flujo cursado,holgura]")
        print(cadsep)
        for k in range(len(C)):
           print(k,F_rec[k])
        print(cadsep)
        ft_rec = imprimeFlujos(F_rec,s,t)
        print(cadsep)
        delta_t_ff_rec = 1000.0 * ( t1 - t0 )
        reportaTs("Ford Fulkerson Recursivo para n " + str(n),t0,t1)
        cto_rec = evaluaFlujo(F_rec, CostosTransporte)
        print("Costo flujo rec:",cto_rec)
        
    if FLUJO_MAX_BFS and FLUJO_MAX_REC:
        compara("F_BFS",F_bfs,"F_REC",F_rec)

    print("n:",n)

    print(cadsep)
    if FLUJO_MAX_BFS:
          print("Flujo Max bfs: %6d deltaT: %-8.6e miliSeg." % (ft_bfs,delta_t_ff_bfs))
          print("Costo flujo bfs:",cto_bfs)
          print(cadsep)
    if FLUJO_MAX_REC:
          print("Flujo Max rec: %6d deltaT: %-8.6e miliSeg." % (ft_rec,delta_t_ff_rec)) 
          print("Costo flujo rec:",cto_rec)
          print(cadsep)
      
    if ALEATORIZA:
        print('Aleatorizando, la semilla operada es ', SEMILLA)
    else:
        print("Elaboración de matriz de flujos con semilla ",SEMILLA)
    print(cadsep)    

    if DIST_MIN:
        vertice = 0
        dist_min_basico  = distMinVertice(C,vertice)
        dist_min_dijstra = distMinVerticeDijstra(C,vertice)
        
        print("origen:",vertice)
        print("Básico :", dist_min_basico)
        print("Dijstra:",dist_min_dijstra)
        print(cadsep)
# =============================================================================
#                   FIN DEL PROGRAMA PRINCIPAL
# =============================================================================


