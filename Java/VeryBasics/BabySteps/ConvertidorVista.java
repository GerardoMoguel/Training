import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConvertidorVista extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel millasLb,kmLb;
	protected JTextField millasTF,kmTF;
	private JButton boton1;//no se necesita pero asi se escriben botones
	
	public ConvertidorVista(String mensaje) {
		super(mensaje); //llama al constructor de JFrame por medio de herencia, construye la ventana y le coloca el mensaje de titulo
		millasLb=new JLabel("Millas: ");
		kmLb= new JLabel("Km: ");
		millasTF=new JTextField(20);
		kmTF=new JTextField(20);
		JPanel panel= new JPanel();//crea el panel para colocar los elementos de la ventana, es un contenedor.
		panel.setLayout(new GridLayout(4,1));//genera 4 renglones por 1 columna
		panel.add(millasLb);
		panel.add(millasTF);
		panel.add(kmLb);
		panel.add(kmTF);
		this.add(panel); //se agrega el panel a la ventana
		this.setBounds(570, 250, 300, 300);//genera las dimensiones del panel
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//permite que el programa termine cuando se cierra la app
		this.setVisible(true);//permite que la ventana se muestre
	}
	
	
	
	
	public static void main(String[] args) {
new ConvertidorVista("Convierte: ");
	}

}
