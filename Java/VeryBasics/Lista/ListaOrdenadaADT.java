/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Lista;

/**
 *
 * @author estephaniamartinez
 */
public interface ListaOrdenadaADT<T> extends ListaADT<T>{
    public boolean agrega(T dato);
    public boolean quita(T dato);
    public boolean contiene(T dato);
    
}
