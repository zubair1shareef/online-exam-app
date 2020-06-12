package com.example.quizappcomplete.DataAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quizappcomplete.Model.QuizInfo;
import com.example.quizappcomplete.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder>{

    private ArrayList<QuizInfo> mQuizArrayList;
    private ArrayList<String> mQuizIdList;
    private Context mContext;
    private Class nextActitivty;

    public QuizListAdapter(ArrayList<QuizInfo> quizArrayList, ArrayList<String> quizIdList, Context context, Class nextActitivty) {
        mQuizArrayList = quizArrayList;
        mQuizIdList = quizIdList;
        mContext = context;
        this.nextActitivty = nextActitivty;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.quiz_list_item, parent, false);
        QuizListAdapter.ViewHolder viewHolder = new QuizListAdapter.ViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final QuizInfo quiz = mQuizArrayList.get (position);
        holder.quizTitle.setText (quiz.getTitle ());
        holder.quizDate.setText (quiz.getDate ());
        holder.quizTime.setText (quiz.getTime ());
        holder.card.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent (mContext,nextActitivty);
                intent.putExtra ("quizInfo",quiz);
                intent.putExtra ("quizId",mQuizIdList.get (position));
                mContext.startActivity (intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mQuizArrayList.size ();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView quizTitle, quizDate, quizTime;
        public CardView card;
        public ViewHolder(View itemView) {
            super(itemView);

            card = itemView.findViewById (R.id.card_quiz_item);
            quizDate = itemView.findViewById (R.id.tvQuizDate_item);
            quizTime = itemView.findViewById (R.id.tvQuizTime_item);
            quizTitle = itemView.findViewById (R.id.tvQuizTitle_item);
        }
    }
}
