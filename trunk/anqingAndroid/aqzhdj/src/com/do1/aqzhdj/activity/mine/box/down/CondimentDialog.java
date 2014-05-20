package com.do1.aqzhdj.activity.mine.box.down;

import java.io.File;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.util.ValidUtil;

/**
 * 自定义弹出框
 * auth:YanFangqin
 * data:2013-4-24
 * thzhd
 */
public class CondimentDialog extends Dialog {

	public Context mContext;
	public ProgressBar progressBar;
	public TextView progressText;
	public Button sure;
	public Button cancle;
	public TextView tipText;
	public boolean stopLine = false;
	public boolean done = false;
	public String mimetype;//文件类型
	public String savePath = "/mnt/sdcard/thzhd/";
	public String fileName;
	public int type;//type=1;礼仪歌曲，2：党务流程指引

	public CondimentDialog(Context context, int theme, int type) {
		super(context, theme);
		this.mContext = context;
		this.type = type;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_condim);
		LayoutParams params = getWindow().getAttributes();
		params.height = LayoutParams.WRAP_CONTENT;
		params.width = LayoutParams.WRAP_CONTENT;
		getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		progressText = (TextView) findViewById(R.id.progressText);
		sure = (Button) findViewById(R.id.sure);
		cancle = (Button) findViewById(R.id.cancle);
		tipText = (TextView) findViewById(R.id.tipText);
		cancle.setOnClickListener(onclick);
		if(type == 2){
			sure.setVisibility(View.GONE);
		}else if(type == 3){
			sure.setVisibility(View.GONE);
		}
	}
	
	public void setProgressBar(int value){
		progressBar.setProgress(value);
		progressText.setText("已下载" + value + "%");
		if(value == 100){
			if(type == 1){//礼仪歌曲
				done = true;
				cancle.setText("确定");
				tipText.setVisibility(View.VISIBLE);
				sure.setClickable(true);
				sure.setOnClickListener(onclick);
			}else if(type == 3){//党情速递
				done = true;
				cancle.setText("确定");
				tipText.setVisibility(View.VISIBLE);
//				sure.setText("打开文件夹");
//				sure.setClickable(true);
//				sure.setOnClickListener(onclick);
			}
		}
	}
	
	public void setFileData(String fileName,String savePath){
		this.fileName = fileName;
		if(!ValidUtil.isNullOrEmpty(savePath)){
			this.savePath = savePath;
		}
	}
	
	public View.OnClickListener onclick = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.cancle:
				stopLine = true;
				dismiss();
				break;
			case R.id.sure:
				dismiss();
//				if(".rar".equals(fileName)){
//					openFileInSdcard();
//				}else{
					openFile();
//				}
				break;
			}
		}
	};
	
	/**
	 * 下载出现问题,更新提示的dialog
	 */
	public void updateDialog(String tips){
		sure.setVisibility(View.GONE);
		cancle.setText("确定");
		progressBar.setVisibility(View.GONE);
		progressText.setVisibility(View.GONE);
		tipText.setVisibility(View.VISIBLE);
		tipText.setText(tips);
	}
	
	/**
	 * 当附件已经存在时弹出的提示框
	 * @param sureStr 确定按钮显示的文字
	 * @param tips 弹出内容
	 * @param from 来自：1：礼仪歌曲；2：党务流程指引
	 */
	public void updateDialog2(String sureStr,String tips,String fileName){
		this.fileName = fileName;
		sure.setVisibility(View.VISIBLE);
		sure.setText(sureStr);
		sure.setOnClickListener(onclick);
		cancle.setText("取消");
		progressBar.setVisibility(View.GONE);
		progressText.setVisibility(View.GONE);
		tipText.setVisibility(View.VISIBLE);
		tipText.setText(tips);
		tipText.setTextColor(R.color.black);
	}
	
	/**
	 * 打开指定目录
	 */
	public void openFileInSdcard(){
		//使用Intent
		Intent intent = new Intent(Intent.ACTION_VIEW);
		File file = new File("/mnt/sdcard/thzhd/");
		intent.setDataAndType(Uri.fromFile(file), "*/*");
		mContext.startActivity(intent);
	}
	
	/**
	 * 打开下载文件
	 */
	public void openFile(){
		try{
			mimetype = getFileType(fileName);
			Intent intent = new Intent();  
	   	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	   	    intent.setAction(android.content.Intent.ACTION_VIEW);
	   	    Log.e(new File(savePath, fileName).toString());
	   	    intent.setDataAndType(Uri.fromFile(new File(savePath, fileName)),mimetype);  
	   	    mContext.startActivity(intent);   
        }catch (ActivityNotFoundException e) {
        	ToastUtil.showShortMsg(mContext, "您没有安装打开该程序的工具");
        }
	}
	
	
	/**
	    * 得到文件类型用于打开
	    * @return
	    */
	   public String getFileType(String url){
		   String type = "".equals(url) ? "" : url.substring(url.lastIndexOf("."));
		   if(".doc".equalsIgnoreCase(type)){
			   mimetype = "application/msword";
		   }else if(".docx".equalsIgnoreCase(type)){
			   mimetype = "application/msword";
		   }else if(".ppt".equalsIgnoreCase(type)){
			   mimetype = "application/vnd.ms-powerpoint";
		   }else if(".xls".equalsIgnoreCase(type)){
			   mimetype = "application/vnd.ms-excel";
		   }else if(".txt".equalsIgnoreCase(type)){
			   mimetype = "text/plain";
		   }else if(".pdf".equalsIgnoreCase(type)){
			   mimetype = "application/pdf";
		   }else if(".apk".equalsIgnoreCase(type)){
			   mimetype = "application/vnd.android.package-archive";
		   }else if(".chm".equalsIgnoreCase(type)){
			   mimetype = "application/x-chm";
		   }else if(".vcf".equalsIgnoreCase(type)){
			   mimetype = "text/x-vcard";
		   }else if(".swf".equalsIgnoreCase(type)){
			   mimetype = "flash/*";
		   }else if(".wps".equalsIgnoreCase(type)){
			   mimetype = "application/msword";
		   }else if(".mp3".equalsIgnoreCase(type)){
			   mimetype = "audio/*";
		   }else if(".mp4".equalsIgnoreCase(type)){
			   mimetype = "video/mp4";
		   }else if(".rar".equalsIgnoreCase(type)){
			   mimetype = "application/x-rar-compressed";
		   }else{
			   mimetype = "*/*";
		   }
		   return mimetype;
	   }
}
