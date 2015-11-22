package teamdevops.mnnit.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import teamdevops.mnnit.R;

/**
 * Activity showing MNNIT Maps with pin points of the locations and function to start navigation
 *
 * @author Deepankar
 */

public class MnnitMapsActivity extends AppCompatActivity {

    private final LatLng LOCATION_MNNIT = new LatLng(25.494127, 81.866386);
    private final String ACTIONBAR_TITLE = "MNNIT Maps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnnit_maps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_maps);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ACTIONBAR_TITLE);
        actionBar.setDisplayHomeAsUpEnabled(true);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        GoogleMap map = mapFragment.getMap();
        UiSettings mapsettings = map.getUiSettings();

        String latitude[]  = getResources().getStringArray(R.array.latitudes);
        String longitude[] = getResources().getStringArray(R.array.longitudes);
        String titles[] = getResources().getStringArray(R.array.maptitles);

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapsettings.setCompassEnabled(true);
        mapsettings.setMyLocationButtonEnabled(true);
        mapsettings.setZoomControlsEnabled(true);
        mapsettings.setMapToolbarEnabled(true);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_MNNIT, 16);
        map.animateCamera(update);
        for(int i=0;i<titles.length;i++)
        {
            float mLatitude = Float.parseFloat(latitude[i]);
            float mLongitude = Float.parseFloat(longitude[i]);
            LatLng coordinates = new LatLng(mLatitude,mLongitude);
            map.addMarker(new MarkerOptions().position(coordinates).title(titles[i]));
        }
    }
}
