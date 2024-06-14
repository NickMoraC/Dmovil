package com.example.proyectov2.repositories.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectov2.repositories.room.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    LiveData <List<Usuario>> findAll();

    @Query("SELECT * FROM usuarios WHERE id = :id")
    Usuario findByPk(int id);

    @Query("SELECT * FROM usuarios WHERE username = :username")
    Usuario findByUsername(String username);
    @Query("SELECT COUNT(*) from usuarios")
    int count();

    @Insert()
    long insertOne(Usuario usuario);

    @Insert
    void insertAll(List<Usuario> usuarios);

    @Delete
    void delete(Usuario usuario);

    @Update
    void update(Usuario usuario);
}

