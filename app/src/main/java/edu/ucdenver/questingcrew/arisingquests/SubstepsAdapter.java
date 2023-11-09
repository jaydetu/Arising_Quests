package edu.ucdenver.questingcrew.arisingquests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubstepsAdapter extends RecyclerView.Adapter<SubstepsAdapter.ListItemHolder>{

    private EditTaskActivity editTaskActivity;
    private ArrayList<Substep> substepList;

    public SubstepsAdapter (EditTaskActivity editTaskActivity, ArrayList<Substep> substepList){
        this.editTaskActivity = editTaskActivity;
        this.substepList = substepList;

    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.substeps_layout, parent, false);
        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Substep substep = substepList.get(position);
        holder.textViewSubstepName.setText(substep.getStep());
    }

    @Override
    public int getItemCount() {
        return substepList.size();

    }

    public void addItem(Substep substep) {
        substepList.add(substep);
        notifyItemInserted(substepList.indexOf(substep));
    }



    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewSubstepName;

        public ListItemHolder(View itemView) {
            super(itemView);
            textViewSubstepName = itemView.findViewById(R.id.textViewStepName);
            textViewSubstepName.setClickable(false);
            textViewSubstepName.setOnClickListener(this);
        }

        public void onClick(View view){


        }


    }
}
