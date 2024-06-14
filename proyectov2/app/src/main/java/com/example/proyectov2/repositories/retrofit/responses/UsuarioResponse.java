package com.example.proyectov2.repositories.retrofit.responses;

import com.example.proyectov2.repositories.room.entities.Usuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsuarioResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<Usuario> usuarios = null;

    public UsuarioResponse(String status, String message, List<Usuario> usuarios) {
        this.status = status;
        this.message = message;
        this.usuarios = usuarios;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Usuario> getUsuario() {
        return usuarios;
    }

    public void setUsuario(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

