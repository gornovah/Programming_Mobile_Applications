package com.gornovah.modern_art_ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.gornovah.modern_art_ui.R;
import com.gornovah.modern_art_ui.dialogs.MoreInformationDialog;
import com.gornovah.modern_art_ui.interfaces.AlertDialogActivity;


public class MainActivity extends ActionBarActivity implements AlertDialogActivity {

    private SeekBar seekBar;
    private RelativeLayout palette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.palette = (RelativeLayout) findViewById(R.id.palette);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (int i = 0; i < palette.getChildCount(); i++) {
                    View child = palette.getChildAt(i);

                    int originalColor = Color.parseColor((String) child.getTag());
                    int inverterColor = (0x00FFFFFF - (originalColor | 0xFF000000)) | (originalColor & 0xFF000000);

                    if (getResources().getColor(R.color.white) != originalColor &&
                            getResources().getColor(R.color.lightgray) != originalColor) {

                        int origR = (originalColor >> 16) & 0x000000FF;
                        int origG = (originalColor >> 8) & 0x000000FF;
                        int origB = originalColor & 0x000000FF;

                        int invR = (inverterColor >> 16) & 0x000000FF;
                        int invG = (inverterColor >> 8) & 0x000000FF;
                        int invB = inverterColor & 0x000000FF;

                        child.setBackgroundColor(Color.rgb(
                                (int) (origR + (invR - origR) * (progress / 100f)),
                                (int) (origG + (invG - origG) * (progress / 100f)),
                                (int) (origB + (invB - origB) * (progress / 100f))));
                        child.invalidate();
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void showDialog (MenuItem item) {

        new MoreInformationDialog().show(getFragmentManager(), MainActivity.class.getName());

    }

    @Override
    public void doNegative() {

    }

    @Override
    public void doPositive() {
        Intent intentVisit = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
        Intent chooser = Intent.createChooser(intentVisit, getResources().getString(R.string.open_with));
        startActivity(chooser);
    }


}
