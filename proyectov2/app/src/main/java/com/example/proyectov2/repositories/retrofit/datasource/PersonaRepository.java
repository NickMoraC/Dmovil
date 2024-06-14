package com.example.proyectov2.repositories.retrofit.datasource;

import androidx.lifecycle.MutableLiveData;

import com.example.proyectov2.repositories.retrofit.apiclient.RetrofitService;
import com.example.proyectov2.repositories.retrofit.responses.PersonaResponse;
import com.example.proyectov2.repositories.retrofit.responses.UsuarioResponse;
import com.example.proyectov2.repositories.retrofit.service.PersonaService;
import com.example.proyectov2.repositories.retrofit.service.UsuarioService;
import com.example.proyectov2.repositories.room.entities.Persona;
import com.example.proyectov2.repositories.room.entities.Usuario;
import com.example.proyectov2.views.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonaRepository {
    private static PersonaRepository personasRepository;
    private PersonaService personasService;

    public PersonaRepository(){
        personasService = RetrofitService.cteateService(PersonaService.class);
    }

    public static PersonaRepository getInstance(){
        if (personasRepository == null){
            personasRepository = new PersonaRepository();
        }
        return personasRepository;
    }

    public MutableLiveData<List<Persona>> getPersonasListRepository() {
        final MutableLiveData<List<Persona>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        //params.put("page", String.valueOf(1));

        Call<PersonaResponse> call = personasService.getPersonasService(params);
        call.enqueue(new Callback<PersonaResponse>() {
            @Override
            public void onResponse(Call<PersonaResponse> call, Response<PersonaResponse> response) {
                //ToastUtils.shortToast("R: " + response.body().getMessage());

                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body().getPersona());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<PersonaResponse> call, Throwable t) {
                data.setValue(null);
                //ToastUtils.shortToast("E: " + t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<PersonaResponse> deletePersonasListRepository(long personaId) {
        final MutableLiveData<PersonaResponse> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("personaId", String.valueOf(personaId));

        Call<PersonaResponse> call = personasService.deletePersonaService(params);
        call.enqueue(new Callback<PersonaResponse>() {
            @Override
            public void onResponse(Call<PersonaResponse> call, Response<PersonaResponse> response) {
                ToastUtils.shortToast(response.body().getMessage());
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<PersonaResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<PersonaResponse> setPersonaListRepository(int cedula, String nombre, String apellido, String email, int telefono) {
        final MutableLiveData<PersonaResponse> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("cedula", String.valueOf(cedula));
        params.put("nombre", String.valueOf(nombre));
        params.put("apellido", String.valueOf(apellido));
        params.put("email", String.valueOf(email));
        params.put("telefono", String.valueOf(telefono));

        Call<PersonaResponse> call = personasService.setPersonaService(params);
        call.enqueue(new Callback<PersonaResponse>() {
            @Override
            public void onResponse(Call<PersonaResponse> call, Response<PersonaResponse> response) {
                ToastUtils.shortToast(response.body().getMessage());
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<PersonaResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Persona> getPersonaListRepository(int personaId) {
        final MutableLiveData<Persona> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("personaId", String.valueOf(personaId));

        Call<Persona> call = personasService.getPersonaService(params);
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
