package com.example.proyectov2.repositories.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "usuarios", foreignKeys = @ForeignKey(entity = Persona.class,
        parentColumns = "id",
        childColumns = "persona_id",
        onDelete = ForeignKey.CASCADE))


public class Usuario extends Persona{


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

    @ColumnInfo(name = "persona_id")
    @SerializedName("id")
    public int personaId;

    @SerializedName("username")
    @ColumnInfo(name = "username")
    public String username;

    @SerializedName("password")
    @ColumnInfo(name = "password")
    public String password;

    public Usuario() {

    }

    public Usuario(int id, int personaId, String username, String password) {
        this.id = id;
        this.personaId = personaId;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

