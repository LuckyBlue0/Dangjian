package com.do1.aqzhdj.activity.circle.adapter;

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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;

public class ManagementCircleMemberListAdapter extends BaseAdapter{

	private Context ctx;
	private List<Map<String, Object>> listMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;

	private AQuery aq;

	public ManagementCircleMemberListAdapter(Context ctx,
			List<Map<String, Object>> lsmap, int itemTemplateId) {
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
		if (convertView == null) {
			convertView = mInflater.inflate(itemTemplateId, null);
		}
		aq = new AQuery(convertView);
		aq.id(R.id.memberImage).image(
				listMap.get(position).get("head_img").toString(), true, true,
				0, 0);
		aq.id(R.id.name).text(listMap.get(position).get("cname").toString());

		int type = Integer
				.valueOf(listMap.get(position).get("type").toString());
		switch (type) {
		case 1:
			aq.id(R.id.memberIcon).visible();
			break;

		case 2:
			aq.id(R.id.memberIcon).gone();
			break;

		default:
			break;
		}

		aq.id(R.id.add).getButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String url = Constants.SERVER_URL+ "CircleMemberManage.aspx";
					String data = httpItem(url, position,"2");
					Log.e(data);
					String ss = JsonUtil.json2Map(data).get("code").toString();
					int key = Integer.valueOf(ss);
					if(key == 0){
						ToastUtil.showShortMsg(ctx, "添加失败");
					}else if(key == 1){
						ToastUtil.showShortMsg(ctx, "添加成功");
						AppManager.needFreshForCircleClass = true;
						listMap.remove(position);
						ManagementCircleMemberListAdapter.this.notifyDataSetChanged();
						int count = Integer.valueOf(Constants.circleInfo.getApplyNum());
						Constants.circleInfo.setApplyNum((count-1)+"");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		aq.id(R.id.remove).getButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String url = Constants.SERVER_URL+ "CircleMemberManage.aspx";
					String data = httpItem(url, position,"3");
					Log.e(data);
					String ss = JsonUtil.json2Map(data).get("code").toString();
					int key = Integer.valueOf(ss);
					if(key == 0){
						ToastUtil.showShortMsg(ctx, "删除失败");
					}else if(key == 1){
						ToastUtil.showShortMsg(ctx, "删除成功");
						listMap.remove(position);
						ManagementCircleMemberListAdapter.this.notifyDataSetChanged();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return convertView;
	}
	
	private String httpItem(String url,int position,String type) throws Exception{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject jsonData = new JSONObject();
		jsonData.put("community_id", Constants.circleInfo.getId());
		jsonData.put("opuser_id", Constants.sharedProxy.getString("keyId", ""));
		jsonData.put("user_id", listMap.get(position).get("user_id").toString());
		jsonData.put("opertype", type);
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
//		else{
//			ToastUtil.showShortMsg(ctx, "删除失败");
//			
//		}
		return null;
	}
	
}
