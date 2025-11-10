package ut3.act2.api_android;

// IMPORTS
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que mostrara los datos que recopilemos de la API
 */
public class MainActivity extends AppCompatActivity {
    // Creamos el recyclerView de la actividad
    RecyclerView vistaCiudades;

    // Creamos la lista de ciudades
    List<Ciudad> ciudades = new ArrayList<Ciudad>();

    /**
     * Función que crea y muestra la actividad principal.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Mostramos la actividad principal
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Activamos el modo EdgetoEdge para que la aplicación tenga una visualización mas moderna
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Añadimos las 3 ciudades predeterminadas
        ciudades.add(new Ciudad(R.drawable.bcn, "Barcelona", "24"));
        ciudades.add(new Ciudad(R.drawable.bcn, "Barcelona", "24"));
        ciudades.add(new Ciudad(R.drawable.bcn, "Barcelona", "24"));

        // Declaramos y configuramos el RecyclerView
        vistaCiudades = findViewById(R.id.vistaCiudades);
        vistaCiudades.setLayoutManager(new LinearLayoutManager(this));
        vistaCiudades.setAdapter(new Adapter(getApplicationContext(), ciudades));
    }
}