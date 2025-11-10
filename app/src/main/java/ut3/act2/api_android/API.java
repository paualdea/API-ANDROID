package ut3.act2.api_android;

// IMPORTS
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Creamos una interfaz en la que le dicemos a Retrofit lo que le vamos a pasar en la petición
 * Indicamos también dónde tiene que ir a buscarlo
 */
public interface API {
    @GET("v1/forecast?current_weather=true")
    Call<String> getWeather(
            @Query("latitude") String latitude,
            @Query("longitude") String longitude
    );
}
