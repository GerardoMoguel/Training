using System;
using System.Collections.Generic;
using System.Data;
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
    /// Lógica de interacción para BusquedaIngrediente.xaml
    /// </summary>
    public partial class BusquedaIngrediente : Window
    {
        public BusquedaIngrediente()
        {
            InitializeComponent();
        }

        private void cbBusquedaIngredientes_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        private void btn_atras_Click(object sender, RoutedEventArgs e)
        {
            //nos redirecciona a la página "SeleccionarOpciones"
            SeleccionarOpciones w = new SeleccionarOpciones();
            w.Show();
            this.Hide();
        }

        //metodo que busca los datos de un ingrediente dependiendo del ID proporcionado.
        private void btBuscarIngre_Click(object sender, RoutedEventArgs e)
        {
            //comenzamos verificando que no esta vacia la casilla para escribir el ID
            if (!txtId_Ingrediete.Equals(""))
            {
                int id=Int32.Parse(txtId_Ingrediete.Text);
                DataView dataView = new DataView(Ingrediente.infoIngre(id));// convertimos el dataTable en un dataView para que sea compatible con el dataGrid
                dgInfoIngr.ItemsSource = dataView;
            }
            else
            {
                MessageBox.Show("Ingrese el Id del ingrediente");
            }
        }
    }
}
