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
    /// Lógica de interacción para AltaPlatillo.xaml
    /// </summary>
    public partial class AltaPlatillo : Window
    {

        private List<IngredientePlatillo> IngPlAux { get; set; }//Lista auxiliar para agregar los ingredientes.

        public AltaPlatillo()
        {
            InitializeComponent();
            IngPlAux = new List<IngredientePlatillo>();
        }

        private void btn_atras_Click(object sender, RoutedEventArgs e)
        {
            //nos redirecciona a la página "SeleccionarOpciones"
            SeleccionarOpciones w = new SeleccionarOpciones();
            w.Show();
            this.Hide();
        }

        //metodo que da de alta el platillo utilizando la lista auxiliar previamente generada
        private void btAltaPlatillo_Click(object sender, RoutedEventArgs e)
        {
            if (IngPlAux.Count>0 && !txtID_Platillo.Text.Equals("") && !txtNombrePlatillo.Text.Equals("") && !txtPrecioPlatillo.Text.Equals("") && !txtDescripcion.Text.Equals("") && !txtIdRest.Text.Equals(""))
            {
                for (int i = 0; i < IngPlAux.Count; i++)
                {
                    IngPlAux.ElementAt(i).Id_Platillo = Int32.Parse(txtID_Platillo.Text);
                }
                
                int res;
                // tenemos que verificar que la información de nuestros Text Box no sea vacía
                //también, tenemos que usar un try catch para "atrapar" algún problema en los datos.
                try
                {
                    //creamos un objeto tipo "Platillo" con la información de los Text Boxes
                    Platillo a = new Platillo(Int32.Parse(txtID_Platillo.Text), txtNombrePlatillo.Text, txtDescripcion.Text, double.Parse(txtPrecioPlatillo.Text), Int32.Parse(txtIdRest.Text), IngPlAux);
                    
                    res = a.agregarPlatillo(a);//igualemos a un int la función agregarPlatillo que creamos en la clase Platillo
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
            else
            {
                MessageBox.Show("Por favor complete los datos");
            }
        }

        //Metodo que agrega ingredientes al arreglo de ingredientePlatillo, y los muestra en el DataGrid
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if(!tbCantidad.Equals("") || cbListaIngredientes.SelectedIndex >0)
            {
                String nom;
                decimal cant;
                int index = cbListaIngredientes.SelectedIndex;
                try
                {
                    //Creamos un objeto tipo IngredientePlatillo con unicamente los dos datos que tenemos.
                    //como el ID de los ingredientes va en orden 1,2,3...n. Podemos suponer que el index elegido es 
                    //el Id_Indgrediente, ya que el indice 0 = "", asi que comienzan en orden, no hay necesidad de +1.
                    
                    //Obtenemos el nombre del Ingrediente, por medio del metodo nomIngre echo en conexion.
                    nom=Ingrediente.nomIngre(index);
                    cant= decimal.Parse(tbCantidad.Text);

                    //Creamos el objeto IngredientePlatillo
                    IngredientePlatillo ingPL = new IngredientePlatillo(cant,index);

                    //Lo ponemos dentro de la lista auxiliar.
                    IngPlAux.Add(ingPL);

                    //Ya que tenemos el objeto dentro del arrayList, procedemos a imprimir los datos en el DataGrid.
                    dgDatosIngr.ItemsSource = null;//primero lo hacemos null, por si es la segunda o mas vez que se ejecuta el boton
                    dgDatosIngr.ItemsSource = IngPlAux;//le pedimos al grind que muestre los datos.

                }
                catch (Exception excep)
                {
                    MessageBox.Show("Hay algún problema con los datos"+excep);
                }
            }
            else
            {
                MessageBox.Show("por favor seleccione ingrediente o anote cantidad");
            }
        }

        private void cbListaIngredientes_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        //función que llena nuestro Combo Box de categorías,
        //ya que está en el Windos Loaded, se ejecuta tan pronto de abre la ventana
        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            //utilizamos la función para llenar un combo de
            //categorías que declaremos en la clase Conexion
            // le damos como parámetro nuestro Combo Box a llenar.
            Conexion.llenarComboIngredientes(cbListaIngredientes);
        }

        private void dgDatosIngr_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }
    }
}
