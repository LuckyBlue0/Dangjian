package com.do1.aqzhdj.activity.bbs.wall;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;
import cn.com.do1.component.util.ViewUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.ImageBase64Util;
import com.do1.aqzhdj.util.ImageCompress;
import com.do1.aqzhdj.util.SDCardUtil;
import com.do1.aqzhdj.util.SecurityDes3Util;
import com.do1.aqzhdj.util.SecurityUtil;
import com.do1.aqzhdj.util.ValidUtil;

/**
 * TODO:评论页面 User:YanFangqin Date:2013-6-4 ProjectName:thzhd
 */
public class CommentActivity extends BaseActivity {

	private Context context;
	private String wallName;// 主题名称
	private String wallId;

	// 上传图片
	private static final int CAMERA_WITH_DATA = 3021; // 拍照标识符
	private static final int LOCAL_WITH_DATA = 3023; // 本地图片标识符
	private ImageView photo;
	private String imagePath = SDCardUtil.getTakingPhotoDir() + File.separator
			+ "IMAG_comment" + SDCardUtil.getFileName() + ".jpg";
	private Uri imageUri = Uri.parse("file://" + imagePath);
	private String picPhotoPath = "";// 拍照图片路径
	private ProgressDialog progressDialog = null;
	private String piccontent = ""; // 图片内容
	private static List<String> imgMapList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);

		context = this;
		aq = new AQuery(this);
		wallId = getIntent().getStringExtra("wallId") != null ? getIntent()
				.getStringExtra("wallId") : "";
		wallName = getIntent().getStringExtra("wallName") != null ? getIntent()
				.getStringExtra("wallName") : "";
		initItems();
	}

	public void initItems() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "评论", R.drawable.btn_head_2, "发布", null, this);
		aq.id(R.id.name).text(wallName);
		aq.id(R.id.tes)
				.text(Html
						.fromHtml("添加图片<font color='#8d8d8d' size='12'>（可填）</font>"));
		photo = aq.id(R.id.image).getImageView();

		ListenerHelper.bindOnCLickListener(this, photoClick, R.id.image);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			// Toast.makeText(this, "发布成功", 0).show();
			// finish();
			ViewUtil.hideInputMethod(this);
			saveImage();
			break;
		}
	}

	Handler rHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				progressDialog.setMessage("正在提交,请稍后...");
			} else {
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				findViewById(R.id.rightImage).setClickable(true);
			}
		}
	};

	public void saveImage() {
		String userid = constants.userInfo.getUserId();
		String title = aq.id(R.id.name).getText().toString();
		String des = aq.id(R.id.content).getText().toString();
		if (!valid()) {
			rHandler.sendEmptyMessage(0);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", wallId);
			map.put("userId", userid);
			map.put("content", aq.id(R.id.content).getText().toString().trim());
			if (imgMapList.size() > 0) {
				map.put("photos", imgMapList);
			}
			Map<String, String> map2 = jiami(map);
			clearCache();
			String url = SERVER_URL + getString(R.string.wall_comment);

			doRequest(1, url, map2);
		}
		// progressDialog = ProgressDialog.show(context, "温馨提示", "正在准备数据...");
		// progressDialog.setCanceledOnTouchOutside(false);
		// findViewById(R.id.rightImage).setClickable(false);
		// if (!valid()) {
		// rHandler.sendEmptyMessage(0);
		// } else {
		// new Thread() {
		// public void run() {
		// // Map<String, String> map = new HashMap<String, String>();
		// // map.put("user_id", constants.userInfo.getUserId());
		// // map.put("content",
		// aq.id(R.id.content).getText().toString().trim());
		// // map.put("wall_id", wallId);
		// // map.put("picAddr",
		// aq.id(R.id.address).getText().toString().trim());
		// String url = SERVER_URL + getString(R.string.wall_comment);
		//
		// // String key = Entryption.getJsonKey2(map);
		// String key = "{" +
		// "\"user_id\":\"" + constants.userInfo.getUserId() +
		// "\",\"content\":\"" + aq.id(R.id.content).getText().toString().trim()
		// +
		// "\",\"wall_id\":\"" + wallId +
		// "\",\"picAddr\":\"" + aq.id(R.id.address).getText().toString().trim()
		// + "\"}";
		//
		// String s = "";
		// for (Map<String, String> imap : imgMapList) {
		// // s += "," + Entryption.getJsonKey2(imap);
		// s += "," + "{" +
		// "\"img\":\"" + imap.get("img") +
		// "\",\"name\":\"" + imap.get("name") + "\"}";
		// }
		// s = s.replaceFirst(",", "");
		// s = "\"photos\":" + "{\"list\":[" + s + "]}";
		//
		// key = key.replace("{", "");
		// key = "{" + s + "," + key;
		// // WriteTxtFile(key, "/sdcard/commentText.txt");
		// JSONObject json = null;
		// try {
		// json = new JSONObject(key);
		// rHandler.sendEmptyMessage(1);
		// // doRequestPostString(99999, url,
		// Entryption.encode(json.toString()));
		// HttpClient client = new DefaultHttpClient();
		// HttpPost post = new HttpPost(url);
		// String params = Entryption.encode(json.toString());
		// StringEntity se = new StringEntity(params);
		// se.setContentEncoding("UTF-8");
		// se.setContentType("application/json");
		// post.setEntity(se);
		// HttpResponse res = client.execute(post);
		// if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		// String data = EntityUtils.toString(res.getEntity());
		// data = Entryption.decode(data);
		// Log.i(data);
		// String ss = JsonUtil.json2Map(data).get("code").toString();
		// int rekey = Integer.valueOf(ss);
		// if(rekey == 0){
		// handler.sendEmptyMessage(0);
		// }else if(rekey == 1){
		// handler.sendEmptyMessage(1);
		// }
		// }else{
		// handler.sendEmptyMessage(1);
		// }
		// } catch (JSONException e) {
		// rHandler.sendEmptyMessage(0);
		// e.printStackTrace();
		// } catch (Exception e) {
		// rHandler.sendEmptyMessage(0);
		// e.printStackTrace();
		// }
		//
		// };
		// }.start();
		// }
	}

	public static Map<String, String> jiami(Map<String, Object> map) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		System.out.println("params:::" + map);
		Object key; // 从map中取得的单个key
		String keys = ""; // 需要拼接的所有的参数
		String digest = ""; // 加密值
		Set set = map.keySet();
		Iterator ite = set.iterator();
		while (ite.hasNext()) {
			try {
				key = ite.next();
				keys += map.get(key) + "";
				if (key.equals("photos")) {
					System.out.println("map.get(key):" + map.get(key));// [16aff461-25f9-4c50-890a-6e7befb2a4e1,
					// f42a5eae-6f02-447d-aa35-9f764a254f7f]
					String substr = map.get(key).toString()
							.substring(1, map.get(key).toString().length() - 1);
					String[] userIds = substr.split(",");
					JSONArray a = new JSONArray();
					for (int i = 0; i < userIds.length; i++) {
						System.out.println("userIds:" + userIds[i] + "");
						String str = SecurityDes3Util.encode(userIds[i]
								.toString().trim());
						a.put(i, str);
					}
					params.put(key + "", a);
				} else {

					System.out.println("111111111111111111111");
					System.out.println("key: " + key + ";根据key 获取到的值: "
							+ map.get(key) + ";加密后的值: "
							+ SecurityDes3Util.encode(map.get(key) + ""));
					// ["["3a91babe-d5a3-47a3-9e1e-85892a10ee6c","
					// 051231e2-e637-440b-b091-7910584575b"]"]
					params.put(key + "",
							SecurityDes3Util.encode(map.get(key) + ""));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("所有key拼接后的keys: " + keys);
		params.put("digest", SecurityUtil.encryptToMD5(keys).toLowerCase());
		System.out.println("params: " + params);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("requestJson", toJsonString(params));
		System.out.println("最后生成的requestjson: " + map2);
		return map2;
	}

	public static String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json + "";
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				findViewById(R.id.rightImage).setClickable(true);
				ToastUtil.showLongMsg(context, "评论失败");
				break;
			case 1:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				clearCache();
				ToastUtil.showLongMsg(context, "评论成功");
				AppManager.needFlesh = true;
				finish();
				break;
			case 2:

				break;
			}
		};
	};

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);

		// if (progressDialog != null && progressDialog.isShowing()) {
		// progressDialog.dismiss();
		// }
		clearCache();
		imgMapList.clear();
		ToastUtil.showLongMsg(context, "评论成功");
//		AppManager.needFlesh = true;
		Intent intent =new Intent(this,WallPhotoInfoActivity.class);
		startActivity(intent);
		for(int i=0;i<AppManager.list.size();i++){
			AppManager.list.get(i).finish();
		}
		finish();
	}

	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		super.onExecuteFail(requestId, resultObject);

		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
//		clearCache();
//		imgMapList.clear();
		findViewById(R.id.rightImage).setClickable(true);
		ToastUtil.showShortMsg(context, resultObject.getDesc());
	}

	public boolean valid() {
		if (ValidUtil.isNullOrEmpty(aq.id(R.id.content).getText().toString())) {
			ToastUtil.showShortMsg(context, "请输入评论内容");
			return false;
		}
		if (photo.getDrawable() != null) {
			piccontent = new ImageBase64Util().getBitmapStrBase64(ImageCompress
					.getBitmapByPath(imagePath));
			// Map<String, String> imgMap = new HashMap<String, String>();
			// imgMap.put("img", piccontent);
			// imgMap.put("name", UUID.randomUUID().toString() + ".jpg");
			imgMapList.add(piccontent);
		}
		return true;
	}

	// private Bitmap decodeUriAsBitmap(Uri uri) {
	// Bitmap bitmap = null;
	// try {
	// bitmap =
	// BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// return null;
	// }
	// return bitmap;
	// }

	private OnClickListener photoClick = new OnClickListener() {
		@Override
		public void onClick(View v) {

			Builder imageialog = new AlertDialog.Builder(context);
			String[] items = { "从本地获取", "照相" };
			imageialog
					.setTitle("请选择操作")
					.setSingleChoiceItems(
							items,
							-1,
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 从本地获取
										Intent intent = new Intent(
												Intent.ACTION_GET_CONTENT);
										intent.addCategory(Intent.CATEGORY_OPENABLE);
										intent.setType("image/*");
										startActivityForResult(intent,
												LOCAL_WITH_DATA);
									} else {// 照相
										Intent intent = new Intent(
												MediaStore.ACTION_IMAGE_CAPTURE,
												null);
										String sdpath = SDCardUtil
												.getTakingPhotoDir()
												+ File.separator
												+ SDCardUtil.getPhotoFileName();
										File file = new File(SDCardUtil
												.getTakingPhotoDir(),
												SDCardUtil.getPhotoFileName());
										intent.putExtra("imagePath", sdpath);
										picPhotoPath = sdpath;
										intent.putExtra(
												MediaStore.EXTRA_OUTPUT,
												Uri.fromFile(file));// 输出文件位置
										startActivityForResult(intent,
												CAMERA_WITH_DATA);
									}
									dialog.dismiss();
								}
							}).create().show();
		}
	};

	public void startPhotoZoom(Uri uri, String path) {
		/*
		 * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * 直接在里面Ctrl+F搜：CROP ，之前没仔细看过，其实安卓系统早已经有自带图片裁剪功能, 是直接调本地库的
		 */
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		intent.putExtra("scale", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 5);
		intent.putExtra("aspectY", 3);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 700);
		intent.putExtra("outputY", 420);
		intent.putExtra("return-data", false);
		File file = new File(path);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		intent.putExtra("noFaceDetection", true);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (data != null) {
				if (imageUri != null) {
					// photo.setImageURI(imageUri);
					photo.setImageDrawable(new BitmapDrawable(
							getBitpMap(imageUri)));
					aq.id(R.id.address).enabled(true);
				}
			}
			break;
		case CAMERA_WITH_DATA:
			if (resultCode == RESULT_OK) {
				if (!ValidUtil.isNullOrEmpty(picPhotoPath)) {
					File file = new File(picPhotoPath);
					if (file != null) {
						startPhotoZoom(Uri.fromFile(file), imagePath);
					}
					picPhotoPath = "";
				}
			}
			break;
		case LOCAL_WITH_DATA:
			if (resultCode == RESULT_OK) {
				startPhotoZoom(data.getData(), imagePath);
			}
			break;
		}
	}

	public static void WriteTxtFile(String strcontent, String strFilePath) {
		// 每次写入时，都换行写
		String strContent = strcontent + "\n";
		try {
			File file = new File(strFilePath);
			if (!file.exists()) {
				Log.d("TestFile:" + "Create the file:" + strFilePath);
				file.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(file.length());
			raf.write(strContent.getBytes());
			raf.close();
		} catch (Exception e) {
			Log.e("TestFile:" + "Error on write File.");
		}
	}

	Bitmap getBitpMap(Uri uri) {
		ParcelFileDescriptor pfd;
		try {
			pfd = this.getContentResolver().openFileDescriptor(uri, "r");
		} catch (IOException ex) {
			return null;
		}
		java.io.FileDescriptor fd = pfd.getFileDescriptor();
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 先指定原始大小
		options.inSampleSize = 1;
		// 只进行大小判断
		options.inJustDecodeBounds = true;
		// 调用此方法得到options得到图片的大小
		BitmapFactory.decodeFileDescriptor(fd, null, options);
		// 我们的目标是在800pixel的画面上显示。
		// 所以需要调用computeSampleSize得到图片缩放的比例
		// options.inSampleSize = computeSampleSize(options, 1280);
		options.inSampleSize = 3;
		// OK,我们得到了缩放的比例，现在开始正式读入BitMap数据
		options.inJustDecodeBounds = false;
		options.inDither = false;
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;

		// 根据options参数，减少所需要的内存
		Bitmap sourceBitmap = BitmapFactory.decodeFileDescriptor(fd, null,
				options);
		return sourceBitmap;
	}

	// 这个函数会对图片的大小进行判断，并得到合适的缩放比例，比如2即1/2,3即1/3
	static int computeSampleSize(BitmapFactory.Options options, int target) {
		int w = options.outWidth;
		int h = options.outHeight;
		int candidateW = w / target;
		int candidateH = h / target;
		int candidate = Math.max(candidateW, candidateH);
		if (candidate == 0)
			return 1;
		if (candidate > 1) {
			if ((w > target) && (w / candidate) < target)
				candidate -= 1;
		}
		if (candidate > 1) {
			if ((h > target) && (h / candidate) < target)
				candidate -= 1;
		}
		return candidate;
	}

	public Bitmap drawableToBitamp(Drawable drawable) {
		BitmapDrawable bd = (BitmapDrawable) drawable;
		return bd.getBitmap();
	}
}
