package com.android.myjacbuddy.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.myjacbuddy.R;
import com.android.myjacbuddy.model.QuestionModel;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private ArrayList<QuestionModel> mList ;

    public QuestionAdapter(ArrayList<QuestionModel> list) {
        mList = list;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_list_item,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        QuestionModel model = mList.get(position);
        holder.name.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
