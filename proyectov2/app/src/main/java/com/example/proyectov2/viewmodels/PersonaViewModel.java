package com.example.proyectov2.viewmodels;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectov2.repositories.retrofit.datasource.PersonaRepository;
import com.example.proyectov2.repositories.retrofit.responses.PersonaResponse;
import com.example.proyectov2.repositories.room.dao.PersonaDao;
import com.example.proyectov2.repositories.room.database.AppDatabase;
import com.example.proyectov2.repositories.room.entities.Persona;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PersonaViewModel extends AndroidViewModel {
    private PersonaRepository personaRepository;
    private MutableLiveData<List<Persona>> personasListObservable;
    private MutableLiveData<Persona> personaListObservable;
    private MutableLiveData<PersonaResponse> personaObservable;

    PersonaDao personaDao;
    AppDatabase db;

    public PersonaViewModel(Application application) {
        super(application);
        db = AppDatabase.getAppDatabase(application);
        personaDao = db.personaDao();
        personasListObservable = new MutableLiveData<>();
    }

    public LiveData<List<Persona>> getPersonaListObservable() {
        personaRepository = PersonaRepository.getInstance();
        personasListObservable = personaRepository.getPersonasListRepository();
        return personasListObservable;
    }

    public LiveData<List<Persona>> getPersonaRoomListObservable() {
        return personaDao.findAll();
    }

    public LiveData<PersonaResponse> deletePersonaListObservable(int personaId) {
        Persona persona = personaDao.findByPk(personaId);
        personaDao.delete(persona);
        return personaObservable;
    }
    public void setPersonaRoom(int cedula, String nombre, String apellido, String email, int telefono){
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setApellido (apellido);
        persona.setEmail(email);
        persona.setCedula(String.valueOf(cedula));
        persona.setTelefono(String.valueOf(telefono));
        personaDao.insertOne(persona);

    }

    public LiveData<PersonaResponse> setPersonaListObservable(int cedula, String nombre, String apellido, String email, int telefono) {
        personaRepository = PersonaRepository.getInstance();
        personaObservable = personaRepository.setPersonaListRepository(cedula, nombre,apellido,email,telefono);
        return personaObservable;
    }

    public LiveData<Persona> getPersonaListObservable(int personaId) {
        personaRepository = PersonaRepository.getInstance();
        personaListObservable = personaRepository.getPersonaListRepository(personaId);
        return personaListObservable;
    }

    public void insertarPersona(int cedula, String nombre, String apellido, String email, int telefono) {
        Persona persona = new Persona();
        persona.setCedula(String.valueOf(cedula));
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setEmail(email);
        persona.setTelefono(String.valueOf(telefono));
        personaDao.insertOne(persona);
    }
    public void updatePersona(String id, Persona updatedPersona) {
        FirebaseFirestore mfirestore = FirebaseFirestore.getInstance();
        mfirestore.collection("personas").document(id).set(updatedPersona.toMap());
    }

    public int getLastInsertedId() {

        Persona ultimaPersona = personaDao.getLastInsertedPersona();
        return ultimaPersona.getId();
    }
    public void insertDummyPersona() {
        List<Persona> personaList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Persona persona = new Persona();
            persona.setNombre("Jose");
            persona.setApellido ("Moscote");
            persona.setCedula(String.valueOf(i));
            persona.setEmail("dsadsa@gmail.com");
            persona.setTelefono("3004328473");
            personaList.add(persona);
        }
        //revistasDao.insertOne(revista);
        personaDao.insertAll(personaList);
    }
}
