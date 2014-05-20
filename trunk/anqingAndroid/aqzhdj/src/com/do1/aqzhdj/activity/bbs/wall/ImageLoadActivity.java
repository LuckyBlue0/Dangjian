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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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
 * 留影墙-图片上传 auth:YanFangqin data:2013-4-25 thzhd
 */
public class ImageLoadActivity extends BaseActivity {

	private Context context;
	// 照片
	private String piccontent = ""; // 图片内容
	private String piccontent2 = ""; // 图片内容
	private String piccontent3 = ""; // 图片内容
	private static final int CAMERA_WITH_DATA = 3021; // 拍照标识符
	private static final int LOCAL_WITH_DATA = 3023; // 本地图片标识符
	private String picPhotoPath = "";// 拍照图片路径
	private ImageView photo;
	private ImageView photo2;
	private ImageView photo3;
	private Spinner typeSpin;
	private int clickedImg = R.id.image;
	private static List<String> imgMapList = new ArrayList<String>();

	// 参数
//	private String typeId;
//	private String title;
	private List<Drawable> draList = new ArrayList<Drawable>();
	// private Bitmap bitmapc = null;

	private String imagePath = SDCardUtil.getTakingPhotoDir() + File.separator
			+ "IMAG_1" + SDCardUtil.getFileName() + ".jpg";
	private String imagePath1 = SDCardUtil.getTakingPhotoDir() + File.separator
			+ "IMAG_2" + SDCardUtil.getFileName() + ".jpg";
	private String imagePath2 = SDCardUtil.getTakingPhotoDir() + File.separator
			+ "IMAG_3" + SDCardUtil.getFileName() + ".jpg";
	private Uri imageUri = Uri.parse("file://" + imagePath);//
	private Uri imageUri1 = Uri.parse("file://" + imagePath1);//
	private Uri imageUri2 = Uri.parse("file://" + imagePath2);//

	private boolean isMetic = false;// 是否是专题图片

	private ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wall_image_load);
		context = this;
		aq = new AQuery(this);
//		typeId = getIntent().getStringExtra("typeId") == null ? ""
//				: getIntent().getStringExtra("typeId");
//		if ("专题图片".equals(typeId)) {
//			isMetic = true;
//		} else {
//			isMetic = false;
//		}

		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "上传图片", R.drawable.btn_head_2, "发布", null, this);

		photo = aq.id(R.id.image).getImageView();
		photo2 = aq.id(R.id.image2).getImageView();
		photo3 = aq.id(R.id.image3).getImageView();
		typeSpin = aq.id(R.id.typeSpin).getSpinner();
		aq.id(R.id.des).text(
				Html.fromHtml("添加图片<font color='#8d8d8d'>（最多上传3张）</font>"));
		typeSpin.setOnItemSelectedListener(new SpinnerSelectedListener());
		ListenerHelper.bindOnCLickListener(this, photoClick, R.id.image,
				R.id.image2, R.id.image3);
		ListenerHelper.bindOnCLickListener(this, delClick, R.id.delImage,
				R.id.delImage2, R.id.delImage3);
		initSpin();
	}

	public void initSpin() {
		List<String> list = WallUtil.listTypeName;
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
				R.layout.myspinner, list);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typeSpin.setAdapter(arrayAdapter);
		typeSpin.setPrompt("选择类型");
		typeSpin.setSelection(Integer.parseInt(AppManager.typeId));
	}

	class SpinnerSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			AppManager.title= adapterView.getItemAtPosition(position)
					.toString();
			AppManager.typeId=position+"";
//			typeId = WallUtil.getIdByName(selected.trim());
//			typeId=position+"";
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	Handler rHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				progressDialog.setMessage("正在上传,请稍后...");
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
			map.put("userId", userid);
			map.put("type", AppManager.typeId);
//			System.out.println("typeId: " + typeId);
			map.put("title", title);
			map.put("desc", des);
			map.put("photos", imgMapList);
			Map<String, String> map2 = jiami(map);
			clearCache();
			String url = SERVER_URL + getString(R.string.create_photo);

			doRequest(1, url, map2);
		}

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
				ToastUtil.showLongMsg(context, "上传失败");
				break;
			case 1:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				clearCache();
				ToastUtil.showLongMsg(context, "上传成功");
				AppManager.needFlesh = true;
				finish();
				break;
			case 2:

				break;
			}
		};
	};

	@Override
	public void onNetworkError(int requestId) {
		super.onNetworkError(requestId);
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}

		clearCache();
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);

		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}

		clearCache();
		imgMapList.clear();
		ToastUtil.showLongMsg(context, "上传成功");
		AppManager.needFlesh = true;
//		Intent intent =new Intent(this,WallImageListActivity.class);
//		intent.putExtra("typeId", getIntent().getStringExtra("typeId"));
//		startActivity(intent);
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
		if (ValidUtil.isNullOrEmpty(aq.id(R.id.name).getText().toString())) {
			ToastUtil.showShortMsg(context, "请输入图片名称");
			return false;
		}
		if (photo.getDrawable() != null) {
			// piccontent = new ImageBase64Util().getBitmapStrBase64(new
			// ImageBase64Util().drawableToBitmap(photo.getDrawable()));
			// System.err.println("piccontent:" + piccontent);
			// WriteTxtFile(piccontent, "/sdcard/test.txt");

			// piccontent = new ImageBase64Util().getBitmapStrBase64(new
			// ImageBase64Util().drawableToBitmap(photo.getDrawable()));
			piccontent = new ImageBase64Util().getBitmapStrBase64(ImageCompress
					.getBitmapByPath(imagePath));
		}
		if (ValidUtil.isNullOrEmpty(piccontent)) {
			ToastUtil.showShortMsg(context, "请添加上传图片");
			return false;
		} else {
			Map<String, Object> imgMap = new HashMap<String, Object>();
			// imgMap.put("photos", piccontent);
			// imgMap.put("imgName", UUID.randomUUID().toString() + ".jpg");
			// imgMap.put("isfirst", "1");
			imgMapList.add(piccontent);
		}
		if (photo2.getDrawable() != null) {
			// piccontent2 = new ImageBase64Util().getBitmapStrBase64(new
			// ImageBase64Util().drawableToBitmap(photo2.getDrawable()));
			piccontent2 = new ImageBase64Util()
					.getBitmapStrBase64(ImageCompress
							.getBitmapByPath(imagePath1));
			// Map<String, Object> imgMap = new HashMap<String, Object>();
			// imgMap.put("photos", piccontent2);
			// imgMap.put("imgName", UUID.randomUUID().toString() + ".jpg");
			// imgMap.put("isfirst", "0");
			imgMapList.add(piccontent2);
		}
		if (photo3.getDrawable() != null) {
			piccontent3 = new ImageBase64Util()
					.getBitmapStrBase64(ImageCompress
							.getBitmapByPath(imagePath2));
			// Map<String, Object> imgMap = new HashMap<String, Object>();
			// imgMap.put("photos", piccontent3);
			// imgMap.put("imgName", UUID.randomUUID().toString() + ".jpg");
			// imgMap.put("isfirst", "0");
			imgMapList.add(piccontent3);
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);

		switch (v.getId()) {
		case R.id.rightImage:
			ViewUtil.hideInputMethod(this);
			saveImage();
		}
	}

	private OnClickListener photoClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.image2 && photo.getDrawable() == null) {
				ToastUtil.showLongMsg(context, "请按顺序上传");
				return;
			} else if (v.getId() == R.id.image3) {
				if (photo.getDrawable() == null || photo2.getDrawable() == null) {
					ToastUtil.showLongMsg(context, "请按顺序上传");
					return;
				}
			}
			clickedImg = v.getId();

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
		intent.putExtra("outputX", 900);
		intent.putExtra("outputY", 540);
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
					ImageView editImg = (ImageView) findViewById(clickedImg);
					switch (clickedImg) {
					case R.id.image:
						// editImg.setImageBitmap(ImageCompress.getimage(imagePath));
						editImg.setImageDrawable(new BitmapDrawable(
								getBitpMap(imageUri)));
						break;

					case R.id.image2:
						// editImg.setImageBitmap(ImageCompress.getimage(imagePath1));
						editImg.setImageDrawable(new BitmapDrawable(
								getBitpMap(imageUri1)));
						break;

					case R.id.image3:
						// editImg.setImageBitmap(ImageCompress.getimage(imagePath2));
						editImg.setImageDrawable(new BitmapDrawable(
								getBitpMap(imageUri2)));
						break;

					default:
						break;
					}
					if (clickedImg == R.id.image) {
						findViewById(R.id.delImage).setVisibility(View.VISIBLE);
					} else if (clickedImg == R.id.image2) {
						findViewById(R.id.delImage2)
								.setVisibility(View.VISIBLE);
					} else if (clickedImg == R.id.image3) {
						findViewById(R.id.delImage3)
								.setVisibility(View.VISIBLE);
					}
				}
			}
			break;
		case CAMERA_WITH_DATA:
			if (resultCode == RESULT_OK) {
				if (!ValidUtil.isNullOrEmpty(picPhotoPath)) {
					File file = new File(picPhotoPath);
					if (file != null) {
						switch (clickedImg) {
						case R.id.image:
							startPhotoZoom(Uri.fromFile(file), imagePath);
							break;

						case R.id.image2:
							startPhotoZoom(Uri.fromFile(file), imagePath1);
							break;

						case R.id.image3:
							startPhotoZoom(Uri.fromFile(file), imagePath2);
							break;

						default:
							break;
						}
					}
					picPhotoPath = "";
				}
			}
			break;
		case LOCAL_WITH_DATA:
			if (resultCode == RESULT_OK) {
				switch (clickedImg) {
				case R.id.image:
					startPhotoZoom(data.getData(), imagePath);
					break;

				case R.id.image2:
					startPhotoZoom(data.getData(), imagePath1);
					break;

				case R.id.image3:
					startPhotoZoom(data.getData(), imagePath2);
					break;

				default:
					break;
				}
			}
			break;
		}
	}

	// private Bitmap decodeUriAsBitmap(Uri uri) {
	// Bitmap bitmap = null;
	// try {
	// bitmap = BitmapFactory.decodeStream(getContentResolver()
	// .openInputStream(uri));
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// return null;
	// }
	// return bitmap;
	// }

	OnClickListener delClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			getImageDrawable();
			switch (v.getId()) {
			case R.id.delImage:
				if (draList.size() > 0) {
					draList.remove(0);
				}
				break;
			case R.id.delImage2:
				if (draList.size() > 1) {
					draList.remove(1);
				}
				break;
			case R.id.delImage3:
				if (draList.size() > 2) {
					draList.remove(2);
				}
				break;
			}
			setImageSrc();
		}
	};

	public void getImageDrawable() {
		draList.clear();
		int[] ids = { R.id.image, R.id.image2, R.id.image3 };
		for (int i = 0; i < ids.length; i++) {
			draList.add(((ImageView) findViewById(ids[i])).getDrawable());
		}
	}

	public void setImageSrc() {
		int[] ids = { R.id.image, R.id.image2, R.id.image3 };
		int[] delIds = { R.id.delImage, R.id.delImage2, R.id.delImage3 };
		for (int i = 0; i < ids.length; i++) {
			if (i < draList.size()) {
				((ImageView) findViewById(ids[i])).setImageDrawable(draList
						.get(i));
				findViewById(delIds[i]).setVisibility(View.VISIBLE);
			} else {
				((ImageView) findViewById(ids[i])).setImageDrawable(null);
				findViewById(delIds[i]).setVisibility(View.GONE);
			}
		}

		for (int i = 0; i < delIds.length; i++) {
			if (i < draList.size()) {
				findViewById(delIds[i]).setVisibility(View.VISIBLE);
			} else {
				findViewById(delIds[i]).setVisibility(View.GONE);
			}
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
		if (drawable == null) {
			return null;
		}
		BitmapDrawable bd = (BitmapDrawable) drawable;
		return bd.getBitmap();
	}

	@Override
	protected void onDestroy() {

		Bitmap ibm = drawableToBitamp(photo.getDrawable());
		Bitmap ibm1 = drawableToBitamp(photo2.getDrawable());
		Bitmap ibm2 = drawableToBitamp(photo3.getDrawable());
		if (ibm != null && !ibm.isRecycled()) {
			ibm.recycle();
		}
		if (ibm1 != null && !ibm1.isRecycled()) {
			ibm1.recycle();
		}
		if (ibm2 != null && !ibm2.isRecycled()) {
			ibm2.recycle();
		}

		System.gc();
		super.onDestroy();
	}

}
