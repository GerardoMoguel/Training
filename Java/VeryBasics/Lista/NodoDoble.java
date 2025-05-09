/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lista;

/**
 *
 * @author estephaniamartinez
 */
public class NodoDoble <T> {
    private T dato;
    private NodoDoble <T> ant;
    private NodoDoble <T> sig;
    
    public NodoDoble(T dato){
       this.dato= dato;
       this.ant = null;
       this.sig=null;
    }

    public NodoDoble(T dato,NodoDoble <T> ant, NodoDoble<T> sig) {
        this.dato = dato;
        this.ant = ant;
        this.sig = sig;
    }

    public T getDato() {
        return dato;
    }

    public NodoDoble<T> getAnt() {
        return ant;
    }

    public NodoDoble<T> getSig() {
        return sig;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setAnt(NodoDoble<T> ant) {
        this.ant = ant;
    }

    public void setSig(NodoDoble<T> sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return dato + "\n";
    }
    
    
    
    
}
