package com.riddlefox.greeting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="entries")
public class Entry {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String title;
	private String link; 
	
	protected Entry() {}
	
    public Entry(String title, String link) {
        this.title = title;
        this.link = link;
    }

	public String getTitle() {
		return title;
	}

    @Override
    public String toString() {
        return String.format(
                "Entry[id=%d, firstName='%s', lastName='%s']",
                id, title, link);
    }	
}
