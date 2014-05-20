package com.do1.zhdj.activity.infomation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.common.WapViewActivity;
import com.do1.zhdj.activity.infomation.widght.MyGallery;
import com.do1.zhdj.activity.infomation.widght.TryRefreshableView;
import com.do1.zhdj.activity.infomation.widght.TryRefreshableView.RefreshListener;
import com.do1.zhdj.activity.parent.AppManager;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.info.GetMobileInfo;
import com.do1.zhdj.info.VersionUpdateCusService;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.ImageViewTool;
import com.do1.zhdj.util.ValidUtil;

/**
 * 资讯 
 * auth:YanFangqin 
 * data:2013-4-19 
 * thzhd
 */
public class InfomationActivity extends BaseActivity {

	public static final String ACTION_INTENT_PUSH = "com.do1.thzhd.push"; 
	private List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
	private List<Map<String, Object>> imgList = new ArrayList<Map<String,Object>>();
	private int[] imgIds = { R.drawable.icon_01,
			R.drawable.icon_03,R.drawable.icon_02,  R.drawable.icon_04, R.drawable.icon_05,
			R.drawable.icon_07, R.drawable.icon_06 };
	private String[] titles = { "通知公告","热点新闻","工作动态", "组织风采" };
	private String[] types = { "1", "5","2","3",  };
	private Map<String, Object> pushMap = new HashMap<String, Object>();
	private LinearLayout itemLayout;
	private Context context;
	public String typeid;
	List<Map<String, Object>> newsInfoslist;
	String newsInfoId,newsInfoId2,toppictype,newsInfoType;
	
//	private Gallery gallery;
	private LinearLayout iconLayout;
	
	public  ScrollView sv;
	private TryRefreshableView rv;
	
	//图片自动播放
	private MyGallery pictureGallery = null;
    private int index = 0;
    private boolean isScrollRefesh = false;
    private Timer timer;
    private TimerTask task;
    
	private Handler mHandler;
	private boolean mRunning = false;
	private int requestId = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infomation_fragment);
		context = this;
		aq = new AQuery(this);
		setHeadView(findViewById(R.id.headLayout), 0, "", "党讯速递",0, "关于我们", null, this);
		sv = (ScrollView) findViewById(R.id.trymySv);//下拉刷新
		rv = (TryRefreshableView) findViewById(R.id.trymyRV);
		initItem();//初始化控件
//		registerBrocast();//注册广播
//		getPushMap();//获取推送数据
		initList();//初始化列表
		
		HandlerThread thread = new HandlerThread("MyHandlerThread");
	    thread.start();//创建一个HandlerThread并启动它
	    mHandler = new Handler(thread.getLooper());//使用HandlerThread的looper对象创建Handler，如果使用默认的构造方法，很有可能阻塞UI线程
		
		fillData(1);//填充列表
	}
	
	//实现耗时操作的线程
    Runnable mBackgroundRunnable = new Runnable() {
        @Override
        public void run() {
        	while(!mRunning){
        		mRunning = true;
        		request();
            }
        }
    };
    
    /**
     * 启动线程请求数据
     */
    public void fillData(int requestId){
    	this.requestId = requestId;
	    mHandler.post(mBackgroundRunnable);//将线程post到Handler中
    }
    
	
	public void request(){
//		new Thread(){
//			public void run() {
			String url = SERVER_URL + getString(R.string.infomation_the_new);
			Map<String, String> map = new HashMap<String, String>();
			doRequest(requestId, url, map);
//			};
//		}.start();
	}
	
	//实现耗时操作的线程
    Runnable getImageRunnable = new Runnable() {
        @Override
        public void run() {
        	while(!mRunning){
        		mRunning = true;
        		String url = SERVER_URL + getString(R.string.news_image_list);
				Map<String, String> map = new HashMap<String, String>();
				doRequest(100000, url, map);
            }
        }
    };
	
//	public void fillImage2(){
//		new Thread(){
//			public void run() {
//				String url = SERVER_URL + getString(R.string.news_image_list);
//				Map<String, String> map = new HashMap<String, String>();
//				doRequest(100000, url, map);
//			};
//		}.start(); 
//	}
	
	
	//实现耗时操作的线程
    Runnable clientInstallRunnable = new Runnable() {
        @Override
        public void run() {
        	while(!mRunning){
        		mRunning = true;
        		String url = Constants.SERVER_RUL2 + getString(R.string.client_install);
				Map<String, String> map = new HashMap<String, String>();
				map.put("type", "1");
				map.put("device_id", Constants.deviceId);
				map.put("os_version", GetMobileInfo.getSystem());
				map.put("app_version", VersionUpdateCusService.getInstance(InfomationActivity.this).getVersion());
				setRequestMode(PROGRESS_SILENT_MODE);
				doRequest(99999, url, map);
            }
        }
    };
	
	/**
	 * 客户端安装日志记录
	 */
//	public void clientInstall2(){
//		boolean needSendLog = Constants.pushShared.getBoolean(AppManager.NEED_SEND_LOG, true);
//		if(needSendLog){
//			new Thread(){
//				public void run() {
//					String url = SERVER_URL + getString(R.string.client_install);
//					Map<String, String> map = new HashMap<String, String>();
//					map.put("type", "1");
//					map.put("device_id", Constants.deviceId);
//					map.put("os_version", GetMobileInfo.getSystem());
//					map.put("app_version", VersionUpdateCusService.getInstance(InfomationActivity.this).getVersion());
//					setRequestMode(PROGRESS_SILENT_MODE);
//					doRequest(99999, url, map);
//				};
//			}.start(); 
//		}
//	}
	
	/**
	 * 注册广播
	 */
	public void registerBrocast(){
		IntentFilter filter = new IntentFilter();
	    filter.addAction(ACTION_INTENT_PUSH);
	    registerReceiver(new MyBrocast(), filter);
	}
	
	private void stopTimer(){
		if(timer != null){
			timer.cancel();
			timer = null;
		}
		if(task != null){
			task.cancel();
			task = null;
		}
	}
	
	/**
	 * 控制下拉刷新的handler
	 */
	Handler rehandler = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			isScrollRefesh = true;
			rv.finishRefresh();
			if(mRunning){
				mRunning = false;
				fillData(999999);
				stopTimer();
			}
		};
	};

	public void initItem() {
		itemLayout = (LinearLayout) findViewById(R.id.itemLayout);
		iconLayout = (LinearLayout) findViewById(R.id.iconLayout);
//		System.out.println("sv: " + findViewById(R.id.trymySv));
		sv = (ScrollView) findViewById(R.id.trymySv);//下拉刷新
		rv = (TryRefreshableView) findViewById(R.id.trymyRV);
		rv.sv = sv;
		rv.setRefreshListener(new RefreshListener() {//监听是否加载刷新
			@Override
			public void onRefresh() {
				if (rv.mRefreshState == 4) {
					rehandler.sendEmptyMessageDelayed(1, 1000);//伪处理
				}
			}
		});
		
		//图片自动播放初始化
		pictureGallery = (MyGallery) findViewById(R.id.mygallery);
		pictureGallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				toppictype=imgList.get(position%imgList.size()).get("typeId")+"";
//				System.out.println("topictype: " + toppictype);
				aq.id(R.id.imgDesc).text(imgList.get(position%imgList.size()).get("title")+"");
				changeBg(position%imgList.size());
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		pictureGallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String url = imgList.get(position%imgList.size()).get("detail_url")+"";
				newsInfoId=imgList.get(position%imgList.size()).get("newsInfoId")+"";
				toppictype=imgList.get(position%imgList.size()).get("typeId")+"";
				Intent intent = new Intent(InfomationActivity.this,WapViewActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("url", url);
				intent.putExtra("title", "热点新闻详情");
				intent.putExtra("newsInfoId", newsInfoId);
				intent.putExtra("newsInfoType", "0");
				startActivity(intent);
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.rightImage:
//			intent = new Intent(InfomationActivity.this,AboutActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	class MyBrocast extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {

			String type = intent.getStringExtra("type");//信息类型
			for(int i = 0; i < itemLayout.getChildCount(); i++){
				View view = itemLayout.getChildAt(i);
				String t = ((TextView)view.findViewById(R.id.type)).getText().toString();
				if(t.equals(type)){
					TextView v = (TextView) view.findViewById(R.id.leftNo);
					v.setVisibility(View.VISIBLE);
					if("10+".equals(v.getText().toString())){
						
					}else if("10".equals(v.getText().toString())){
						String s = "10+";
						setFrameBgByTrim(s, v);
					}else if("0".equals(v.getText().toString())){
						String s = "1";
						setFrameBgByTrim(s, v);
					}else{
						String s = (Integer.parseInt(v.getText().toString()) + 1) + "";
						setFrameBgByTrim(s, v);
					}
				}
			}
		}
	}
	
	/**
	 * 设置列表监听
	 */
	public void setItemOnclick(){
		for(int i=0; i < itemLayout.getChildCount(); i++){
			View item = itemLayout.getChildAt(i);
			
			item.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
 					String type = ((TextView)v.findViewById(R.id.type)).getText().toString();
// 					newsInfoId2=((TextView)v.findViewById(R.id.infomationid)).getText().toString();
// 					newsInfoslist.get(i);
					clearPushByType(type);
					((TextView)v.findViewById(R.id.leftNo)).setText("0");
					v.findViewById(R.id.leftNo).setVisibility(View.GONE);
					Intent intent = new Intent();
					if("5".equals(type)){
						intent.setClass(InfomationActivity.this, OrganizaListActivity.class);// 组织风采
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					}else if("8".equals(type)){
						ToastUtil.showLongMsg(context, "该模块正在建设中...");
						return;
					} else {
						intent.setClass(InfomationActivity.this, NoticeListActivity.class);// 通知公告、热点新闻、学习十八大、党员先锋
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("newsInfoType", type);
//						System.out.println("infomation.type: " + type);
						intent.putExtra("newsInfoId", newsInfoId2);
					}
					startActivity(intent);
				}
			});
		}
	}
	
	/**
	 * 得到推送数量的map
	 * @return
	 */
	public void getPushMap(){
		String mapjson = Constants.sharedProxy.getString(AppManager.PUSH_KEY, "{}");
		try {
			pushMap = JsonUtil.json2Map(new JSONObject(mapjson)); 
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断推送条数
	 * @return
	 */
	public String getCount(String count){
		int total = Integer.parseInt(count);
		if(total > 10){
			return "10+";
		}else{
			return total+"";
		}
	}
	
	/**
	 * 清除该分类的推送数量
	 */
	public void clearPushByType(String type){
		String mapjson = Constants.sharedProxy.getString("pushJson", "{}");
		try {
			Map<String, Object> pushMap = JsonUtil.json2Map(new JSONObject(mapjson));
			if(pushMap.get(type) != null && !"".equals(pushMap.get(type)+"") && Integer.parseInt(pushMap.get(type)+"") > 0){
				pushMap.put(type, "0");
				Constants.sharedProxy.putString("pushJson", new JSONObject(pushMap).toString());
				Constants.sharedProxy.commit();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化列表
	 */
	public void initList(){
		getPushMap();
		itemLayout.removeAllViews();
		TextView idtext;
		for(int i = 0; i < titles.length; i++){
			View view = View.inflate(this, R.layout.infomation_fragment_item, null);
			if(i==1){//如果是热点新闻就不显示
				view.setVisibility(View.GONE);
			}
//			idtext=(TextView)view.findViewById(R.id.infomationid);
			((TextView)view.findViewById(R.id.type)).setText(types[i]);
			view.findViewById(R.id.image).setBackgroundResource(imgIds[i]);
			((TextView)view.findViewById(R.id.title)).setText(titles[i]);
			String type = types[i];
			if(pushMap.get(type) != null && !"".equals(pushMap.get(type)) && Integer.parseInt(pushMap.get(type)+"") > 0){
				view.findViewById(R.id.leftNo).setVisibility(View.VISIBLE);
				setFrameBgByTrim(getCount(pushMap.get(type)+""), view.findViewById(R.id.leftNo));
			}else{
				view.findViewById(R.id.leftNo).setVisibility(View.GONE);
			}
			
			if(dataList.size() > 0){
				for(int j = 0; j < dataList.size(); j++){
					Map<String, Object> map = dataList.get(j);
//					System.out.println("idtext: " + idtext);
					Log.e("newsinfoid: " + map.get("newsInfoId")+"");
//					idtext.setText(map.get("newsInfoId")+"");
					
					String mapType = map.get("typeId") != null ? map.get("typeId") + "" : "";
					if(!"".equals(mapType) && ValidUtil.isNumeric(mapType)){
						if(Integer.parseInt(mapType) == Integer.parseInt(types[i])){
							String rs = "";
							String time = map.get("pushTime") != null ? map.get("pushTime") + "" : "";
//							if(!"".equals(time)){
//								try {
//									time = constants.sdfTime.format(constants.sdfReturn.parse(time));
//									String today = constants.sdfTime.format(new Date());
//									if(today.substring(0,10).equals(time.substring(0, 10))){
//										int h = Integer.parseInt(time.substring(11, 13));
//										if(h < 6){
//											rs = "凌晨";
//										}else if(h >= 6 && h < 12){
//											rs = "上午";
//										}else if(h >= 12 && h < 18){
//											rs = "下午";
//										}else{
//											rs = "晚上";
//										}
//										rs = rs + " " + time.substring(11, time.length());
//									}else{
//										rs = time.substring(0, 10);
//									}
//								} catch (ParseException e) {
//									e.printStackTrace();
//								}
//							}
							newsInfoId2=map.get("newsInfoId")+"";
							newsInfoType=map.get("typeId")+"";
							((TextView)view.findViewById(R.id.time)).setText(time);
							((TextView)view.findViewById(R.id.content)).setText(map.get("title") != null ? map.get("title") + "" : "");
						}
					}
				}
			}
			itemLayout.addView(view);
		}
		setItemOnclick();
	}
	
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
//		System.out.println("成功了");
		Map<String, Object> map = resultObject.getDataMap();
//		System.out.println("map : " + map);
		try {
			//=============topimginfos================
			Object object = map.get("topImgInfos");
			JSONArray array = new JSONArray(object.toString());
			List<Map<String, Object>> topImgInfoslist = JsonUtil.jsonArray2List(array); 
			System.out.println("topImgInfoslist: " + topImgInfoslist);
			
			//=============newsInfos==================
			Object object2 = map.get("newsInfos");
			JSONArray array2 = new JSONArray(object2.toString());
			newsInfoslist = JsonUtil.jsonArray2List(array2);
			System.out.println("newsInfoslist: " + newsInfoslist);
			dataList = newsInfoslist;
			if(dataList.size() > 0){
				
				initList();
			}
			if(requestId == 99999){
				Constants.sharedProxy.putBoolean(AppManager.NEED_SEND_LOG, false);
				Constants.sharedProxy.commit();
				Log.e("============日志上报成功=============");
			}else if(requestId == 1 || requestId == 999999){
				dataList = newsInfoslist;
				System.out.println("datalist:  " + dataList);
				if(dataList.size() > 0){
					
					initList();
				}
				if(topImgInfoslist.size() > 0){
					imgList =topImgInfoslist;
					iconLayout.removeAllViews();
					for(int i = 0; i < imgList.size(); i++){
						View view = View.inflate(this, R.layout.img_icon, null);
						view.findViewById(R.id.image).setBackgroundResource(R.drawable.new_icon_g);
						iconLayout.addView(view);
					}
					
					ImageAdapter adapter = new ImageAdapter(context);
			        this.pictureGallery.setAdapter(adapter);
			        startTimer();
			        findViewById(R.id.imageLay).setBackgroundDrawable(null);
//				}
//				if(!isScrollRefesh){
////					clientInstall();//客户端安装日志记录
//					if(mRunning){
//						mRunning = false;
//						mHandler.post(clientInstallRunnable);
//					}
//				}else{
					isScrollRefesh = true;
//				}
			}
//				rv.finishRefresh();
//				fillImage();//图片列表
//				if(mRunning){
//					mRunning = false;
//					mHandler.post(getImageRunnable);
//				}
			}else if(requestId == 9){//点击图片跳转热点新闻详情
				Intent intent = new Intent(InfomationActivity.this,WapViewActivity.class);
				intent.putExtra("url", resultObject.getDataMap().get("content_url")+"");
				intent.putExtra("title", "热点新闻详情");
				startActivity(intent);
			}
//			else if(requestId == 100000){
				
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void startTimer(){
		//图片自动播放初始化
		/**
	     * 定时器，实现自动播放
	     */
		if(timer == null){
			timer = new Timer();
		}
		if(task == null){
			task = new TimerTask() {
		        @Override
		        public void run() {
		            Message message = new Message();
		            message.what = 2;
		            index = pictureGallery.getSelectedItemPosition();
		            index++;
		            handler.sendMessage(message);
		        }
			};
		}
		timer.schedule(task, 3000, 3000);		
	}
	
	public void changeBg(int position){
		for(int i = 0; i < iconLayout.getChildCount(); i++){
			if(i == position){
				iconLayout.getChildAt(i).findViewById(R.id.image).setBackgroundResource(R.drawable.new_icon_w);
			}else{
				iconLayout.getChildAt(i).findViewById(R.id.image).setBackgroundResource(R.drawable.new_icon_g);
			}
		}
	}
	
    
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
//          imageView.setImageResource(picture[position % picture.length]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(R.drawable.index_default);
            imageView.setTag(imgList.get(position%imgList.size()).get("newsInfoId").toString());
            imageView.setLayoutParams(new Gallery.LayoutParams(
                    Gallery.LayoutParams.FILL_PARENT,
                    Gallery.LayoutParams.FILL_PARENT));
//            System.out.println("position: " + position);
//            System.out.println("imgList.size: " + imgList.size());
//            System.out.println("imglist: " + imgList.get(position%imgList.size()).get("imgPath").toString());
            ImageViewTool.getAsyncImageBg(imgList.get(position%imgList.size()).get("resizeImgPath").toString(), imageView, R.drawable.index_default);
            return imageView;
        }
    }
    
    @Override
    public void onNetworkError(int requestId) {
    	super.onNetworkError(requestId);
    	rv.finishRefresh();
    	startTimer();
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		AppManager.contextManager = this;
		Log.i("==============onResume============:"+AppManager.contextManager);
		if(AppManager.HAVE_PUSH){
			AppManager.HAVE_PUSH = false;
			initList();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		AppManager.contextManager = null;
		Log.i("==============onPause============:"+AppManager.contextManager);
	}
	
	@Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁线程
        mHandler.removeCallbacks(mBackgroundRunnable);
    }
	
}

