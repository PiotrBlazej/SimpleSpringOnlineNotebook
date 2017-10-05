package OnlineNotebook.Service;

import java.util.List;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Service;

import OnlineNotebook.Model.Note;

@Service
public interface NoteService {
	public void saveNote(String title, String note) throws InvalidNameException;
	public void deleteNote(int id);
	public Note findNoteById(int id);
	public List<Note> findAllNote();
	public void updateNote(Note note);
	

}
