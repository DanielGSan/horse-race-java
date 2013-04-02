import java.awt.*;

// en pruebas

//CREAMOS NUESTRA CLASE "Ventana" Y LA HACEMOS QUE HEREDE LOS ATRIBUTOS 
//Y METODOS DE LA CLASE "Frame"
public class Ventana extends Frame{

    //CREAMOS EL CONSTRUCTOR DE NUESTRA CLASE
    public Ventana(){
        super("Ventana en JAVA"); //LE DAMOS UN NOMBRE A NUESTRA VENTANA
        this.setSize(500, 500); //ESTABLECEMOS EL TAMAÑO DE LA VENTANA
    }//FIN DEL CONSTRUCTOR DE LA CLASE Ventana

    //PROCEDIMIENTO PRINCIPAL DEL PROGRAMA
    public static void main(String g[]){
        Ventana prog = new Ventana();//Instanciamos la clase que creamos
        prog.show();//Mostramos esa ventana
    }//FIN DEL PROCEDIMIENTO PRINCIPAL

    //PARA PODER CERRAR LA VENTANA
    public boolean handleEvent(Event evt){
        if (evt.id == Event.WINDOW_DESTROY)
            System.exit(0);
        return super.handleEvent(evt);
    }
}//FIN DE LA CLASE Ventana
//FIN
