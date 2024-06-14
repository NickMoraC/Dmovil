package com.example.proyectov2.fragments.login;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.proyectov2.R;
import com.example.proyectov2.viewmodels.PersonaViewModel;
import com.example.proyectov2.views.adapters.PersonaAdapter;

public class LoginFragment extends Fragment {


    EditText usernameInput;
    EditText passwordInput;
    Button loginButton;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        usernameInput = view.findViewById(R.id.username_input);
        passwordInput = view.findViewById(R.id.password_input);

        view.findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (username.equals("jose") && password.equals("11")) {
                    // Credenciales válidas, navegamos a ListFragment
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_listFragment);
                } else {

                }
            }
        });

        view.findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
        return view;
    }
}