package com.example.quizappcomplete.DataAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quizappcomplete.Model.QuizInfo;
import com.example.quizappcomplete.Model.Result;
import com.example.quizappcomplete.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder>{

    List<Result> mResults;

    public ResultListAdapter(List<Result> results) {
        mResults = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.result_list_item, parent, false);
        ResultListAdapter.ViewHolder viewHolder = new ResultListAdapter.ViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Result result = mResults.get (position);
        holder.correct.setText (result.getCorrect ());
        holder.wrong.setText (result.getWrong ());
        holder.marks.setText (result.getMarksObtained ());
        holder.name.setText (result.getName ());

    }

    @Override
    public int getItemCount() {
        return mResults.size ();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView correct, wrong, marks,name;
        public CardView card;
        public ViewHolder(View itemView) {
            super(itemView);

            card = itemView.findViewById (R.id.card_result_item);
            correct = itemView.findViewById (R.id.tvCorrectAns_reuslt);
            wrong = itemView.findViewById (R.id.tvWrongAns_Result);
            marks = itemView.findViewById (R.id.tvMarks_result);
            name  = itemView.findViewById (R.id.tvName_result);
        }
    }
}
