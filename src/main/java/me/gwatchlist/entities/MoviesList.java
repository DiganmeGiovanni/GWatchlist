package me.gwatchlist.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by giovanni on 21/09/16.
 */
@Entity
public class MoviesList {

    @Id
    private Long id;

    @Index
    private String name;
    private Date createdAt;

    @Index
    private List<String> sharedWith = new ArrayList<String>();

    @Index
    private boolean isPersonalList;

    @Index
    private String ownerEmail;

    private List<Movie> movies = new ArrayList<Movie>();

    /**
     * Checks if a given email exists in {@link #sharedWith} list
     * @param email Email to search on {@link #sharedWith}
     * @return true if email is in {@link #sharedWith} | false otherwise
     */
    public boolean isSharedWith(String email) {
        for (String sharedWithEmail : sharedWith) {
            if (sharedWithEmail.compareTo(email) == 0) {
                return true;
            }
        }

        return false;
    }

    ////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<String> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public boolean isPersonalList() {
        return isPersonalList;
    }

    public void setPersonalList(boolean personalList) {
        isPersonalList = personalList;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
