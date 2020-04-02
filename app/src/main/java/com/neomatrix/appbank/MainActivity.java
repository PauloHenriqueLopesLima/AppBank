package com.neomatrix.appbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.neomatrix.appbank.adapter.StatementsAdapter;
import com.neomatrix.appbank.model.StatementList;

import com.neomatrix.appbank.model.StatementsResponse;
import com.neomatrix.appbank.model.UserAccount;
import com.neomatrix.appbank.service.RetrofitService;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private StatementsAdapter adapter;
    private TextView name;
    private TextView account;
    private TextView balance;
    private ImageButton exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name_textView);
        account = findViewById(R.id.conta_textView);
        balance = findViewById(R.id.saldo_textView);
        exit = findViewById(R.id.exit_imageButton);
        exit.setOnClickListener(view -> finish());

        receiveUser();
    }

    private void receiveUser() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        UserAccount userAccount = (UserAccount) Objects.requireNonNull(bundle).getSerializable("USER");
        name.setText(userAccount.getName());
        account.setText(userAccount.getBankAccount() + " / " + userAccount.getAgency());
        balance.setText("R$" + userAccount.getBalance());
        responseFromApi(userAccount);
    }

    private void responseFromApi(UserAccount userAccount) {


        Call<StatementsResponse> call = RetrofitService
                .getInstance()
                .getStatementsApi()
                .getStatements(userAccount.getUserId());
        call.enqueue(new Callback<StatementsResponse>() {
            @Override
            public void onResponse(Call<StatementsResponse> call, Response<StatementsResponse> response) {
                if (!response.isSuccessful()) {
                    System.out.println("codigo de resposta" + response.errorBody());
                }
                StatementsResponse lists = response.body();
                setUpRecyclerView(lists);
            }

            @Override
            public void onFailure(Call<StatementsResponse> call, Throwable t) {
                System.out.println("falhou em algum lugar");
                System.out.println(t.getMessage());
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

    private void setUpRecyclerView(StatementsResponse statements) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new StatementsAdapter();
        adapter.atualizarStatements(statements);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
