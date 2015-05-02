package com.yohan.scrollattache;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Main extends ActionBarActivity  implements CustomScrollView.ScrollListener{

    public Button stickyButton;  // a sibling of the scrollview, becomes visible the moment dummyStickyView is completely off the screen
    public Button dummyStickyView; // lives inside the scrollview
    private TextView ratingsView; //
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
        dummyStickyView = (Button) findViewById(R.id.dummy_sticky_view);
        ratingsView = (TextView) findViewById(R.id.ratings);
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
        RelativeLayout.LayoutParams lp =(RelativeLayout.LayoutParams) ratingsView.getLayoutParams();
        lp.addRule(RelativeLayout.BELOW,dummyStickyView.getId());
        ratingsView.setLayoutParams(lp);
        ((ViewGroup) stickyButton.getParent()).removeView(stickyButton);
        dummyStickyView.setVisibility(View.INVISIBLE);
        rootView.addView(stickyButton,0);
    }


    @Override
    public void onScroll(int oldTop, int newTop) {
//        Log.d("onScroll", "Old top : " + oldTop + " newTop : " + newTop);
//        if (newTop < (mainContent.getTop())) { //
//            Log.d("onScroll", "dummy button is visible");
//            stickyButton.setVisibility(View.GONE);
//            dummyStickyView.setVisibility(View.VISIBLE);
//    }
//        else if (newTop > (mainContent.getTop())) {
//            Log.d("onScroll", "dummy button is not visible");
//            stickyButton.setVisibility(View.VISIBLE);
//            dummyStickyView.setVisibility(View.GONE);
//        }
    }
}
