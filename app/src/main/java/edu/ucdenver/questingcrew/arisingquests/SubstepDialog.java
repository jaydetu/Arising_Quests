package edu.ucdenver.questingcrew.arisingquests;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import edu.ucdenver.questingcrew.arisingquests.databinding.DialogSubstepBinding;

public class SubstepDialog extends DialogFragment {

    private DialogSubstepBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DialogSubstepBinding.inflate(LayoutInflater.from (getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

        //return super.onCreateDialog(savedInstanceState);
        return builder.create();
    }

    private void clearStepClicked(){
        binding.textInputStep.setText("");

    }

    private void addSubstepClicked(){
        String step = binding.textInputStep.getText().toString();
        Substep substep = new Substep(step);
        EditTaskActivity editTaskActivity = (EditTaskActivity) getActivity();
        editTaskActivity.addStep(substep);
        dismiss();
    }
}
