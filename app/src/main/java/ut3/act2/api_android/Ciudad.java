package ut3.act2.api_android;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Esta es la clase que define los componentes de la vista de la ciudad.
 * Sirve para crear un listado de Items que luego mostraremos en el RecyclerView.
 */
public class Ciudad {
    // Creamos los elementos
    int imagenClima;
    String nombreCiudad;
    String temp;

    // Constructor
    public Ciudad(int clima, String ciudad, String temp) {
        this.imagenClima = clima;
        this.nombreCiudad = ciudad;
        this.temp = temp;
    }

    // GETTERS y SETTERS
    public int getClima() {
        return imagenClima;
    }

    public void setClima(int clima) {
        this.imagenClima = clima;
    }

    public String getCiudad() {
        return nombreCiudad;
    }

    public void setCiudad(String ciudad) {
        this.nombreCiudad = ciudad;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
