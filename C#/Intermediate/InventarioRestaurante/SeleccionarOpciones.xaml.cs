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
    /// Interaction logic for SeleccionarOpciones.xaml
    /// </summary>
    public partial class SeleccionarOpciones : Window
    {
        public SeleccionarOpciones()
        {
            InitializeComponent();
        }

        //función que se activa al presionar el botón "Alta platillo"
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            AltaPlatillo ap = new AltaPlatillo();
            ap.Show();
            this.Hide();
        }

        //función que se activa al presionar el botón  "Baja platillo"
        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            BajaPlatillo bp = new BajaPlatillo();
            bp.Show();
            this.Hide();
        }

        //función que se activa al presionar el botón "Busqueda Ingrediente"
        private void btBusqueda_Click(object sender, RoutedEventArgs e)
        {
            BusquedaIngrediente bp = new BusquedaIngrediente();
            bp.Show();
            this.Hide();
        }

        //función que se activa al presionar el botón "Alta Ingrediente"
        private void btAltaIngre_Click(object sender, RoutedEventArgs e)
        {
            AltaIngrediente bp = new AltaIngrediente();
            bp.Show();
            this.Hide();
        }

        //función que se activa al presionar el botón "Cuenta"
        private void btCuenta_Click(object sender, RoutedEventArgs e)
        {
            Cuenta bp = new Cuenta();
            bp.Show();
            this.Hide();
        }

        //función que se activa al presionar el botón "BajaIngrediente"
        private void btBajaIngre_Click(object sender, RoutedEventArgs e)
        {
            BajaIngrediente bp = new BajaIngrediente();
            bp.Show();
            this.Hide();
        }


        //función que se activa al presionar el botón "Salir"
        private void btSalir_Click(object sender, RoutedEventArgs e)
        {
            //El siguiente comando dejará de correr la aplicación,
            //cerrando y saliendo del programa
            System.Windows.Application.Current.Shutdown();

        }

        //función que se activa al presionar el botón "Atras"
        private void btn_atras_Click(object sender, RoutedEventArgs e)
        {
            //nos redirecciona a la página "InicioSesion"
            InicioSesion w = new InicioSesion();
            w.Show();
            this.Hide();
        }

        //función que se activa al presionar el botón "Modificar Ingrediente"
        private void btnModificarIng_Click(object sender, RoutedEventArgs e)
        {
            ModificarIngrediente bp = new ModificarIngrediente();
            bp.Show();
            this.Hide();
        }
    }
}
