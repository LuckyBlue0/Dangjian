package com.do1.aqzhdj.activity.study.yuancheng;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.SDCardUtil;
import com.do1.aqzhdj.util.ValidUtil;

public class PaizhaoActivity extends BaseActivity {

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
	private List<Map<String, String>> imgMapList = new ArrayList<Map<String, String>>();

	// 参数
	private String typeId;
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

	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_yuancheng_paizhao);
		context = this;
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				getString(R.string.paizhao), 0, "", this, null);

		photo = aq.id(R.id.image).getImageView();
		photo2 = aq.id(R.id.image2).getImageView();
		photo3 = aq.id(R.id.image3).getImageView();

		ListenerHelper.bindOnCLickListener(this, photoClick, R.id.image,
				R.id.image2, R.id.image3);
		ListenerHelper.bindOnCLickListener(this, delClick, R.id.delImage,
				R.id.delImage2, R.id.delImage3);
	}

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

}
