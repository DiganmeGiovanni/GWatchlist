package me.gwatchlist.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

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

    private String name;
    private String emailOwner;
    private Date createdAt;
    private List<String> sharedWith;
    private boolean isPersonalList;
}
