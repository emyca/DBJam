package em.kh.ua.roomrx.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import em.kh.ua.roomrx.data.repository.NoteRepository;
import em.kh.ua.roomrx.data.model.Note;
import io.reactivex.Flowable;

public class MainViewModel extends AndroidViewModel {

    private Flowable<List<Note>> noteList;
    private NoteRepository noteRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }

    public Flowable<List<Note>> fetchNotes(){
        noteList = noteRepository.fetchNotes();
        return noteList;
    }

    public Flowable<List<Note>> fetchNotes(String search){
        noteList = noteRepository.fetchNotes(search);
        return noteList;
    }
}
