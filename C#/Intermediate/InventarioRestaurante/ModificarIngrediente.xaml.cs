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
    /// Lógica de interacción para ModificarIngrediente.xaml
    /// </summary>
    public partial class ModificarIngrediente : Window
    {
        public ModificarIngrediente()
        {
            InitializeComponent();
        }

        //metodo que busca los datos de un ingrediente dependiendo del ID proporcionado.
        private void btDarDeAlta_Click(object sender, RoutedEventArgs e)
        {
            //comenzamos verificando que no esta vacia la casilla para escribir el ID
            if (!txtID.Equals(""))
            {
                int id = Int32.Parse(txtID.Text);
                Boolean flag = true; //tenemos esta bandera que se pone negativa si se tiene algun dato a cambiar
                //si no se coloca nada, le dice al usuario que no coloco ningun dato para modificar.

                // a continuacion tenemos varios if, ya que el cliente no esta forzado a cambiar todos los atributos 
                //del Ingrediente, por lo que se verifica casilla por casilla para cambiar el correspondiente.
                if (!string.IsNullOrEmpty(txtNombre.Text)) //Metodo que modifica el nombre
                {
                    int a = Ingrediente.modifNomIngre(id, txtNombre.Text);
                    flag = false;
                    if (a > 0)
                    {
                        MessageBox.Show("Nombre modificado con exito");
                    }
                    else
                    {
                        MessageBox.Show("No se pudo modificar nombre");
                    }
                }

                if (!string.IsNullOrEmpty(txtCantidad.Text)) //Metodo que modifica la cantidad
                {
                    int a = Ingrediente.modifCantIngre(id, double.Parse(txtCantidad.Text));
                    flag = false;
                    if (a > 0)
                    {
                        MessageBox.Show("Cantidad modificada con exito");
                    }
                    else
                    {
                        MessageBox.Show("No se pudo modificar la cantidad");
                    }
                }

                if (!string.IsNullOrEmpty(txtDescripcion.Text)) //Metodo que modifica la descripcion
                {
                    int a = Ingrediente.modifDescripIngre(id, txtDescripcion.Text);
                    flag = false;
                    if (a > 0)
                    {
                        MessageBox.Show("Descripcion modificada con exito");
                    }
                    else
                    {
                        MessageBox.Show("No se pudo modificar la descripcion");
                    }
                }
                if (!string.IsNullOrEmpty(txtPrecio.Text)) //Metodo que modifica el precio
                {
                    int a = Ingrediente.modifPrecioIngre(id, decimal.Parse(txtPrecio.Text));
                    flag = false;
                    if (a > 0)
                    {
                        MessageBox.Show("Precio modificado con exito");
                    }
                    else
                    {
                        MessageBox.Show("No se pudo modificar el precio");
                    }
                }
                if (flag) //por si no se ingresan datos para modificar, pero si un Id_Ingrediente
                {
                    MessageBox.Show("No escribio ningun dato para modificar");
                }
            }
            else
            {
                MessageBox.Show("Ingrese el Id del ingrediente"); //por si no se ingredo Id_Ingrediente
            }
        }

        private void btn_atras_Click(object sender, RoutedEventArgs e)
        {
            //nos redirecciona a la página "SeleccionarOpciones"
            SeleccionarOpciones w = new SeleccionarOpciones();
            w.Show();
            this.Hide();
        }

        private void txtID_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}
