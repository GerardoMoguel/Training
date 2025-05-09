/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioreinasperm;

import interfazreinas.InterfazReinas;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rgamboah
 */
public class ServicioReinasPerm implements InterfazReinas
{
    static boolean IMPRIME = true;
    
    public ServicioReinasPerm()
    {
        
    }
    
    @Override
    public void setIMPRIME(boolean imprime)
    {
        IMPRIME = imprime;
    }
    
    @Override
    public boolean getIMPRIME()
    {
        return IMPRIME;
    }
    
    @Override
    public double ejecuta(int n)
    {
       long t0,t1;
       double deltaT_perm_mSeg;
       int c_perm[] = new int[1]; 
       int x[] = new int[n+1];
       for(int i = 1; i <= n; i++) x[i] = i;
       int d[] = new int[n+1];
       java.util.Arrays.fill(d,1);
       
       c_perm[0]    = 0;
       t0 = System.nanoTime();
       permut(x, d, 1, n, c_perm);
       t1 = System.nanoTime();
       deltaT_perm_mSeg = 1E-6 * ( t1 - t0 );
       
       return deltaT_perm_mSeg;

    }
          
    static boolean valida( int x[], int n)
    {
        int i,j;
        boolean ban = true;
        i = 1;
        while( ban & i <= n - 1 )
        {
          j = i + 1;  
          while(ban & j <= n)
          {    
            ban = j - i != Math.abs(x[i] - x[j]);
            j++;
          }
          i++;
        }
        return ban;
    }
  
    static void permut( int x[], int d[], int k, int n, int c[])
    {
        if( k > n )
        {
            if(valida(x,n))
            {
              c[0]++;
              if(IMPRIME)
              {
                System.out.print(c[0] + "..... ");
                for( int i = 1; i <= n; i++)
                  System.out.print(x[i] + " ");
                System.out.println();
              }
            }
            return;
        }
        
        for( int j = 1; j <= n; j++)
            if( d[j] == 1)
            {
                d[j] = 0;
                x[k] = j;
                permut(x,d,k+1,n,c);
                d[j] = 1;
            }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       IMPRIME = true;
       
       double deltaT_perm_mSeg;
       int N   = 8;
       
       if( args.length > 0)
       {
          N = Integer.parseInt(args[0]);
          IMPRIME = false;
       }  
       
       String strClaseDeServicio = MethodHandles.lookup().lookupClass().getName();
       if( args.length > 1 )
           strClaseDeServicio = args[1];
       
       InterfazReinas  objServ = null;
        try {
            objServ = (InterfazReinas) Class.forName(strClaseDeServicio).newInstance();
            objServ.setIMPRIME(IMPRIME);
            deltaT_perm_mSeg = objServ.ejecuta(N);
            System.out.println(N + "," + strClaseDeServicio + "," + deltaT_perm_mSeg); 
        } catch (Exception ex) {
            Logger.getLogger(ServicioReinasPerm.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
}
