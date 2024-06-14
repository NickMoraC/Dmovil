package com.example.proyectov2.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectov2.repositories.retrofit.datasource.UsuarioRepository;
import com.example.proyectov2.repositories.retrofit.responses.UsuarioResponse;
import com.example.proyectov2.repositories.room.dao.UsuarioDao;
import com.example.proyectov2.repositories.room.database.AppDatabase;
import com.example.proyectov2.repositories.room.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioViewModel extends AndroidViewModel {
    private UsuarioRepository usuarioRepository;
    private MutableLiveData<List<Usuario>> usuariosListObservable;
    private MutableLiveData<Usuario> usuarioListObservable;
    private MutableLiveData<UsuarioResponse> usuarioObservable;

    UsuarioDao usuarioDao;
    AppDatabase db;

    public UsuarioViewModel(Application application) {
        super(application);
        db = AppDatabase.getAppDatabase(application);
        usuarioDao = db.usuarioDao();
        usuariosListObservable = new MutableLiveData<>();
    }

    public LiveData<List<Usuario>> getUsuarioListObservable() {
        usuarioRepository = UsuarioRepository.getInstance();
        usuariosListObservable = usuarioRepository.getUsuariosListRepository();
        return usuariosListObservable;
    }

    public LiveData<List<Usuario>> getUsuarioRoomListObservable() {
        return usuarioDao.findAll();
    }

    public LiveData<UsuarioResponse> deleteUsuarioListObservable(int usuarioId) {
        Usuario usuario = (Usuario) usuarioDao.findByPk(usuarioId);
        usuarioDao.delete(usuario);
        return usuarioObservable;
    }
    public void setUsuarioRoom(int personaId, String username, String password){
        Usuario usuario = new Usuario();
        usuario.setPersonaId(personaId);
        usuario.setUsername (username);
        usuario.setPassword(password);
        usuarioDao.insertOne(usuario);

    }

    public LiveData<UsuarioResponse> setUsuarioListObservable(int personaId, String username, String password) {
        usuarioRepository = UsuarioRepository.getInstance();
        usuarioObservable = usuarioRepository.setUsuarioListRepository(personaId,username,password);
        return usuarioObservable;
    }

    public LiveData<Usuario> getUsuarioListObservable(int usuarioId) {
        usuarioRepository = UsuarioRepository.getInstance();
        usuarioListObservable = usuarioRepository.getUsuarioListRepository(usuarioId);
        return usuarioListObservable;
    }

    public void insertarUsuario(int personaId, String username, String password) {
        Usuario usuario = new Usuario();
        usuario.setPersonaId(personaId);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuarioDao.insertOne(usuario);
    }


    public void insertDummyUsuario() {
        List<Usuario> usuarioList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre("Jose");
            usuario.setApellido ("Moscote");
            usuario.setCedula(String.valueOf(i));
            usuario.setEmail("dsadsa@gmail.com");
            usuario.setTelefono("3004328473");
            usuarioList.add(usuario);
        }
        //revistasDao.insertOne(revista);
        usuarioDao.insertAll(usuarioList);
    }

}
