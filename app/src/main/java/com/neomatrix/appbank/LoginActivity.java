package com.neomatrix.appbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.neomatrix.appbank.model.UserAccountResponse;
import com.neomatrix.appbank.service.RetrofitService;
import com.neomatrix.appbank.service.api.UserApi;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Call<UserAccountResponse> call = RetrofitService.getInstance().getUserApi().getUser(user,password);
        call.enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                UserAccountResponse userAccountResponse = response.body();
                if (userAccountResponse != null) {
                    System.out.println("nome do usuario: "+userAccountResponse.getUserAccount().getName());
                    System.out.println("nome agencia: "+userAccountResponse.getUserAccount().getAgency());
                    System.out.println("saldo bancario: "+userAccountResponse.getUserAccount().getBalance());
                    System.out.println("conta bancaria: "+userAccountResponse.getUserAccount().getBankAccount());
                }else {}

            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {

            }
        });




   }







    private void receberDados(String user, String password)
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi service = retrofit.create(UserApi.class);
        Call<UserAccountResponse> call = service.getUser(user,password);
        call.enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                if (response.isSuccessful()) {
                    UserAccountResponse resposta = response.body();
                    Toast.makeText(LoginActivity.this, resposta.getUserAccount()+": conta do usuario", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(LoginActivity.this, "nao retornou nada", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "nao retornou nada"+t.getMessage()+call.toString(), Toast.LENGTH_SHORT).show();

            }



        });

}}
