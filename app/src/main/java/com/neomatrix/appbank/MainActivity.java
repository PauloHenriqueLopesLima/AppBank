package com.neomatrix.appbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.neomatrix.appbank.adapter.StatementsAdapter;
import com.neomatrix.appbank.model.Statements;
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
        exit =findViewById(R.id.exit_imageButton);

        exit.setOnClickListener(view -> finish());



        receiveUser();

    }

    private void receiveUser() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        UserAccount userAccount = (UserAccount) Objects.requireNonNull(bundle).getSerializable("USER");

        System.out.println(userAccount.getName());
        name.setText(userAccount.getName());
        account.setText(userAccount.getBankAccount()+" / "+userAccount.getAgency());
        balance.setText("R$"+userAccount.getBalance());
        responseFromApi(userAccount);
    }

    private void responseFromApi(UserAccount userAccount) {
        Call<List<Statements>> call = RetrofitService.getInstance().getStatementsApi().getStatements(String.valueOf(userAccount.getUserId()));
        call.enqueue(new Callback<List<Statements>>() {
            @Override
            public void onResponse(Call<List<Statements>> call, Response<List<Statements>> response) {
                List<Statements> statements = response.body();
                if (statements != null) {
                    setUpRecyclerView(statements);
                }
            }



            @Override
            public void onFailure(Call<List<Statements>> call, Throwable t) {
            }
        });
    }


    private void setUpRecyclerView(List<Statements> statements) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new StatementsAdapter(statements);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
