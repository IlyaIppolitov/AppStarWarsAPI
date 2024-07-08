package com.example.appstarwarsapi;

import com.example.appstarwarsapi.models.SWApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*!
 * Интерфейс для описания строки запроса
 */
public interface SWApiService {
    @GET("people/")
    Call<SWApiResponse> getCharacters(@Query("search") String name);
}
