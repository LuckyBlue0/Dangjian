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
import com.do1.aqzhdj.activity.circle.CirclMemberListActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;

public class CircleMemberListAdapter extends BaseAdapter {
	private Context ctx;
	private List<Map<String, Object>> listMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;
	private int type;
	private AQuery aq;

	public CircleMemberListAdapter(Context ctx,
			List<Map<String, Object>> lsmap, int itemTemplateId,int type) {
		super();
		this.itemTemplateId = itemTemplateId;
		this.ctx = ctx;
		this.listMap = lsmap;
		this.type = type;
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
		Log.e(listMap.toString());
		aq = new AQuery(convertView);
		aq.id(R.id.memberImage).image(
				listMap.get(position).get("head_img").toString(), true, true,
				0, 0);
		aq.id(R.id.name).text(listMap.get(position).get("cname").toString());
		int isCreate = Integer.valueOf(listMap.get(position).get("is_create").toString());
		
		if(type != 0){
			if(isCreate == 1){
				aq.id(R.id.add).gone();
			}else{
				if (CirclMemberListActivity.state == 0) {
					aq.id(R.id.add).gone();
				} else if (CirclMemberListActivity.state == 1) {
					aq.id(R.id.add).visible();
				}
			}
		}else{
			aq.id(R.id.add).gone();
		}
		
		int type = Integer.valueOf(listMap.get(position).get("type").toString());
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
					HttpClient client = new DefaultHttpClient();
					String url = Constants.SERVER_URL+ "CircleMemberManage.aspx";
					HttpPost post = new HttpPost(url);
					JSONObject jsonData = new JSONObject();
					jsonData.put("community_id", Constants.circleInfo.getId());
					jsonData.put("opuser_id", Constants.sharedProxy.getString("keyId", ""));
					jsonData.put("user_id", listMap.get(position).get("user_id").toString());
					jsonData.put("opertype", "1");
					String params = Entryption.encode(jsonData.toString());
					StringEntity s = new StringEntity(params);
					s.setContentEncoding("UTF-8");
					s.setContentType("application/json");
					post.setEntity(s);
					HttpResponse res = client.execute(post);
					if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String data =  EntityUtils.toString(res.getEntity());
						data = Entryption.decode(data);
						Log.e(data);
						String ss = JsonUtil.json2Map(data).get("code").toString();
						int key = Integer.valueOf(ss);
						if(key == 0){
							ToastUtil.showShortMsg(ctx, "删除失败");
						}else if(key == 1){
							ToastUtil.showShortMsg(ctx, "删除成功");
							listMap.remove(position);
							CircleMemberListAdapter.this.notifyDataSetChanged();
						}
					}else{
						ToastUtil.showShortMsg(ctx, "删除失败");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return convertView;
	}
}
