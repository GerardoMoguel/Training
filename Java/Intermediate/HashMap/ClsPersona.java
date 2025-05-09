/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_02;

/**
 *
 * @author RGAMBOAH
 */
public class ClsPersona implements IObjetoPortado 
{
    String nombre;
    String primAp;
    String segAp;
    String strGen;
    
    public ClsPersona()
    {
    }
    
    public ClsPersona( String nombre, String primApellido, String segApellido, String strGenero)
    {
        this.nombre = nombre;
        this.primAp = primApellido;
        this.segAp  = segApellido;
        this.strGen = strGenero;
    }
    
    public String strPersona()
    {
        return this.nombre + " " + this.primAp + " " + this.segAp + " " + this.strGen;
    }  
          
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimAp() {
        return primAp;
    }

    public void setPrimAp(String primAp) {
        this.primAp = primAp;
    }

    public String getSegAp() {
        return segAp;
    }

    public void setSegAp(String segAp) {
        this.segAp = segAp;
    }

    public String getStrGen() {
        return strGen;
    }

    public void setStrGen(String strGen) {
        this.strGen = strGen;
    }
    
    public String toString()
    {
        return this.strPersona();
    }

    @Override
    public String cadena() 
    {
        return this.strPersona();   
    }
    
    
    
}
