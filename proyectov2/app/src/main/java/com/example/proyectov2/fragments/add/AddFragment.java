package com.example.proyectov2.fragments.add;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectov2.R;
import com.example.proyectov2.viewmodels.PersonaViewModel;
import com.example.proyectov2.viewmodels.UsuarioViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddFragment extends Fragment {

    private PersonaViewModel vmPersona;
    private FirebaseFirestore mfirestore;
    EditText nombreInput;
    EditText apellidoInput;
    EditText cedulaInput;
    EditText telefonoInput;
    EditText emailInput;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        vmPersona = new ViewModelProvider(this).get(PersonaViewModel.class);
        mfirestore = FirebaseFirestore.getInstance();
        nombreInput = view.findViewById(R.id.addname_input);
        apellidoInput = view.findViewById(R.id.addlastname_input);
        cedulaInput = view.findViewById(R.id.addcedula_input);
        telefonoInput = view.findViewById(R.id.addtelefono_input);
        emailInput = view.findViewById(R.id.addemail_input);

        view.findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos de los EditText

                String nombre = nombreInput.getText().toString();
                String apellido = apellidoInput.getText().toString();
                String email = emailInput.getText().toString();
                String cedulaString = cedulaInput.getText().toString();
                String telefonoString = telefonoInput.getText().toString();
                Map<String,Object> map = new HashMap<>();
                map.put("nombre",nombre);
                map.put("apellido",apellido);
                map.put("email",email);
                map.put("cedula",cedulaString);
                map.put("telefono",telefonoString);


                if (!nombre.isEmpty() && !email.isEmpty() &&
                        !apellido.isEmpty() && !cedulaString.isEmpty() && !telefonoString.isEmpty()) {

                    int cedula = Integer.parseInt(cedulaString);
                    int telefono = Integer.parseInt(telefonoString);


                    vmPersona.insertarPersona(cedula, nombre, apellido, email, telefono);
                    mfirestore.collection("personas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getContext(),"Datos insertados con éxito", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(),"Error al insertar datos", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Navigation.findNavController(v).navigate(R.id.action_addFragment_to_listFragment);
                } else {

                    Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}