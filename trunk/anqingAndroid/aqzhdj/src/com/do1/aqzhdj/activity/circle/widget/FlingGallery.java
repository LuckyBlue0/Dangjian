package com.do1.aqzhdj.activity.circle.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * 可以循环左右滑动的组件，用于制作广告效果等
 * @author DZP
 *
 * 2012-4-21
 */

public class FlingGallery extends FrameLayout {
    // Constants
    private final int swipe_min_distance = 120;
    private final int swipe_max_off_path = 250;
    private final int swipe_threshold_veloicty = 400;

    // Properties
    private int mViewPaddingWidth = 0;
    private int mAnimationDuration = 250;
    private float mSnapBorderRatio = 0.5f;
    private boolean mIsGalleryCircular = true;

    // Members
    private int mGalleryWidth = 0;
    private boolean mIsTouched = false;
    private boolean mIsDragging = false;
    private float mCurrentOffset = 0.0f;
    private long mScrollTimestamp = 0;
    private int mFlingDirection = 0;
    private int mCurrentPosition = 0;
    private int mCurrentViewNumber = 0;

    private Context mContext;
    private Adapter mAdapter;
    private FlingGalleryView[] mViews;
    private FlingGalleryAnimation mAnimation;
    private GestureDetector mGestureDetector;
    private Interpolator mDecelerateInterpolater;
    private OnGalleryChangeListener onGalleryChangeListener;
    
    public FlingGallery(Context context) {
        this(context,null);
    }
    public FlingGallery(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        
        mContext = context;
        mAdapter = null;

        mViews = new FlingGalleryView[3];
        mViews[0] = new FlingGalleryView(0, this);
        mViews[1] = new FlingGalleryView(1, this);
        mViews[2] = new FlingGalleryView(2, this);

        mAnimation = new FlingGalleryAnimation();
        mGestureDetector = new GestureDetector(new FlingGestureDetector());
        mDecelerateInterpolater = AnimationUtils.loadInterpolator(mContext, android.R.anim.decelerate_interpolator);
    }
    
    /**
     * 设置监听滚动改变事件
     * @param listener
     */
    public void setGalleryChangeListener(OnGalleryChangeListener listener) {
        onGalleryChangeListener = listener;
    }
    
    private void notifyGalleryChangeListener() {
        if(onGalleryChangeListener != null) {
            onGalleryChangeListener.onGalleryChange(mCurrentPosition);
        }
    }

    public void setPaddingWidth(int viewPaddingWidth) {
        mViewPaddingWidth = viewPaddingWidth;
    }

    public void setAnimationDuration(int animationDuration) {
        mAnimationDuration = animationDuration;
    }

    public void setSnapBorderRatio(float snapBorderRatio) {
        mSnapBorderRatio = snapBorderRatio;
    }

    /**
     * 设置是否可以循环滚动
     * @param isGalleryCircular true 可以循环，false不可以
     */
    public void setIsGalleryCircular(boolean isGalleryCircular) {
        if (mIsGalleryCircular != isGalleryCircular) {
            mIsGalleryCircular = isGalleryCircular;

            if (mCurrentPosition == getFirstPosition()) {
                mViews[getPrevViewNumber(mCurrentViewNumber)].recycleView(getPrevPosition(mCurrentPosition));
            }

            if (mCurrentPosition == getLastPosition()) {
                mViews[getNextViewNumber(mCurrentViewNumber)].recycleView(getNextPosition(mCurrentPosition));
            }
        }
    }

    public int getGalleryCount() {
        return (mAdapter == null) ? 0 : mAdapter.getCount();
    }

    public int getFirstPosition() {
        return 0;
    }

    public int getLastPosition() {
        return (getGalleryCount() == 0) ? 0 : getGalleryCount() - 1;
    }

    private int getPrevPosition(int relativePosition) {
        int prevPosition = relativePosition - 1;

        if (prevPosition < getFirstPosition()) {
            prevPosition = getFirstPosition() - 1;

            if (mIsGalleryCircular == true) {
                prevPosition = getLastPosition();
            }
        }
        notifyGalleryChangeListener();
        return prevPosition;
    }

    private int getNextPosition(int relativePosition) {
        int nextPosition = relativePosition + 1;

        if (nextPosition > getLastPosition()) {
            nextPosition = getLastPosition() + 1;

            if (mIsGalleryCircular == true) {
                nextPosition = getFirstPosition();
            }
        }
        notifyGalleryChangeListener();
        return nextPosition;
    }

    private int getPrevViewNumber(int relativeViewNumber) {
        return (relativeViewNumber == 0) ? 2 : relativeViewNumber - 1;
    }

    private int getNextViewNumber(int relativeViewNumber) {
        return (relativeViewNumber == 2) ? 0 : relativeViewNumber + 1;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mGalleryWidth = right - left;

        if (changed == true) {
            mViews[0].setOffset(0, 0, mCurrentViewNumber);
            mViews[1].setOffset(0, 0, mCurrentViewNumber);
            mViews[2].setOffset(0, 0, mCurrentViewNumber);
        }
    }

    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;
        mCurrentPosition = 0;
        mCurrentViewNumber = 0;

        mViews[0].recycleView(mCurrentPosition);
        mViews[1].recycleView(getNextPosition(mCurrentPosition));
        mViews[2].recycleView(getPrevPosition(mCurrentPosition));

        mViews[0].setOffset(0, 0, mCurrentViewNumber);
        mViews[1].setOffset(0, 0, mCurrentViewNumber);
        mViews[2].setOffset(0, 0, mCurrentViewNumber);
    }

    private int getViewOffset(int viewNumber, int relativeViewNumber) {
        int offsetWidth = mGalleryWidth + mViewPaddingWidth;

        if (viewNumber == getPrevViewNumber(relativeViewNumber)) {
            return offsetWidth;
        }

        if (viewNumber == getNextViewNumber(relativeViewNumber)) {
            return offsetWidth * -1;
        }

        return 0;
    }

    void movePrevious() {
        mFlingDirection = 1;
        processGesture();
    }

    void moveNext() {
        mFlingDirection = -1;
        processGesture();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            movePrevious();
            return true;

        case KeyEvent.KEYCODE_DPAD_RIGHT:
            moveNext();
            return true;

        case KeyEvent.KEYCODE_DPAD_CENTER:
        case KeyEvent.KEYCODE_ENTER:
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	return this.onGalleryTouchEvent(event);
    }
    
    public boolean onGalleryTouchEvent(MotionEvent event) {
        boolean consumed = mGestureDetector.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mIsTouched || mIsDragging) {
                processScrollSnap();
                processGesture();
            }
        }

        return consumed;
    }

    void processGesture() {
        int newViewNumber = mCurrentViewNumber;
        int reloadViewNumber = 0;
        int reloadPosition = 0;

        mIsTouched = false;
        mIsDragging = false;

        if (mFlingDirection > 0) {
            if (mCurrentPosition > getFirstPosition() || mIsGalleryCircular == true) {
                newViewNumber = getPrevViewNumber(mCurrentViewNumber);
                mCurrentPosition = getPrevPosition(mCurrentPosition);
                reloadViewNumber = getNextViewNumber(mCurrentViewNumber);
                reloadPosition = getPrevPosition(mCurrentPosition);
            }
        }

        if (mFlingDirection < 0) {
            if (mCurrentPosition < getLastPosition() || mIsGalleryCircular == true) {
                newViewNumber = getNextViewNumber(mCurrentViewNumber);
                mCurrentPosition = getNextPosition(mCurrentPosition);
                reloadViewNumber = getPrevViewNumber(mCurrentViewNumber);
                reloadPosition = getNextPosition(mCurrentPosition);
            }
        }

        if (newViewNumber != mCurrentViewNumber) {
            mCurrentViewNumber = newViewNumber;
            mViews[reloadViewNumber].recycleView(reloadPosition);
        }

        mViews[mCurrentViewNumber].requestFocus();

        mAnimation.prepareAnimation(mCurrentViewNumber);
        this.startAnimation(mAnimation);

        mFlingDirection = 0;
    }

    void processScrollSnap() {
        float rollEdgeWidth = mGalleryWidth * mSnapBorderRatio;
        int rollOffset = mGalleryWidth - (int) rollEdgeWidth;
        int currentOffset = mViews[mCurrentViewNumber].getCurrentOffset();

        if (currentOffset <= rollOffset * -1) {
            mFlingDirection = 1;
        }

        if (currentOffset >= rollOffset) {
            mFlingDirection = -1;
        }
    }

    private class FlingGalleryView {
        private int mViewNumber;
        private FrameLayout mParentLayout;

        private FrameLayout mInvalidLayout = null;
        private LinearLayout mInternalLayout = null;
        private View mExternalView = null;

        public FlingGalleryView(int viewNumber, FrameLayout parentLayout) {
            mViewNumber = viewNumber;
            mParentLayout = parentLayout;

            mInvalidLayout = new FrameLayout(mContext);
            mInvalidLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

            mInternalLayout = new LinearLayout(mContext);
            mInternalLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

            mParentLayout.addView(mInternalLayout);
        }

        public void recycleView(int newPosition) {
            if (mExternalView != null) {
                mInternalLayout.removeView(mExternalView);
            }

            if (mAdapter != null) {
                if (newPosition >= getFirstPosition() && newPosition <= getLastPosition()) {
                    mExternalView = mAdapter.getView(newPosition, mExternalView, mInternalLayout);
                } else {
                    mExternalView = mInvalidLayout;
                }
            }

            if (mExternalView != null) {
                mInternalLayout.addView(mExternalView, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            }
        }

        public void setOffset(int xOffset, int yOffset, int relativeViewNumber) {
            // Scroll the target view relative to its own position relative to
            // currently displayed view
            mInternalLayout.scrollTo(getViewOffset(mViewNumber, relativeViewNumber) + xOffset, yOffset);
        }

        public int getCurrentOffset() {
            // Return the current scroll position
            return mInternalLayout.getScrollX();
        }

        public void requestFocus() {
            mInternalLayout.requestFocus();
        }
    }

    private class FlingGalleryAnimation extends Animation {
        private boolean mIsAnimationInProgres;
        private int mRelativeViewNumber;
        private int mInitialOffset;
        private int mTargetOffset;
        private int mTargetDistance;

        public FlingGalleryAnimation() {
            mIsAnimationInProgres = false;
            mRelativeViewNumber = 0;
            mInitialOffset = 0;
            mTargetOffset = 0;
            mTargetDistance = 0;
        }

        public void prepareAnimation(int relativeViewNumber) {
            if (mRelativeViewNumber != relativeViewNumber) {
                if (mIsAnimationInProgres == true) {
                    int newDirection = (relativeViewNumber == getPrevViewNumber(mRelativeViewNumber)) ? 1 : -1;
                    int animDirection = (mTargetDistance < 0) ? 1 : -1;

                    if (animDirection == newDirection) {
                        mViews[0].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                        mViews[1].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                        mViews[2].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                    }
                }

                mRelativeViewNumber = relativeViewNumber;
            }

            mInitialOffset = mViews[mRelativeViewNumber].getCurrentOffset();
            mTargetOffset = getViewOffset(mRelativeViewNumber, mRelativeViewNumber);
            mTargetDistance = mTargetOffset - mInitialOffset;

            this.setDuration(mAnimationDuration);
            this.setInterpolator(mDecelerateInterpolater);

            mIsAnimationInProgres = true;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {
            interpolatedTime = (interpolatedTime > 1.0f) ? 1.0f : interpolatedTime;
            int offset = mInitialOffset + (int) (mTargetDistance * interpolatedTime);

            for (int viewNumber = 0; viewNumber < 3; viewNumber++) {
                if ((mTargetDistance > 0 && viewNumber != getNextViewNumber(mRelativeViewNumber)) || (mTargetDistance < 0 && viewNumber != getPrevViewNumber(mRelativeViewNumber))) {
                    mViews[viewNumber].setOffset(offset, 0, mRelativeViewNumber);
                }
            }
        }

        @Override
        public boolean getTransformation(long currentTime, Transformation outTransformation) {
            if (super.getTransformation(currentTime, outTransformation) == false) {
                mViews[0].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                mViews[1].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                mViews[2].setOffset(mTargetOffset, 0, mRelativeViewNumber);

                mIsAnimationInProgres = false;

                return false;
            }

            if (mIsTouched || mIsDragging) {
                return false;
            }

            return true;
        }
    }

    private class FlingGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            mIsTouched = true;
            mFlingDirection = 0;
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (e2.getAction() == MotionEvent.ACTION_MOVE) {
                if (mIsDragging == false) {
                    mIsTouched = true;
                    mIsDragging = true;
                    mFlingDirection = 0;
                    mScrollTimestamp = System.currentTimeMillis();
                    mCurrentOffset = mViews[mCurrentViewNumber].getCurrentOffset();
                }

                float maxVelocity = mGalleryWidth / (mAnimationDuration / 1000.0f);
                long timestampDelta = System.currentTimeMillis() - mScrollTimestamp;
                float maxScrollDelta = maxVelocity * (timestampDelta / 1000.0f);
                float currentScrollDelta = e1.getX() - e2.getX();

                if (currentScrollDelta < maxScrollDelta * -1)
                    currentScrollDelta = maxScrollDelta * -1;
                if (currentScrollDelta > maxScrollDelta)
                    currentScrollDelta = maxScrollDelta;
                int scrollOffset = Math.round(mCurrentOffset + currentScrollDelta);

                if (scrollOffset >= mGalleryWidth)
                    scrollOffset = mGalleryWidth;
                if (scrollOffset <= mGalleryWidth * -1)
                    scrollOffset = mGalleryWidth * -1;

                mViews[0].setOffset(scrollOffset, 0, mCurrentViewNumber);
                mViews[1].setOffset(scrollOffset, 0, mCurrentViewNumber);
                mViews[2].setOffset(scrollOffset, 0, mCurrentViewNumber);
            }

            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(e1.getY() - e2.getY()) <= swipe_max_off_path) {
                if (e2.getX() - e1.getX() > swipe_min_distance && Math.abs(velocityX) > swipe_threshold_veloicty) {
                    movePrevious();
                }

                if (e1.getX() - e2.getX() > swipe_min_distance && Math.abs(velocityX) > swipe_threshold_veloicty) {
                    moveNext();
                }
            }

            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            mFlingDirection = 0;
            processGesture();
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            mFlingDirection = 0;
            return false;
        }
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }
    
    /**
     * FlingGallery组件滑动项改变时监听器
     * @author DZP
     *
     * 2012-4-21
     */
    public static interface OnGalleryChangeListener {
        public void onGalleryChange(int currentItem);
    }
}
