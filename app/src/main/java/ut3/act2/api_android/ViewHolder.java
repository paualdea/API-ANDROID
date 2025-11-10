package ut3.act2.api_android;

// IMPORTS
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Clase que administra la vista de cada elemento de la lista
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    // Creamos los elementos de la vista
    ImageView imagen;
    TextView ciudad, temp;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        // Asignamos los elementos de la vista
        imagen = itemView.findViewById(R.id.imagenClima);
        ciudad = itemView.findViewById(R.id.nombreCiudad);
        temp = itemView.findViewById(R.id.temperatura);
    }
}
