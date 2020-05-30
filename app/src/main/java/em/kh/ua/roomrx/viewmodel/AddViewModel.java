package em.kh.ua.roomrx.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import em.kh.ua.roomrx.database.Note;
import em.kh.ua.roomrx.repository.NoteRepository;

public class AddViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;

    public AddViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }

    public void addNote(Note note){
        noteRepository.addNote(note);
    }
}
