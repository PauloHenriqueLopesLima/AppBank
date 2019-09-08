package com.neomatrix.appbank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neomatrix.appbank.R;
import com.neomatrix.appbank.model.StatementList;
import com.neomatrix.appbank.model.StatementsResponse;


import java.util.ArrayList;
import java.util.List;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.ViewHolder> {


    private StatementsResponse statementsList;

    public void atualizarStatements (StatementsResponse list){
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
        StatementList statementList = statementsList.getStatementList().get(position);
        holder.bind(statementList);


    }

    @Override
    public int getItemCount() {
        return statementsList.getStatementList().size();
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

        public void bind(StatementList statementList) {
            titulo.setText(statementList.getTitle());
            data.setText((CharSequence) statementList.getDate());
            descricao.setText(statementList.getDesc());
            //valor.setText( statementList.getValue());

        }
    }
}
