package coefbin;
import interfazcall.InterfazCall;

/**
 * @author Gerardo Andres Moguel y Diego Federico Romero
 */
public class CoefBin implements InterfazCall{
    boolean IMPRIME;
    
    private static long [] obtenCoefBin(int n){
        long coefBin[]=new long[n+1];
        int pot=n;
        coefBin[0]=1;
        for(int i=1;i<=n;i++){
            coefBin[i]=coefBin[i-1]*pot/i;
            pot--;
        }
        return coefBin;
    }
    
   // private static double createR(double r, int n){
      //  return 1/Math.pow((1+r),(double) (1/((double)(12/n))))-1;
     //   
    //}
    
    public static double call(double s0, double sK, double p, double up, double dn, double r, int n){
        double resp;
        double suma=0;
        long coefBin[]=new long[n+1];
        coefBin=obtenCoefBin(n);
        //obtenemos s(k)
        double aumenDismin=Math.pow(up,n);
        double probK=Math.pow(up,n);
        double aux;
        
        for(int i=n;i>=0;i--){
            aumenDismin=s0*(Math.pow((up*p), n)*(Math.pow(dn,i))); 
            aux=(aumenDismin)-sK;
            System.out.println(aux);
            //Si la suma de S(k) con sK es positiva se multiplica por el coefBin y se agrega a la suma
                if(aux>0){
                    aux=aux*coefBin[i]*Math.pow(p,n-i)*Math.pow((1-p),i);
                    suma+=aux;
                }
        }
        //Ya que tenemos la suma calculamos el valor de re, y lo multiplicamos con la suma
        //para obtener la respuesta
        resp=(double)((1/(1+r))*suma);
        return resp;
        
    }
    
    @Override
    public void setIMPRIME(boolean imp) {
        IMPRIME = imp;
    }

    @Override
    public boolean getIMPRIME() {
        return IMPRIME;
    }

    @Override
    public double ejecuta(double s0, double sK, double p, double up, double dn, double r, int n) {
        long t0,t1;
       double deltaT_inter_mSeg;
       t0 = System.nanoTime();
       System.out.println("Resp = "+call(s0,sK,p,up,dn,r,n));
       t1 = System.nanoTime();
       deltaT_inter_mSeg = 1E-6 * ( t1 - t0 );
       
       return deltaT_inter_mSeg;
    }
    
    
    
    public static void main(String[] args) {
        //int n=4;
        //long c[];
        //c = obtenCoefBin(n);
        //System.out.println("N:"+n);
        //for(int i=0;i<=n;i++){
//            System.out.print(c[i]+" ");
//        }
        double s0=30000;
        double sk=35000;
        double p=0.4;
        double up=0.25; 
        double dn=0.15;
        double r=0.05;
        int n=12;
        System.out.println(call(s0,sk,p,up,dn,r,n));
        
        String a = "Hola ITAM";
        String b = a;
        a = "Hola DAI";
        System.out.println(a + b);
        
    }

    
}
