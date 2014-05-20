package com.do1.aqzhdj.activity.mine.box;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.com.do1.component.util.Log;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.box.down.CondimentDialog;
import com.do1.aqzhdj.activity.mine.box.down.DownLoadAsyncTask;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.widght.pager.ViewPageControll;

/**
 * TODO:身边党史
 * User:YanFangqin
 * Date:2013-6-5
 * ProjectName:thzhd
 */
public class ZcwjListActivity extends BaseListActivity implements ItemViewHandler{

	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		aq = new AQuery(this);
		context = this;
		setCusItemViewHandler(this);
		String[] keys = new String[]{"name"};
        int[] ids = new int[]{R.id.title};
        Map<String, Object> map = new HashMap<String, Object>();
        ViewPageControll.needReflesh = false;
        setAdapterParams(keys,ids, R.layout.box_sing_item2,map);
        
	}
	
	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "政策文件", 0,"", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.zcwj_list);
	}
	
	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.itemClick(parent, view, position, id);
		
		String fileUrl = mSlpControll.getmListData().get(position).get("url").toString();
		String fileName = mSlpControll.getmListData().get(position).get("name").toString();
		Log.e(fileName+"xxxxxx");
		if(view.findViewById(R.id.image).getTag() != null && "9".equals(view.findViewById(R.id.image).getTag().toString())){
			CondimentDialog dialog = new CondimentDialog(context, R.style.dialog, 3);
			dialog.setCanceledOnTouchOutside(false);
			dialog.updateDialog2("打开","是否打开该文件？",fileName);
			dialog.show();
		}else{
			showDialog(fileUrl,fileName,"确定下载？");
		}
	}
	
	
	/**
	 * 展示提示的dialog
	 * @param msg
	 */
	public void showDialog(final String fileUrl,final String fileName,String msg){
		new AlertDialog.Builder(context)
		.setTitle("温馨提示")
		.setMessage(msg)
		.setPositiveButton("确定",
				new DialogInterface.OnClickListener() { 
					public void onClick(DialogInterface dialog,
							int whichButton) {
						dialog.dismiss();
						new DownLoadAsyncTask(context,3).execute(fileUrl,fileName);
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).create().show();
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		
		TextView v = (TextView) itemView.findViewById(R.id.title);
		v.setText(v.getText());
		
		String path = DownLoadAsyncTask.savePath + v.getText().toString();
		String size = mSlpControll.getmListData().get(position).get("size")+"";
		File file = new File(path);
		Log.e(path+"11xxxxxx");
		float fdata = Float.valueOf(!"".equals(size) ? size : "0");//返回来的大小
		Log.i("========返回来的文件大小：" + fdata);
		Log.e(file.exists()+"11xxxxxx");
		if(file != null && file.exists()){
			float ffile = getFileM(file);
			Log.i("========获取的文件大小：" + ffile);
			if(fdata - ffile < 0.1){
				((TextView)itemView.findViewById(R.id.image)).setText("已下载");
				itemView.findViewById(R.id.image).setTag(9);
				itemView.findViewById(R.id.image).setVisibility(View.VISIBLE);
			}else{
				itemView.findViewById(R.id.image).setVisibility(View.GONE);
			}
		}else{
			itemView.findViewById(R.id.image).setVisibility(View.GONE);
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
