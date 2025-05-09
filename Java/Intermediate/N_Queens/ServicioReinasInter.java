/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioreinasinter;

import interfazreinas.InterfazReinas;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rgamboah
 */
public class ServicioReinasInter implements InterfazReinas
{
    static boolean IMPRIME = true;
    
    public ServicioReinasInter()
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
       double deltaT_inter_mSeg;
       int c_inter[] = new int[1]; 
       int x[] = new int[n+1];
       for(int i = 1; i <= n; i++) x[i] = i;      
       c_inter[0]    = 0;
       t0 = System.nanoTime();
       per_inter(x,1,n,c_inter);
       t1 = System.nanoTime();
       deltaT_inter_mSeg = 1E-6 * ( t1 - t0 );
       
       return deltaT_inter_mSeg;

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
    
    static void inter( int x[], int i, int j)
    {
        int t;
        t    = x[i];
        x[i] = x[j];
        x[j] = t;
    }        
            
    static void per_inter(int x[], int k, int n, int c[])
    {
        if( k > n)
        {
            if(valida(x,n))
            {
              c[0]++;
              if( IMPRIME)
              {
                System.out.print(c[0] + "..... ");
                for(int i = 1; i <= n; i++) System.out.print(x[i] + " ");
                System.out.println();
              }
            }
            return;
        }
        for(int j = k; j <= n; j++)
        {
            inter(x,k,j);
            per_inter(x,k+1,n,c);
            inter(x,k,j);
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
            objServ.setIMPRIME(false);
             deltaT_perm_mSeg = objServ.ejecuta(N);
            System.out.println(N + "," + strClaseDeServicio + "," + deltaT_perm_mSeg); 
        } catch (Exception ex) {
            Logger.getLogger(ServicioReinasInter.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
}
