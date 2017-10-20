package OnlineNotebook.OnlineNotebook;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import OnlineNotebook.OnlineNotebookApplication;
import OnlineNotebook.Model.Note;
import OnlineNotebook.Repository.NoteRepository;

import OnlineNotebook.Service.NoteServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=OnlineNotebookApplication.class)
public class NoteServiceTest {
	@Autowired
	private NoteRepository repository;
	@Autowired
	private NoteServiceImpl noteService;


	@Test
	public void shouldSaveNote() {
	Note note = new Note("title","note");
		
	noteService.saveNote(note);
	Note findById = repository.findById(1);
	
	assertNotNull(findById);
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Date date = new Date();
	
	assertEquals(findById.getDate(), dateFormat.format(date));
	
	assertEquals(findById.getNote(), "note");
	assertEquals(findById.getTitle(), "title");
	assertEquals(findById.getShortNote().trim(), "note");
	}
	
	public void shouldDeleteNote(){
		Note note = new Note("title","note");
		repository.save(note);
		noteService.deleteNote(1);
		Note findById = repository.findById(1);
		assertNull(findById);
	}

	public void shouldFindNoteById(){
		Note note = new Note("title","note");
		noteService.saveNote(note);
		Note findNoteById = noteService.findNoteById(1);
		assertNotNull(findNoteById);
		assertEquals(note.getTitle(), findNoteById.getTitle());
		assertEquals(note.getNote(), findNoteById.getNote());
	}
}
