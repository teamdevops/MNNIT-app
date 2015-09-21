package teamdevops.mnnit.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
 * @author Deepankar
 */

public class MnnitMapsActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private GoogleMap map;
    private final LatLng LOCATION_MNNIT = new LatLng(25.494127, 81.866386);
    private UiSettings mapsettings;

    private String latitude[];
    private String longitude[];
    private String titles[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnnit_maps);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("MNNIT Maps");
        toolbar.setBackgroundColor(Color.BLACK);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();
        mapsettings = map.getUiSettings();
        latitude = getResources().getStringArray(R.array.latitudes);
        longitude = getResources().getStringArray(R.array.longitudes);
        titles = getResources().getStringArray(R.array.maptitles);
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
