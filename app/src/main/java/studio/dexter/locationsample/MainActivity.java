package studio.dexter.locationsample;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import studio.dexter.tools.MLocationManager;


public class MainActivity extends Activity implements View.OnClickListener {
    ImageView imgLocation;
    TextView tvLocation, tvProvider;

    MLocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        imgLocation = (ImageView) findViewById(R.id.imgLocation);
        imgLocation.setOnClickListener(this);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvProvider = (TextView) findViewById(R.id.tvProvider);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLocation:
                getLocation();
                break;
        }
    }

    private void getLocation() {
        if (mLocationManager != null) mLocationManager.close();
        mLocationManager = new MLocationManager(this);
        if (mLocationManager.hasProvider()) {
            String location = mLocationManager.getLocation();
            if (TextUtils.isEmpty(location))
                location = getResources().getString(R.string.non_location);
            tvLocation.setText(location);
            tvProvider.setText(getResources().getString(R.string.provider) + ":" + mLocationManager.getProvider());
        } else {
            tvProvider.setText(getResources().getString(R.string.non_provider));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationManager != null) mLocationManager.close();
    }
}
