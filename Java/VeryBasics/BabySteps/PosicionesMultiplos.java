import java.util.ArrayList;

public class PosicionesMultiplos {
	public static ArrayList<Double> buscaPosic(double[]arr, int x){
		ArrayList<Double> res=new ArrayList();
		if(x>0)
		for(int i=x;i<arr.length;i=i+x) {
			res.add(arr[i]);
		}
		return res;
	}
	public static void main(String[] args) {
		double[]a1= {1,2,3,4,5,6,6,7,8,4,2,4,6,3,6,7,4,2,1,4,6,8};
		ArrayList<Double> resultado;
		resultado=buscaPosic(a1,5);
		System.out.println(resultado);
		System.out.println("\nCon x=3 "+ buscaPosic(a1,3));
		System.out.println("\nCon x=0 "+ buscaPosic(a1,0));

	}
}
