package edu.ucdenver.questingcrew.arisingquests;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import edu.ucdenver.questingcrew.arisingquests.databinding.DialogSubstepBinding;

public class SubstepDialog extends DialogFragment {

    private DialogSubstepBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.i("info", "begin substep onCreateDialog");
        binding = DialogSubstepBinding.inflate(LayoutInflater.from (getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
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
        Log.i("info", "end of substep onCreateDialog");
        return builder.create();
    }

    private void clearStepClicked(){
        binding.textInputStep.setText("");
    }

    private void addSubstepClicked(View view){
        // create substep object
        String step = binding.textInputStep.getText().toString();
        Log.i("info", "step: " + step);
        Substep substep = new Substep(step);

        // pass susbtep object to addStep()
        EditTaskActivity editTaskActivity = (EditTaskActivity) getActivity();
        editTaskActivity.addStep(substep);

        // need to dismass dialog frament and return to EditTaskActivity to save task
        dismiss();
    }

}
