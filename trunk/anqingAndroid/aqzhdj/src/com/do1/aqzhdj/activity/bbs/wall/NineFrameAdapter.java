package com.do1.aqzhdj.activity.bbs.wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.com.do1.component.util.Log;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.ImageViewTool;

/**
 * 不规则列表加载视图
 * auth:YanFangqin
 * data:2013-4-28
 * thzhd
 */
public class NineFrameAdapter extends BaseAdapter{

	private Context context;
	private List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
	
	/**
	 * 留影墙adapter构造函数
	 * @param context
	 * @param maplist
	 */
	public NineFrameAdapter(Context context,List<Map<String, Object>> maplist){

		this.context = context;
		this.dataList = maplist;
	}
	
	public int getCount() {
		int a;
		if(dataList.size() % 9 == 0){
			a = dataList.size()/9;
		}else{
			a = dataList.size()/9 + 1;
		}
		return a;
	}

	public Object getItem(int position) {
		return dataList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = View.inflate(context, R.layout.nine_frame_item, null);
		}
		try {
			int[] ids = {R.id.image1,R.id.image2,R.id.image3,R.id.image4,R.id.image5,R.id.image6,R.id.image7,R.id.image8,R.id.image9};
			for(int i = 0; i < ids.length; i++){
				int dataposition = i+(position*9);
				if(dataList.size() > dataposition){
					String loadImageUrl =dataList.get(dataposition).get("imgUrl").toString();
					ImageView imageView = (ImageView) convertView.findViewById(ids[i]);
					ImageViewTool.getAsyncImageBg(loadImageUrl, imageView, 0);
					final String imgTag = dataList.get(dataposition).get("id").toString() ;
					
//							+ "," +
//									dataList.get(dataposition).get("name").toString() + "," +
//									dataList.get(dataposition).get("author").toString() + "," +
//									dataList.get(dataposition).get("create_time").toString() + "," +
//									dataList.get(dataposition).get("wall_id").toString();
					imageView.setTag(imgTag);
					imageView.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Log.e("[" + v.getTag() + "]");
							String[] tags = (v.getTag()+"").split(",");
							Intent intent = new Intent(context,WallPhotoInfoActivity.class);
							AppManager.wallid=imgTag;
//							intent.putExtra("id",imgTag);
//							intent.putExtra("wallId", tags[4]);
//							intent.putExtra("createTime", tags[3]);
//							intent.putExtra("author", tags[2]);
//							intent.putExtra("wallName", tags[1]);
//							intent.putExtra("wallDesc", tags[0]);
							context.startActivity(intent);
						}
					});
				}else{
					int a = (dataList.size() % 9);
					for(int j = 0; j < 9; j++){
						if(j >= a){
							convertView.findViewById(ids[i]).setVisibility(View.INVISIBLE);
							if(a <= 3){
								convertView.findViewById(R.id.twoRow).setVisibility(View.GONE);
								convertView.findViewById(R.id.threeRow).setVisibility(View.GONE);
							}else if(a <= 6){
								convertView.findViewById(R.id.threeRow).setVisibility(View.GONE);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}
}
