using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace _209304WPF
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            Conexion.llenarComboNombrePersona(cbPersona);
            Conexion.llenarComboNombreCurso(cbCurso);

        }

        private void btnRegistrar_Click(object sender, RoutedEventArgs e)
        {
                SqlConnection con;
                int idP = cbPersona.SelectedIndex;
                int idC = cbCurso.SelectedIndex;
                
                SqlDataReader dr;
                int idCT;
                int res;
                try
                {
                    
                    con = Conexion.conectarBD(); //nos conectamos a la bd
                    SqlCommand cmd = new SqlCommand("SELECT MAX(idCursoTomado) AS Maximo_idCursoTomado FROM cursoTomado", con);
                    dr = cmd.ExecuteReader();
                    dr.Read();
                    idCT = dr.GetInt32(0);
                    idCT = idCT+1; //agregamos 1 al mayor id de curso actual.
                    //creamos un objeto tipo "CursoTomado" con la información de los Text Boxes

                    CursoTomado a = new CursoTomado(idCT, idP, idC);
                    res = a.agregarCursoTomado(a);//igualemos a un int la función agregarLibro que creamos en la clase Libro
                    con.Close();
                    if (res > 0)
                    {//si res es positivo, nuestra alta fue exitosa
                        MessageBox.Show("Alta Existosa");
                    }
                    else
                    {//caso contrario, no se pudo hacer el alta
                        MessageBox.Show("No se pudo hacer el alta");
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Hay algún problema con los datos" + ex);
                }
        }

        private void btnVentana2_Click(object sender, RoutedEventArgs e)
        {
            Ventana2 w = new Ventana2();
            w.Show();
            this.Hide();
        }
    }
}
