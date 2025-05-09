using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _209304WPF
{
    class CursoTomado
    {
        public int idCursoTomado { get; set; }
        public int idPersona { get; set; }
        public int idCurso { get; set; }

        public CursoTomado()
        {

        }

        public CursoTomado(int idCursoTomado, int idPersona, int idCurso)
        {
            this.idCursoTomado = idCursoTomado;
            this.idPersona = idPersona;
            this.idCurso = idCurso;
        }

        //función para agregar un curso tomado, recibe un CursoTomado, regresa un valor int.
        public int agregarCursoTomado(CursoTomado a)
        {
            int res;
            SqlCommand cmd;
            SqlConnection con;
            con = Conexion.conectarBD(); //creamos una conexión
            //usaremos un comando SQL con el siguiente query para intertar la información del curso tomado.
            //nos apoyamos con un String.Format
            cmd = new SqlCommand(String.Format(
                "insert into cursoTomado (idCursoTomado,idPersona,idCurso) " +
                "values ({0},'{1}','{2}')",
                a.idCursoTomado, a.idPersona, a.idCurso), con);
            res = cmd.ExecuteNonQuery(); //ejecutamos el query y guardamos la variable en res (int)
            con.Close();
            return res; //regresamos "res" (int) que será positivo si se logró la inserción, caso contrario, será negativo
        }
    }
}
