package com.example.proyectov2.repositories.retrofit.responses;


import com.example.proyectov2.repositories.room.entities.Persona;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonaResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<Persona> personas = null;

    public PersonaResponse(String status, String message, List<Persona> personas) {
        this.status = status;
        this.message = message;
        this.personas = personas;
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

    public List<Persona> getPersona() {
        return personas;
    }

    public void setPersona(List<Persona> personas) {
        this.personas = personas;
    }
}

