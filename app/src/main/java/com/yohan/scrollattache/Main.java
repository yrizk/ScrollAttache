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
import android.widget.Toast;


public class Main extends ActionBarActivity  implements CustomScrollView.ScrollListener{

    public Button stickyButton;  // a sibling of the scrollview, becomes visible the moment dummyStickyView is completely off the screen
    private TextView ratingsView; //
    public CustomScrollView scrollView;
    private TextView descriptionView;
    private LinearLayout rootView;
    private boolean stuckToTop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (CustomScrollView) findViewById(R.id.scroll);
        scrollView.setListener(this);
        stickyButton = (Button) findViewById(R.id.sticky_view);
        rootView = (LinearLayout) findViewById(R.id.outmost_parent);
        ratingsView = (TextView) findViewById(R.id.ratings);
        descriptionView = (TextView) findViewById(R.id.description);
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
            if (stuckToTop) undoStickyBomb();
            else throwStickyBomb();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void throwStickyBomb() {
        RelativeLayout.LayoutParams lp =(RelativeLayout.LayoutParams) ratingsView.getLayoutParams();
        lp.addRule(RelativeLayout.BELOW,descriptionView.getId());
        ratingsView.setLayoutParams(lp);
        ((ViewGroup) stickyButton.getParent()).removeView(stickyButton);
        rootView.addView(stickyButton, 0);
        stuckToTop = true;
    }

    private void undoStickyBomb() {

        RelativeLayout.LayoutParams lp =(RelativeLayout.LayoutParams) ratingsView.getLayoutParams();
        lp.addRule(RelativeLayout.BELOW,stickyButton.getId());
        ratingsView.setLayoutParams(lp);
        lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW,descriptionView.getId());
        rootView.removeView(stickyButton);
        stickyButton.setLayoutParams(lp);
        ((ViewGroup)ratingsView.getParent()).addView(stickyButton);
        stuckToTop = false;
    }


    @Override
    public void onScroll(int oldTop, int newTop) {
        Log.d("onScroll", " newTop : " + newTop + " height: " + stickyButton.getHeight() + " getTop: " + stickyButton.getTop());
        if(!stuckToTop) {
            if (newTop + stickyButton.getHeight() >= stickyButton.getTop()) {
                Toast.makeText(this, "NOW", Toast.LENGTH_SHORT).show();
                throwStickyBomb();
            }
        } else if (newTop <= ratingsView.getTop()) {
            undoStickyBomb();
        }
    }
}
