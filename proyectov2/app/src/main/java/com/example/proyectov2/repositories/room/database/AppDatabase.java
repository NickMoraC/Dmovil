package com.example.proyectov2.repositories.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectov2.repositories.room.dao.PersonaDao;
import com.example.proyectov2.repositories.room.dao.UsuarioDao;
import com.example.proyectov2.repositories.room.entities.Persona;
import com.example.proyectov2.repositories.room.entities.Usuario;

@Database(entities = {Usuario.class, Persona.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "my_database.db";
    private static AppDatabase sInstance;

    public abstract UsuarioDao usuarioDao();
    public abstract PersonaDao personaDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }
}

