# -*- coding: utf-8 -*-

# =============================================================================
#                                Utilerías Generales
# =============================================================================
import sys

cadsep = "=" * 60

def letrero(strLetrero):
    print(cadsep)
    print("          " + strLetrero)
    print(cadsep)

def salir(strLetrero):
    letrero(strLetrero)
    sys.exit()	
# =============================================================================
#                                Clase de la Contabilidad
# =============================================================================
class Contabilidad:
    def __init__(self,ejer,nombre):
        self.ejercicio = ejer
        self.nombre    = nombre
        self.colPartes = [0] * 6
        self.colPartes[1] = ParteContable(1,"Activo  ","D")
        self.colPartes[2] = ParteContable(2,"Pasivo  ","A")
        self.colPartes[3] = ParteContable(3,"Capital ","A")
        self.colPartes[4] = ParteContable(4,"Ingresos","A")
        self.colPartes[5] = ParteContable(5,"Egresos ","D")
        
        
    def altaCta(self,id_cta,nomCta,natCta):
        #print("Se va a dar de alta la cuentaT:" +
        #      str(id_cta) + " " + nomCta + " (" + natCta + ")")
        #
        # identificamos la parte a la que le corresponde la responsabilidad de 
        # dar de alta la cta
        #
        idParte = id_cta // 100000
        if idParte < 1 or 5 < idParte :
            raise Exception("la parte " + str(idParte) + " en la cta " + str(id_cta) + " no existe")
        else:
            if self.colPartes[idParte].existeCta(id_cta):
              raise Exception("la cta " + str(self.idCta) + " ya existe")  
            else:
              self.colPartes[idParte].altaCta(id_cta,nomCta,natCta)  

    def registraPoliza(self, pol): #arreglar este para poner como un sB de como hacer para que tenga una lista d elos problemas en vez de que te avise cada vez que haya un pedo
        blnRes = True
        #
        # Verificar que la poliza estè cuadrada suma de cargos == suma de abonos
        sc=0 #suma de cargos
        sa=0#suma de abonos
        for m in pol.colMovtos:
            if(m.tipo == 'C'):
                sc += m.monto
            else:
                sa += m.monto
                
        if sc != sa:
            raise Exception("Poliza descuadrada: \n" + pol)
        # y que cada una de las cuentas involucradas existan
        # (En caso de error mandar una excepcion)
        for m in pol.colMovtos:
            num_cta = m.numCta
            idParte = num_cta // 100000
            if idParte < 1 or 5< idParte:
                raise ("La cta "+ str(num_cta) + " es invalidad")
            else:
                if not self.colPartes[idParte].existeCta(m.numCta):
                    raise("La cta " + str(num_cta) + " no existe")
              
        # En su caso postear la poliza a las cuentas
        for m in pol.colMovtos:
            self.colPartes[m.numCta // 100000].registraMovto(m)
        
        return blnRes

    def cierre (self):
        self.altaCta (300100, 'Resultado del ejercicio','A')
        poliza_cierre = PolizaContable(1000,"Poliza de cierre", 20231231)
        self.colPartes[4].cierra(poliza_cierre)
        self.colPartes[5].cierra(poliza_cierre)
        self.registra(poliza_cierre)
            
    def __str__(self):
        strRes = str(self.nombre) + " " + str(self.ejercicio) + "\n"
        
        for k in range(1,6):
          strRes += str(self.colPartes[k])
        return strRes
        
          
       
        
class ParteContable:
    def __init__(self,idParte,nombre,nat):
        self.id        = idParte
        self.nombre    = nombre
        self.nat       = nat
        self.ColCtas   = {}

    def existeCta(self,idCta):
        return self.ColCtas.get(idCta) != None
        
    
    def altaCta(self,id_cta,nomCta,natCta):
        #print("Parte conta:"+str(self.id) +
        #      " Se va a dar de alta la cuentaT:" +
        #      str(id_cta) + " " + nomCta + " (" + natCta + ")")
        self.ColCtas[id_cta] = CuentaContable(id_cta,nomCta,natCta)

    def registraMovto(self,movto):
        cta = movto.numCta
        self.ColCtas[cta].registraMovto(movto) #aqui se modifica para q se vea bien los numeros, en formato 1,000.00

    def __str__(self):
        strRes = str(self.id) + " ... " + \
                 self.nombre + " (" + self.nat + ")\n"
        for cta in self.ColCtas.values():
                     strRes += str(cta)
        return strRes
    
    def cierra(self, polc):
        for cta in self.ColCtas.values():
            movto_saldo_cta = cta.saldo()
            movto_saldo_res = movto_saldo_cta.copy
            movto_saldo_res.numCta = 300100
            if movto_saldo_cta.tipo == 'C':    
                movto_saldo_cta.tipo ='A'
            else:
                movto_saldo_cta.tipo = 'C'
            
        
class CuentaContable:
    def __init__(self,idCta,nombre,nat):
        self.numCta    = idCta
        self.nombre    = nombre
        self.nat       = nat
        self.ColMovtos = []
    
    def registraMovto(self,movto):
        self.ColMovtos.append(movto)
        
    def __str__(self):
        strRes = str(self.numCta) + " ... " + \
                 self.nombre + " (" + self.nat + ")\n"
        for m in self.ColMovtos:
            strRes += str(m) + '\n'
        return strRes 
    

class PolizaContable:
    def __init__(self,id,desc,intFecha):
        self.idPoliza    = id
        self.descripcion = desc
        self.fecha       = intFecha
        self.colMovtos   = []
        
    def cargo(self,idCta,monto):
        m = MovtoContable("C", idCta, monto)
        self.colMovtos.append(m)
        
    def abono(self,idCta,monto):
        m = MovtoContable("A", idCta, monto)
        self.colMovtos.append(m)
        
    def __str__(self):
        strRes = "Poliza " + str(self.idPoliza) + " " + \
                 self.descripcion + " " + str(self.fecha) + "\n"
        for m in self.colMovtos:
            strRes += str(m) + "\n"
        return strRes

class MovtoContable:
    def __init__(self,tipo,numCta,monto):
        self.tipo   = tipo
        self.numCta = numCta
        self.monto  = monto

    def __str__(self):
         strRes = "(" + self.tipo + ") " + str(self.numCta)
         if self.tipo == "A":
             strRes += " " * 12
         strRes += "  " + str(self.monto)
         return strRes
     

# =============================================================================
#                                Script principal
# =============================================================================

conta = Contabilidad(2023, "MiEmpre S.A.")

conta.altaCta(100100,"Bancos               ","D")
conta.altaCta(100200,"Inventario           ","D")
conta.altaCta(200100,"Proveedores          ","A")
conta.altaCta(300000,"Capital              ","A")
conta.altaCta(400100,"Ventas               ","A")
conta.altaCta(500100,"Costo de lo Vendido  ","D")


print(cadsep)
print(conta)


pol1 = PolizaContable(1,"Constitución de la Empresa",20190121)
pol1.cargo(100100,10000)
pol1.abono(300000,10000)
conta.registraPoliza(pol1)

pol2 = PolizaContable(2,"Compra de mercancía por 3000 pagados al contado",20190122)
pol2.cargo(100200,3000)
pol2.abono(100100,3000)
conta.registraPoliza(pol2)

pol3 = PolizaContable(3,"Venta al contado por 1500 de mercancía que costó 1000",20190122)
pol3.abono(100200,1000)
pol3.cargo(500100,1000)
pol3.abono(400100,1500)
pol3.cargo(100100,1500)
conta.registraPoliza(pol3)

pol4 = PolizaContable(4,"Venta al contado por 1000 de mercancía que costó 750",20190201)
pol4.abono(100200, 750)
pol4.cargo(500100, 750)
pol4.abono(400100,1000)
pol4.cargo(100100,1000)
conta.registraPoliza(pol4)

print(cadsep)
print (conta) #estudiar perfectamente bien el codigo
#print(conta.balance())
print(cadsep)

conta.cierre()
print(cadsep)
print("Posterior al cierre")
print(cadsep)
print(conta)
print(cadsep)

#
# ========================== FIN DEL SCRIPT PRINCIPAL =====================
