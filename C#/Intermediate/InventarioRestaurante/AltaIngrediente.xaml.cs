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
    /// Lógica de interacción para AltaIngrediente.xaml
    /// </summary>
    public partial class AltaIngrediente : Window
    {
        public AltaIngrediente()
        {
            InitializeComponent();
        }

        private void btn_atras_Click(object sender, RoutedEventArgs e)
        {
            //nos redirecciona a la página "SeleccionarOpciones"
            SeleccionarOpciones w = new SeleccionarOpciones();
            w.Show();
            this.Hide();
        }


        private void btDarDeAlta_Click(object sender, RoutedEventArgs e)
        {
            
            
            if (!txtNombre.Text.Equals("") && !txtCantidad.Text.Equals("") && !txtDescripcion.Text.Equals("")  && !txtPrecio.Text.Equals("") && !txtId_Restaurante.Text.Equals(""))
            {
                Ingrediente a = new Ingrediente(txtNombre.Text, txtDescripcion.Text, Decimal.Parse(txtPrecio.Text), Decimal.Parse(txtCantidad.Text), Int32.Parse(txtId_Restaurante.Text));
                MessageBox.Show(a.agregarIngrediente(a));
            }
            else
            {
                MessageBox.Show("Hay cuadros de texto vacíos");
            }
        }


    }
}
