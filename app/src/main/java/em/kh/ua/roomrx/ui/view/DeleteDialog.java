package em.kh.ua.roomrx.ui.view;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import em.kh.ua.roomrx.R;
import em.kh.ua.roomrx.data.model.Note;
import em.kh.ua.roomrx.ui.viewmodel.DeleteViewModel;


public class DeleteDialog extends DialogFragment {

    private DeleteViewModel deleteViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.dialog_del_title)
                .setMessage(R.string.dialog_del_text)
                .setPositiveButton(R.string.dialog_ok, (dialog, id) -> {
                    Bundle bundle = getArguments();
                    if (bundle != null) {
                        Note note = bundle.getParcelable("note_bundle");
                        deleteViewModel = new ViewModelProvider(requireActivity())
                                .get(DeleteViewModel.class);
                        deleteViewModel.deleteNote(note);
                        Toast.makeText(requireActivity(),R.string.toast_delete,
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, (dialog, id) ->
                        Toast.makeText(requireActivity(),R.string.toast_cancel,
                                Toast.LENGTH_SHORT).show());
        return builder.create();
    }
}
