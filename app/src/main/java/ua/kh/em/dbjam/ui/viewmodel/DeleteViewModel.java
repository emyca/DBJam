package ua.kh.em.dbjam.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import ua.kh.em.dbjam.data.model.Note;
import ua.kh.em.dbjam.data.repository.NoteRepository;

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
