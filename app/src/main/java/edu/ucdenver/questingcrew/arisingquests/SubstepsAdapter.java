package edu.ucdenver.questingcrew.arisingquests;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PublicKey;

/*
 * SubstepsAdapter is the recycler adapter for the list of substeps in a task.
 * It shows each substep.
 * SubstepAdapter supports both only viewing substeps and being able to view and edit the substeps.
 * The "mode" is determined by which constructor is called when creating the recycler.
 * When the step is clicked, it allows you to edit the step (by calling SubstepDialog that's populated with existing details).
 * @author Jayde Tu
 * @version 11302023
 */
public class SubstepsAdapter extends RecyclerView.Adapter<SubstepsAdapter.ListItemHolder>{

    private MainActivity mainActivity;
    private Substep[] substepList;
    private boolean viewOnly;

    // Constructor for substep list that enables editing steps
    public SubstepsAdapter (MainActivity mainActivity, Substep[] substepList){
        this.mainActivity = mainActivity;
        this.substepList = substepList;
        viewOnly = false;
    }

    // Constructor for only viewing substep list (pass field viewOnly as true)
    public SubstepsAdapter (MainActivity mainActivity, Substep[] substepList, boolean viewOnly){
        this.mainActivity = mainActivity;
        this.substepList = substepList;
        this.viewOnly = viewOnly;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.substeps_layout, parent, false);
        return new ListItemHolder(listItem, viewOnly);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Substep substep = substepList[position];
        holder.textViewSubstepName.setText(substep.getStep());
    }

    @Override
    public int getItemCount() {
        return substepList.length;
    }

    // get new substep list to update recycler
    public void updateItemList(Substep[] changedSubsteps){
        substepList = changedSubsteps;
        Log.i("info", "adapter list length " + substepList.length);
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewSubstepName;
        private boolean viewOnly;

        public ListItemHolder(View itemView, boolean viewOnly) {
            super(itemView);
            this.viewOnly = viewOnly;
            textViewSubstepName = itemView.findViewById(R.id.textViewStepName);
            textViewSubstepName.setClickable(false);
            textViewSubstepName.setOnClickListener(this);
        }

        public void onClick(View view) {
            // check if only viewing substeps list or adding/editing substeps
            if (!viewOnly) {
                int position = getAdapterPosition();
                Substep substep = substepList[position];
                mainActivity.editStep(substep, position);
            }
        }
    }
}