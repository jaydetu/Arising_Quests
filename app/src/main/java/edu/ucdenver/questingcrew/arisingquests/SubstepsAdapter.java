package edu.ucdenver.questingcrew.arisingquests;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 * SubstepsAdapter is the recycler adapter for the list of substeps in a task.
 * It shows each substep.
 * When the step is clicked, it allows you to edit the step (by calling SubstepDialog that's populated with existing details).
 * @author Jayde Tu
 * @version 11302023
 */
public class SubstepsAdapter extends RecyclerView.Adapter<SubstepsAdapter.ListItemHolder>{

    private MainActivity mainActivity;
    private Substep[] substepList;

    public SubstepsAdapter (MainActivity mainActivity, Substep[] substepList){
        this.mainActivity = mainActivity;
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
        if (substepList != null) {
            Substep substep = substepList[position];
            holder.textViewSubstepName.setText(substep.getStep());
        }
    }

    @Override
    public int getItemCount() {
        return substepList.length;
    }

    // get new substep list to update recycler
    public void updateItemList(Substep[] changedSubsteps){
        substepList = changedSubsteps;
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
            int position = getAdapterPosition();
            Substep substep = substepList[position];
            mainActivity.editStep(substep, position);
        }


    }
}
