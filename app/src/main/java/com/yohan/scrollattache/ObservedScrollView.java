package com.yohan.scrollattache;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by yohannarizk on 4/20/15.
 */
public class ObservedScrollView extends ScrollView {

    ScrollListener listener;
    public ObservedScrollView(Context context) {
        super(context);
    }

    public ObservedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public  void setListener(ScrollListener listener) {
        this.listener = listener;
    }



    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l,t,oldl,oldt);
        if (listener != null) {
            listener.onScroll(oldl,t);
        }
    }

    /**
     * This is key, ObservedScrollView needs to be ... well, observed. It itself can't be in charge
     * of determining the moment for view switching
     */
    interface ScrollListener   {
        public void onScroll(int oldTop, int newTop);
    }


}
