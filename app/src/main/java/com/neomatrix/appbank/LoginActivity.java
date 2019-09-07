package com.neomatrix.appbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.neomatrix.appbank.model.UserAccountResponse;
import com.neomatrix.appbank.service.api.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText userLogin;
    private TextInputEditText userPassword;
    private Button loginBtn;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLogin = findViewById(R.id.user_login);
        userPassword = findViewById(R.id.user_password);
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser();
            }
        });
    }

    private void validateUser(){

        String user = userLogin.getEditableText().toString().trim();
        String password = userPassword.getEditableText().toString().trim();

        if (user.isEmpty()){
            userLogin.setError("Digite seu email");
            userLogin.requestFocus();
            return;
        }
     // if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()){
     //     userLogin.setError("Digite um email valido");
     //     userLogin.requestFocus();
     //     return;
      //  }
        if (password.isEmpty()){
            userPassword.setError("Digite sua senha");
            userPassword.requestFocus();
            return;
        }
        if (password.length() <6){
            userPassword.setError("sua senha deve ter pelo menos '6' caracteres");
            userPassword.requestFocus();
            return;

        }

        receberDados(user,password);




   }







    private void receberDados(String user, String password)
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .build();

        UserApi service = retrofit.create(UserApi.class);
        Call<UserAccountResponse> call = service.getUser(user,password);
        call.enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                if (response.isSuccessful()) {
                    UserAccountResponse resposta = response.body();
                } else {
                }
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {

            }



        });

}}
