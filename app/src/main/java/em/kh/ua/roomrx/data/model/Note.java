package em.kh.ua.roomrx.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes")
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int noteId;

    @ColumnInfo(name = "name")
    private String noteName;

    @ColumnInfo(name = "content")
    private String noteContent;

    public Note() {

    }

    public Note(String noteName, String noteContent) {
        this.noteName = noteName;
        this.noteContent = noteContent;
    }

    public Note(int noteId, String noteName, String noteContent) {
        this.noteId = noteId;
        this.noteName = noteName;
        this.noteContent = noteContent;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    protected Note(Parcel in) {
        noteId = in.readInt();
        noteName = in.readString();
        noteContent = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(noteId);
        dest.writeString(noteName);
        dest.writeString(noteContent);
    }
}
