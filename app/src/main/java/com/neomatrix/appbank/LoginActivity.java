package com.neomatrix.appbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.neomatrix.appbank.model.UserAccount;
import com.neomatrix.appbank.model.UserAccountResponse;
import com.neomatrix.appbank.service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private TextInputEditText userLogin;
    private TextInputEditText userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLogin = findViewById(R.id.user_login);
        userPassword = findViewById(R.id.user_password);
        Button loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(view -> validateUser());
    }

    private void validateUser() {

        String user = userLogin.getEditableText().toString().trim();
        String password = userPassword.getEditableText().toString().trim();

        if (user.isEmpty()) {
            userLogin.setError("Digite seu email");
            userLogin.requestFocus();
            return;
        }
        // if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()){
        //     userLogin.setError("Digite um email valido");
        //     userLogin.requestFocus();
        //     return;
        //  }
        if (password.isEmpty()) {
            userPassword.setError("Digite sua senha");
            userPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            userPassword.setError("sua senha deve ter pelo menos '6' caracteres");
            userPassword.requestFocus();
            return;

        }

        responseFromApi(user, password);


    }

    private void responseFromApi(String user, String password) {
        Call<UserAccountResponse> call = RetrofitService.getInstance().getUserApi().getUser(user, password);
        call.enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                UserAccountResponse userAccountResponse = response.body();
                if (userAccountResponse != null) {

                    UserAccount userAccount = new UserAccount();
                    userAccount.setUserId(userAccountResponse.getUserAccount().getUserId());
                    userAccount.setName(userAccountResponse.getUserAccount().getName());
                    userAccount.setAgency(userAccountResponse.getUserAccount().getAgency());
                    userAccount.setBankAccount(userAccountResponse.getUserAccount().getBankAccount());
                    userAccount.setBalance(userAccountResponse.getUserAccount().getBalance());

                    sendUserToMainActivity(userAccount);


                }

            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {

            }
        });
    }


    public void sendUserToMainActivity(UserAccount userAccount) {

        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userAccount);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


}
