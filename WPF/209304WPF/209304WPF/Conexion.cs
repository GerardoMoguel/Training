using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace _209304WPF
{
    class Conexion
    {

        //función que crea una conexión SQL con nuestra base de datos
        public static SqlConnection conectarBD()
        {
            //usamos un try catch en caso de no podernos conectar.
            SqlConnection cnn;
            try
            {                    //Con el siguiente código hacemos la conexión
                cnn = new SqlConnection("Data Source=MSI;Initial Catalog=dbCursos;Integrated Security=True");
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
        public static void llenarComboNombrePersona(ComboBox cb)
        {
            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;

            try
            {
                con = Conexion.conectarBD(); //nos conectamos a la bd
                //queremos obtener toda la columna de nombre de la tabla categoría
                cmd = new SqlCommand("select nombre from persona", con);
                dr = cmd.ExecuteReader();
                while (dr.Read())
                {//con un while vamos a leer todos los elementos
                    cb.Items.Add(dr.GetString(0));//vamos a agregar todos los elementos a nuestro combo
                }
                cb.SelectedIndex = 0; //empezamos con segundo elemento primer que esta vacio, para que el usuario  escoga algo a fueza
                //no olvidemos cerrar nuestro dataReader y nuestra conexión
                dr.Close();
                con.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error combo" + ex);
            }
        }
        public static void llenarComboNombreCurso(ComboBox cb)
        {
            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;

            try
            {
                con = Conexion.conectarBD(); //nos conectamos a la bd
                //queremos obtener toda la columna de nombre de la tabla categoría
                cmd = new SqlCommand("select nombre from curso", con);
                dr = cmd.ExecuteReader();
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

        public static void llenarComboNombreEstado(ComboBox cb)
        {
            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;

            try
            {
                con = Conexion.conectarBD(); //nos conectamos a la bd
                //queremos obtener toda la columna de nombre de la tabla categoría
                cmd = new SqlCommand("select nombre from estado", con);
                dr = cmd.ExecuteReader();
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
