# -*- coding: utf-8 -*-
"""
Created on Sat Dec  3 17:53:03 2022

@author: rggh
"""

import random
import numpy as np
import time
import matplotlib.pyplot as plt


cadsep           = "=" * 100
cadast           = "*" * 50
ALEATORIZA       = False
if not ALEATORIZA:
   SEMILLA       = 3346 
else:
   SEMILLA       =  random.randint(1,10000) 

# =============================================================================
#                 opciones de despliegue para numpy
# =============================================================================

np.set_printoptions(linewidth =  180,
                    suppress  = None,
                    edgeitems = 2000,
                    threshold = 5000)
np.set_printoptions(formatter={'float': '{: 8.4f}'.format})

# =============================================================================
#                        separaSecciones
# =============================================================================
def separaSecciones(strSeccion):
       print()
       print(cadast)
       print(strSeccion)
       print(cadast)
# =============================================================================
#                        
# =============================================================================
def impDeltaT(strLetrero,t0):
    deltaT = time.time() - t0
    print(cadsep)
    print(strLetrero + ": {:>10.4f} segundos".format(deltaT))
    print(cadsep)
    return deltaT
# =============================================================================
#                        
# =============================================================================
def evaluaComponentes(M,T0,T1,t):
    #
    # scam con los polinomios de Tchebycheff trasladados entre T0 y T1
    #
    x = -1.0 + 2.0 * t / ( T1 - T0 )
    C = np.zeros(M+1)
    C[0] = 1.0
    C[1] = x
    for k in range(2,M+1):
        C[k] = 2 * x * C[k-1] - C[k-2]
    return np.exp(-C)
    
# =============================================================================
#                        
# =============================================================================
def genMuestra(N,M,sigmaC = 0.0,sigmaT= 0.0):
    random.seed(SEMILLA)
    #
    #  Con la semilla se genera el estado para el generador de numpy,
    #  se usa este generador para elaborar las series de números para los
    #  valores de los componentes de los estados de los procesos.
    #
    estado = random.randint(1,1000)
    print("Estado:", estado)
    rng = np.random.default_rng(estado)
    T0 = -1.0
    T1 =  1.0
    dt =  ( T1 - T0 ) / (N+1)
    PT = np.zeros((N,M+1))
    for r in range(N):
        PT[r] =  evaluaComponentes(M,T0,T1,(r+1)*dt *(1 + sigmaT * rng.normal(0.0,sigmaT)))
    desv = rng.normal(0.0,sigmaC,size=(N,M+1))
    PT = PT * ( 1.0 + desv )    
    return PT

# =============================================================================
#         Funciones para la Programación Dinámica               
# =============================================================================

# -----------------------------------------------------------------------------
#                           evalua
# -----------------------------------------------------------------------------
#  Esta función evalúa la distancia euclideana entre las muestras de los
#  valores de los componentes de ambos procesos para efectuar la comparación.
#  Los "estados" de los procesos son Y y X, ambos con el mismo número de
#  componentes (pero posiblemente valores diferentes).
#
def evalua(Y,X):
    return np.sqrt(((X - Y) ** 2).sum())
 
# -----------------------------------------------------------------------------
#                           dp_00_rec
# -----------------------------------------------------------------------------
# Esta es la función principal que lleva a cabo la recursión del proceso de
# búsqueda. Cada "nodo" de confluencia entre las muestras de los procesos Y y X
# se van evaluando y a partir del nodo "pivote"se selecciona el que produzca
# el valor mínimo en su camino al nodo "destino".
# Cada nodo ya visitado tiene el valor del mínimo almacenado en la matriz matdp y
# el movimiento que produjo el valor mínimo en la matriz matmov. 
#

def dp_00_rec(matdp,matmov,Y,X,movs,destino,pivote):
    piv_y = pivote[0]
    piv_x = pivote[1]
    valor_local = matdp[piv_y][piv_x]
    mov_min = (0,0)
    #
    # si ya se evaluó este nodo simplemente se regresa el resultado
    #
    if valor_local != np.Inf:
        return valor_local
    #
    # Si es el nodo destino se calcula el valor de la distancia entre las
    # muestras de ambos procesos, se guarda en la matriz y se regresa el valor.
    #
    if pivote == destino:
        matdp[piv_y][piv_x] = evalua(Y[piv_y],X[piv_x])
        return matdp[piv_y][piv_x]
    else:    
    #
    #  en caso contrario se calcula el camino óptimo al nodo destino evaluando
    #  los movimientos posibles.
    #
        v_min = np.Inf
        for mov in movs:
            #
            # cuidamos que no se salga del huacal y en su caso procedemos
            #
           if piv_y+mov[0] <= destino[0] and piv_x+mov[1] <= destino[1]:   
              v_mov = dp_00_rec(matdp,matmov,Y,X,movs,destino,(piv_y+mov[0],piv_x+mov[1]))
              if v_mov < v_min:
                  mov_min = mov
                  v_min   = v_mov
    #
    # Se define el valor de este nodo y el movimiento al nodo que lo produjo.
    #              
    matmov[piv_y][piv_x] = mov_min
    matdp[piv_y][piv_x]  = evalua(Y[piv_y],X[piv_x]) + v_min
    return matdp[piv_y][piv_x]

# -----------------------------------------------------------------------------
#                                dp_00
# -----------------------------------------------------------------------------
# dp_00 recibe los dos procesos a comparar para obtener el emparejamiento
# óptimo, recibe los valores muestreados de los estados de ambos procesos.
# Los procesos entran a su vez como areglos bidimensionales con la cantidad de
# muestras en el tiempo en los renglones o primera dimensión y con los componentes
# en las columnas o segunda dimensión. Por lo general son T tiempos de muestreo y
# cada muestreo tiene M componentes.
# Se requiere el apareamiento porque no necesariamente son la misma cantidad
# de los valores en el tiempo y uno de los procesos se muestreó en Ty instantes
# del tiempo y el otro en Tx instantes del tiempo.
#
# La ruta de emparejamiento se lleva a cabo desde el nodo [0][0] de la matriz
# de correspondencias al nodo [Vr_Y.shape[0]-1][Vc_X.shape[0]-1].
# entradas:
# Vr_Y        Proceso que se coloca como filas en la matriz de correspondencias.
# Vc_X        Proceso que se coloca como renglones en la matriz de correspondencias.
#
# salidas:
# valor       Valor mínimo obtenido como suma de las distancias euclideanas entre
#             los apareamientos de instantes muestrales de los procesos. 
# matdp       Matriz de correspondencias actualizadas con los valores mínimos
#             desde cada nodo [i][j] al nodo destino.
# matmov      En cada valor de esta matriz se tiene el movimiento que produjo
#             el camino ruta con valor mínimo.
# ruta        La ruta desde origen a destino con valor mínimo.
# movs_ruta   Los movimientos que corresponden a la ruta de valor mínimo.
#  
#  Esta función es el acoplador entre el programa del usuario y el proceso de 
#  búsqueda del emparejamiento (posiblemente múltiple) óptimo.
#
    
def dp_00(Vr_Y,Vc_X, movs = [(0,1),(1,0),(1,1),(0,2),(2,0),(1,2),(2,1)]):
   
    m = Vr_Y.shape[0]
    n = Vc_X.shape[0]
    matdp = np.zeros((m,n))
    matmov = [None]*m
    for i in range(m):
        matmov[i] = [None]*n
    for i in range(m):
        for j in range(n):
            matdp[i][j] = np.Inf
            
    destino = (m-1,n-1)
    origen = (0,0)
  
    valor = dp_00_rec(matdp,matmov,Vr_Y,Vc_X,movs,destino,origen)
    ruta,movs_ruta = elabora_ruta(matmov,matdp,origen,destino)
    return valor,matdp,matmov,ruta,movs_ruta             

# -----------------------------------------------------------------------------
#                   elabora_ruta
# -----------------------------------------------------------------------------
# Esta función se utiliza a posteriori para obtener la ruta óptima y otros valores
# necesarios en el aspecto de "negocio" del problema a tratar.
#

def elabora_ruta(matmov,matdp,origen,destino):
    
    col      = origen[0]
    ren      = origen[0]
    ruta     = [(col,ren)]
    movs_ruta = []
    while col < destino[0] or ren < destino[1]:
        col_sig = col + matmov[col][ren][0]
        ren_sig = ren + matmov[col][ren][1]
        movs_ruta.append((matmov[col][ren][0],matmov[col][ren][1]))
        col = col_sig
        ren = ren_sig
        ruta.append((col,ren))
    return ruta,movs_ruta   
        

# =============================================================================
#                        
# =============================================================================
def obtenMatDist(Vr_Y,Vc_X, movs = [(0,1),(1,0),(1,1),(0,2),(2,0),(1,2),(2,1)]):
    m = Vr_Y.shape[0]
    n = Vc_X.shape[0]
    
    matalc  = np.zeros((m,n))
    matval  = np.zeros((m,n))
    for r in range(m):
      for c in range(n):
         matval[r][c] = np.Inf
         
    matalc[0][0] = 1

    for r in range(m):
        for c in range(n):
          if matalc[r][c] == 1:   # este vertice es alcanzable            
            for mov in movs:
                if r + mov[0] < m:
                    if c + mov[1] < n:
                        matalc[r + mov[0]][c + mov[1]] = 1
    for r in range(m):
      for c in range(n):
        if matalc[r][c] == 1:  
           matval[r][c] = evalua(Vr_Y[r],Vc_X[c])                      
                        
    dict_dist = dict()
    for r in range(m):
        for c in range(n):
            if matalc[r][c] == 1:
                dict_rc = dict_dist.get((r,c),None)
                if dict_rc == None:
                    dict_rc = dict()
                for mov in movs:
                  if r + mov[0] < m:
                    if c + mov[1] < n:
                      dict_rc[(r+mov[0],c+mov[1])] = matval[r + mov[0]][c + mov[1]]                                         
                #if len(dict_rc) > 0:
                dict_dist[(r,c)] = dict_rc
    return matalc,matval,dict_dist
# =============================================================================
#                    distMinVerticeDijstra:
# Obtiene la distancia mínima desde un vèrtice dado al resto de los vértices
# del grafo.    
# =============================================================================
def distMinVerticeDijstra(dict_dist,vertice,destino):
    #
    # Distancia inicial del vérice origen al resto de los vértices.
    # Se actualizará conforme se ejecute el proceso.
    #
    dmin_vertice = dict_dist[vertice].copy()
    #
    # para guardar los predecesores
    #
    predecesores = dict()
    #
    # Se marcan los predecesores de los vértices inicialmente conectados al "vertice"
    #
    
    for k in dmin_vertice:
            predecesores[k] = vertice
    #
    # Y la distancia del vertice a sí mismo se hace cero.
    #         
    dmin_vertice[vertice] = 0.0
    for k in dict_dist.keys():
        dmin_vertice[k] = dmin_vertice.get(k,np.Inf)
                
    #
    # El conjunto porVerificar contiene los vértices aún no verificados
    #
    porVerificar = set([t for t in dmin_vertice.keys()])
    verificados  = set()
    #
    # Se itera sobre los vertices no verifiados eligiendo el que tenga la distancia
    # mínima al vertice dado
    #
    while len(porVerificar) and not (destino in verificados) > 0:
        #
        # Las dos instrucciones siguientes sirven para localizar el vértice aún no 
        # verificado y que tenga la distancia mínima.
        #
        l_pv   = list(porVerificar)
        l_dist = np.array([dist for dist in [dmin_vertice[k] for k in l_pv]])
        v =l_pv[l_dist.argmin()]
        #
        # una vez elegido eliminamos v del conjunto de vértices por verificar.
        #
        porVerificar.remove(v)
        verificados.add(v)
        #
        # Obtenemos los adyacentes al vértice v seleccionado
        #
        adyacentes = [k for k in dict_dist[v].keys() if (k != vertice) and (k in porVerificar) and not ( k in verificados)]
        #print(v,adyacentes)
        #
        # y en su caso actualizamos la distancia al vértice dado utilizando
        # al vértice v como pivote.
        # 
        for w in adyacentes:
            dmin_vertice[w] = dmin_vertice.get(w,np.Inf)
            if dmin_vertice[w] > dmin_vertice[v] + dict_dist[v][w]:
                dmin_vertice[w] = dmin_vertice[v] + dict_dist[v][w]
                predecesores[w] = v
                porVerificar.add(w)
                
                
    return dmin_vertice,predecesores,verificados
    

# =============================================================================

# =============================================================================
#                        PROGRAMA PRINCIPAL
# =============================================================================
"""
 En los negocios y en distintas ramas de la ingeniería se tienen ocasiones en
 las que se tienen procesos que se desean identificar o ver qué tanto se asemejan.
 La manera en que se tienen los procesos es como muestreos en el tiempo.
 Cada procesos tiene M componentes y cada componente tiene un valor que es 
 función del tiempo.
 Así, el proceso tendrá (para cada muestra en el tiempo) M valores (que suponemos
 números reales).
 A la vez, suponemos que se tienen T instantes de los muestreos en el tiempo.
 Entonces, cada proceso se caracteriza por una matriz de T valores como renglones
 y M columnas, una por cada componente.
 
 El algoritmo de programación dinámica sirve para obtener el emparejamiento 
 (posiblemente múltiple) entre las muestras en el tiempo de dos procesos que
 se desean identificar.
 
 La problemática involucra el hecho de que ambos procesos no se han muestreado
 ni en exactamente los mismos instantes del tiempo y en algunos casos con valores
 diferentes de muestras en el tiempo. Así un proceso puede tener T1 muestras 
 en el tiempo y el otro T2.

 
 El algoritmo de búsqueda del apareamiento de los instantes en el tiempo 
 utiliza una matriz de valores de las diferencias entre los
 valores de las muestras en el tiempo para ambos procesos. Por lo general se 
 utiliza la distancia euclideana pero puede ser otra y en ocasiones utilizamos 
 otra métrica o hasta una medida de distorsión (no simétrica). En este ejemplo
 se utiliza la distancia euclideana.

  Para la busqueda de la distancia mínima se utiliza un conjunto permitido de 
  movimientos para "avanzar" del nodo en que nos encontremos hacia el destino.
  
  El algoritmo funciona recursivamente y va trabajando incialmente como DFS y 
  posteriormente va como BFS aprovechando el trabajo ya realizado.

"""

if __name__ == "__main__":
  
  N1 = 50     # intervalos de tiempo para el muestreo del primer proceso
  N2 = 45    # intervalos de tiempo para el muestreo del segundo proceso
  
  M  = 5       # cantidad de componentes en cada momento para ambos procesos 
  
  #
  # Parámetros para la generación pseudoaleatoria de los conjuntos de prueba
  #
  
  sigmaC = 0.0125 # perurbación para los valores de los componentes
  sigmaT = 0.125  # perturbación para los valores de los tiempos de muestreo
  #
  # Se generan los procesos cada uno con M componentes (Ignorar)
  #
  V1 = genMuestra(N1,M,sigmaC,sigmaT)
  
  V2 = genMuestra(N2,M,sigmaC,sigmaT)
  #
  # Gráfica para monitoreo (Ignorar)
  #
  plt.plot(V1)
  plt.plot(V2)
  plt.show()
  
  #
  # Se aplica el DP y se obtiene la medida de diferencia
  #
  movs = [(0,1),(1,0),(1,1),(0,2),(2,0),(1,2),(2,1)]
  
  #movs = [(1,1),(2,2),(1,2),(2,1)] 
  t0_dp = time.time()
  valor,matdp,matmov,ruta,movs_ruta = dp_00(V1,V2,movs)
  deltaT_dp = impDeltaT("DP", t0_dp)
  
  print(cadsep)
  print("Valor:",valor)
  print("Ruta:")
  print(ruta)
  print("Movimientos en la ruta:")
  print(movs_ruta)
  print(cadsep)
  print("La SEMILLA fué:",SEMILLA)
  
  t0_prep_dijstra = time.time()
  matalc,matval,dict_dist = obtenMatDist(V1,V2,movs)
  deltaT_prep_dijstra = impDeltaT("Prop Dijstra",t0_prep_dijstra)
  
  print(cadsep)
  print("             matalc")
  print(cadsep)
  print(matalc)
  print(cadsep)
  print("            matval")
  print(cadsep)
  print(matval)
  print(cadsep)
  print("     dict_dist")
  print(cadsep)
  for k in dict_dist.keys():
      print(str(k), dict_dist[k])
  print(cadsep)  
  
  t0_dijstra = time.time()
  dmin_vertice,predec_vertice,verificados = distMinVerticeDijstra(dict_dist,(0,0),(N1-1,N2-1))
  deltaT_dijstra = impDeltaT("Doijstra",t0_dijstra)
  
  print(cadsep)
  print("             dmin_vertice")
  print(cadsep)
  print(dmin_vertice)
  print(cadsep)
  print("            predec_vertice")
  print(cadsep)
  print(predec_vertice)
  print(cadsep)
  print("    verificados")
  print(verificados)
  print(cadsep)
  destino = (N1-1,N2-1)
  suc = destino
  ruta_dijstra = [suc]
  while suc != (0,0):
      suc = predec_vertice[suc]
      ruta_dijstra.append(suc)
  ruta_dijstra = ruta_dijstra[::-1]
  print("   Ruta Dijstra:", ruta_dijstra)
  print(cadsep)
  print("        Ruta dp:",ruta)
  print(cadsep)
  print("DP:           {:>8.4f} segundos".format(deltaT_dp))
  print("Prep Dijstra: {:>8.4f} segundos".format(deltaT_prep_dijstra))
  print("Dijstra:      {:>8.4f} segundos".format(deltaT_dijstra))
  print("Total Dijstra:{:>8.4f} segundos".format(deltaT_prep_dijstra+deltaT_dijstra))
  print(cadsep)
    

# =============================================================================
#                        Fin del Código
# =============================================================================

