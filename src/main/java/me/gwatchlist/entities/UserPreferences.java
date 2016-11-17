package me.gwatchlist.entities;

/**
 *
 * Created by giovanni on 21/09/16.
 */
public class UserPreferences {

    public static final String THEME_DARK_BLUE = "DARK_BLUE";
    public static final String THEME_DARK_PINK = "DARK_PINK";
    public static final String THEME_GRADIENT_BLUE = "GRADIENT._BLUE";
    public static final String THEME_GRADIENT_PINK = "GRADIENT_PINK";

    private boolean notifyOnMovieAdded;
    private boolean notifyOnListShared;
    private String theme;

    public boolean isNotifyOnMovieAdded() {
        return notifyOnMovieAdded;
    }

    public void setNotifyOnMovieAdded(boolean notifyOnMovieAdded) {
        this.notifyOnMovieAdded = notifyOnMovieAdded;
    }

    public boolean isNotifyOnListShared() {
        return notifyOnListShared;
    }

    public void setNotifyOnListShared(boolean notifyOnListShared) {
        this.notifyOnListShared = notifyOnListShared;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
