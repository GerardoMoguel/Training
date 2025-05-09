using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InventarioRestaurante
{
    class IngredientePlatillo
    {
        public decimal cantidad { get; set; }
        public int Id_Ingrediente { get; set; }
        public int Id_Platillo { get; set; }

        public IngredientePlatillo(decimal cantidad, int Id_Ingrediente, int Id_Platillo)
        {
            this.cantidad = cantidad;
            this.Id_Ingrediente = Id_Ingrediente;
            this.Id_Platillo = Id_Platillo;
        }

        public IngredientePlatillo(decimal cantidad, int Id_Ingrediente) //constructor con solo cantidad e ingrediente para utilizar en la lista aux
        {
            this.cantidad = cantidad;
            this.Id_Ingrediente = Id_Ingrediente;
        }

    }
}
