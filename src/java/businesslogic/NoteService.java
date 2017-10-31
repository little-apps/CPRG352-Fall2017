package businesslogic;

import dataaccess.NoteDB;
import domainmodel.Note;
import java.util.Date;
import java.util.List;

public class NoteService {

    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(int noteId) throws Exception {
        return noteDB.getNote(noteId);
    }

    public List<Note> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(int noteId, Date dateCreated, String contents) throws Exception {
        return noteDB.update(new Note(noteId, dateCreated, contents));
    }

    public int delete(int noteId) throws Exception {
        return noteDB.delete(noteDB.getNote(noteId));
    }

    public int insert(Date dateCreated, String contents) throws Exception {
        Note note = new Note();
        
        note.setDateCreated(dateCreated);
        note.setContents(contents);
        
        return noteDB.insert(note);
    }
}
