package teamdevops.mnnit.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import teamdevops.mnnit.R;
import teamdevops.mnnit.entities.TimeTableData;

/**
 * Created by king on 24-11-2015.
 */
public class DescriptionDialog extends DialogFragment {

    private EditText etSubject;
    private EditText etCode;
    private EditText etVenue;
    private EditText etInstructor;

    DialogCommunicator mDialogCommunicator;
    public interface DialogCommunicator {
        public void onDialogPositiveClick(DialogFragment dialog, TimeTableData data);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Instantiating the dialog communicator
        mDialogCommunicator = (DialogCommunicator) activity;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.description_dialog, null);

        etSubject = (EditText) view.findViewById(R.id.etSubject);
        etCode = (EditText) view.findViewById(R.id.etSubjectCode);
        etVenue = (EditText) view.findViewById(R.id.etVenue);
        etInstructor = (EditText) view.findViewById(R.id.etInstructor);

        builder.setView(view).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final TimeTableData data = new TimeTableData();
                data.setSubject(etSubject.getText().toString());
                data.setCode(etCode.getText().toString());
                data.setVenue(etVenue.getText().toString());
                data.setInstructor(etInstructor.getText().toString());
                mDialogCommunicator.onDialogPositiveClick(DescriptionDialog.this, data);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        return  builder.create();
    }
}

