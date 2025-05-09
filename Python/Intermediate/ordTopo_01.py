# -*- coding: utf-8 -*-
"""
Created on Thu Apr 25 19:52:08 2024

@author: RGAMBOAH
"""

cadsep = "=" * 60
# =============================================================================
#
# =============================================================================

class AsaTopo:
    def __init__(self,nombre_topo):
        self.nombre        = nombre_topo
        self.dic           = {}
        self.lista_ord     = []
        self.inconsistente = False
        self.ordenada      = False
        self.vacia         = True
        
    def agrega(self,id_inicio,id_fin): #probablemente tengamos que optimizarlo
        self.vacia   = False
        # si lo tiene lo entrega, si no lo tiene lo agrega y lo crea
        #no verifico que no hubieran duplicados
        nodo_inicial = self.dic.get(id_inicio,NodoTopo(id_inicio))
        nodo_final   = self.dic.get(id_fin,   NodoTopo(id_fin   ))
        
        self.dic[id_inicio] = nodo_inicial
        self.dic[id_fin   ] = nodo_final
        nodo_inicial.agregaSucesor(nodo_final)
        #si ya lo tiene lo vuelve a guardar y tiene que organizar cosas
        
    def ord_topo(self):
        sigue = True
        lis_nodos = list(self.dic.values())
        while sigue and len(lis_nodos) > 0:
            # buscamos uno que ya no tenga antecesores
            for nodo in lis_nodos:#en algun momento tenemos que proteger el codigo con un 
                if nodo.antecesores == 0:
                    break
            if nodo.antecesores > 0: # ciclo... 
               raise "Ciclo, ..."
            
            # disminuimos en 1 los antecesores de los sucesores de este nodo
            for nodo_suc in nodo.lista_suc:
                nodo_suc.antecesores -= 1
            
            # y lo pasamos a la lista de nodos ordenados
            self.lista_ord.append(nodo)
            lis_nodos.remove(nodo)
            
        self.ordenada = True        
                
        
        
        
    def __str__(self):
        strRes = "Red topológica " + self.nombre + '\n'
        
        if self.vacia:
            strRes += "Red vacía...\n"
        else: 
            if self.ordenada: # tambien deberia de preguntar si esta vacia al ordenar
                strRes += "Red ordenada:\n"
                for nodo in self.lista_ord:
                    strRes += str(nodo) + '\n'
            else:
                if self.inconsistente:
                    strRes += "Tiene ciclos\n"
                else:
                    strRes += "No se ha ordenado\n"
                    for nodo in self.dic.values():
                        strRes += "-----------------\n"
                        strRes += str(nodo) + '\n'
        return strRes

# =============================================================================
#
# =============================================================================
class NodoTopo:
    def __init__(self,edo_ini):
        self.id          = edo_ini
        self.antecesores = 0
        self.lista_suc   = []
    
    def agregaSucesor(self,nodoSucesor):
        self.lista_suc.append(nodoSucesor)
        nodoSucesor.antecesores += 1

    def __str__(self):
        strRes = "NodoTopo " + self.id + \
                 " con " + str(self.antecesores)  +" antecesores\n"
        if len(self.lista_suc) > 0:
          strRes += "Sucesores:\n"
          for s in self.lista_suc:
            strRes += str(s.id) + "\n"
        else:
            strRes += "Sin sucesores"
        return strRes     

# =============================================================================
#
# =============================================================================



# =============================================================================
#                           Programa Principal
# =============================================================================
if __name__ == "__main__":
    
    asaTopo = AsaTopo("Red de prueba")

    print(cadsep)
    print("asaTopo:" + str(asaTopo))
    print(cadsep)
    
    asaTopo.agrega('A','B')
    asaTopo.agrega('A','W')
    asaTopo.agrega('B','D')
    asaTopo.agrega('A','C')
    asaTopo.agrega('H','I')
    asaTopo.agrega('I','J')
    asaTopo.agrega('J','K')
    asaTopo.agrega('W','T')
    asaTopo.agrega('T','D')
    asaTopo.agrega('I','T')
    
    print('Nodos agregados..')
    print("asaTopo:" + str(asaTopo))

    print(cadsep)
    asaTopo.ord_topo()
    print('Red ordenada..')
    print("asaTopo:" + str(asaTopo))
    print(cadsep)


# =============================================================================
#
# =============================================================================
        