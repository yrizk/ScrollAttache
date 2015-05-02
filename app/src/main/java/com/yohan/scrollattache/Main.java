package com.yohan.scrollattache;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class Main extends ActionBarActivity  implements CustomScrollView.ScrollListener{

    public Button stickyButton;  // a sibling of the scrollview, becomes visible the moment dummyButton is completely off the screen
    public Button dummyButton; // lives inside the scrollview
    public TextView mainContent;
    public CustomScrollView scrollView;
    private LinearLayout rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (CustomScrollView) findViewById(R.id.scroll);
//        scrollView.setListener(this);
        stickyButton = (Button) findViewById(R.id.sticky_view);
        rootView = (LinearLayout) findViewById(R.id.outmost_parent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            throwStickyBomb();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void throwStickyBomb() {
        ((ViewGroup)stickyButton.getParent()).removeView(stickyButton);
        rootView.addView(stickyButton,0);

    }


    @Override
    public void onScroll(int oldTop, int newTop) {
//        Log.d("onScroll", "Old top : " + oldTop + " newTop : " + newTop);
        if (newTop < (mainContent.getTop())) { //
            Log.d("onScroll", "dummy button is visible");
            stickyButton.setVisibility(View.GONE);
            dummyButton.setVisibility(View.VISIBLE);
    }
        else if (newTop > (mainContent.getTop())) {
            Log.d("onScroll", "dummy button is not visible");
            stickyButton.setVisibility(View.VISIBLE);
            dummyButton.setVisibility(View.GONE);
        }
    }
}
