package Conjuntos;

import java.util.Iterator;

/**
 *
 * @author User
 */
public interface ConjuntosADT <T> extends Iterable <T>{
    public boolean agrega(T nuevo);
    public T quita (T dato);
    public int getCardinalidad();
    public boolean pertenece(T dato);
    public ConjuntosADT<T> union(ConjuntosADT<T> otro);
    public boolean estaVacio();
    public Iterator <T> iterator();
}
