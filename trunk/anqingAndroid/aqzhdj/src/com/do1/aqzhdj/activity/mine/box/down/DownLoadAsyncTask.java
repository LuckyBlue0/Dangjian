package com.do1.aqzhdj.activity.mine.box.down;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
/**
 * 附件下载
 * @author yanfangqin
 * @Date:2012-4-9
 */
public class DownLoadAsyncTask extends AsyncTask<String, Integer, Integer>{
	protected final String TAG = this.getClass().getSimpleName();
	private Context context;
	private InputStream inputStream;
	private int currentProgress = 0;//当前进度
	CondimentDialog dialog;
	
	public static final String savePath = "/mnt/sdcard/thzhd/";
	private String fileName;
	
	private final static int RESULT_OK = 0;
	private final static int RESULT_ERROR = -1;
	public boolean done = false;
	private String mimetype;
	private int errorCode = 0;
	private int type;//type=1;礼仪歌曲，2：党务流程指引；3：党情速递
	
	public DownLoadAsyncTask(Context context,int type) {
		super();
		this.context = context;
		this.type = type;
	}

	@Override
	protected Integer doInBackground(String... params) {
		
		Log.d(TAG, "#####doInBackground######");
		Log.e(TAG, "doInBackground--"+Thread.currentThread().getName());
		FileOutputStream fos = null;
    	try {
    		String url = params[0];
       		if (!TextUtils.isEmpty(url)){
    			url = url.trim();
    		}
    		mimetype = getFileType(url);
    		Log.e(TAG, "url=[" + url + "],mimetype=[" + mimetype + "]");
    		
    		fileName = params[1];
        	Log.e("fileName=",fileName);
            File f = createFile(savePath,fileName);
            fos = new FileOutputStream(f);
             
    		int cnTimeout = 60000;
    		int timeoutSocket = 60000;
    		HttpParams httpParameters = new BasicHttpParams();
    		HttpConnectionParams.setConnectionTimeout(httpParameters, cnTimeout);
    		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
    		HttpClient httpClient = new DefaultHttpClient(httpParameters);
    		System.out.println("url="+url);
    		HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			int stateCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("stateCode="+stateCode);
			if (stateCode != HttpStatus.SC_OK){
				return RESULT_ERROR;
			}
			
			HttpEntity entity = httpResponse.getEntity();
	        byte[] buffer = new byte[2048];
	        long contentLength = entity.getContentLength();
	        Log.d(TAG, "contentLength="+contentLength);
	        if (contentLength < 0) {
	        	contentLength = 3845038;
	        }
	        if (contentLength <= 0){
	        	return RESULT_ERROR;
	        }
	        
	        int downdedLen= 0;
	        int len = 0;
	        inputStream = entity.getContent();
	        while ((len = inputStream.read(buffer)) >= 0){
	        	int progress = (int)(downdedLen*100/contentLength);
	        	downdedLen += len;
	    		fos.write(buffer, 0, len);
        		fos.flush();
	        	if (progress > currentProgress){
	        		currentProgress = progress;
	        		publishProgress(progress);
	        	}
	        }
	        return RESULT_OK;
    	}catch (IOException e2) {
    		errorCode = 1;
    		e2.printStackTrace();
		} catch (Exception e) {
			errorCode = 0;
    		Log.e("", e.getMessage(), e);
			e.printStackTrace();
		}finally{
				try {
					if (inputStream!=null){
						inputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					inputStream = null;
				}
				
				try {
					if (fos!=null){
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					fos = null;
				}
		}
    	return RESULT_ERROR;
	}
	
	public void show(){
		 new AlertDialog.Builder(context)
			.setTitle("温馨提示")
			.setMessage("没有找到手机sdcard")
			.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							dialog.dismiss();
						}
					}).show();
	}

	
	private File createFile(String filePath,String fileName) throws IOException{
		System.out.println("filePath:"+filePath+fileName);
        File f = new File(filePath);
        if(!f.exists()){
        	f.mkdirs();
		}
        f = new File(filePath,fileName);
        if(!f.exists()){
        	f.createNewFile();
        }
        return f;
	}
	
	 @Override
	protected void onProgressUpdate(Integer... values) {
		 int progress = values[0];
		 Log.d(TAG, "#####onProgressUpdate######,progress="+progress);
		 dialog.setProgressBar(progress);
		 if(dialog.stopLine){
			 if(!isCancelled()){
				 cancel(true);
			 }
		 }
	}

	/**
	  * 执行网络访问方法之前进行的处理，这里显示加载数据框
	  */
   @Override  
   protected void onPreExecute() {  
	  	Log.d(TAG, "#####onPreExecute######");
	  	dialog = new CondimentDialog(context, R.style.dialog, type);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
   }  
   
   /**
    * 得到文件类型用于打开
    * @return
    */
   public String getFileType(String url){
	   cn.com.do1.component.util.Log.e(url);
	   String type = url.substring(url.lastIndexOf("."));
	   if(".doc".equalsIgnoreCase(type)){
		   mimetype = "application/msword";
	   }else if(".docx".equalsIgnoreCase(type)){
		   mimetype = "application/msword";
	   }else if(".ppt".equalsIgnoreCase(type)){
		   mimetype = "application/vnd.ms-powerpoint";
	   }else if(".xls".equalsIgnoreCase(type)){
		   mimetype = "application/vnd.ms-excel";
	   }else if(".xlsx".equalsIgnoreCase(type)){
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
	   }else{
		   mimetype = "*/*";
	   }
	   return mimetype;
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
	   	    intent.setDataAndType(Uri.fromFile(new File(savePath, fileName)),mimetype);  
	   	    context.startActivity(intent);   
       }catch (ActivityNotFoundException e) {
       		ToastUtil.showShortMsg(context, "您没有安装打开该程序的工具");
       }
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		if (result == RESULT_OK){
			CacheFileClearThread.addCacheFile(savePath+fileName);
			done = true;
			dialog.setProgressBar(100);
			dialog.setFileData(fileName, savePath);
			if(type == 2){//如果是党务流程指引，就直接打开
				dialog.dismiss();
				openFile();
			}
		}else{
			String tips = "下载出现异常";
			if(errorCode == 1){
				tips = "下载失败,没有找到手机sdcard";
				errorCode = 0;
			}else if(errorCode == 2){
				tips = "下载失败,请登录";
				errorCode = 0;
			}else{
				errorCode = 0;
				tips = "下载失败,附件不存在";
			}
			
//			showDialog(tips);
//			if(dialog != null && dialog.isShowing())
//				dialog.dismiss();
			dialog.updateDialog(tips);
		}
	}
	
	public void showDialog(String msg){
		new AlertDialog.Builder(context)
		.setTitle("温馨提示")
		.setMessage(msg)
		.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
						dialog.dismiss();
					}
				}).show();
	}
}
