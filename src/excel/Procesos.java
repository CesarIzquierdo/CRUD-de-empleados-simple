package excel;

import java.io.IOException;

public class Procesos
{

    public void cargarArchivo()
    {
        abrir();
    }

    private void abrir()
    {
        //ruta del archivo en el pc
        String file = new String("C:\\Users\\cesar\\OneDrive\\Documentos\\NetBeansProjects\\BajasAyu\\pruebas\\prueba1.xlsx");

        try
        {
            //definiendo la ruta en la propiedad file
            Runtime.getRuntime().exec("cmd /c start " + file);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
