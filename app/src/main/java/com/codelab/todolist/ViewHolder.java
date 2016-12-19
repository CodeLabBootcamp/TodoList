package com.codelab.todolist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codelab.todolist.Utilities.Consts;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.is_done)
    CheckBox isDone;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;

    public ViewHolder(View v) {
        super(v);

        // THE RIGHT WAY
        ButterKnife.bind(this, v);

        // on check listener to update the model
        isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, NewTaskActivity.class);
//                intent.putExtra(Consts.POSITION, getLayoutPosition());
//                context.startActivity(intent);
            }
        });


    }

}
