package ua.kh.em.dbjam.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import ua.kh.em.dbjam.data.model.Note;
import ua.kh.em.dbjam.data.repository.NoteRepository;

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
