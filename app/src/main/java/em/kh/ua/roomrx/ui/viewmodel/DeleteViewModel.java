package em.kh.ua.roomrx.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import em.kh.ua.roomrx.data.model.Note;
import em.kh.ua.roomrx.data.repository.NoteRepository;

public class DeleteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;

    public DeleteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }

    public void deleteNote(Note note){
        noteRepository.deleteNote(note);
    }
}
