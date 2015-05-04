package com.yohan.scrollattache;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Main extends ActionBarActivity  implements ObservedScrollView.ScrollListener{

    public Button stickyButton;  // a sibling of the scrollview, becomes visible the moment dummyStickyView is completely off the screen
    private TextView bottomView; //
    public ObservedScrollView scrollView;
    private TextView topView;
    private LinearLayout rootView;

    /**
     * the flag indicating the current state of the stickyButton
     * if there are more other writes to this variable this becomes less of a good idea.
     * Instead, consider view logic to determine where stickyButton is.
     *
     */
    private boolean stuckToTop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (ObservedScrollView) findViewById(R.id.scroll);
        scrollView.setListener(this);
        stickyButton = (Button) findViewById(R.id.sticky_view);
        rootView = (LinearLayout) findViewById(R.id.outmost_parent);
        bottomView =  (TextView) findViewById(R.id.bottom_view);
        topView = (TextView) findViewById(R.id.main_content);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
     * no black magic here , just switching the immediate parent to make stickyView a sibling of the scrollView.
     */
    private void throwStickyBomb() {
        RelativeLayout.LayoutParams lp =(RelativeLayout.LayoutParams) bottomView.getLayoutParams();
        lp.addRule(RelativeLayout.BELOW, topView.getId());
        bottomView.setLayoutParams(lp);
        ((ViewGroup) stickyButton.getParent()).removeView(stickyButton);
        rootView.addView(stickyButton, 0);
        stuckToTop = true;
    }

    /**
     * the  opposite of throwStickyBomb(), with the regeneration of stickyView's RelativeLayout.Params,
     * which it looses the moment it becomes a child of the top root,  a LinearLayout.
     */
    private void undoStickyBomb() {

        RelativeLayout.LayoutParams lp =(RelativeLayout.LayoutParams) bottomView.getLayoutParams();
        lp.addRule(RelativeLayout.BELOW,stickyButton.getId());
        bottomView.setLayoutParams(lp);
        lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, topView.getId());
        rootView.removeView(stickyButton);
        stickyButton.setLayoutParams(lp);
        ((ViewGroup) bottomView.getParent()).addView(stickyButton);
        stuckToTop = false;
    }


    @Override
    public void onScroll(int oldTop, int newTop) {
        if(!stuckToTop) {
            if (newTop + stickyButton.getHeight() >= stickyButton.getTop()) {
                /**
                 * One thing to note about this math, we  trigger the switch when the space just above
                 * stickyView equals its actual height, which seems (to me) as the best moment to make the
                 * transition seamless.
                 */
                throwStickyBomb();
            }
        } else if (newTop <= bottomView.getTop()) {
            undoStickyBomb();
        }
    }
}
