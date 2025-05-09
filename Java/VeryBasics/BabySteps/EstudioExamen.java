import java.util.Scanner;

public class EstudioExamen {

	public void recorreDer(double[]arr,int total,int pos) {
		if (total<arr.length)
			for(int i=total;i>pos;i--) 
				arr[i]=arr[i-1];
	}
	
	public void recorreIzq(double[]arr,int total, int pos) {
		if(total<arr.length) 
			for(int i=pos;i<total;i++)
				arr[i]=arr[i+1];
	}
	
	public int busquedaSecuencial(double[]arr,int total, int dato){
		int pos=-1;
		for(int i=0;i<total;i++)
			if(arr[i]==dato)
				pos=i;
		return pos;
	}
	
	public int busquedaBinaria(double[]arr,int total,double dato) {
		int izq=0,der=total-1,mid=(izq+der)/2;
		while(izq<=der && dato!=arr[mid]) {
			if(dato>mid)
				izq=mid+1;
			else
				der=mid-1;
			mid=(izq+der)/2;
			if(izq>der)
				mid=-(izq+1);
		}
		return mid;
	}
	
	public int insertaOrdenado(double[]arr,int total,double dato) {
		int pos;
		pos=busquedaBinaria(arr,total,dato);
		if(pos<0) {
			pos=-(pos-1);
			recorreDer(arr,total,pos);
			arr[pos]=dato;
			total++;
		}
		return total;
	}
	
	public int insertaDesordenado(double[]arr,int total, double dato) {
		if(total<arr.length) {
			arr[total-1]=dato;
		total++;
		}
		return total;
	}
	
	public boolean altaDatosOrdenado(double[]arr,int total,double dato) {
		boolean resp=false;
		int pos;
		if(total<arr.length) {
			pos=busquedaBinaria(arr,total,dato);
			if(pos<0) {
				pos=-(pos-1);
				recorreDer(arr,total,pos);
				arr[pos]=dato;
				total++;
				resp=true;
			}
		}
		return resp;
	}
}
