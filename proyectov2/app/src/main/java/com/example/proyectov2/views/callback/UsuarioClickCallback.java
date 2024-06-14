package com.example.proyectov2.views.callback;

import com.example.proyectov2.repositories.room.entities.Usuario;

public interface UsuarioClickCallback {
    void onClick(Usuario usuario);
    void onDelete(Usuario usuario);
    void onUpdate(Usuario usuario);
}

