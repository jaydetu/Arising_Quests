package edu.ucdenver.questingcrew.arisingquests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubstepsAdapter extends RecyclerView.Adapter<SubstepsAdapter.ListItemHolder>{

    private EditTaskActivity editTaskActivity;
    private ArrayList<Substep> list;

    public SubstepsAdapter (EditTaskActivity editTaskActivity, ArrayList<Substep> list){
        this.editTaskActivity = editTaskActivity;
        this.list = list;

    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.substeps_layout, parent, false);
        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Substep substep = list.get(position);
        holder.textViewName.setText(substep.getStep());
    }

    @Override
    public int getItemCount() {
        return list.size();

    }



    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewName;

        public ListItemHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textInputStep);
            textViewName.setClickable(true);
            textViewName.setOnClickListener(this);
        }

        public void onClick(View view){

        }
    }
}
