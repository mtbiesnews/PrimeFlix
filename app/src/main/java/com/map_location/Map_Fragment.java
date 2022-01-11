package com.map_location;



import static com.map_location.Constants.MAPVIEW_BUNDLE_KEY;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rcloud.netflix.R;
import com.skyfishjy.library.RippleBackground;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Map_Fragment extends Fragment implements OnMapReadyCallback {

    private MapView mMapView;
    private GoogleMap MoveMap;
    private FusedLocationProviderClient mFusedLocationClient;
    //defining some widgets
//    private MaterialSearchBar searchBar;
    private EditText searchBar;
    private FloatingActionButton fab_search, fab_marker;
    private int searchBarToggle = 0;
    //finding places client for places api
    private PlacesClient placesClient;
    //finding ripple background
    private RippleBackground ripple_effect;
    private boolean admin = false;
    private boolean mapMarkFlag = false;
    private String area;
    private String address;
    private String pinCode;
    private Double pinLatitude, pinLongitude;
    private Bundle bundle;
    private String cus_latitude,cus_longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_, container, false);
        bundle = getArguments();


        mMapView = view.findViewById(R.id.mapView);
        fab_search = view.findViewById(R.id.fab_search);
        fab_marker = view.findViewById(R.id.fab_marker);
        ripple_effect = view.findViewById(R.id.ripple_effect);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
// searchbar task started

        Places.initialize(getContext(), "AIzaSyC7YwtlUEEQS6r5R-KCeERp6oUYVA19Nfk");
//        Places.initialize(getContext(),"AIzaSyBJUd_-MqjFOEG-BFjzSIxhdvlq3FsFb1c") ;
        getPlaces();

// search bar task finished

        initGoogleMap(savedInstanceState);

        return view;
    }

    private void markCusLocation(GoogleMap cusMap) {

        mapMarkFlag = true;
        pinLatitude = null;
        pinLongitude = null;

        LatLng latLng = new LatLng(Double.parseDouble(cus_latitude) , Double.parseDouble(cus_longitude));



        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(bitmapDescriptor(getContext(), R.drawable.custom_marker));
        markerOptions.position(latLng);
        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
        cusMap.clear();
        cusMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        cusMap.addMarker(markerOptions);


    }

    private void pinMarkerPoint(GoogleMap pinMap) {
        if (!admin) {


            pinMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull LatLng latLng) {
                    mapMarkFlag = true;
                    pinLatitude = null;
                    pinLongitude = null;

                    pinLatitude = latLng.latitude;
                    pinLongitude = latLng.longitude;


                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.icon(bitmapDescriptor(getContext(), R.drawable.custom_marker));
                    markerOptions.position(latLng);
                    markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                    pinMap.clear();
                    pinMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    pinMap.addMarker(markerOptions);


                    //getting mark info///////////////////////////////////////


                }
            });

            fab_marker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        if (mapMarkFlag) {

                            Geocoder geocoder;
                            List<Address> addresses = null;
                            geocoder = new Geocoder(getContext(), Locale.getDefault());
                            try {
                                addresses = geocoder.getFromLocation(pinLatitude, pinLongitude, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            pinCode = addresses.get(0).getPostalCode();
                            address = addresses.get(0).getAddressLine(0);


                            area = addresses.get(0).getSubLocality();
                            if (area == null) {
                                area = addresses.get(0).getLocality();
                            }

                            Intent intent = new Intent(getContext(), pinMarkInfo_Activity.class);
                            intent.putExtra("area", area);
                            intent.putExtra("address", address);
                            intent.putExtra("pinCode", pinCode);
                            intent.putExtra("latitude", pinLatitude.toString());
                            intent.putExtra("longitude", pinLongitude.toString());
                            getContext().startActivity(intent);
                            mapMarkFlag = false;
                            pinMap.clear();

                        } else {
                            Toast.makeText(getContext(), "Please long press on map to mark the location !", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }


    }

    //Creating Custom Marker///////////////////////////////////////////

    private BitmapDescriptor bitmapDescriptor(Context context, int iconId) {
        Drawable iconDrawable = ContextCompat.getDrawable(context, iconId);
        iconDrawable.setBounds(0, 0, iconDrawable.getIntrinsicWidth(), iconDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(iconDrawable.getIntrinsicWidth(), iconDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        iconDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }


    // search bar task method started //////////////////////

    private void getPlaces() {
        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);

                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList)
                        .build(getContext());

                startActivityForResult(intent, 100);

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == getActivity().RESULT_OK) {

            ripple_effect.startRippleAnimation();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ripple_effect.stopRippleAnimation();

                }
            }, 3000);

            Place place = Autocomplete.getPlaceFromIntent(data);
            LatLng latLng = place.getLatLng();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.icon(bitmapDescriptor(getContext(), R.drawable.custom_marker));
            markerOptions.position(latLng);
            markerOptions.title(place.getName());
            MoveMap.addMarker(markerOptions);
            float zoomLevel = 13.0f; //This goes up to 21
            MoveMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));

        } else if (requestCode == AutocompleteActivity.RESULT_ERROR) {
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    ///search bar task method finished//////////////////////////////////

    private void initGoogleMap(Bundle savedInstanceState) {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();


    }

    @Override
    public void onStart() {
        super.onStart();

//        if(user!=null){
//            if(user.getPhoneNumber().equals("6393567935")){
//                admin=true;
//            }else {
//                fab_marker.setVisibility(View.GONE);
//            }
//        }
        mMapView.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {


        MoveMap = map;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            return;
        }
        map.setMyLocationEnabled(true);


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    //For getting current user location with zoom

                    getLocation(map, task);
                    pinMarkerPoint(map);

                    if (bundle != null) {
                         cus_latitude = bundle.getString("cus_latitude");
                         cus_longitude = bundle.getString("cus_longitude");

                        markCusLocation(map);


                    }


                }

            }
        });


    }


    public void getLocation(GoogleMap map, Task<Location> task) {
        Location location = task.getResult();
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(bitmapDescriptor(getContext(), R.drawable.custom_marker));
        markerOptions.position(latLng);
        markerOptions.title("i am here");
        map.addMarker(markerOptions);
        float zoomLevel = 13.0f; //This goes up to 21
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));


    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


}