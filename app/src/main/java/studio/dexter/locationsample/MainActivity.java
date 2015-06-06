package studio.dexter.locationsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {
    ImageView imgLocation;
    TextView tvLocation;

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

                break;
        }
    }
}
