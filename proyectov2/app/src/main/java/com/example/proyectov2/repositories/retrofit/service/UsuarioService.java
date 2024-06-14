package com.example.proyectov2.repositories.retrofit.service;

import com.example.proyectov2.repositories.retrofit.apiclient.RetrofitService;
import com.example.proyectov2.repositories.retrofit.responses.UsuarioResponse;
import com.example.proyectov2.repositories.room.entities.Usuario;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UsuarioService {

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_USUARIOS)
    Call<UsuarioResponse> getUsuariosService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_DELETE_USUARIO)
    Call<UsuarioResponse> deleteUsuarioService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_UPDATE_USUARIO)
    Call<UsuarioResponse> updateUsuarioService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_USUARIO)
    Call<UsuarioResponse> setUsuarioService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_USUARIOS)
    Call<Usuario> getUsuarioService(@Body Map<String, String> params);

}
