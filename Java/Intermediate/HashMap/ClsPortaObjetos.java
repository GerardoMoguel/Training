/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_02;

/**
 *
 * @author RGAMBOAH
 * @param <E> implementando la strCadena para poder obtener el valor hash.
 */
public class ClsPortaObjetos<E extends IObjetoPortado> 
{
    E objeto;
    int valHash;
    
    public ClsPortaObjetos( E objeto )
    {
        this.objeto = objeto;
    }
    
    public void calc_valHash( int usarMod )
    {
        int valor = 0;
        String strCadena = objeto.cadena().strip();
        for( int k = 0; k < strCadena.length(); k++)
           valor += ((int) strCadena.charAt(k));
        //valor = valor % usarMod; //segun yo esta no deberia de ir
        
        this.valHash = valor;
        
    }  
          
    public int posicion( int intTam )
    {
        this.calc_valHash(intTam);
        return this.valHash % intTam;
    }        
    
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + " accareando un objeto de tipo " + objeto.getClass().getSimpleName() + " con contenido " + objeto.toString();
    }
    
    // =========================================================================
    // rutina de prueba local para esta clase
    // =========================================================================
    
    public static void main(String args[])
    {
        ClsPersona per = new ClsPersona("Rafael", "Gamboa","Hirales","Masc");
        
        ClsPortaObjetos po = new ClsPortaObjetos(per);
        
        System.out.println("po:" + po);
        
        // probando el cálculo del valor hash a almacenar
        
        po.calc_valHash(1024);
        long pos = po.posicion(1024);
        
        System.out.println("va en la posición " + pos);
        System.out.println("El valHash es " + po.valHash );
    }
                
}
