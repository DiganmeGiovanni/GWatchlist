package me.gwatchlist.beans;

import me.gwatchlist.entities.MoviesList;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by giovanni on 24/09/16.
 */
public class ListsNames {

    private String ownerEmail;
    private List<ListName> listsNames;

    public ListsNames(String ownerEmail) {
        this.ownerEmail = ownerEmail;
        listsNames = new ArrayList<ListName>();
    }

    public void addMoviesLists(List<MoviesList> moviesLists) {
        for (MoviesList moviesList : moviesLists) {

            ListName listName = new ListName();
            listName.setListId(moviesList.getId());
            listName.setListName(moviesList.getName());
            listName.setShared(moviesList.getOwnerEmail().compareTo(this.ownerEmail) != 0);
            this.listsNames.add(listName);
        }
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public List<ListName> getListsNames() {
        return listsNames;
    }

}
