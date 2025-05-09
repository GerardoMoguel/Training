/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hash_02;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**
 *
 * @author rggh
 */
public class Hash_02 {
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
