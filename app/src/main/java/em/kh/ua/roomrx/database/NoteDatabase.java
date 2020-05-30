package em.kh.ua.roomrx.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Note.class,version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
    private static NoteDatabase INSTANCE;

    public static NoteDatabase getDatabase(Context context){
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "roomrx.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
