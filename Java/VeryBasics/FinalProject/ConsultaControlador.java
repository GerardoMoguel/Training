
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//clase verifica si el imput del usuario es ajo, si no es ajo se lo hace saber.
//si es ajo, le da una breve biografia
public class ConsultaControlador extends ConsultaVista{
	private static final long serialVersionUID = 1L;
	
	public ConsultaControlador(String titulo) {
		super(titulo);
		btnSubmit.addActionListener(new ConsultaIng());
		
	}
	
	private class ConsultaIng implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			String ing;
			ing = txtIngrediente.getText();	
			String res=encuentraIng(ing);
				lRespuesta.setText(res);
				//llamamos funcion adentro de la clase calcular costo 
			}
			
		}
		
		public String encuentraIng(String ing) {
			StringBuilder datos = new StringBuilder();
			if(ing.equals("ajo")) {
				datos.append("Ingrediente: ajo  \n");
				datos.append("\nOrigen: Mexico  ");
				datos.append("\nPrecio: $10");
			}
			else
				datos.append("El ingrediente no es ajo");
			
			return datos.toString();
		}

	public static void main(String[] args) {
		new ConsultaControlador ("ej");
	}
}
