package ut3.act2.api_android;

// IMPORTS
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Clase principal que mostrara los datos que recopilemos de la API
 */
public class MainActivity extends AppCompatActivity {
    // Creamos el recyclerView de la actividad
    RecyclerView vistaCiudades;

    // Creamos las variables que almacenan los datos recopilados de la API
    String temp, estado;

    // Creamos el array de datos que queremos
    String[][] datos = {
            {"Barcelona", "41.38", "2.17"},
            {"Berlín", "52.52", "13.40"},
            {"Buenos Aires", "-34.60", "-58.38"}
    };

    // Creamos la lista de ciudades con sus datos
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

        // Hacemos la solicitud a la API con el array
        solicitudApi(datos);

        // Declaramos y configuramos el RecyclerView
        vistaCiudades = findViewById(R.id.vistaCiudades);
        vistaCiudades.setLayoutManager(new LinearLayoutManager(this));
        vistaCiudades.setAdapter(new Adapter(getApplicationContext(), ciudades));
    }

    /**
     * Función que solicita los datos de la API
     */
    private void solicitudApi(String[][] datos) {
        // Creamos el cliente Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        // Creamos la API para hacer las solicitudes usando nuestra interfaz
        API api = retrofit.create(API.class);

        // Creamos un bucle for que ejecute todas las solicitudes
        for (int i = 0; i < datos.length; i++) {
            // Variables de los datos del array
            String ciudad = datos[i][0];
            String latitud = datos[i][1];
            String longitud = datos[i][2];

            // Preparamos la solicitud
            Call<String> call = api.getWeather(latitud, longitud);

            // Ejecutamos la solicitud en segundo plano
            call.enqueue(new Callback<String>() {
                // Se crea una estructura de funciones en las que se ejecuta cierto código si recibe o no respuesta de la API

                // Si se responde a la CALL
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    // Comprobamos que la respuesta tenga contenido
                    if (response.body() != null) {
                        // Guardamos el contenido en un String y notificamos por consola
                        String json = response.body();
                        Log.d("RESPUESTA API", json);

                        // Pasamos a procesar el String con una estructura de control try - catch
                        try {
                            // Creamos un objeto JSONObject que parsea el JSON
                            JSONObject object = new JSONObject(json);
                            // Obtenemos el objeto 'current_weather'
                            JSONObject weather = object.getJSONObject("current_weather");

                            // Obtenemos los datos que necesitamos
                            temp = weather.getString("temperature");
                            estado = weather.getString("weathercode");
                            // Convertimos el estado en un entero
                            int estado_int = Integer.parseInt(estado);

                            // Definimos el logo acorde al estado
                            // Si es 0 o 1, es despejado
                            if (estado_int >= 0 && estado_int <= 2) {
                                estado_int = R.drawable.sol;
                            }
                            // Si es del 2 al 48 es nublado (generalizando)
                            else if (estado_int > 2 && estado_int <= 48) {
                                estado_int = estado_int = R.drawable.nublado;;
                            }
                            // Si es mayor a 48 es lluvia (generalizando)
                            else if (estado_int > 48 && estado_int <= 99){
                                estado_int = R.drawable.lluvia;
                            }
                            // En otro caso ponemos nublado
                            else {
                                estado_int = R.drawable.nublado;
                            }

                            // Añadimos los datos obtenidos a la lista de ciudades del RecyclerView
                            ciudades.add(new Ciudad(estado_int, ciudad, temp + " º"));

                            // Actualizamos el RecyclerView
                            Adapter adaptador = (Adapter) vistaCiudades.getAdapter();
                            if (adaptador != null) {
                                adaptador.notifyDataSetChanged();
                            }
                        }catch (Exception e) {
                            // Si falla al procesar el JSON, mandamos mensaje por consola y Toast
                            Log.e("JSON ERROR", "No se pudo procesar el JSON. " + e.getMessage());
                            Toast.makeText(MainActivity.this, "Error procesado JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                    // Si se responde con un error, notificamos tanto por consola cómo por la aplicación
                    else {
                        Log.e("RESPUESTA API ERROR", response.errorBody().toString());
                        Toast.makeText(MainActivity.this, "Error de API", Toast.LENGTH_SHORT).show();
                    }
                }

                // Si no hay internet o hay fallo en el servidor de destino se lanza esta función
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("ERROR DE RED", "Fallo de conexión");
                    Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                }
             }
            );
        }
    }
}