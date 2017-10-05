package OnlineNotebook.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Note {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@NotNull
private String title;
private String note;
private Date date;

public Note(){
	
}

public Note( String title, String note) {


	this.title = title;
	this.note = note;

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
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}



}
