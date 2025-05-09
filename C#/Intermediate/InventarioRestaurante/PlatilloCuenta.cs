using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InventarioRestaurante
{
    class PlatilloCuenta
    {
        public int cantidad { get; set; }
        public int id_Venta { get; set; }
        public int id_Platillo { get; set; }

        public PlatilloCuenta()
        {

        }

        public PlatilloCuenta(int cantidad, int id_Venta, int id_Platillo)
        {
            this.cantidad = cantidad;
            this.id_Venta = id_Venta;
            this.id_Platillo = id_Platillo;
        }

        public PlatilloCuenta(int cantidad, int id_Platillo)
        {
            this.cantidad = cantidad;
            this.id_Platillo = id_Platillo;
        }

    }
}
