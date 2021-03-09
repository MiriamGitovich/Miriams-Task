package com.example.miriamstask;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

public class ShowsForUser {

    @Embedded
    public User user;

    @Relation(parentColumn = "uid", entityColumn = "showId", associateBy = @Junction(UserShowCrossRef.class))
    public List<Show> shows;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }



}
