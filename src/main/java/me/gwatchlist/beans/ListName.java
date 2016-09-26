package me.gwatchlist.beans;

/**
 *
 * Created by giovanni on 24/09/16.
 */
public class ListName {

    private Long listId;
    private String listName;
    private boolean isShared;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }
}
