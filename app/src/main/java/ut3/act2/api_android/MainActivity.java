package ut3.act2.api_android;

// IMPORTS
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Clase principal que mostrara los datos que recopilemos de la API
 */
public class MainActivity extends AppCompatActivity {


    /**
     * Función que crea y muestra la actividad principal.
     */
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
    }
}