package OnlineNotebook.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.InvalidNameException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OnlineNotebook.Model.Note;
import OnlineNotebook.Repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {

	private NoteRepository repository;

	@Autowired
	public NoteServiceImpl(NoteRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveNote(String title, String note) throws InvalidNameException {
		String findByTitle = repository.findByTitle(title);
		if (findByTitle != null)
			throw new InvalidNameException("Note with title " + title + " exsist");
		else
		{DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
List<String> list = Arrays.asList(note.split( " "));
	 String collect = list.stream().limit(3).collect(Collectors.joining( " "));
			repository.save(new Note(title, note, dateFormat.format(date), collect));
		}

	}

	@Override
	public void deleteNote(int id) {
		repository.delete(repository.findById(id));

	}

	@Override
	public Note findNoteById(int id) {
		return repository.findById(id);
	}

	@Override
	public List<Note> findAllNote() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void updateNote(Note note) {
		repository.update(note.getId(), note.getTitle(), note.getNote());

	}

}
