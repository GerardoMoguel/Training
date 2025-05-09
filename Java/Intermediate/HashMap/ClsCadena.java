package hash_02;

import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rggh
 */
public class ClsCadena
{
      String strCadena;
      String strAdicional;
      int    frecuencia;
      double prop_acum;

    public String getStrCadena() {
        return strCadena;
    }

    public void setStrCadena(String strCadena) {
        this.strCadena = strCadena;
    }

    public String getStrAdicional() {
        return strAdicional;
    }

    public void setStrAdicional(String strAdicional) {
        this.strAdicional = strAdicional;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getProp_acum() {
        return prop_acum;
    }

    public void setProp_acum(double prop_acum) {
        this.prop_acum = prop_acum;
    }
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName())
          .append(" ... ")
          .append(this.strCadena) 
          .append(" => ")
          .append(this.strAdicional)
          .append("::")
          .append(this.frecuencia)
          .append("::")
          .append(this.prop_acum)
          .append('\n');
        return sb.toString();
    }
      
}
