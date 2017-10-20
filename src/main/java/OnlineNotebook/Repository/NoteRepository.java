package OnlineNotebook.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import OnlineNotebook.Model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
	Note findByTitle(String title);
	Note findById(int id);
	@Transactional
	@Modifying
	@Query("update Note n set n.title = ?2, n.note =?3, n.shortNote=?4 where n.id = ?1")
	public void update(Integer id, String title, String note, String shortNote );

}
