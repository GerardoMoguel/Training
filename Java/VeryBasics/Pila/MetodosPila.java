package Pila;

public class MetodosPila{
	
	public static <T>int numElements(PilaADT<T> pila){
		int cont=0;
		PilaA<T> x=new PilaA(50);
		while(!pila.isEmpty()) {
			x.push(pila.pop());
			cont++;
		}
		while(!x.isEmpty()) {
			pila.push(x.pop());
		}
		return cont;
	}
	
	public static int intToBinar(int num){
		int resp=0,cont=0,x=num;
		PilaA <Integer>pila=new PilaA();
		while(x!=0) {	
			pila.push(x%2);
			x=x/2;
			cont++;
		}
		while(!pila.isEmpty()) {
			if(pila.pop()==1) {
				resp=resp+(int) Math.pow(10, cont-1);	
			}
			cont--;
		}
		return resp;
	}
	
	public static <T>boolean isPYinPX(PilaADT<T> pilaX, PilaADT<T> pilaY){
		boolean resp=false;
		PilaA<T> x=new PilaA(50);
		PilaA<T> y=new PilaA(50);
		while(!pilaX.isEmpty()) {
			if(!pilaY.isEmpty()&&pilaY.peek().equals(pilaX.peek())) {
				x.push(pilaX.pop());
				y.push(pilaY.pop());
			}
			else {
				x.push(pilaX.pop());
			}
		}
		if(pilaY.isEmpty()) {
			resp=true;
		}
		while(!x.isEmpty()) {
			pilaX.push(x.pop());
		}
		while(!y.isEmpty()) {
			pilaY.push(y.pop());
		}
		return resp;
	}
	
	
	public static void main (String []args) {
		System.out.println(intToBinar(25));
		
		PilaA uno=new PilaA();
		PilaA dos=new PilaA();
		uno.push(8);
		uno.push(2);
		uno.push(1);
		uno.push(1);
		uno.push(1);
		uno.push(1);
		uno.push(1); //5
		uno.push(13);

		
		dos.push(1); 
		dos.push(1);
		dos.push(1);
		dos.push(1);
		dos.push(1); //5

		System.out.println(uno.toString());
		System.out.println(isPYinPX(uno,dos));
		System.out.println(uno.toString());

	}
}
