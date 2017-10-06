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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
	public void saveNote( Note note)  {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
List<String> list = Arrays.asList(note.getNote().split( " "));
	 String collect = list.stream().limit(3).collect(Collectors.joining( " "));
			repository.save(new Note(note.getTitle().trim(), note.getNote().trim(), dateFormat.format(date), collect));
	}

	@Override
	public void deleteNote(int id) {
		Note findNoteById = findNoteById(id);
		if(findNoteById!=null)
		repository.delete(repository.findById(id));
		

	}

	@Override
	public Note findNoteById(int id) {
		return repository.findById(id);
	}

	@Override
	public List<Note> findAllNote() {
		return repository.findAll();
	}

	@Override
	public void updateNote(Note note) {
		List<String> list = Arrays.asList(note.getNote().split(" "));
		 String collect = list.stream().limit(3).collect(Collectors.joining( " "));
		 Note findNoteById = findNoteById(note.getId());
		if(findNoteById!=null)
		 repository.update(note.getId(), note.getTitle(), note.getNote(),collect);

	}

}
