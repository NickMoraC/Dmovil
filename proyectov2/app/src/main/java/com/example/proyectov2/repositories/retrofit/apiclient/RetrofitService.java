package com.example.proyectov2.repositories.retrofit.apiclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    //public static final String IP = "http://108.200.198.29:8888";
    public static final String IP = "http://192.168.1.42:80";

    public static final String BACKEND = "/api/";
    public static final String BASE_URL = IP + BACKEND;

    public static final String GET_USUARIOS = BASE_URL + "views/app_get_all_usuarios.php";
    public static final String GET_USUARIO = BASE_URL + "views/app_get_usuario.php";
    public static final String SET_DELETE_USUARIO = BASE_URL + "views/app_delete_usuarios.php";
    public static final String SET_UPDATE_USUARIO = BASE_URL + "views/app_update_usuarios.php";
    public static final String SET_USUARIO = BASE_URL + "views/app_set_usuarios.php";
    public static final String GET_PERSONAS = BASE_URL + "views/app_get_all_personas.php";
    public static final String GET_PERSONA = BASE_URL + "create.php";
    public static final String SET_DELETE_PERSONA = BASE_URL + "eliminar.php";
    public static final String SET_UPDATE_PERSONA = BASE_URL + "actualizar.php";
    public static final String SET_PERSONA = BASE_URL + "read.php";

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();


    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
