package com.do1.zhdj.widght;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-11
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class MyScrollView extends ScrollView {
    private static final String tag = "MyScrollView";
    private Handler handler;
    private View view;
    public MyScrollView(Context context) {
        super(context);
    }
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    //这个获得总的高度
    @Override
    public int computeVerticalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override
    public int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }
    private void init(){

        this.setOnTouchListener(onTouchListener);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what) {
                    case 1:
                        if(view.getMeasuredHeight() <= getScrollY() + getHeight()) {
                            if(onScrollListener != null) {
                                onScrollListener.onBottom();
                            }

                        }else if(getScrollY() == 0) {
                            if(onScrollListener!=null) {
                                onScrollListener.onTop();
                            }
                        }
                        else {
                            if(onScrollListener != null) {
                                onScrollListener.onScroll();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        };

    }

    OnTouchListener onTouchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    if(view != null && onScrollListener != null) {
                        handler.sendMessageDelayed(handler.obtainMessage(1), 200);
                    }
                    break;

                default:
                    break;
            }
            return false;
        }

    };

    /**
     * 获得参考的View，主要是为了获得它的MeasuredHeight，然后和滚动条的ScrollY+getHeight作比较。
     */
    public void getView() {
        this.view = getChildAt(0);
        if(view != null){
            init();
        }
    }

    /**
     * 定义接口
     */
    public interface OnScrollListener {
        void onBottom();
        void onTop();
        void onScroll();
    }

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

}
