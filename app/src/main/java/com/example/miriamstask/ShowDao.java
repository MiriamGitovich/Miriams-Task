package com.example.miriamstask;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.room.Query;

@Dao  // Data Access Object
public interface ShowDao {
    @Query("SELECT * FROM shows")
    List<Show> getAll();

    @Query("SELECT * FROM shows WHERE showId IN (:showIds)")
    List<Show> loadAllByIds(int[] showIds);

    @Query("SELECT * FROM shows WHERE showId LIKE :showId")
    List<Show> loadShowById(long showId);

    @Transaction
    @Query("SELECT * from shows")
     List<ShowWithGeoLocation> loadShowWithGeoLocation();

    @Query("SELECT * FROM shows WHERE show_name LIKE :name LIMIT 1")
    Show findByName(String name);

    @Query("SELECT Count(showId) FROM UserShowCrossRef WHERE uid LIKE :userid")
    int showForUser(int userid);

    @Query("SELECT Count(showId) FROM UserShowCrossRef WHERE uid LIKE :user_id AND showId LIKE :show_id")
    int userHasShow(long user_id, long show_id);

    @Query("SELECT Count(showGeoId) FROM geolocations WHERE showId LIKE :show_id AND geo_restriction_latitude LIKE :latitude AND geo_restriction_longitude LIKE :longitude")
    int showHasLocation(long show_id, double latitude, double longitude);

    @Insert
    long insert(Show show);

    @Insert
    long insert(User user);

    @Insert
    long insert(GeoLocation geoLocation);

    @Insert
    long insert(UserShowCrossRef usershow);

    @Query("SELECT * FROM users")
    List<ShowsForUser> loadUserWithShows();

    @Query("SELECT show_name from UserShowCrossRef JOIN shows on shows.showId = UserShowCrossRef.showId GROUP BY UserShowCrossRef.showId ORDER BY COUNT(UserShowCrossRef.showId) DESC")
    String loadMostPopularShowName();

    @Query("SELECT Count(showId) from UserShowCrossRef GROUP BY showId ORDER BY COUNT(showId) DESC")
    int countMostPopularShow();


    @Transaction
    @Query("SELECT * FROM users")
    public List<ShowsForUser> getShowsForUsers();

    @Delete
    void delete(Show show);

    @Update
     void updateShow(Show... show);

}