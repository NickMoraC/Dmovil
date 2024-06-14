package com.example.proyectov2.repositories.retrofit.service;

import com.example.proyectov2.repositories.retrofit.apiclient.RetrofitService;
import com.example.proyectov2.repositories.retrofit.responses.PersonaResponse;
import com.example.proyectov2.repositories.retrofit.responses.UsuarioResponse;
import com.example.proyectov2.repositories.room.entities.Persona;
import com.example.proyectov2.repositories.room.entities.Usuario;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PersonaService {
    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_PERSONA)
    Call<PersonaResponse> getPersonasService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_DELETE_PERSONA)
    Call<PersonaResponse> deletePersonaService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_UPDATE_PERSONA)
    Call<PersonaResponse> updatePersonaService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.SET_PERSONA)
    Call<PersonaResponse> setPersonaService(@Body Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST(RetrofitService.GET_PERSONA)
    Call<Persona> getPersonaService(@Body Map<String, String> params);
}
