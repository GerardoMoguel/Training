package ProyectoCalculadora;
/*
 * Gerardo Moguel
 * Mi propio Exception, subclase RuntimeException
 */
public class ExceptionColeccionVacia extends RuntimeException{

	public ExceptionColeccionVacia() {
		super();
	}

	public ExceptionColeccionVacia(String message) { //es por si queremos que mande un mensaje "esta vacio" o algo asi 
		super(message);
	}

	
	
}
