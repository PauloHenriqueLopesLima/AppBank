package com.neomatrix.appbank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neomatrix.appbank.R;
import com.neomatrix.appbank.model.Statements;

import java.util.ArrayList;
import java.util.List;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.ViewHolder> {

    private List<Statements> statementsList = new ArrayList<>();



    public void atualizarStatements (List<Statements> list){
        this.statementsList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statements_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Statements statements = statementsList.get(position);
        holder.bind(statements);


    }

    @Override
    public int getItemCount() {
        return statementsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView data;
        TextView descricao;
        TextView valor;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.title_textView);
            data = itemView.findViewById(R.id.date_textView);
            descricao = itemView.findViewById(R.id.desc_textView);
            valor = itemView.findViewById(R.id.value_textView);
        }

        public void bind(Statements statements) {
            titulo.setText(statements.getTitle());
            data.setText((CharSequence) statements.getDate());
            descricao.setText(statements.getDesc());
            valor.setText((int) statements.getValue());

        }
    }
}
