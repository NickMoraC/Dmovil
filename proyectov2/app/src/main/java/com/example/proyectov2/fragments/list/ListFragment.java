package com.example.proyectov2.fragments.list;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.proyectov2.R;
import com.example.proyectov2.repositories.retrofit.responses.PersonaResponse;
import com.example.proyectov2.repositories.room.entities.Persona;
import com.example.proyectov2.viewmodels.PersonaViewModel;
import com.example.proyectov2.views.adapters.PersonaAdapter;
import com.example.proyectov2.views.callback.PersonaClickCallback;
import com.example.proyectov2.views.utils.ToastUtils;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;


public class ListFragment extends Fragment {
    private PersonaViewModel vmPersona;
    ProgressBar pgPersona;
    RecyclerView rvPersona;
    PersonaAdapter aPersona;
    SwipeRefreshLayout swipeRefreshLayout;
    FirebaseFirestore mfirestore;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mfirestore = FirebaseFirestore.getInstance();
        view.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_listFragment_to_addFragment);
            }
        });

        vmPersona = ViewModelProviders.of(this).get(PersonaViewModel .class);
        rvPersona = view.findViewById(R.id.mRecyclerView);
        rvPersona.setLayoutManager(new LinearLayoutManager(getContext()));
        pgPersona = view.findViewById(R.id.progress);
        pgPersona.setVisibility(View.VISIBLE);
        //vmPersona.insertDummyPersona();
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        init();
        return view;
    }
    @SuppressLint("FragmentLiveDataObserve")
    private void init() {
        Query query = mfirestore.collection("personas");
        FirestoreRecyclerOptions<Persona> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Persona>().setQuery(query, Persona.class).build();

        aPersona = new PersonaAdapter(firestoreRecyclerOptions);
        rvPersona.setAdapter(aPersona);

        //from room
        vmPersona.getPersonaRoomListObservable().observe(this, new Observer<List<Persona>>() {
            //from ws
            //vmRevistas.getRevistasListObservable().observe(this, new Observer<List<Revistas>>() {
            @Override
            public void onChanged(List<Persona> persona) {
                //Toast.makeText(getBaseContext(),"Datos: " + revistas,Toast.LENGTH_LONG).show();
                pgPersona.setVisibility(View.INVISIBLE);
            }

        });
    }
    private final PersonaClickCallback personaClickCallback = new PersonaClickCallback() {
        @Override
        public void onClick(Persona persona) {
            ToastUtils.shortToast("Cedula: " + persona.cedula);
            roomsave();
        }

        @Override
        public void onDelete(Persona persona) {
            //delete(revistas.id);
            vmPersona.deletePersonaListObservable(persona.id);
            init();
        }

            @Override
            public void onUpdate(Persona persona) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_listFragment_to_updateFragment);
            }

    };

    public void delete(int personaId){
        vmPersona.deletePersonaListObservable(personaId).observe(this, new Observer<PersonaResponse>() {
            @Override
            public void onChanged(PersonaResponse response) {
                ToastUtils.shortToast(response.getMessage());
                init();
            }
        });
    }
    public void save(){
        vmPersona.setPersonaListObservable(1118874934,"jose","daza","jfdazaquitian@uniguajira.edu.co",304532683).observe(this, new Observer<PersonaResponse>() {
            @Override
            public void onChanged(PersonaResponse response) {
                ToastUtils.shortToast(response.getMessage());
                init();
            }
        });
    }
    public void roomsave(){
        vmPersona.setPersonaRoom(1118874934,"jose","daza","jfdazaquitian@uniguajira.edu.co",304532683);
        init();
    }
    public void getpersona(int personaId){
        vmPersona.getPersonaListObservable(personaId).observe(this, new Observer<Persona>() {
            @Override
            public void onChanged(Persona response) {
                ToastUtils.shortToast(response.getMessage());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        aPersona.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        aPersona.stopListening();
    }
}