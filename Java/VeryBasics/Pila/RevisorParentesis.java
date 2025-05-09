package Pila;

public class RevisorParentesis {
private String cadena;

public RevisorParentesis(String x) {
	this.cadena=x;
}

public boolean isParentesisGood() {
	int i=0;
	PilaA <Character>pila=new PilaA(50);
	boolean resp=false;
	while(i<cadena.length()) {
		if(cadena.charAt(i)=='(') {
			pila.push('(');
		}
		else
			if(cadena.charAt(i)==')') {
				if(!pila.isEmpty()) {
				pila.pop();
				}
				else {
					i=cadena.length();
					resp=true;
				}
			}
		i++;
	}
	return !resp && pila.isEmpty();
}
public boolean isParentesisETCGood() {
	int i=0;
	PilaA <Character>abre=new PilaA(50);
	PilaA <Character>cierra=new PilaA(50);
	boolean resp=false;
	while(i<cadena.length()) {
		if(cadena.charAt(i)=='[') {
			abre.push('[');
		}
		if(cadena.charAt(i)=='(') {
			abre.push('(');
		}
		if(cadena.charAt(i)=='{') {
			abre.push('{');
		}
		
		if(cadena.charAt(i)==']') {
			cierra.push(']');
			if(abre.pop()=='[') {
				cierra.pop();
			}
			else {
				i=cadena.length();
				resp=true;
			}
		}
		else if(cadena.charAt(i)==')') {
			cierra.push(')');
			if(abre.pop()=='(') {
				cierra.pop();
			}
			else {
				i=cadena.length();
				resp=true;
			}
		}
		else if(cadena.charAt(i)=='}') {
			cierra.push('}');
			if(abre.pop()=='{') {
				cierra.pop();
			}
			else {
				i=cadena.length();
				resp=true;
			}
		}
		
		i++;
	}
	return !resp && abre.isEmpty();
}

	public static void main (String[]args) {
		RevisorParentesis a=new RevisorParentesis("([{}])");
		RevisorParentesis b=new RevisorParentesis("([{}");
		RevisorParentesis c=new RevisorParentesis("(x+4)*5[i]-{lol}");
		RevisorParentesis d=new RevisorParentesis("({[}])");
		RevisorParentesis e=new RevisorParentesis("");

		System.out.println("a: "+a.isParentesisETCGood());
		System.out.println("b: "+b.isParentesisETCGood());
		System.out.println("c: "+c.isParentesisETCGood());
		System.out.println("d: "+d.isParentesisETCGood());
		System.out.println("e: "+e.isParentesisETCGood());


	}
}
