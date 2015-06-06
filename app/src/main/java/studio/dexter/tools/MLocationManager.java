package studio.dexter.tools;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by dexter on 2015/6/6.
 */
public class MLocationManager implements LocationListener {
    Activity activity;
    LocationManager locationManager;
    String provider;

    public MLocationManager(Activity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), true);
    }

    public String getLocation() {
        MLog.i(this, "dex getLocation......");
        locationManager.requestLocationUpdates(provider, 0, 0, this);
        checkProvider();
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null) return null;
        String lat = String.valueOf(location.getLatitude());
        String lng = String.valueOf(location.getLongitude());
        return lat + ", " + lng;
    }

    private void checkProvider() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            MLog.v(this, "dex GPS_PROVIDER is true.");
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            MLog.v(this, "dex NETWORK_PROVIDER is true.");
        if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER))
            MLog.v(this, "dex PASSIVE_PROVIDER is true.");

    }

    public boolean hasProvider() {
        if (TextUtils.isEmpty(provider)) return false;
        return true;
    }

    public boolean hasLocation(Location location) {
        if (location == null) return false;
        return true;
    }

    public String getProvider() {
        return provider;
    }

    /**
     * Called when the location has changed.
     * <p/>
     * <p> There are no restrictions on the use of the supplied Location object.
     *
     * @param location The new location, as a Location object.
     */
    @Override
    public void onLocationChanged(Location location) {
        MLog.i(this, "dex onLocationChanged (has location=" + hasLocation(location) + ")");
    }

    /**
     * Called when the provider status changes. This method is called when
     * a provider is unable to fetch a location or if the provider has recently
     * become available after a period of unavailability.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     * @param status   {@link LocationProvider#OUT_OF_SERVICE} if the
     *                 provider is out of service, and this is not expected to change in the
     *                 near future; {@link LocationProvider#TEMPORARILY_UNAVAILABLE} if
     *                 the provider is temporarily unavailable but is expected to be available
     *                 shortly; and {@link LocationProvider#AVAILABLE} if the
     *                 provider is currently available.
     * @param extras   an optional Bundle which will contain provider specific
     *                 status variables.
     *                 <p/>
     *                 <p> A number of common key/value pairs for the extras Bundle are listed
     *                 below. Providers that use any of the keys on this list must
     *                 provide the corresponding value as described below.
     *                 <p/>
     *                 <ul>
     *                 <li> satellites - the number of satellites used to derive the fix
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        MLog.i(this, "dex onStatusChanged (provider=" + provider + "/status=" + status + ")");
    }

    /**
     * Called when the provider is enabled by the user.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     */
    @Override
    public void onProviderEnabled(String provider) {
        MLog.i(this, "dex onProviderEnabled (provider=" + provider + ")");
    }

    /**
     * Called when the provider is disabled by the user. If requestLocationUpdates
     * is called on an already disabled provider, this method is called
     * immediately.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     */
    @Override
    public void onProviderDisabled(String provider) {
        MLog.i(this, "dex onProviderDisabled (provider=" + provider + ")");
    }

    public void close() {
        locationManager.removeUpdates(this);
    }
}
