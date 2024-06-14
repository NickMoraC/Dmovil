package com.example.proyectov2.repositories.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "personas")
public class Persona {
    @SerializedName("status")
    @Expose
    @Ignore
    private String status;

    @SerializedName("message")
    @Expose
    @Ignore
    private String message;

    /**
     * The name of the ID column.
     */
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "id")
    public int id;

    @SerializedName("cedula")
    @ColumnInfo(name = "cedula")
    public String cedula;

    @SerializedName("nombre")
    @ColumnInfo(name = "nombre")
    public String nombre;

    @SerializedName("apellido")
    @ColumnInfo(name = "apellido")
    public String apellido;

    @SerializedName("email")
    @ColumnInfo(name = "email")
    public String email;

    @SerializedName("telefono")
    @ColumnInfo(name = "telefono")
    public   String telefono;

    public Persona() {

    }
    public Persona( String cedula, String nombre, String apellido, String email, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("cedula", cedula);
        result.put("nombre", nombre);
        result.put("apellido", apellido);
        result.put("email", email);
        result.put("telefono", telefono);
        return result;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
