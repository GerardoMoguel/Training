

# =============================================================================
#                                Utilerías Generales
# =============================================================================
import sys
import datetime
import time

cadsep = "=" * 70

def letrero(strLetrero):
    print(cadsep)
    print("          " + strLetrero)
    print(cadsep)

def salir(strLetrero):
    letrero(strLetrero)
    sys.exit()	

# =============================================================================
#   Asigne su CU y nombre como cadenas de caracteres
# =============================================================================
CU     = 'NNNNNNNNN'
NOMBRE = 'PRIMER_APELLIDO SEGUNDO APELLIDO NOMBRE(s)'
# =============================================================================
#                                     print_id
# =============================================================================
def print_id():
    global CU
    global NOMBRE
    print(cadsep)
    print('CU:' + CU + ' ... ' + NOMBRE)
    print(cadsep)

# =============================================================================
#                                     salir
# =============================================================================
def salir(letrero):
    global t0
    print(cadsep)
    print(letrero)
    t1 = time.time()
    deltaT = t1-t0
    print('DeltaT:','{:8.3} segs.'.format(deltaT))
    print(cadsep)
    sys.exit(0)
    
def iif(blnVal, trueVal, falseVal):
    if blnVal:
        return trueVal
    else:
        return falseVal

# =============================================================================
# =============================================================================
#                             Clases de ParteContable
# =============================================================================

# =============================================================================
#                             Clases Contabilidad
# =============================================================================

class Contabilidad:
    def __init__(self,intEjer, strEmpresa):
        self.ejer         = intEjer
        self.empresa      = strEmpresa
        self.colPartes    = [0]*6 #se va a desperdiciar el primer indice (0)
        self.colPartes[1] = ParteContable (1,"Activo   "   , "D")
        self.colPartes[2] = ParteContable (2,"Pasivo   "   , "A")
        self.colPartes[3] = ParteContable (3,"Capital  "  , "A")
        self.colPartes[4] = ParteContable (4,"Ingresos " , "A")
        self.colPartes[5] = ParteContable (5,"Egresos  "  , "D")

        
    def __str__(self):
        strRes = "Contabilidad de "+self.empresa + " ejercicio: "+ str(self.ejer)#sirve para poner un int en una cadena sin necesidad de formatear
        for k in range(1,6):
            strRes+= str(self.colPartes[k]) + "\n"
        return strRes

# =============================================================================
#                             Clases de ParteContable
# =============================================================================
class ParteContable:
    def __init__(self, intId, strNombre, strNat):
        self.id     = intId
        self.nombre = strNombre
        self.nat    = strNat

    def __str__(self):
        strRes = str(self.id) + " " + self.nombre + " (" + self.nat + ")"
        return strRes
# =============================================================================
#                                Script principal
# =============================================================================

#python es no coercitivo.

if __name__ == "__main__":
    strVersion   = "01"
    str_dateTime = datetime.datetime.now().strftime('%d-%m-%Y %H:%M:%S')
    
    print(cadsep)
    print('                            Contabilidad')
    print(' '*25 + str_dateTime)
    print_id()
    
    
    
    
    conta = Contabilidad(2024, "MiEmpre S.A.")
    print (conta)
    """
    conta.altaCta(100100,"Bancos               ","D")
    conta.altaCta(100200,"Inventario           ","D")
    conta.altaCta(200100,"Proveedores          ","A")
    conta.altaCta(300000,"Capital              ","A")
    conta.altaCta(400100,"Ventas               ","A")
    conta.altaCta(500100,"Costo de lo Vendido  ","D")
    
    
    print(cadsep)
    pol1 = Poliza(1,"Constitución de la Empresa",20240121)
    pol1.cargo(100100,10000)
    pol1.abono(300000,10000)
    conta.registraPoliza(pol1)
    
    pol2 = Poliza(2,"Compra de mercancía por 3000 pagados al contado",20240122)
    pol2.cargo(100200,3000)
    pol2.abono(100100,3000)
    conta.registraPoliza(pol2)
    
    pol3 = Poliza(3,"Venta al contado por 1500 de mercancía que costó 1000",20240122)
    pol3.abono(100200,1000)
    pol3.cargo(500100,1000)
    pol3.abono(400100,1500)
    pol3.cargo(100100,1500)
    conta.registraPoliza(pol3)
    
    pol4 = Poliza(4,"Venta al contado por 1000 de mercancía que costó 750",20240201)
    pol4.abono(100200, 750)
    pol4.cargo(500100, 750)
    pol4.abono(400100,1000)
    pol4.cargo(500100,1000)
    conta.registraPoliza(pol4)
    
    print(cadsep)
    print(conta.balance())
    print(cadsep)
    """
#
# ========================== FIN DEL SCRIPT PRINCIPAL =====================
