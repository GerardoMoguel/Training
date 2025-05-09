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
using System.Windows.Shapes;

namespace _209304WPF
{
    /// <summary>
    /// Lógica de interacción para Ventana2.xaml
    /// </summary>
    public partial class Ventana2 : Window
    {
        public Ventana2()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            Conexion.llenarComboNombreEstado(cbEstado);
            Conexion.llenarComboNombreCurso(cbCurso);
        }

        private void btnAtras_Click(object sender, RoutedEventArgs e)
        {
            
            MainWindow w = new MainWindow();
            w.Show();
            this.Hide();
        }

        private void btnSalir_Click(object sender, RoutedEventArgs e)
        {
            System.Windows.Application.Current.Shutdown();
        }

        private void btnCambia_Click(object sender, RoutedEventArgs e)
        {
            
                int idC = cbCurso.SelectedIndex;
                int idE = cbEstado.SelectedIndex;
                SqlConnection con;
                
                con = Conexion.conectarBD(); //nos conectamos a la bd
                SqlCommand cmd = new SqlCommand("UPDATE curso SET idEdo = @idEstado WHERE idCurso = @idCurso;", con);
                cmd.Parameters.AddWithValue("@idCurso", idC);
                cmd.Parameters.AddWithValue("@idEstado", idE);

                int res = cmd.ExecuteNonQuery();
                con.Close();
                if (res > 0)
                {//si res es positivo, nuestra alta fue exitosa
                    MessageBox.Show("Cambio exitoso");
                }
                else
                {//caso contrario, no se pudo hacer el alta
                    MessageBox.Show("No se pudo hacer el cambio");
                }
        }
    }
}
