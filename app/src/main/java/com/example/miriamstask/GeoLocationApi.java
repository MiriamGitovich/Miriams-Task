package com.example.miriamstask;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//API 1: purchase a show with restriction.
//
//        The API should receive user id, show name and geolocation.
//
//        API implementation will check if this user is from Israel based on his location and block a purchase outside of Israel.
//
//        Response
//
//        Return the appropriate http status to the client and in case of error explain what the error was.


//API 2: Append restrictions for viewing the show.
//
//        This API should receive show id and geolocation viewing restriction (where it can be played).
//
//        Example: show with id “x” can be viewed only if the user is in geolocation “y”
//
//        API implementation will save the restriction to the DB.
//
//        API implementation will allow editing an existing restriction for a show.
//
//        API implementation will allow appending more than 1 restriction for each show.


//API 3: view the show you purchased with restriction.
//
//        The API should receive user id, show name and geolocation.
//
//        API implementation should allow view in case the user already purchased the show.
//
//        API implementation should allow view in case the user does not violate any restriction of this show.
//
//        API implementation should aggregate the geolocation from a local DB. If the geolocation does not exist in the DB, or your DB is not accessible, get the info from an external service and then try to store it in the DB again. (if it is accessible).
//
//        Response
//
//        Return the appropriate http status to the client and in case of error explain what the error was.


//
//        API 4: get the most popular views.
//
//        The API should return the most popular show and the number of times it was played.
//
//        Response
//
//        Return the appropriate http status to the client and in case of error explain what the error was.

public class GeoLocationApi {
    Context context;

    public GeoLocationApi(Context c){
        context = c;
    }

    //API 1: purchase a show with restriction.
    public boolean purchaseShow(long userId, String showName,Location geolocation){
        if(isGeolocationInIsrael(geolocation)){
            purchase(userId,  showName);
            return true;
        }
        return false;
    }

    private void purchase(final long userId, final String showName) {
        class purchaseTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Show show = DatabaseShow.getInstance(context).getAppDatabase().showDao().findByName(showName);

                UserShowCrossRef showsForUser = new UserShowCrossRef();
                showsForUser.setUid(userId);
                showsForUser.setShowId(show.getShowId());
                DatabaseShow.getInstance(context).getAppDatabase().showDao().insert(showsForUser);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("purchase","purchase "+showName);

            }
        }

        purchaseTask st = new purchaseTask();
        st.execute();
    }

    private boolean isGeolocationInIsrael( Location geolocation) {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(geolocation.getLatitude(), geolocation.getLongitude(), 1);
               // List<Address> addresses = geocoder.getFromLocation( 31.801442412428894, 35.209357795206095, 1);

                if (addresses.size() > 0)
                {
                    String countryName = addresses.get(0).getCountryName();
                    if(countryName.equalsIgnoreCase("Israel")){
                        return true;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        return false;
    }



    //API 2: Append restrictions for viewing the show.
    public void showRestriction(int showId, Location geolocation, boolean edit){
        saveRestriction(showId, geolocation.getLatitude(), geolocation.getLongitude(), edit );

    }

    private void saveRestriction(final int showId, final double latitude, final double longitude, final boolean edit) {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //Show show = new Show();
                //show.setShowId(showId);
                //
                //List<Show> showList = DatabaseShow.getInstance(context).getAppDatabase().showDao().loadShowById(showId);
                //if(!showList.isEmpty()){
                //    if (edit) {
                //        show = showList.get(0);
                //        GeoLocation geoLocation = new GeoLocation();
                //      //  geoLocation.setShowId(show.getShowId());
                //        geoLocation.setGeoRestrictionLatitude(latitude);
                //        geoLocation.setGeoRestrictionLongitude(longitude);
                //       // DatabaseShow.getInstance(context).getAppDatabase().showDao().upd(geoLocation);
                //        return null;
                //    }
                //}
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }



//API 3: view the show you purchased with restriction.
    public void viewShow(final int userId, String showName, Location geolocation){
        class getTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                int count = DatabaseShow.getInstance(context).getAppDatabase().showDao().showForUser(userId);
                if(count>0){
                    return "the user already purchased the show";
                }
                else{
                    return "not allowed";
                }

            }

            @Override
            protected void onPostExecute(String str) {
                Log.d("viewShow",str);

            }
        }

        getTask st = new getTask();
        st.execute();

    }



  //  API 4: get the most popular views.
    public void getMostPopularShowName(){
        class getTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                String showName = DatabaseShow.getInstance(context).getAppDatabase().showDao().loadMostPopularShowName();

                return showName;
            }

            @Override
            protected void onPostExecute(String string) {
                Log.d("getMostPopularShowName",string);
            }
        }

        getTask st = new getTask();
        st.execute();
    }

    public void getMostPopularShowCount(){
        class getTask extends AsyncTask<Void, Void, Integer> {

            @Override
            protected Integer doInBackground(Void... voids) {
                int count = DatabaseShow.getInstance(context).getAppDatabase().showDao().countMostPopularShow();

                return count;
            }

            @Override
            protected void onPostExecute(Integer count) {
                Log.d("getMostPopularShowCount",count+"");
            }
        }

        getTask st = new getTask();
        st.execute();
    }


    public void insertUser(final User user){
        class insertTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseShow.getInstance(context).getAppDatabase().showDao().insert(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("insert","insertUser");

            }
        }

        insertTask st = new insertTask();
        st.execute();
    }

    public void insertShow(final Show show){
        class insertTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseShow.getInstance(context).getAppDatabase().showDao().insert(show);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("insert","insertShow");

            }
        }

        insertTask st = new insertTask();
        st.execute();
    }
    public void insertUserShowCrossRef(final UserShowCrossRef showsForUser){
        class insertTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseShow.getInstance(context).getAppDatabase().showDao().insert(showsForUser);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("insert","insertShowForUser");

            }
        }

        insertTask st = new insertTask();
        st.execute();
    }
}
