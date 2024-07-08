package com.example.appstarwarsapi.utils;

import com.example.appstarwarsapi.SWApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/*!
 * Класс для роботы с библиотекой ретрофит с зашитой базовой строкой запроса
 */
public class RetrofitClient {
    private static final String BASE_URL = "https://swapi.dev/api/";
    private static RetrofitClient instance;
    private Retrofit retrofit;

    /*!
     * Конструктор
     */
    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /*!
     * Статический метод получения объекта данного класса
     * synchronized обеспечивает потокобезопасность
     */
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    /*!
     * Получить готовый сервис отправки запросов на сервер
     * (прописан только один метод)
     */
    public SWApiService getApi() {
        return retrofit.create(SWApiService.class);
    }
}
