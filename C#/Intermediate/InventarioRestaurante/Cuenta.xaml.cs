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
    /// Lógica de interacción para Cuenta.xaml
    /// </summary>
    public partial class Cuenta : Window
    {
        private List<PlatilloCuenta> ListPlatilloCuentaAux { get; set; }//Lista auxiliar para agregar los ingredientes.
        private List<String> ListNomPlatillos { get; set; }//Lista auxiliar solo para mostrar nombre de platillos agregados
        private decimal total=0;

        public Cuenta()
        {
            InitializeComponent();
            ListPlatilloCuentaAux = new List<PlatilloCuenta>();
            ListNomPlatillos = new List<String>();
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        private void DataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {

        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {

        }

        private void btn_atras_Click(object sender, RoutedEventArgs e)
        {
            //nos redirecciona a la página "SeleccionarOpciones"
            SeleccionarOpciones w = new SeleccionarOpciones();
            w.Show();
            this.Hide();
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            int resp=0;
            for(int i=0;i< ListPlatilloCuentaAux.Count; i++)
            {
                resp+=Platillo.Venta(ListPlatilloCuentaAux.ElementAt(i).id_Platillo); 

            }
            if (resp > 0)
            {
                MessageBox.Show("Cantidades actualizadas con exito");
            }
            else
            {
                MessageBox.Show("No se pudieron actualizar las cantidades");
            }
            //reestablecemos todos los valores de la ventana a null junto con los arrayList auxiliares
            ListPlatilloCuentaAux.Clear();
            ListNomPlatillos.Clear();
            dgCuentaRT.ItemsSource = null;
            total = 0;
            cbPlatillos.SelectedIndex = 0;
            txtBTotal.Text = null;
        }

        private void DataGrid_SelectionChanged_1(object sender, SelectionChangedEventArgs e)
        {
            //llenar combo box, la posicion es el mismo q el ID, al agregar platillo agarro el id seleccionado, q no sea 0 y meto el id al arreglo de platillos mi clase 
        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            if (cbPlatillos.SelectedIndex > 0)
            {
                int index = cbPlatillos.SelectedIndex;
                PlatilloCuenta a = new PlatilloCuenta(1, index);
                string textoPlatillo = $"{Platillo.nomPlatillo(index)} - Precio: {Platillo.precioPlatillo(index)}";
                ListPlatilloCuentaAux.Add(a);//anadimos el objeto a la lista
                ListNomPlatillos.Add(textoPlatillo); // anadimos el nombre del platillo con su precio

                //ponemos el DG en null para que no se acumulen los datos
                dgCuentaRT.ItemsSource = null;
                //despues ya anadimos los nombres
                dgCuentaRT.ItemsSource = ListNomPlatillos;
                
                total += Platillo.precioPlatillo(index);
                txtBTotal.Text = null;
                txtBTotal.Text = total.ToString();
            }
        }

        private void DataGrid_SelectionChanged_2(object sender, SelectionChangedEventArgs e)
        {

        }

        private void Cuentas_Loaded(object sender, RoutedEventArgs e)
        {
            Conexion.llenarComboPlatillos(cbPlatillos);
        }
    }
}
