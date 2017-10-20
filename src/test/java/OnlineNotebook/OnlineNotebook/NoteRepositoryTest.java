package OnlineNotebook.OnlineNotebook;


import static org.junit.Assert.*;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import OnlineNotebook.OnlineNotebookApplication;
import OnlineNotebook.Model.Note;
import OnlineNotebook.Repository.NoteRepository;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=OnlineNotebookApplication.class)
@DataJpaTest
public class NoteRepositoryTest {

	@Autowired
	private NoteRepository repository;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void shouldReturnNoteIfFindById() {
		Note note = new Note("title", "note");
		entityManager.persist(note);
		entityManager.flush();
		
		Note findById = repository.findById(1);
		assertNotNull(findById);
		assertEquals(findById, note);

	}
	
	@Test
	public void shouldReturnNoteIfFindByTitle() {
		Note note = new Note("title", "note");
		entityManager.persist(note);
		entityManager.flush();
		
	Note updateNote = repository.findByTitle("title");
		assertNotNull(updateNote);
		assertEquals(updateNote.getTitle(), note.getTitle());

	}

	


	

}
