package ut3.act2.api_android;

// IMPORTS
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Clase del adaptador del RecyclerView
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    // Contexto y lista de elementos (NECESARIO)
    Context context;
    List<Ciudad> ciudades;

    // Constructor de la clase
    public Adapter(Context context, List<Ciudad> ciudades) {
        this.context = context;
        this.ciudades = ciudades;
    }

    // METODOS HEREDADOS OBLIGATORIOS

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflamos la vista con el layout personalizado
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.fila_ciudad, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Asignamos los valores a los elementos de la vista
        holder.ciudad.setText(ciudades.get(position).getCiudad());
        holder.temp.setText(ciudades.get(position).getTemp());
        holder.imagen.setImageResource(ciudades.get(position).getClima());
    }

    @Override
    public int getItemCount() {
        // Devolvemos el tamaño de la lista de ciudades
        return ciudades.size();
    }
}
