package OnlineNotebook.Model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Note {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@NotEmpty(message="Title can not be empty")

private String title;
private String note;
private String shortNote;
private String date;

public Note(){
	
}

public Note( String title, String note) {
	this.title = title;
	this.note = note;
}


public Note(String title, String note, String date, String shortNote) {
	this.shortNote=shortNote;
	this.title = title;
	this.note = note;
	this.date = date;
}


public String getShortNote() {
	return shortNote;
}

public void setShortNote(String shortNote) {
	this.shortNote = shortNote;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

@Override
public String toString() {
	return "Note [id=" + id + ", title=" + title + ", note=" + note + ", date=" + date + "]";
}



}
