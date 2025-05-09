/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hash_02;

import java.io.File;
import java.io.FileNotFoundException;
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
public class ClsColCadenas 
{
    public static boolean IMPRIME = false;
    
    long SEMILLA            = 1234;    
    java.util.Random rnd    = new java.util.Random();
    ClsCadena arr_simple[]  = null;
    
    public ClsColCadenas()
    {
        rnd.setSeed(SEMILLA);
    }
    
    public long getSEMILLA() {
        return SEMILLA;
    }

    public void setSEMILLA(long SEMILLA) {
        rnd.setSeed(SEMILLA);
        this.SEMILLA = SEMILLA;
    }
    
    public double unif01()
    {
        return rnd.nextDouble();
    }

    public static ClsColCadenas obtenColCadenas( String strTrayNomArch, boolean blnSonNombres) 
    {
        java.util.ArrayList<ClsCadena> arrCadenas = new java.util.ArrayList<>();    
        ClsColCadenas colCad    = new ClsColCadenas(); // agregarle la funcionalidad a estas instancias
        ClsCadena     objCadena = null;
        String strCadena        = null;
        String strGenero        = null;
        int    frec             = 0;
        int    van              = 0;   
        
        
        
        try 
        {
            File arch_txt = new File(strTrayNomArch);
            
            Scanner entrada = new Scanner(arch_txt);
            entrada.useDelimiter(Pattern.compile("[\\r\\n;]+|,"));
            
            while( entrada.hasNext())
            {
                van++;
                strCadena = entrada.next();
                frec      = entrada.nextInt();
                
                objCadena = new ClsCadena();
                objCadena.setStrCadena(strCadena);
                if( blnSonNombres)
                {
                    strGenero = entrada.next();
                    objCadena.setStrAdicional(strGenero);
                }
                else
                    objCadena.setStrAdicional(strCadena); // solo para probar
                objCadena.setFrecuencia(frec);
                objCadena.setProp_acum(frec);             // para probar el ordenamiento
                
                arrCadenas.add(objCadena);
                if(IMPRIME) System.out.println(van + " => " +strCadena + " .... " + frec);
            }
            entrada.close();
            //
            //  probando el ordenamiento sobre un arraylist de objetos
            //
            arrCadenas.sort(new OrdPorPropAcum());
            if(IMPRIME) System.out.println(arrCadenas);
           
            //
            // Obtenemos las proporciones 
            //
            double sumProp = 0.0;
            for(ClsCadena cadena : arrCadenas )
                sumProp += cadena.getProp_acum();
            
            double propAcumCad = 0.0, aux_prop = 0.0;
            for(ClsCadena cadena : arrCadenas )
            {  
            // los aleatorios uniforme se generan en [0,1)
               
                aux_prop = cadena.getProp_acum();
                propAcumCad += aux_prop;
                cadena.setProp_acum(propAcumCad/sumProp);
               
            }
            if(IMPRIME)System.out.println(arrCadenas);
            
            //
            // Generando algunos apellidos al azahar
            //
            int tam = arrCadenas.size();
            if(IMPRIME)System.out.println("Size:" + tam);
            
            colCad.arr_simple = new ClsCadena[tam];
            int i = 0;
            for(ClsCadena cadena : arrCadenas)
                colCad.arr_simple[i] = arrCadenas.get(i++);
           
            // truco para la búsqueda binaria
            ClsCadena cad_aux = new ClsCadena();
            double u = 0.0;
            int    k = 0;
            OrdPorPropAcum objComp = new OrdPorPropAcum();
            
            int NUM_GEN = 100;
            
            int arr_k_usados[] = new int[tam];
            Arrays.fill(arr_k_usados,0);
            
            if(IMPRIME)
            {
              for( i = 0; i < NUM_GEN; i++ )
              {
                u = colCad.unif01(); // Usamos el del java.util
                cad_aux.setProp_acum(u);
                k = Arrays.binarySearch(colCad.arr_simple, cad_aux, objComp);
                if(k < 0) k = -k;
                k--;
                //if( k >= tam ) k = tam -1; // innecesario por el 1.0 de prob_acum
                arr_k_usados[k]++;
                System.out.print(i + "," + u + "," + k + "," + colCad.arr_simple[k]);
              }
            
              System.out.println("=====================================================");
              System.out.println("       Reporte de índices utilizados");
              for( k = 0; k < tam; k++)
                if(arr_k_usados[k] > 0) System.out.println(k + " ... " + arrCadenas.get(k).strCadena + "     " + arr_k_usados[k] + " veces" );
              System.out.println("Son " + tam + " cadenas");
              System.out.println("====================================================="); 
            }  
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(ClsColCadenas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colCad;
    }   

    public String unNombre(String strGenero[])
    {
        ClsCadena cad_aux = new ClsCadena();
        double u;
        int    k;
        OrdPorPropAcum objComp = new OrdPorPropAcum();
        u = this.unif01(); // Usamos el del java.util
        cad_aux.setProp_acum(u);
        k = Arrays.binarySearch(this.arr_simple, cad_aux, objComp);
        if( k < 0) k = -k;
        k--;
        
        strGenero[0] = this.arr_simple[k].strAdicional;
        return this.arr_simple[k].strCadena;
    }        

// =============================================================================    
// rutina de prueba local para esta clase
// =============================================================================
    
enum TIPO_DATOS {NOMBRES,APELLIDOS};

  public static void main(String args[]) throws NoSuchFieldException
  {
      
      
      TIPO_DATOS tipo_datos;
      
      tipo_datos = TIPO_DATOS.APELLIDOS;
      boolean tienen_genero;
      System.out.println(System.getProperty("file.encoding"));
      
      String strTrayArchCadenas = null;
      Path currentRelativePath = Paths.get("");
      String strCWD = currentRelativePath.toAbsolutePath().toString();
      System.out.println("Current absolute path is:" + strCWD);
      
      if( tipo_datos == TIPO_DATOS.NOMBRES )
      {
         strTrayArchCadenas = strCWD + '/' + "nombres.csv"; //Apellidos_mas_comunes_2020.csv"; 
         tienen_genero = true;
      }        
      else
      {
         strTrayArchCadenas = strCWD + '/' + "Apellidos_mas_comunes_2020.csv"; 
         tienen_genero = false;
          
      }
      
      ClsColCadenas colCadenas = ClsColCadenas.obtenColCadenas(strTrayArchCadenas,tienen_genero);
     
      System.out.println("=====================================================");
      System.out.println("SEMILLA:" + colCadenas.getSEMILLA() );
      System.out.println("=====================================================");
     
      
      
      
  }
    
}
