
import java.awt.GridLayout;
/*
 * import 
 * 
 * 
 * 
 */
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ConsultaVista extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JTextField txtIngrediente, txtResp;
	private JLabel lIngrediente, lNada;
	protected  JLabel lRespuesta;
	protected JButton btnSubmit;
	
	public ConsultaVista(String titulo) {
		super(titulo);
		
		// Panel
		JPanel panel = new JPanel();
		
		// Grid(renglones y columnas)
		panel.setLayout(new GridLayout(3,2));
		
		//Border (Borde entre el panel y frame(arriba, izquierda, abajo, derecha) en pixeles).
		Border gap = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panel.setBorder(gap);
		
		
		//Atributos
		txtIngrediente = new JTextField(16);
		txtResp = new JTextField(30);

		lIngrediente = new JLabel("Ingrediente a consultar:");
		lNada = new JLabel("");

		lRespuesta = new JLabel("Respuesta: ");
		
		btnSubmit = new JButton("Enviar");
		
		panel.add(lIngrediente);
		panel.add(txtIngrediente);
		panel.add(lNada);

		panel.add(btnSubmit);
		panel.add(lRespuesta);
		panel.add(lNada);
		add(panel);
		
		
		setBounds(600, 250, 400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}

	
	public static void main(String[] args) {
		ConsultaVista ej = new ConsultaVista("ej");
		

	}

}