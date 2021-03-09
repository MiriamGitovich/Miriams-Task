package com.example.miriamstask;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.room.Query;

@Dao
public interface ShowDao {
    @Query("SELECT * FROM shows")
    List<Show> getAll();

    @Query("SELECT * FROM shows WHERE showId IN (:showIds)")
    List<Show> loadAllByIds(int[] showIds);

    @Query("SELECT * FROM shows WHERE showId LIKE :showId")
    List<Show> loadShowById(int showId);

    @Query("SELECT * from shows")
     List<ShowWithGeoLocation> loadShowWithGeoLocation();

    @Query("SELECT * FROM shows WHERE show_name LIKE :name LIMIT 1")
    Show findByName(String name);

    @Query("SELECT Count(showId) FROM UserShowCrossRef WHERE uid LIKE :userid")
    int showForUser(int userid);

    @Insert
    long insert(Show show);

    @Insert
    long insert(GeoLocation geoLocation);

    @Insert
    long insert(ShowsForUser show);

    @Query("SELECT * FROM users")
    List<ShowsForUser> loadUserWithShows();

    @Query("SELECT show_name from UserShowCrossRef JOIN shows on shows.showId = UserShowCrossRef.showId GROUP BY showId ORDER BY COUNT(showId) DESC")
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

    //@Transaction
    //public void insert(Show show, List<GeoLocation> geoLocationList) {
    //
    //    // Save rowId of inserted CompanyEntity as companyId
    //    final long showId = insert(show);
    //
    //    // Set companyId for all related employeeEntities
    //    for (GeoLocation geoLocation : geoLocationList) {
    //        geoLocation.setShowId(showId);
    //        insert(geoLocation);
    //    }
    //
    //}
}