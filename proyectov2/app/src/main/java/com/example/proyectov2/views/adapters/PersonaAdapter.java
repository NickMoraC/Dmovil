package com.example.proyectov2.views.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectov2.R;
import com.example.proyectov2.databinding.MainPersonaRowBinding;
import com.example.proyectov2.repositories.room.entities.Persona;
import com.example.proyectov2.views.callback.PersonaClickCallback;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PersonaAdapter extends FirestoreRecyclerAdapter<Persona, PersonaAdapter.PersonaViewHolder> {
    private FirebaseFirestore mfirestore = FirebaseFirestore.getInstance();
    public PersonaAdapter(@NonNull FirestoreRecyclerOptions<Persona> options) {
        super(options);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainPersonaRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.main_persona_row, parent, false);
        return new PersonaViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(@NonNull PersonaViewHolder holder, int position, @NonNull Persona model) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        holder.binding.setPersonas(model);
        holder.binding.executePendingBindings();
        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersona(id);
            }
        });

        holder.binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Bundle con el ID de la persona
                Bundle bundle = new Bundle();
                bundle.putString("persona_id", id);

                // Navegar al UpdateFragment con el ID de la persona
                Navigation.findNavController(v).navigate(R.id.action_listFragment_to_updateFragment, bundle);
            }
        });
    }
    private void deletePersona(String id) {
        mfirestore.collection("personas").document(id).delete();

    }


    static class PersonaViewHolder extends RecyclerView.ViewHolder {
        final MainPersonaRowBinding binding;

        public PersonaViewHolder(MainPersonaRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
