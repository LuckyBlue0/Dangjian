package com.do1.zhdj.activity.infomation;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.infomation.widght.MyGallery;
 
/**
 * TODO:gallery 图片循环播放，自动播放，无惯性（一次滑动一张图）
 * User:YanFangqin
 * Date:2013-5-27
 * ProjectName:thzhd
 */
public class CycleImageActivity extends Activity {
    public static int[] picture = { R.drawable.icon_01, R.drawable.icon_02,
            R.drawable.icon_03 };
    private MyGallery pictureGallery = null;
    private int index = 0;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_grallery);
        this.pictureGallery = (MyGallery) findViewById(R.id.mygallery);
        ImageAdapter adapter = new ImageAdapter(this);
        this.pictureGallery.setAdapter(adapter);
        Timer timer = new Timer();
        timer.schedule(task, 7000, 7000);
    }
 
    /**
     * 定时器，实现自动播放
     */
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 2;
            index = pictureGallery.getSelectedItemPosition();
            index++;
            handler.sendMessage(message);
        }
    };
 
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            case 2:
                pictureGallery.setSelection(index);
                break;
            default:
                break;
            }
        }
    };
 
    /**
     * 自定义图片显示适配器
     *
     */
    class ImageAdapter extends BaseAdapter {
        private Context context;
 
        public ImageAdapter(Context context) {
            this.context = context;
        }
 
        public int getCount() {
            return Integer.MAX_VALUE;
        }
 
        public Object getItem(int position) {
            return position;
        }
 
        public long getItemId(int position) {
            return position;
        }
 
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(picture[position % picture.length]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(
                    Gallery.LayoutParams.FILL_PARENT,
                    Gallery.LayoutParams.FILL_PARENT));
            return imageView;
        }
    }
 
}

