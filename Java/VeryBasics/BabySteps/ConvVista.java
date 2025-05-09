
import javax.swing.*;

import javax.swing.border.Border;
import java.awt.GridLayout;

public class ConvVista extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	
	private JLabel nomArchLb, letraLb;
	protected JTextField nomArchTF, letraTF;
	protected JButton buscarBtn; 
	protected JLabel respLb;
	
	public ConvVista(String titulo) {
		super(titulo);
		
		//Atributos
		nomArchLb = new JLabel("Nombre del archivo: ");
		letraLb = new JLabel("Letra: ");
		
		nomArchTF = new JTextField(20);
		letraTF = new JTextField(20);
		
		buscarBtn = new JButton("Aceptar");
		
		respLb = new JLabel("");
		
		//Panel
		JPanel panel = new JPanel();
		
		//Grid (ren, col)
		panel.setLayout(new GridLayout(3, 2));
		
		//Border
		Border gap = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panel.setBorder(gap);
		
		panel.add(nomArchLb);
		panel.add(nomArchTF);
		panel.add(letraLb);
		panel.add(letraTF);
		panel.add(buscarBtn);
		panel.add(respLb);
		
		this.add(panel);
		this.setBounds(500, 200, 300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		this.setVisible(true);
	}
	

	public static void main(String[] args) {
		new ConvVista("Procesa Archivo");
	}

}
