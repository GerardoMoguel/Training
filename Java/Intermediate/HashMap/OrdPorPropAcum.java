/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hash_02;

import java.util.Comparator;

/**
 *
 * @author rggh
 */

//
// Objetos comparadores de objetos de la clase ClsCadena en términos de sus proporciones acumuladas
//
public class OrdPorPropAcum implements Comparator<ClsCadena>
{
    @Override
    public int compare(ClsCadena o1, ClsCadena o2) {
        double prop1 = o1.getProp_acum();
        double prop2 = o2.getProp_acum();
        return prop1  > prop2 ? 1 :
               prop1 == prop2 ? 0 : -1;
        
    }
    
}
