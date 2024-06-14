package com.example.proyectov2.repositories.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectov2.repositories.room.entities.Persona;

import java.util.List;

@Dao
public interface PersonaDao {
    @Query("SELECT * FROM personas")
    LiveData<List<Persona>> findAll();

    @Query("SELECT * FROM personas WHERE id = :id")
    Persona findByPk(int id);

    @Query("SELECT COUNT(*) from personas")
    int count();

    @Insert()
    long insertOne(Persona persona);

    @Insert
    void insertAll(List<Persona> persona);

    @Delete
    void delete(Persona persona);

    @Update
    void update(Persona persona);
    @Query("SELECT * FROM personas ORDER BY id DESC LIMIT 1")
    Persona getLastInsertedPersona();
}
