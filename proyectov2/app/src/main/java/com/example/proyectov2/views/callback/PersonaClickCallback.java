package com.example.proyectov2.views.callback;

import com.example.proyectov2.repositories.room.entities.Persona;

public interface PersonaClickCallback {
    void onClick(Persona persona);
    void onDelete(Persona persona);
    void onUpdate(Persona persona);
}
