package em.kh.ua.roomrx.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import em.kh.ua.roomrx.R;
import em.kh.ua.roomrx.database.Note;
import em.kh.ua.roomrx.viewmodel.DeleteViewModel;


public class DeleteDialog extends DialogFragment {

    private DeleteViewModel deleteViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.dialog_del_title)
                .setMessage(R.string.dialog_del_text)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Bundle bundle = getArguments();
                        if (bundle != null) {
                            Note note = bundle.getParcelable("note_bundle");
                            deleteViewModel = new ViewModelProvider(requireActivity())
                                    .get(DeleteViewModel.class);
                            deleteViewModel.deleteNote(note);
                            Toast.makeText(requireActivity(),R.string.toast_delete,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(requireActivity(),R.string.toast_cancel, Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
