package com.example.quizappcomplete.DataAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizappcomplete.Model.Question;
import com.example.quizappcomplete.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfQuestionListAdapter extends RecyclerView.Adapter<ProfQuestionListAdapter.ViewHolder>{

    private ArrayList<Question> mQuestionArrayList;
    private Context mContext;
    private String quizId;

    public ProfQuestionListAdapter(ArrayList<Question> questionArrayList, Context context, String quizId) {
        mQuestionArrayList = questionArrayList;
        mContext = context;
        this.quizId = quizId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.question_list_item, parent, false);
        ProfQuestionListAdapter.ViewHolder viewHolder = new ProfQuestionListAdapter.ViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Question question = mQuestionArrayList.get (position);
        holder.question.setText (question.getQuestion ());
        holder.option1.setText (question.getOption1 ());
        holder.option2.setText (question.getOption2 ());
        holder.option3.setText (question.getOption3 ());
        holder.option4.setText (question.getOption4 ());
        String answer=question.getAnswer ();

        if(answer.equals (question.getOption1 ()))
            holder.option1.setBackgroundResource (R.drawable.option_correct);
        else if(answer.equals (question.getOption2 ()))
            holder.option2.setBackgroundResource (R.drawable.option_correct);
        else if(answer.equals (question.getOption3 ()))
            holder.option3.setBackgroundResource (R.drawable.option_correct);
        else if(answer.equals (question.getOption4 ()))
            holder.option4.setBackgroundResource (R.drawable.option_correct);

        holder.delete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DatabaseReference mReference = FirebaseDatabase.getInstance ().getReference ("Question/"+quizId+"/"+question.getQuestionno ());
                mReference.removeValue ();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestionArrayList.size ();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView question;
        public Button option1, option2, option3, option4, delete;
        public ViewHolder(View itemView) {
            super(itemView);

            question = itemView.findViewById (R.id.tvQuestion_listitem);
            option1 = itemView.findViewById (R.id.btnOption1_listitem);
            option2 = itemView.findViewById (R.id.btnOption2_listitem);
            option3 = itemView.findViewById (R.id.btnOption3_listitem);
            option4 = itemView.findViewById (R.id.btnOption4_listitem);
            delete = itemView.findViewById (R.id.btnQuestionDelete);
        }
    }
}
