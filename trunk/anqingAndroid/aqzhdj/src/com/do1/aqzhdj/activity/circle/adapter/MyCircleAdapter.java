package com.do1.aqzhdj.activity.circle.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.circle.BaomingLisetActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;

public class MyCircleAdapter extends BaseAdapter {
	private Context ctx;
	private List<Map<String, Object>> listMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");//时间转换
	
	public MyCircleAdapter(Context ctx, List<Map<String, Object>> lsmap) {
		super();
		this.itemTemplateId = itemTemplateId;
		this.ctx = ctx;
		this.listMap = lsmap;
		mInflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return listMap.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		if(Constants.type == 1){
//			convertView = mInflater.inflate(R.layout.my_circle_item, null);
			convertView = mInflater.inflate(R.layout.wall_com_item, null);
			AQuery aq = new AQuery(convertView);
			aq.id(R.id.image).image(listMap.get(position).get("user_head").toString(), true, true, 0, R.drawable.persong_head_bg);
			aq.id(R.id.title).text(listMap.get(position).get("user_name").toString());
//			aq.id(R.id.time).text(DateTool.transition(listMap.get(position).get("publish_time").toString()));
			aq.id(R.id.time).text(getLastTime(listMap.get(position).get("publish_time").toString()));
			
			aq.id(R.id.content).text(listMap.get(position).get("msg_content").toString());
		}else if(Constants.type == 2){
			convertView = mInflater.inflate(R.layout.my_circle_activity_item, null);
			final AQuery mAq = new AQuery(convertView);
			Log.e(listMap.get(position).get("activity_addr").toString());
			mAq.id(R.id.image).image(listMap.get(position).get("user_head").toString(), true, true, 0, R.drawable.persong_head_bg);
			mAq.id(R.id.name).text(listMap.get(position).get("user_name").toString());
			mAq.id(R.id.activityName).text(listMap.get(position).get("activity_name").toString());
//			mAq.id(R.id.activityCircleName).text(MyCircleActivity.circleName);
			mAq.id(R.id.activityAddress).text(listMap.get(position).get("activity_addr").toString());
			mAq.id(R.id.activityTime).text(listMap.get(position).get("activity_time").toString());
			mAq.id(R.id.activityGuize).text(listMap.get(position).get("activity_content").toString());
			mAq.id(R.id.baomingCount).text("已报名人数:"+listMap.get(position).get("enter_nums").toString()+"人");
			int code = Integer.valueOf(listMap.get(position).get("Is_party_member").toString());
			if(code == 0){
				mAq.id(R.id.memberIcon).gone();
			}else if(code == 1){
				mAq.id(R.id.memberIcon).visible();
			}
			
			Button bt = (Button) convertView.findViewById(R.id.baoming);
			bt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						String url = Constants.SERVER_URL+ "JoinCircleActivity.aspx";
						String data = httpItem(url, position);
						Log.e(data);
						String ss = JsonUtil.json2Map(data).get("code").toString();
						int key = Integer.valueOf(ss);
						if(key == 0){
							ToastUtil.showShortMsg(ctx, JsonUtil.json2Map(data).get("desc").toString());
						}else if(key == 1){
							ToastUtil.showShortMsg(ctx, "报名成功");
							mAq.id(R.id.baomingCount).text("已报名人数:"+(Integer.valueOf(listMap.get(position).get("enter_nums").toString())+1)+"人");
//							listMap.remove(position);
//							MyCircleAdapter.this.notifyDataSetChanged();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			RelativeLayout btr = (RelativeLayout) convertView.findViewById(R.id.toActivityList);
			btr.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(ctx, BaomingLisetActivity.class);
					intent.putExtra("activity_id", listMap.get(position).get("activity_id").toString());
					((Activity)ctx).startActivity(intent);
				}
			});
		}
		return convertView;
	}
	
	private String httpItem(String url,int position) throws Exception{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject jsonData = new JSONObject();
		jsonData.put("activity_id", listMap.get(position).get("activity_id").toString());
		jsonData.put("user_id", Constants.sharedProxy.getString("keyId", ""));
		String params = Entryption.encode(jsonData.toString());
		StringEntity s = new StringEntity(params);
		s.setContentEncoding("UTF-8");
		s.setContentType("application/json");
		post.setEntity(s);
		HttpResponse res = client.execute(post);
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String data =  EntityUtils.toString(res.getEntity());
			Log.e(data);
			return data = Entryption.decode(data);
			
		}
		return null;
	}
	
	/**
	 * 根据时间字符串获取该时间过去了多久
	 * @param time
	 * @return
	 */
	public String getLastTime(String time){
		String str = "";
		time = time.replace(":", ",").replace("/", ",").replace(" ", ",");
		List<Integer> old = new ArrayList<Integer>();
		List<Integer> now = new ArrayList<Integer>();
		
		String[] a = time.split(",");
		for(String s : a){
			old.add(Integer.parseInt(s));
		}
		
		String[] b = sdf.format(new Date()).split(",");
		for(String s : b){
			now.add(Integer.parseInt(s));
		}
		
		int size = old.size() > now.size() ? old.size() : now.size();
		
		for(int i = 0; i < size; i++){
			if(now.get(i) > old.get(i)){
				str = "刚刚".equals(getLast(i)) ? "刚刚" : (now.get(i) - old.get(i)) + getLast(i);
				break;
			}
		}
		
		return str;
	}
	
	/**
	 * 返回尾字符串
	 * @param i
	 * @return
	 */
	public String getLast(int i){
		String s = "";
		switch (i) {
			case 0:
				s = "年前";
				break;
			case 1:
				s = "个月前";
				break;
			case 2:
				s = "天前";
				break;
			case 3:
				s = "小时前";
				break;
			case 4:
				s = "分钟前";
				break;
			case 5:
				s = "刚刚";
				break;
			}
		return s;
	}


}
