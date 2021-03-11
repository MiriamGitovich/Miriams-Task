package com.example.miriamstask;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GeoLocationApi {
    Context context;
    private static final String LOG_TAG = "GeoLocationApi";


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
                Log.d(LOG_TAG,"purchase "+showName);

            }
        }

        purchaseTask st = new purchaseTask();
        st.execute();
    }

    private boolean isGeolocationInIsrael( Location geolocation) {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(geolocation.getLatitude(), geolocation.getLongitude(), 1);

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
    public void showRestriction(long showId, GeoLocation geolocation, boolean edit){
        saveRestriction(showId, geolocation, edit );

    }

    private void saveRestriction(final long showId, final GeoLocation geolocation, final boolean edit) {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Show show;
                List<Show> showList = DatabaseShow.getInstance(context).getAppDatabase().showDao().loadShowById(showId);
                if(!showList.isEmpty()){
                    GeoLocation geoLocation = new GeoLocation();
                    geoLocation.setGeoRestrictionLatitude(geolocation.getGeoRestrictionLatitude());
                    geoLocation.setGeoRestrictionLongitude(geolocation.getGeoRestrictionLongitude());
                    geoLocation.setShowGeoId(geolocation.getShowGeoId());
                    if(edit) {
                        show = showList.get(0);
                        DatabaseShow.getInstance(context).getAppDatabase().showDao().updateShow(show);
                    }else{
                        geoLocation.setShowId(showId);
                        DatabaseShow.getInstance(context).getAppDatabase().showDao().insert(geoLocation);
                    }
                    return null;
                }
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d(LOG_TAG,"saveRestriction ");

            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }



//API 3: view the show you purchased with restriction.
    public void viewShow(final long userId,final String showName,final Location geolocation){
        class getTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                //1
                boolean alreadyPurchased = false, notViolateRestriction = false;
                Show show = DatabaseShow.getInstance(context).getAppDatabase().showDao().findByName(showName);
                int count = DatabaseShow.getInstance(context).getAppDatabase().showDao().userHasShow(userId, show.getShowId());
                if(count > 0){
                    alreadyPurchased = true;
                }

                //2

                int count2 = DatabaseShow.getInstance(context).getAppDatabase().showDao().showHasLocation(show.getShowId(), geolocation.getLatitude(),geolocation.getLongitude());
                if(count2 > 0){
                    notViolateRestriction = true;
                }
                if(alreadyPurchased && notViolateRestriction){
                    return "allowed";
                }
                else{
                    return "not allowed";
                }

            }

            @Override
            protected void onPostExecute(String str) {
                Log.d(LOG_TAG,"viewShow " +userId+ " "+showName+ " "+str);

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
                Log.d(LOG_TAG," getMostPopularShowName"+string);
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
                Log.d(LOG_TAG,"getMostPopularShowCount "+count+"");
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
                Log.d(LOG_TAG,"insertUser");

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
                Log.d(LOG_TAG,"insertShow");

            }
        }

        insertTask st = new insertTask();
        st.execute();
    }
    public void insertGeoLocation(final GeoLocation geoLocation){
        class insertTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseShow.getInstance(context).getAppDatabase().showDao().insert(geoLocation);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d(LOG_TAG,"insertGeoLocation");

            }
        }

        insertTask st = new insertTask();
        st.execute();
    }
}
