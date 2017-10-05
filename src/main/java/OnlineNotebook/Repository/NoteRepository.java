package OnlineNotebook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import OnlineNotebook.Model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
	String findByTitle(String title);
	Note findById(int id);
	@Query("update Note n set n.title = ?2, n.note =?3 where n.id = ?1")
	public void update(Integer id, String title, String note );

}
