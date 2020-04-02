package com.neomatrix.appbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
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
    private MaterialButton loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLogin = findViewById(R.id.user_login);
        userPassword = findViewById(R.id.user_password);
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(view -> validateUserLogin());
    }

    private void validateUserLogin() {

        String user = userLogin.getEditableText().toString().trim();
        String password = userPassword.getEditableText().toString().trim();

        if (user.isEmpty()) {
            userLogin.setError(getString(R.string.digite_seu_email));
            userLogin.requestFocus();
            return;
        }

        if (!checkString(password)){
            userPassword.setError(getString(R.string.erro_senha));
            userPassword.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            userPassword.setError(getString(R.string.erro_senha_vazia));
            userPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            userPassword.setError(getString(R.string.erro_senha_tamanho));
            userPassword.requestFocus();
            return;

        }

        loginUserToApi(user, password);


    }

    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }

    private void loginUserToApi(String user, String password) {
        Call<UserAccountResponse> call = RetrofitService.getInstance().getUserApi().getUser(user, password);
        call.enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                UserAccountResponse userAccountResponse = response.body();
                if (userAccountResponse != null) {
                    Toast.makeText(LoginActivity.this, R.string.login_sucessfull, Toast.LENGTH_SHORT).show();
                    UserAccount userAccount = getUserAccount(userAccountResponse);

                    sendUserToAccountDetails(userAccount);
                }
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, R.string.login_error+":"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private UserAccount getUserAccount(UserAccountResponse userAccountResponse) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userAccountResponse.getUserAccount().getUserId());
        userAccount.setName(userAccountResponse.getUserAccount().getName());
        userAccount.setAgency(userAccountResponse.getUserAccount().getAgency());
        userAccount.setBankAccount(userAccountResponse.getUserAccount().getBankAccount());
        userAccount.setBalance(userAccountResponse.getUserAccount().getBalance());
        return userAccount;
    }

    public void sendUserToAccountDetails(UserAccount userAccount) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.USER), userAccount);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
