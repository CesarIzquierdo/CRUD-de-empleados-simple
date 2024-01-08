/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bajasayu;

import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class validar
{

    String nombre, recidencia, curp, gradoEstudios, licenciaturaOficio, noIssemyn;

    //este metodo va a validar el nombre, la recidencia, el grado de estudios y la licenciatura u oficio
  

    public String curp(String c)
    {
        if (c.length() <= 17)
        {
            JOptionPane.showMessageDialog(null, "faltan caracteres, por favor ingrese una curp con 16 caracteres");
        } else
        {
            if (c.length() >= 19)
            {
                JOptionPane.showMessageDialog(null, "excede el numero de caracteres, por favor ingrese una curp con 16 caracteres");
            }
        }
        return c;
    }

    public String noI(String num)
    {
        if (num.equals("No") || num.equals("no"))
        {
            
        } else
        {
            if (num.equals("n/a"))
            {

            } else
            {
                if (num.equals("Si") || num.equals("si"))
                {

                }
            }
        }
        return num;
    }
}
