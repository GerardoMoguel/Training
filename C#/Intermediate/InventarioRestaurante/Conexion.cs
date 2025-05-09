using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace InventarioRestaurante
{
	class Conexion
	{

        //función que crea una conexión SQL con nuestra base de datos
        public static SqlConnection agregarConexion()
        {
            //usamos un try catch en caso de no podernos conectar.
            SqlConnection cnn;
            try
            {                    //Con el siguiente código hacemos la conexión
                cnn = new SqlConnection("Data Source=MSI;Initial Catalog=dbInventarioRestaurante;Integrated Security=True");
                // MessageBox.Show("Se conectó");
                cnn.Open();
            }
            catch (Exception ex)
            {
                cnn = null;
                MessageBox.Show("No se pudo contectar" + ex);
            }
            return cnn;
        }

        // función nos sirve para verificar la validez del usuario y contraseña
        public static int verificacionUsuario(String usuario, int contra)
        {
            int res = 0;

            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;
            //con un try catch
            try
            {
                con = Conexion.agregarConexion(); //nos conectamosa la base de datos
                cmd = new SqlCommand(String.Format("select Id_Restaurante from Restaurante where nombre = '{0}'", usuario), con);
                //vamos a sacar del SQL la contraseña del empleado que tenga la clave dada
                dr = cmd.ExecuteReader();
               
                if (dr.Read())
                {
                        //al ejecutar el query, compararemos la contraseña obtenida contra la dada
                        if (dr.GetInt32(0)==contra)
                    {
                        res = 1;// si la contraseña es correcta regresamos un 1
                    }
                    else
                        res = 2; //si es incorrecta regresamos un 2
                }
                else
                    res = 3; //si no pudo leer información según el query, regresamos un 3
                con.Close();
                dr.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error " + ex);
            }

            return res;
        }

        public static void llenarComboIngredientes(ComboBox cb)
        {
            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener toda la columna de nombre de la tabla categoría
                cmd = new SqlCommand("select nombre from Ingrediente", con);
                dr = cmd.ExecuteReader();
                cb.Items.Add(" ");
                while (dr.Read())
                {//con un while vamos a leer todos los elementos
                    cb.Items.Add(dr.GetString(0));//vamos a agregar todos los elementos a nuestro combo
                }
                cb.SelectedIndex = 0; //empezamos con el primer elemento    
                //no olvidemos cerrar nuestro dataReader y nuestra conexión
                dr.Close();
                con.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error combo" + ex);
            }
        }

        public static void llenarComboPlatillos(ComboBox cb)
        {
            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener toda la columna de nombre de la tabla categoría
                cmd = new SqlCommand("select nombre from Platillo", con);
                dr = cmd.ExecuteReader();
                cb.Items.Add(" ");
                while (dr.Read())
                {//con un while vamos a leer todos los elementos
                    cb.Items.Add(dr.GetString(0));//vamos a agregar todos los elementos a nuestro combo
                }
                cb.SelectedIndex = 0; //empezamos con el primer elemento    
                //no olvidemos cerrar nuestro dataReader y nuestra conexión
                dr.Close();
                con.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error combo" + ex);
            }
        }


    }
}
