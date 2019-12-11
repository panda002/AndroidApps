package com.ownproj.firebase_demo;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewAccountFragment extends Fragment {
    EditText et_fullname;
    EditText et_email;
    EditText et_password;
    Button bt_signup;
    Button bt_cancel;

    SignupFragmentInterface mlistner;

    public NewAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_account, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mlistner = (SignupFragmentInterface) getActivity();

        et_fullname =  getActivity().findViewById(R.id.et_fullname_ca);
        et_email = getActivity().findViewById(R.id.et_email_ca);
        et_password = getActivity().findViewById(R.id.et_password_ca);
        bt_signup = getActivity().findViewById(R.id.bt_signup);
        bt_cancel = getActivity().findViewById(R.id.bt_cancel);

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = et_fullname.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if (fname.equals("")) {
                    Toast.makeText(getActivity(), "Enter full name", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(getActivity(), "Enter email", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(getActivity(), "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (!email.contains(".com")) {
                    Toast.makeText(getActivity(), "Enter valid email (xyz@xyx.com)", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(getActivity(), "Enter password", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(getActivity(), "Enter password with length > 6.", Toast.LENGTH_SHORT).show();
                } else {
                    mlistner.signUp(fname, email, password);
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistner.goToLogin();
            }
        });
    }

    public final static boolean isValidEmail(CharSequence emailString) {
        if (emailString == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(emailString).matches();
        }
    }

    public interface SignupFragmentInterface {
        void goToLogin();
        void signUp(String fullName, String email, String password);
    }
}
