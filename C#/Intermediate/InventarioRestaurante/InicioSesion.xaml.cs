using System;
using System.Collections.Generic;
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

namespace InventarioRestaurante
{
    /// <summary>
    /// Interaction logic for InicioSesion.xaml
    /// </summary>
    public partial class InicioSesion : Window
    {
        public InicioSesion()
        {
            InitializeComponent();
        }

        private void btConfirmar_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                int res;
                res = Conexion.verificacionUsuario(txtUsuario.Text, Int32.Parse(txtContraseña.Text));

                if (res == 1)
                {
                    SeleccionarOpciones v = new SeleccionarOpciones();
                    v.Show();
                    this.Hide();
                }
                else
                {
                    if (res == 2)
                    {
                        MessageBox.Show("Contraseña equivicada");
                    }
                    else
                    {
                        MessageBox.Show("Usuario equivicado");
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error" + ex);
            }
      }

        private void txtUsuario_TextChanged(object sender, TextChangedEventArgs e)
        {

        }

        private void txtContraseña_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}
