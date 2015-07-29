package com.gornovah.modern_art_ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.gornovah.modern_art_ui.R;
import com.gornovah.modern_art_ui.interfaces.AlertDialogActivity;

/**
 * Created by domingo.espinosa on 19/07/2015.
 */
public class MoreInformationDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.dialog_text).setNegativeButton(R.string.dialog_no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int id) {
                                ((AlertDialogActivity) getActivity())
                                        .doNegative();
                            }
                        })
                .setPositiveButton(R.string.dialog_yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int id) {
                                ((AlertDialogActivity) getActivity())
                                        .doPositive();
                            }

                        }).create();
    }
}
