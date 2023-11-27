package edu.ucdenver.questingcrew.arisingquests;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import edu.ucdenver.questingcrew.arisingquests.databinding.DialogSubstepBinding;
/*
 * SubstepDialog is a dialog fragment that controls adding a new substep and editing an existing substep.
 * SubstepDialog includes:
 *      - Toolbar menu with "Cancel" button
 *      - View to input Substep (text of what the step is)
 *      - "Clear" button to clear input
 *      - "Add" button to create/save new substep
 * @author Jayde Tu
 * @version 11302023
 */
public class SubstepDialog extends DialogFragment {

    private DialogSubstepBinding binding;
    private Substep step;
    private long taskID;
    private int position;
    private boolean editingStep;


    public SubstepDialog (Substep step, int position) {
        this.step = step;
        this.taskID = step.getTaskId();
        this.position = position;
        this.editingStep = true;
    }
    public SubstepDialog (long taskId) {
        this.taskID = taskId;
        this.editingStep = false;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogSubstepBinding.inflate(LayoutInflater.from (getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
        binding.substepToolbar.inflateMenu(R.menu.menu_substep);

        binding.substepToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_cancel){
                    clearStepClicked();
                    dismiss();
                }
                return false;
            }
        });

        binding.elevatedButtonSaveStep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addSubstepClicked(view);
            }
        });
        binding.elevatedButtonCancelStep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                clearStepClicked();
            }
        });

        if (step != null) {
            displayStep();
        }

        return builder.create();
    }

    private void clearStepClicked(){
        binding.textInputStep.setText("");
    }

    private void addSubstepClicked(View view){
        // create substep object
        String stepText = binding.textInputStep.getText().toString();
        Substep substep = new Substep(stepText);
        substep.setTaskId(taskID);
        //Log.i("info", "created step: " + substep.getStep() + " w/ task id: " + substep.getTaskId());
        // add or update substep in database
        MainActivity mainActivity = (MainActivity) getActivity();
        if (editingStep){
            mainActivity.passUpdateStep(substep, position);
            //Log.i("info", "updated step: " + substep.getStep() + " to database");
        } else {
            //Log.i("info", "adding step: " + substep.getStep() + " to database");
            mainActivity.passAddStep(substep);
            //Log.i("info", "added step: " + taskDatabase.substepDao().getSubstep(substep.getId()).getStep()+ " to database");
        }

        // need to dismass dialog frament and return to EditTaskActivity to save task
        dismiss();
    }

    public void displayStep(){
        binding.textInputStep.setText(step.getStep());
    }

}
