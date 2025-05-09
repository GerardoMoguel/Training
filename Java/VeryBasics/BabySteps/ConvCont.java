
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class ConvCont extends ConvVista {

	private static final long serialVersionUID = 1L;
	
	public ConvCont(String titulo) {
		super(titulo);
		buscarBtn.addActionListener(new buscaLetra());
	}
	
	private class buscaLetra implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			
			char l;
			String letra = letraTF.getText();
			l = letra.charAt(0);
			String nomArch = nomArchTF.getText();
			
			try {
				File arch = new File(nomArch);
				Scanner scan = new Scanner(arch);
				StringBuilder sB = new StringBuilder();
				
				String fruta;
				
				while(scan.hasNext()) {
					fruta = scan.next();
					if(fruta.charAt(0) == l || fruta.charAt(fruta.length() - 1) == l)
						sB.append(fruta + ", ");
				}
				
				scan.close();
				
				respLb.setText(sB.toString());
				
			} catch (Exception e) {
				System.out.println("\nError: " + e.toString());
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		new ConvCont("Procesa Archivo");
	}

}
