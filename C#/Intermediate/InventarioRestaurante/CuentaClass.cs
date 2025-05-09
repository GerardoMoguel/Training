using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InventarioRestaurante
{
    class CuentaClass
    {
        public int fecha { get; set; }
        public decimal total { get; set; }
        public int id_Venta { get; set; }
        public int id_Restaurante { get; set; }

        public CuentaClass() // constructor vacio
        {

        }

        public CuentaClass(int fecha, decimal total,int id_Venta, int id_Restaurante)
        {
            this.fecha = fecha;
            this.total = total;
            this.id_Venta = id_Venta;
            this.id_Restaurante = id_Restaurante;
        }
    }
}
