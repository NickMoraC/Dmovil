package com.example.proyectov2.repositories.retrofit.datasource;


import androidx.lifecycle.MutableLiveData;

import com.example.proyectov2.repositories.retrofit.apiclient.RetrofitService;
import com.example.proyectov2.repositories.retrofit.responses.UsuarioResponse;
import com.example.proyectov2.repositories.retrofit.service.UsuarioService;
import com.example.proyectov2.repositories.room.entities.Usuario;
import com.example.proyectov2.views.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {
    private static UsuarioRepository usuariosRepository;
    private UsuarioService usuariosService;

    public UsuarioRepository(){
        usuariosService = RetrofitService.cteateService(UsuarioService.class);
    }

    public static UsuarioRepository getInstance(){
        if (usuariosRepository == null){
            usuariosRepository = new UsuarioRepository();
        }
        return usuariosRepository;
    }

    public MutableLiveData<List<Usuario>> getUsuariosListRepository() {
        final MutableLiveData<List<Usuario>> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        //params.put("page", String.valueOf(1));

        Call<UsuarioResponse> call = usuariosService.getUsuariosService(params);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                //ToastUtils.shortToast("R: " + response.body().getMessage());

                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body().getUsuario());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                data.setValue(null);
                //ToastUtils.shortToast("E: " + t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<UsuarioResponse> deletePersonasListRepository(long usuarioId) {
        final MutableLiveData<UsuarioResponse> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("usuarioId", String.valueOf(usuarioId));

        Call<UsuarioResponse> call = usuariosService.deleteUsuarioService(params);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                ToastUtils.shortToast(response.body().getMessage());
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<UsuarioResponse> setUsuarioListRepository(int personaId, String username, String password) {
        final MutableLiveData<UsuarioResponse> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("persona_id", String.valueOf(personaId));
        params.put("username", String.valueOf(username));
        params.put("password", String.valueOf(password));

        Call<UsuarioResponse> call = usuariosService.setUsuarioService(params);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                ToastUtils.shortToast(response.body().getMessage());
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Usuario> getUsuarioListRepository(int usuarioId) {
        final MutableLiveData<Usuario> data = new MutableLiveData<>();

        final Map<String, String> params = new HashMap<>();
        params.put("usuarioId", String.valueOf(usuarioId));

        Call<Usuario> call = usuariosService.getUsuarioService(params);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.body().getStatus().equals("success")){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
