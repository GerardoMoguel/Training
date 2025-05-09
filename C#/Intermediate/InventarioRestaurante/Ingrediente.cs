using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Data;
using System.Windows;

namespace InventarioRestaurante
{
    class Ingrediente
    {
        public String nombre { get; set; }
        public String descripcion { get; set; }
        public Decimal precio { get; set; }
        public Decimal cantidad { get; set; }
        public Int32 Id_Restaurante { get; set; }




        public Ingrediente()
        {//constructor vacío
        }


        //constructor completo
        public Ingrediente( String nombre, String descripcion, Decimal precio, Decimal cantidad, Int32 Id_Restaurante)
        {
            
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.descripcion = descripcion;
            
            this.precio = precio;
            this.Id_Restaurante = Id_Restaurante;
        }


        //función para agregar un ingrediente, recibe un ingrediente, regresa un valor int.
        public String agregarIngrediente(Ingrediente a)
        {
            String respuesta="";
            
            int idIng;
            SqlCommand cmd;
            SqlConnection con = Conexion.agregarConexion(); //creamos una conexión
            SqlDataReader rd;
            try
            {
                
                cmd = new SqlCommand(String.Format("Select top(1) Id_ingrediente from Ingrediente order by Id_ingrediente desc"),con);
                rd = cmd.ExecuteReader();
                if (rd.HasRows)
                {
                    rd.Read();
                    idIng = rd.GetInt32(0) + 1;
                }
                else
                    idIng = 1;
                rd.Close();
                //usaremos un comando SQL con el siguiente query para intertar la información del ingrediente.
                //nos apoyamos con un String.Format
                cmd = new SqlCommand(String.Format("insert into Ingrediente values ({0},'{1}','{2}',{3},{4},{5})", idIng, a.nombre, a.descripcion, a.precio, a.cantidad, a.Id_Restaurante), con);
                int res = cmd.ExecuteNonQuery(); //ejecutamos el query y guardamos la variable en res (int)
                if (res > 0)
                {
                    respuesta = "Alta exitosa";
                }
                else
                    respuesta = "Alta NO exitosa";
                
            } catch (Exception ex)
            {
                MessageBox.Show("No se pudo dar de alta" + ex);
 
            }
            return respuesta; 
        }


        //función para eliminar un ingrediente de la bd
        public static int eliminarIngrediente(int clave)
        {
            int res = 0;
            try
            {
                SqlCommand cmd;
                SqlConnection con;
                con = Conexion.agregarConexion();//creamos una conexión
                // Eliminar las filas relacionadas en la tabla PlatilloIngrediente
                SqlCommand cmdEliminarRelaciones = new SqlCommand("DELETE FROM PlatilloIngrediente WHERE id_ingrediente = @clave", con);
                cmdEliminarRelaciones.Parameters.AddWithValue("@clave", clave);
                res += cmdEliminarRelaciones.ExecuteNonQuery();
                con.Close();

                con = Conexion.agregarConexion();//creamos una conexión
                cmd = new SqlCommand(String.Format("delete from Ingrediente where id_ingrediente = {0}", clave), con);
                res = cmd.ExecuteNonQuery(); //ejecutamos el query
                con.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Se esta intentado eliminar un ingrediente que es utilizado"+ex);
            }
            return res; //regresamos un int según nuestro resultado
        }


        /*
        ================================================================================
        Utileria de metodos para la clase Ingrediente.
        ================================================================================
        */

        //Nos regresa el nombre del ingrediente, proporcionando su iD
        public static String nomIngre(int clave)
        {
            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;
            String resp = null;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener unicamente el nombre del ingrediente por medio de su ID
                cmd = new SqlCommand("select nombre from Ingrediente where Id_ingrediente = @clave", con); //forma distinta de poner variable en el query
                cmd.Parameters.AddWithValue("@clave", clave);
                dr = cmd.ExecuteReader();
                dr.Read();
                resp = dr.GetString(0);
                dr.Close();
                con.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error al buscar nombre del ingrediente" + e);
            }
            return resp;
        }

        //Nos regresa toda la informacion del ingrediente en un DataTable, proporcionando su iD, esto es para el metodo de busquedaIngrediente
        public static DataTable infoIngre(int clave)
        {
            SqlConnection con;
            SqlCommand cmd;
            DataTable dataTable = new DataTable();

            try
            {
                con = Conexion.agregarConexion();
                cmd = new SqlCommand("SELECT nombre, descripcion, precio, cantidad FROM Ingrediente WHERE Id_ingrediente = @clave", con);
                cmd.Parameters.AddWithValue("@clave", clave);

                SqlDataAdapter adapter = new SqlDataAdapter(cmd);
                adapter.Fill(dataTable);

                con.Close(); // Cerrar la conexión después de llenar la tabla
            }
            catch (Exception e)
            {
                MessageBox.Show("Error al buscar los datos del ingrediente" + e);
            }

            return dataTable;
        }

        /*
       ================================================================================
       Metodos de modificacion de cada atributo en la clase Ingrediente.
       ================================================================================
       */

        //Se hacen metodos individuales de cambio para cada atributo ya que se considera que al momento de realizar el cambio
        //el cliente no necesariamente debe de volver a escribir todos los atributos, si no cambia los que les conviene.


        //Cambia el nombre del ingrediente, proporcionando su iD y el nuevo nombre
        public static int modifNomIngre(int clave, String nom)
        {
            SqlConnection con;
            SqlCommand cmd;
            int resp=0;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener unicamente el nombre del ingrediente por medio de su ID
                cmd = new SqlCommand("UPDATE Ingrediente SET nombre = @nuevoNom WHERE Id_ingrediente = @clave", con); //forma distinta de poner variable en el query
                cmd.Parameters.AddWithValue("@clave", clave);
                cmd.Parameters.AddWithValue("@nuevoNom", nom);
                resp = cmd.ExecuteNonQuery(); //ejecutamos el query y guardamos la variable en res (int)
                con.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error con los datos proporcionados" + e);
            }
            return resp;
        }

        //Cambia la cantidad del ingrediente, proporcionando su iD y la nueva cantidad
        public static int modifCantIngre(int clave, double cant)
        {
            SqlConnection con;
            SqlCommand cmd;
            int resp = 0;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener unicamente el nombre del ingrediente por medio de su ID
                cmd = new SqlCommand("UPDATE Ingrediente SET cantidad = @nuevaCant WHERE Id_ingrediente = @clave", con); //forma distinta de poner variable en el query
                cmd.Parameters.AddWithValue("@clave", clave);
                cmd.Parameters.AddWithValue("@nuevaCant", cant);
                resp = cmd.ExecuteNonQuery(); //ejecutamos el query y guardamos la variable en res (int)
                con.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error con los datos proporcionados" + e);
            }
            return resp;
        }

        //Cambia la descipcion del ingrediente, proporcionando su iD y la nueva descripcion
        public static int modifDescripIngre(int clave, String desc)
        {
            SqlConnection con;
            SqlCommand cmd;
            int resp = 0;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener unicamente el nombre del ingrediente por medio de su ID
                cmd = new SqlCommand("UPDATE Ingrediente SET descripcion = @nuevaDesc WHERE Id_ingrediente = @clave", con); //forma distinta de poner variable en el query
                cmd.Parameters.AddWithValue("@clave", clave);
                cmd.Parameters.AddWithValue("@nuevaDesc", desc);
                resp = cmd.ExecuteNonQuery(); //ejecutamos el query y guardamos la variable en res (int)
                con.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error con los datos proporcionados" + e);
            }
            return resp;
        }

        //Cambia el precio del ingrediente, proporcionando su iD y el nuevo precio
        public static int modifPrecioIngre(int clave, decimal precio)
        {
            SqlConnection con;
            SqlCommand cmd;
            int resp = 0;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener unicamente el nombre del ingrediente por medio de su ID
                cmd = new SqlCommand("UPDATE Ingrediente SET precio = @nuevoPrecio WHERE Id_ingrediente = @clave", con); //forma distinta de poner variable en el query
                cmd.Parameters.AddWithValue("@clave", clave);
                cmd.Parameters.AddWithValue("@nuevoPrecio", precio);
                resp = cmd.ExecuteNonQuery(); //ejecutamos el query y guardamos la variable en res (int)
                con.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error con los datos proporcionados" + e);
            }
            return resp;
        }
    }
}
