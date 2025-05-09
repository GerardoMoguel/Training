package Lista;
import java.util.Iterator;

public interface ListaADT <T> extends Iterable<T>{
    public Iterator<T> iterator();
    public boolean estaVacia();
    public T quitaPrimero();
    public T quitaUltimo();
    public T quitaDato(T dato);
    public boolean contiene(T dato);
    public int calculaTamanio();
    public T consultaPrimero();
    public T consultaUltimo();  
}
