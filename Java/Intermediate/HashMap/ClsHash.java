/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_02;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author RGAMBOAH
 */
public class  ClsHash <E extends IObjetoPortado>
{
    int tamInicial = 128;
    java.util.ArrayList<ClsPortaObjetos> listaPortaObjetos; // = new java.util.ArrayList<>();
    java.util.ArrayList<ClsPortaObjetos> arr[] = new java.util.ArrayList[tamInicial];
    
    int intMod     = tamInicial;
    int tam_actual = tamInicial;
    int intModMax  = 4096;
    
    ClsPortaObjetos<E> po;
    int pos;
    int hay = 0;
    int lugares_ocupados = 0;
    
    
    public double duplicaHash()
    {
        double densidad = 0.0;
        if( tam_actual < intModMax)
        {
            intMod *= 2;
        }
        
        return densidad; 
    }
    
    public int agrega(E obj)
    {
        po = new ClsPortaObjetos(obj);
        pos = po.posicion(tam_actual);
        
        if(arr[pos] == null)
        {
           arr[pos] = new java.util.ArrayList();
           lugares_ocupados++;
        }
        arr[pos].add(po);
        hay++;
         
        return hay;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        int k;
        int kj;
        
        for( k = 0; k < tam_actual; k++)
            if( arr[k] != null)
            {
                kj = 0;
                sb.append("arr[").append(k).append("]:");
                for(ClsPortaObjetos porta_obj : arr[k])
                {   
                    kj++;
                    if( kj > 1) sb.append("         ");
                    sb.append(porta_obj.toString()).append('\n');
                }
            }
        return sb.toString();
    }
    
    // =========================================================================
    // rutina de prueba local para esta clase
    // =========================================================================
    
    public static void main( String args[])
    {
      int CUANTOS = 256;
      
      ClsHash tabla_hash = new ClsHash<ClsPersona>();
      ClsPersona per;
      
      long num_gen_fem  = 0;
      long num_gen_masc = 0;
      
      ClsColCadenas.IMPRIME = false;
      
      boolean tienen_genero;
      System.out.println(System.getProperty("file.encoding"));
      
      String strTrayArchNombres;
      String strTrayArchApellidos;
      Path currentRelativePath = Paths.get("");
      String strCWD = currentRelativePath.toAbsolutePath().toString();
      System.out.println("Current absolute path is:" + strCWD);
    
      strTrayArchNombres = strCWD + '/' + "nombres.csv"; //Apellidos_mas_comunes_2020.csv"; 
      tienen_genero = true;
      ClsColCadenas colNombres = ClsColCadenas.obtenColCadenas(strTrayArchNombres,tienen_genero);        
    
      strTrayArchApellidos = strCWD + '/' + "Apellidos_mas_comunes_2020.csv"; 
      tienen_genero = false;
      ClsColCadenas colApellidos = ClsColCadenas.obtenColCadenas(strTrayArchApellidos,tienen_genero);    
      
      int k;
      String strNombre, strPrimAp, strSegAp, strGen;
      String strGenero[] = new String[1];
      
      for(k = 1; k <= CUANTOS;k++)
      {
          strNombre = colNombres.unNombre(strGenero);
          strGen    = strGenero[0];
          strPrimAp = colApellidos.unNombre(strGenero);
          strSegAp  = colApellidos.unNombre(strGenero);
          
          if( strGen.compareTo("Fem") == 0)
              num_gen_fem++;
          else
              num_gen_masc++;

          System.out.println("k:" + k + " ... " + strGen + " " + strNombre + " " + strPrimAp + " " + strSegAp);
           
          per = new ClsPersona(strNombre,strPrimAp,strSegAp,strGen);
         
          tabla_hash.agrega(per);
      }
      
      System.out.println("=====================================================");
      System.out.println("                  Tabla Hash");
      System.out.println("=====================================================");
      System.out.println(tabla_hash);
      System.out.println("=====================================================");
       
      System.out.println("Fem: " + num_gen_fem );
      System.out.println("Masc:" + num_gen_masc);
     
      
      System.out.println("=====================================================");
      System.out.println("colNombres,   SEMILLA:" + colNombres.getSEMILLA() );
      System.out.println("colApellidos, SEMILLA:" + colApellidos.getSEMILLA() );
      System.out.println("=====================================================");
             
    }
    
    
}
