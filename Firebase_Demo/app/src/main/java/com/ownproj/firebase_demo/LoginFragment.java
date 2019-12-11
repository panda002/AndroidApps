package com.ownproj.firebase_demo;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    EditText et_email;
    EditText et_password;
    Button bt_login;
    Button bt_createacc;
    String email = "";
    String password = "";

    LoginFragmentInterface mlistner;

    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mlistner = (LoginFragmentInterface) getActivity();

        et_email = getActivity().findViewById(R.id.et_email_login);
        et_password = getActivity().findViewById(R.id.et_password_login);

        bt_login = getActivity().findViewById(R.id.bt_login);
        bt_createacc = getActivity().findViewById(R.id.button_create_new_account);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_email.getText().toString();
                password = et_password.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    mlistner.signIn(email, password);
                }
            }
        });

        bt_createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistner.goToCreateAccount();
            }
        });
    }

    public interface LoginFragmentInterface {
        void goToCreateAccount();

        void signIn(String email, String password);
    }
}
