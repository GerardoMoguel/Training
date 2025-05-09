using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Windows;

namespace InventarioRestaurante
{
    class Platillo
    {
        public int Id_Platillo { get; set; }
        public String nombre { get; set; }
        public String descripcion { get; set; }
        public double precio { get; set; }
        public int Id_Restaurante { get; set; }

        //tenemos un ArrayList de IngredientesPlatillo ya que estos son los ingredientes y cantidades
        //que posee el platillo que vamos a crear.
        public List<IngredientePlatillo> ListaIngredientes { get; set; }

        //Constructor vacio
        public Platillo()
        {

        }

        //Constructor completo
        public Platillo(int Id_Platillo, string nombre, string descripcion, double precio, int Id_Restaurante, List<IngredientePlatillo> ingredientePlatillos)
        {
            this.Id_Platillo = Id_Platillo;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
            this.Id_Restaurante = Id_Restaurante;
            this.ListaIngredientes = ingredientePlatillos;
        }


        //función para agregar un platillo, recibe un platillo, regresa un valor int.
        public int agregarPlatillo(Platillo a)
        {
            int res;
            SqlCommand cmd;
            SqlConnection con;
            con = Conexion.agregarConexion(); //creamos una conexión
            //usaremos un comando SQL con el siguiente query para intertar la información del ingrediente.
            //nos apoyamos con un String.Format
            
            string query = "INSERT INTO Platillo (Id_platillo, nombre, precio, descripcion, Id_restaurante) " +
                      "VALUES (@IdPlatillo, @Nombre, @Precio, @Descripcion, @IdRestaurante)";
            cmd = new SqlCommand(query, con);
            cmd.Parameters.AddWithValue("@IdPlatillo", a.Id_Platillo);
            cmd.Parameters.AddWithValue("@Nombre", a.nombre);
            cmd.Parameters.AddWithValue("@Precio", a.precio);
            cmd.Parameters.AddWithValue("@Descripcion", a.descripcion);
            cmd.Parameters.AddWithValue("@IdRestaurante", a.Id_Restaurante);
            res = cmd.ExecuteNonQuery(); //ejecutamos el query y guardamos la variable en res (int)

            agregarIngredienteAPlatillo(a);//ejecutamos este metodo para meter a la bd los datos que tiene el ArrayList de platilloIngrediente
            con.Close();
            return res; //regresamos "res" (int) que será positivo si se logró la inserción, caso contrario, será negativo
        }

        //funcion para agregar los ingredientesPlatillos que se encuentran dentro del arrayList en la bd de IgredientePlatillo
        public int agregarIngredienteAPlatillo(Platillo a)
        {
            int res=-1;
            SqlCommand cmd;
            SqlConnection con;
            con = Conexion.agregarConexion(); //creamos una conexión
            //usaremos un comando SQL con el siguiente query para intertar la información del ingrediente.
            //nos apoyamos con un String.Format

            //tenemos un for, para que en el arraylist de PlatilloIngrediente pueda acceder a cada uno de los elementos del objeto y poder meterlos a la base de datos.
            for (int i=0;i< a.ListaIngredientes.Count;i++)
            {
                cmd = new SqlCommand(String.Format(
                "insert into PlatilloIngrediente (Id_platillo ,Id_ingrediente ,cantidad) " +
                "values ('{0}','{1}','{2}')",
                a.ListaIngredientes.ElementAt(i).Id_Platillo, a.ListaIngredientes.ElementAt(i).Id_Ingrediente, a.ListaIngredientes.ElementAt(i).cantidad), con);
                res = cmd.ExecuteNonQuery(); //ejecutamos el query y guardamos la variable en res (int)
            }
            con.Close();
            return res; //regresamos "res" (int) que será positivo si se logró la inserción, caso contrario, será negativo
        }

        //función para eliminar un platillo de la bd
        public static int eliminarPlatillo(int clave)
        {

            int res;
            SqlCommand cmd;
            SqlConnection con;
            con = Conexion.agregarConexion();//creamos una conexión
                                             // Eliminar las filas relacionadas en la tabla PlatilloIngrediente
            SqlCommand cmdEliminarRelaciones = new SqlCommand("DELETE FROM PlatilloIngrediente WHERE id_ingrediente = @clave", con);
            cmdEliminarRelaciones.Parameters.AddWithValue("@clave", clave);
            res = cmdEliminarRelaciones.ExecuteNonQuery();
            

            cmd = new SqlCommand(String.Format(//con el String.Format, el comamnd y el query:
                " delete from Platillo where Id_platillo  = {0}", clave), con);
            res = cmd.ExecuteNonQuery(); //ejecutamos el query
            con.Close();
            return res; //regresamos un int según nuestro resultado

        }
        public static String nomPlatillo(int clave)
        {
            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;
            String resp = null;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener unicamente el nombre del ingrediente por medio de su ID
                cmd = new SqlCommand("select nombre from Platillo where Id_platillo = @clave", con); //forma distinta de poner variable en el query
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

        public static decimal precioPlatillo(int clave)
        {
            SqlDataReader dr;
            SqlConnection con;
            SqlCommand cmd;
            decimal resp = 0;

            try
            {
                con = Conexion.agregarConexion(); //nos conectamos a la bd
                //queremos obtener unicamente el nombre del ingrediente por medio de su ID
                cmd = new SqlCommand("select precio from Platillo where Id_platillo = @clave", con); //forma distinta de poner variable en el query
                cmd.Parameters.AddWithValue("@clave", clave);
                dr = cmd.ExecuteReader();
                dr.Read();
                resp = dr.GetDecimal(0);
                dr.Close();
                con.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error al buscar nombre del ingrediente" + e);
            }
            return resp;
        }

        public static int Venta(int idPlatillo)
        {
            SqlConnection con;
            SqlCommand cmd;
            SqlDataReader dr;
            int resp;

            try
            {
                con = Conexion.agregarConexion(); // Conectar a la base de datos

                // Consultar la cantidad de ingredientes asociados al platillo dado
                cmd = new SqlCommand("SELECT Id_ingrediente, cantidad FROM PlatilloIngrediente WHERE Id_platillo = @idPlatillo", con);
                cmd.Parameters.AddWithValue("@idPlatillo", idPlatillo);
                dr = cmd.ExecuteReader();

                // Iterar sobre los resultados y restar la cantidad correspondiente a cada ingrediente
                while (dr.Read())
                {
                    int idIngrediente = Convert.ToInt32(dr["Id_ingrediente"]);
                    decimal cantidad = Convert.ToDecimal(dr["cantidad"]);

                    // Restar la cantidad del ingrediente correspondiente en la tabla Ingrediente
                    RestarCantidadIngrediente(idIngrediente, cantidad);
                }

                dr.Close();
                con.Close();
                resp = 1;
            }
            catch (Exception e)
            {
                resp = -1;
                MessageBox.Show("Error en la venta: " + e.Message);
            }
            return resp;
        }

        private static void RestarCantidadIngrediente(int idIngrediente, decimal cantidad)
        {
            try
            {
                SqlConnection con = Conexion.agregarConexion(); // Conectar a la base de datos

                // Actualizar la cantidad del ingrediente restando la cantidad vendida
                SqlCommand cmd = new SqlCommand("UPDATE Ingrediente SET cantidad = cantidad - @cantidad WHERE Id_ingrediente = @idIngrediente", con);
                cmd.Parameters.AddWithValue("@cantidad", cantidad);
                cmd.Parameters.AddWithValue("@idIngrediente", idIngrediente);

                cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error al restar cantidad del ingrediente: " + e.Message);
            }
        }
    }
}
