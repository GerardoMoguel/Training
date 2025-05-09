/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenadores_2024;
import java.lang.management.*;
/**
 *
 * @author rafael
 */
public class Main 
{
     static long   SEMILLA = 12345;
     static int    VECES   =  50; //100;
     static int    N_MIN   =  10000000;
     static int    N_MAX   =  20000000;
     static double dblLIM  = 10000.0d;
    /**
     * @param args the command line arguments
     */
    
    private static java.util.Random rnd;
     
    private enum TipoSort
    {
      enumSELDIR,enumINSDIR,enumSTONESORT,enumQUICKSORT,enumHEAPSORT;    
    } 
    
    private static String strTipo[] = {"SELSORT","INSSORT","STONESORT","QUICKSORT", "HEAPSORT"};
    
    
    public static void main(String[] args)
    {
        int vez, n, n_args = args.length;
        long t0,t1, deltaT;
        double a[];
        
        //String strMetodo = "HEAPSORT";
        //TipoSort cual = TipoSort.enumHEAPSORT;
        
        String strMetodo = "QUICKSORT";
        TipoSort cual = TipoSort.enumQUICKSORT;
        
        if ( n_args < 4 )
        {
            System.out.println("Uso:");
            System.out.println("java - jar ordenamientos.jar veces limInf limSup  metodo [SEMILLA]"  );
            System.out.println("(limInf,limSup) es el rango para la CANTIDAD de datos double a ordenar.");
            System.out.println("Con metodo:");
            System.out.println("SelSort   ... Seleccion directa");
            System.out.println("InsSort   ... Insercion directa");
            System.out.println("StoneSort ... Stone Sort");
            System.out.println("QuickSort ... Quick Sort (Hoare)");
            System.out.println("HeapSort  ... Heap Sort");
        }
        else
        {
          if( n_args > 0 )
            VECES = Integer.parseInt(args[0]);
          if( n_args > 1 )
            N_MIN = Integer.parseInt(args[1]);
          if( n_args > 2 )
            N_MAX = Integer.parseInt(args[2]);
          if( n_args > 3 )
          {
            if( strTipo[0].compareToIgnoreCase(args[3])==0 ) cual = TipoSort.enumSELDIR;
            if( strTipo[1].compareToIgnoreCase(args[3])==0 ) cual = TipoSort.enumINSDIR;
            if( strTipo[2].compareToIgnoreCase(args[3])==0 ) cual = TipoSort.enumSTONESORT;
            if( strTipo[3].compareToIgnoreCase(args[3])==0 ) cual = TipoSort.enumQUICKSORT;
            if( strTipo[4].compareToIgnoreCase(args[3])==0 ) cual = TipoSort.enumHEAPSORT;
          } 
          if( n_args > 4 )
              SEMILLA = Long.parseLong(args[4]);
          else
              SEMILLA = (long)(100000.0*Math.random());
        }
       
        rnd = new java.util.Random(SEMILLA);
        //
        // Modificarlo para que genere las ejecuciones de acuerdo a los solicitado en la Tarea
        //
        for( vez = 1; vez <= VECES; vez++ )
        {
            n = (int)( N_MIN + ( N_MAX - N_MIN )* rnd.nextDouble());
            a = new double[n];
            Ord_2024.rellena(a, -dblLIM, dblLIM, rnd );
            //Ord_2024.rellena(a, -10000000.0d,10000000.0d, rnd );
            t0 = System.nanoTime(); //Main.getCpuTime(); //System.nanoTime();
            switch( cual )
            {
                case enumSELDIR:    Ord_2024.ordenaPorSeleccionDta(a); 
                                    strMetodo = "SeleccionDta"; break;
                case enumINSDIR:    Ord_2024.ordenaPorInsersionDta(a);
                                    strMetodo = "InsercionDta"; break;
                case enumSTONESORT: Ord_2024.ordenaPorStoneSort(a);
                                    strMetodo = "StoneSort";    break;
                case enumQUICKSORT: Ord_2024.ordQuickSort(a);
                                    strMetodo = "QuickSort";    break;
                case enumHEAPSORT:  Ord_2024.ordenaPorHeapSort(a);             
                                     strMetodo = "HeapSort";    break;
            }
            t1 = System.nanoTime(); // Main.getCpuTime();//System.nanoTime();
            //Ord_2024.esc_arr(a);
            deltaT = ( t1 - t0 ); // / 1000;
            /*
            if( Ord_2024.distintos(a) < n/2 )
                System.out.println("Menos de la mitad de números distintos");
            if( !Ord_2024.verificaOrdenAscendente(a))
                System.out.println("Error al ordenar");
            else
            */
                System.out.println( strMetodo + "," + vez + "," + n + "," + deltaT + ", nanoSeg," + SEMILLA );
            
        }   
        
    }

/** Get CPU time in nanoseconds. */
  public static long getCpuTime( ) 
  {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
    
    return bean.isCurrentThreadCpuTimeSupported( ) ?
        bean.getCurrentThreadCpuTime( ) : 0L; 
  }
 
/** Get user time in nanoseconds. */
  public static long getUserTime( ) 
  {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
  
    return bean.isCurrentThreadCpuTimeSupported( ) ?
        bean.  getCurrentThreadUserTime( ) : 0L;
  }
  
}
