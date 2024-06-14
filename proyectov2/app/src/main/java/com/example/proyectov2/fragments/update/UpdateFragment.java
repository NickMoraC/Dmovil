package com.example.proyectov2.fragments.update;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectov2.R;
import com.example.proyectov2.repositories.room.entities.Persona;
import com.example.proyectov2.viewmodels.PersonaViewModel;
import com.example.proyectov2.viewmodels.UsuarioViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateFragment extends Fragment {

    private PersonaViewModel vmPersona;

    EditText nombreInput;
    EditText apellidoInput;
    EditText cedulaInput;
    EditText telefonoInput;
    EditText emailInput;
    private String personaId;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        vmPersona = new ViewModelProvider(this).get(PersonaViewModel.class);
        nombreInput = view.findViewById(R.id.upname_input);
        apellidoInput = view.findViewById(R.id.uplastname_input);
        cedulaInput = view.findViewById(R.id.upcedula_input);
        telefonoInput = view.findViewById(R.id.uptelefono_input);
        emailInput = view.findViewById(R.id.upemail_input);

        view.findViewById(R.id.update_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos de los EditText
                String nombre = nombreInput.getText().toString();
                String apellido = apellidoInput.getText().toString();
                String email = emailInput.getText().toString();
                String cedulaString = cedulaInput.getText().toString();
                String telefonoString = telefonoInput.getText().toString();


                if (getArguments() != null) {
                    personaId = getArguments().getString("persona_id");
                    loadPersonaData(personaId);
                }
                if (!nombre.isEmpty() && !email.isEmpty() &&
                        !apellido.isEmpty() && !cedulaString.isEmpty() && !telefonoString.isEmpty()) {

                    Persona updatedPersona = new Persona(cedulaString, nombre, apellido, email, telefonoString);

                    vmPersona.updatePersona(personaId, updatedPersona);



                    Navigation.findNavController(v).navigate(R.id.action_updateFragment_to_listFragment);
                }  else {

                    Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void loadPersonaData(String personaId) {
        FirebaseFirestore mfirestore = FirebaseFirestore.getInstance();
        mfirestore.collection("personas").document(personaId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Persona persona = documentSnapshot.toObject(Persona.class);
                            if (persona != null) {
                                nombreInput.setText(persona.getNombre());
                                apellidoInput.setText(persona.getApellido());
                                cedulaInput.setText(persona.getCedula());
                                telefonoInput.setText(persona.getTelefono());
                                emailInput.setText(persona.getEmail());
                            }
                        }
                    }
                });
    }
}