package com.do1.aqzhdj.activity.mine.box;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.com.do1.component.util.Log;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.box.down.CondimentDialog;
import com.do1.aqzhdj.activity.mine.box.down.DownLoadAsyncTask;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.ValidUtil;
import com.do1.aqzhdj.widght.pager.ViewPageControll;

/**
 * 礼仪歌曲、党务流程指引列表
 * auth:YanFangqin
 * data:2013-4-28
 * thzhd
 */
public class BoxSingActivity extends BaseListActivity implements ItemViewHandler{

	private String type = "1";//1：礼仪歌曲，2：党务流程指引
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		aq = new AQuery(this);
		context = this;
		setCusItemViewHandler(this);
		type = getIntent().getStringExtra("type") != null ? getIntent().getStringExtra("type") : "1"; 
		String[] keys = new String[]{"name"};
        int[] ids = new int[]{R.id.title};
        Map<String, Object> map = new HashMap<String, Object>();
        if(!"1".equals(type)){
        	ViewPageControll.needReflesh = false;
        }
        map.put("type", type);
        setAdapterParams(keys,ids,R.layout.box_sing_item2,map);
	}
	
	@Override
	public void setHeadMethod() {
		String title = "礼仪歌曲";
		if("1".equals(type))
			title = "礼仪歌曲";
		else
			title = "党务流程指引";
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", title, 0,"", null, null);
		
	}

	@Override
	public void setRequestMethod() {
		method = AppManager.singUrl;
	}
	
	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.itemClick(parent, view, position, id);
		
		TextView v = (TextView) view.findViewById(R.id.title);
		
		if(view.findViewById(R.id.image).getTag() != null && "9".equals(view.findViewById(R.id.image).getTag().toString())){
			CondimentDialog dialog = new CondimentDialog(context, R.style.dialog, 3);
			dialog.setCanceledOnTouchOutside(false);
			dialog.updateDialog2("打开","是否打开该文件？",v.getText().toString());
			dialog.show();
		}else{
			if("1".equals(type)){
				String fileUrl = mSlpControll.getmListData().get(position).get("url").toString();
				String fileName = mSlpControll.getmListData().get(position).get("name").toString();
				new DownLoadAsyncTask(context,1).execute(fileUrl,fileName);
			}
		}
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		
		TextView v = (TextView) itemView.findViewById(R.id.title);
		String path = DownLoadAsyncTask.savePath + v.getText().toString();
		String size = mSlpControll.getmListData().get(position).get("size")+"";
		File file = new File(path);
		float fdata = Float.valueOf(!"".equals(size) ? size : "0");//返回来的大小
		Log.i("========返回来的文件大小：" + fdata);
		if(file != null && file.exists()){
			float ffile = getFileM(file);
			Log.i("========获取的文件大小：" + ffile);
			if(!ValidUtil.isNullOrEmpty(size) && fdata - ffile < 0.1){
				((TextView)itemView.findViewById(R.id.image)).setText("已下载");
				itemView.findViewById(R.id.image).setTag(9);
				itemView.findViewById(R.id.image).setVisibility(View.VISIBLE);
			}else{
				if("2".equals(type)){
					((TextView)itemView.findViewById(R.id.image)).setText("预  览");
					final int po = position;
					itemView.findViewById(R.id.image).setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if(v.getTag() == null || !"9".equals(v.getTag().toString())){
								String fileUrl = mSlpControll.getmListData().get(po).get("url").toString();
								String fileName = mSlpControll.getmListData().get(po).get("name").toString();
								new DownLoadAsyncTask(context,2).execute(fileUrl,fileName);
							}
						}
					});
				}else{
					itemView.findViewById(R.id.image).setVisibility(View.GONE);
				}
			}
		}else{
			if("2".equals(type)){
				((TextView)itemView.findViewById(R.id.image)).setText("预  览");
				final int po = position;
				itemView.findViewById(R.id.image).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(v.getTag() == null || !"9".equals(v.getTag().toString())){
							String fileUrl = mSlpControll.getmListData().get(po).get("url").toString();
							String fileName = mSlpControll.getmListData().get(po).get("name").toString();
							new DownLoadAsyncTask(context,2).execute(fileUrl,fileName);
						}
					}
				});
			}else{
				itemView.findViewById(R.id.image).setVisibility(View.GONE);
			}
		}
	}
	
	/**
	 * 根据文件路劲获取文件大小
	 * @param filePath
	 */
	private float getFileM(File f) {
		try {
			FileInputStream fis = new FileInputStream(f);
			double fileByte = fis.available();
			double fileM = fileByte/1024/1024;
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
			String fileMs = df.format(fileM);
			Log.i("文件大小:" + fileMs);
			return Float.valueOf(fileMs);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return 0;
	}

}
