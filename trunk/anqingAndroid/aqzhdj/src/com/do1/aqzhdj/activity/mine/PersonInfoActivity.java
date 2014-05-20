package com.do1.aqzhdj.activity.mine;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.util.ToastUtil;
import cn.com.do1.component.util.ViewUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.user.LoginActivity;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.ImageBase64Util;
import com.do1.aqzhdj.util.ImageViewTool;
import com.do1.aqzhdj.util.Listenertool;
import com.do1.aqzhdj.util.SDCardUtil;
import com.do1.aqzhdj.util.StringUtil;
import com.do1.aqzhdj.util.ValidUtil;

/**
 * 个人信息页面；当用户为群众，为我的页面，党员，为个人信息页面
 * auth:YanFangqin
 * data:2013-4-22
 * thzhd
 */
public class PersonInfoActivity extends BaseActivity{
	
	private Context context;
	private AQuery aq;
	
	//照片
	private String piccontent = "";					   // 图片内容
	private static final int CAMERA_WITH_DATA = 3021;  //拍照标识符
	private static final int LOCAL_WITH_DATA = 3023;   //本地图片标识符
	private String picPhotoPath = "";//拍照图片路径
	private ImageView photo;
	private boolean isChangeLogo = false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		aq = new AQuery(this);
		if("2".equals(constants.userInfo.getUser_type()))
			setContentView(R.layout.mine_fragment_for_public);
		else
			setContentView(R.layout.mine_fragment_for_dang);
		context = this;
		aq = new AQuery(this);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "2".equals(constants.userInfo.getUser_type()) ? "个人中心" : "个人信息", R.drawable.btn_head_2,"编辑", this, this);
	
		photo = aq.id(R.id.personLogo).getImageView();
		if("2".equals(constants.userInfo.getUser_type())){
			findViewById(R.id.leftImage).setVisibility(View.GONE);
			fillDataPublic();
		}else
			initDang();
	}
	
	public void fillDataPublic(){
		new Thread(){
			public void run() {
				String url = SERVER_URL + getString(R.string.mine);
				Map<String, String> map = new HashMap<String, String>();
				map.put("user_id", constants.userInfo.getUserId());
				doRequest(1, url, map);
			};
		}.start();
	}
	
	/**
	 * 用户身份为党员
	 */
	public void initDang(){
		
		aq.id(R.id.yonghuming).text(Constants.sharedProxy.getString("userId","")+"");
		aq.id(R.id.shoujihaoma).text(constants.userInfo.getMobile());
//		aq.id(R.id.dianziyouxiang_).text(constants.userInfo.getEmail());
//		aq.id(R.id.qq_).text(constants.userInfo.getQq());
//		aq.id(R.id.lianxidizhi_).text(constants.userInfo.getContactAddr());
		
		System.out.println("name: "+ Constants.sharedProxy.getString("userId",""));
		
		aq.id(R.id.yonghuming_).text(Constants.sharedProxy.getString("userId","")+"");
		aq.id(R.id.personName).text(constants.userInfo.getUserName());
		
//		aq.id(R.id.xingming).text(constants.userInfo.getName());
//		aq.id(R.id.shenfenzhenghao).text(constants.userInfo.getIDcard());
//		System.out.println("mobile: " + );
		((TextView)findViewById(R.id.shoujihaoma)).setText(constants.userInfo.getMobile());
		aq.id(R.id.shoujihaoma_).text(constants.userInfo.getMobile());
		aq.id(R.id.zhiwu).text(constants.userInfo.getJob());
		String joinTime = "";
//		if(!ValidUtil.isNullOrEmpty(constants.userInfo.getJoinTime())){
//			try {
//				joinTime = constants.sdfDate.format(constants.sdfReturn.parse(constants.userInfo.getJoinTime()));
//				joinTime = joinTime.replace("-", "月").replaceFirst("月", "年")+"日";
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
		aq.id(R.id.rudangshijian).text(constants.userInfo.getJoinTime());
		aq.id(R.id.dangling).text(constants.userInfo.getPartAge()+"年");
		aq.id(R.id.suoshuzuzhi).text(constants.userInfo.getCheckDept());
//		aq.id(R.id.zhucezuzhi).text(constants.userInfo.getRegDept());
//		aq.id(R.id.dianziyouxiang).text(constants.userInfo.getEmail());
//		aq.id(R.id.qq).text(constants.userInfo.getQq());
//		aq.id(R.id.lianxidizhi).text(constants.userInfo.getContactAddr());ImageViewTool.getAsyncImageBg(map.get("portraitPic") + "",
		ImageViewTool.getAsyncImageBg(constants.userInfo.getHeadImg() + "", aq.id(R.id.personLogo).getImageView(), 0);
	}
	
	/**
	 * 用户身份为群众
	 */
	public void initPublic(){
		aq.id(R.id.yonghuming_).text(constants.userInfo.getUserName());
		aq.id(R.id.shoujihaoma_).text(constants.userInfo.getMobile());
		aq.id(R.id.dianziyouxiang_).text(constants.userInfo.getEmail());
		aq.id(R.id.qq_).text(constants.userInfo.getQq());
		aq.id(R.id.lianxidizhi_).text(constants.userInfo.getContactAddr());
		
		aq.id(R.id.personName).text(constants.userInfo.getName());
		aq.id(R.id.yonghuming).text(constants.userInfo.getUserName());
		aq.id(R.id.xingming).text(constants.userInfo.getName());
		aq.id(R.id.shenfenzhenghao).text(constants.userInfo.getIDcard().substring(0, 6%constants.userInfo.getIDcard().length()) + "************");
		aq.id(R.id.shoujihaoma).text(constants.userInfo.getMobile());
		aq.id(R.id.dianziyouxiang).text(constants.userInfo.getEmail());
		aq.id(R.id.qq).text(constants.userInfo.getQq());
		aq.id(R.id.lianxidizhi).text(constants.userInfo.getContactAddr());
		ImageViewTool.getAsyncImageBg(constants.userInfo.getHeadImg() + "", aq.id(R.id.personLogo).getImageView(), 0);
	
		int[] onclickListenerIds = { R.id.xiugaimimaLayout,R.id.loginOut,R.id.wuxianshenghuoLayout };
		Listenertool.bindOnCLickListener(this, this, onclickListenerIds);
	}
	
	/**
	 * 将能够修改的字段设置成Edit状态
	 * @param editTexts
	 */
	public void setEditView(int show,EditText...editTexts){
		
		for(EditText e : editTexts){
			e.setVisibility(show);
		}
		if(View.VISIBLE == show){
			aq.id(R.id.editImg).visible();
			aq.id(R.id.yonghuming).visible();
			aq.id(R.id.shoujihaoma).visible();
			if("2".equals(constants.userInfo.getUser_type())){
				aq.id(R.id.dianziyouxiang).visible();
				aq.id(R.id.qq).visible();
				aq.id(R.id.lianxidizhi).visible();
			}
			
			aq.id(R.id.yonghuming_).gone();
			aq.id(R.id.shoujihaoma_).gone();
			if("2".equals(constants.userInfo.getUser_type())){
				aq.id(R.id.dianziyouxiang_).gone();
				aq.id(R.id.qq_).gone();
				aq.id(R.id.lianxidizhi_).gone();
			}
		}else{
			aq.id(R.id.yonghuming_).visible();
			aq.id(R.id.shoujihaoma_).visible();
			if("2".equals(constants.userInfo.getUser_type())){
				aq.id(R.id.dianziyouxiang_).visible();
				aq.id(R.id.qq_).visible();
				aq.id(R.id.lianxidizhi_).visible();
			}
			
			aq.id(R.id.editImg).gone();
			aq.id(R.id.yonghuming).gone();
			aq.id(R.id.shoujihaoma).gone();
			if("2".equals(constants.userInfo.getUser_type())){
				aq.id(R.id.dianziyouxiang).gone();
				aq.id(R.id.qq).gone();
				aq.id(R.id.lianxidizhi).gone();
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
			case R.id.leftImage:
				if("2".equals(constants.userInfo.getUser_type())){
					setEditView(View.GONE,
						    aq.id(R.id.yonghuming).getEditText(),
						    aq.id(R.id.shoujihaoma).getEditText(),
						    aq.id(R.id.dianziyouxiang).getEditText(),
						    aq.id(R.id.qq).getEditText(),
						    aq.id(R.id.lianxidizhi).getEditText());
					aq.id(R.id.rightImage).text("编辑");
					ListenerHelper.bindOnCLickListener(PersonInfoActivity.this, null, R.id.personLogo);
				}else{
					finish();
				}
				break;
			case R.id.rightImage:
				if("保存".equals(aq.id(R.id.rightImage).getText().toString())){
					saveUserInfo();
				}else{
					setEditView(View.VISIBLE,
								aq.id(R.id.yonghuming).getEditText(),
							    aq.id(R.id.shoujihaoma).getEditText(),
							    aq.id(R.id.dianziyouxiang).getEditText(),
							    aq.id(R.id.qq).getEditText(),
							    aq.id(R.id.lianxidizhi).getEditText());
					findViewById(R.id.leftImage).setVisibility(View.VISIBLE);
					aq.id(R.id.rightImage).text("保存");
					ListenerHelper.bindOnCLickListener(PersonInfoActivity.this, photoClick, R.id.personLogo);
				}
				break;
				
			case R.id.xiugaimimaLayout:
				intent = new Intent(context, ChangePasswordActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
				
			case R.id.wuxianshenghuoLayout:
				intent = new Intent(context, FreeLifeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
	
			case R.id.loginOut:
				Dialog dialog = new AlertDialog.Builder(context).setTitle("温馨提示")
				.setMessage("确定退出登录吗？").setPositiveButton("是",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
									register();
									constants.userInfo = null;
									Constants.loginInfo.setLogin(false);
									Constants.sharedProxy.putBoolean("isFirst", false);
									Constants.sharedProxy.commit();
									Intent intent = new Intent(context, LoginActivity.class);
									intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
							        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(intent);
									constants.exit(context);
									finish();
								}
						}).setNegativeButton("否",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.dismiss();
							}
						}).create();
					dialog.show();
				break;
		}
	}
	
	/**
	 * 验证个人信息保存
	 */
	public boolean valid(){
		if(ValidUtil.isNullOrEmpty(aq.id(R.id.yonghuming).getText().toString())){
			ToastUtil.showShortMsg(context, "请填写用户名");
			return false;
		}
		if(ValidUtil.isNullOrEmpty(aq.id(R.id.shoujihaoma).getText().toString())){
			ToastUtil.showShortMsg(context, "请填写手机号码");
			return false;
		}
		if(!ValidUtil.isMoble(aq.id(R.id.shoujihaoma).getText().toString())){
			ToastUtil.showShortMsg(context, "请输入正确的手机号码");
			return false;
		}
//		if(ValidUtil.isNullOrEmpty(aq.id(R.id.dianziyouxiang).getText().toString())){
//			ToastUtil.showShortMsg(context, "请填写电子邮箱");
//			return false;
//		}
//		if(ValidUtil.isNullOrEmpty(aq.id(R.id.qq).getText().toString())){
//			ToastUtil.showShortMsg(context, "请填写QQ");
//			return false;
//		}
//		if(ValidUtil.isNullOrEmpty(aq.id(R.id.lianxidizhi).getText().toString())){
//			ToastUtil.showShortMsg(context, "请填写联系地址");
//			return false;
//		}
		if (isChangeLogo && photo.getDrawable() != null) {
			isChangeLogo = false;
			piccontent = new ImageBase64Util().getBitmapStrBase64(new ImageBase64Util().drawableToBitmap(photo.getDrawable()));
			System.out.println("piccontent:" + piccontent);
		}
		return true;
	}
	
	/**
	 * 保存用户信息编辑
	 */
	public void saveUserInfo(){
		ViewUtil.hideKeyboard(this);
		if(valid()){
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			String usertype=constants.userInfo.getUser_type();
			String userid=constants.userInfo.getUserId();
			String username=aq.id(R.id.yonghuming).getText().toString();
//			String portraitPic="";
			String mobile=aq.id(R.id.shoujihaoma).getText().toString();
			String email="";
			
			map.put("userType", usertype);
			map.put("userId", userid);
			map.put("userName", username);
//			map.put("head_filename", UUID.randomUUID().toString()+".jpg");
//			map.put("head_img", piccontent);// 头像
			
			map.put("portraitPic", piccontent);  //  图片内容
			map.put("mobile", mobile);
//			map.put("email", aq.id(R.id.dianziyouxiang).getText().toString());
//			map.put("qq", aq.id(R.id.qq).getText().toString());
//			map.put("contact_addr", aq.id(R.id.lianxidizhi).getText().toString());
			
			map.put("email", email);
			
//			String digest=usertype + userid + username+portraitPic+mobile+email;
//			map.put("digest", SecurityUtil.encryptToMD5(digest.toLowerCase()));
			
//			Map<String, String> map2 = new HashMap<String, String>();
//			map2.put("requestJson", StringUtil.toJsonString(map));
			
			
			String url = Constants.SERVER_RUL2 + getString(R.string.save_person_info);
			doRequest(2, url, StringUtil.jiami(map));
//			String key = Entryption.getJsonKey2(map);
//			Log.e(key);
//			doRequestPostString(2, url, key);
		}
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		if(requestId == 1){  // 我的用户信息
			Map<String, Object>  map = resultObject.getDataMap();
			constants.userInfo.setHeadImg(map.get("head_img")+"");
			constants.userInfo.setBranchId(map.get("branch_id")+"");
			constants.userInfo.setBranchInt(map.get("branch_int")+"");
			constants.userInfo.setBranchPeoples(map.get("branch_peoples")+"");
			constants.userInfo.setBranchRank(map.get("branch_rank")+"");
			constants.userInfo.setBranchSec(map.get("branch_sec")+"");
			constants.userInfo.setCheckDept(map.get("check_dept")+"");
			constants.userInfo.setContactAddr(map.get("contact_addr")+"");
			constants.userInfo.setEmail(map.get("email")+"");
			constants.userInfo.setIDcard(map.get("idcard")+"");
			constants.userInfo.setIntegralRank(map.get("integral_rank")+"");
			constants.userInfo.setIs_can_test(map.get("is_can_test")+"");
			constants.userInfo.setIsCanTest(map.get("is_can_test")+"");
			constants.userInfo.setIsPartyWorkers(map.get("is_party_workers")+"");
			constants.userInfo.setJob(map.get("job")+"");
			constants.userInfo.setJoinTime(map.get("join_time")+"");
			constants.userInfo.setMobile(map.get("mobile")+"");
			constants.userInfo.setName(map.get("cname")+"");
			constants.userInfo.setPartAge(map.get("part_age")+"");
			constants.userInfo.setPartyStuUrl(map.get("party_stu_url")+"");
			constants.userInfo.setQq(map.get("qq")+"");
			constants.userInfo.setRegDept(map.get("reg_dept")+"");
			constants.userInfo.setUserName(map.get("user_name")+"");
			constants.userInfo.setTodos(map.get("todos")+"");
			constants.userInfo.setMettingSigns(map.get("metting_signs")+"");
			constants.userInfo.setAnalysisReports(map.get("analysis_reports")+"");
			constants.userInfo.setBranchImg(map.get("branch_img")+"");
			constants.userInfo.setBranchName(map.get("branch_name")+"");
			
			initPublic();
		}else{  // 保存用户信息
			ToastUtil.showLongMsg(this, resultObject.getDesc());
			if(!"2".equals(constants.userInfo.getUser_type())){
				if(!ValidUtil.isNullOrEmpty(piccontent)){
					AppManager.needFlesh = true;
				}
//				aq.id(R.id.personName).text(constants.userInfo.getUserName());
//				constants.userInfo.setUserId(aq.id(R.id.yonghuming_).getText().toString());
				Constants.sharedProxy.putString("userId", aq.id(R.id.yonghuming).getText().toString()); // 用户id
				Constants.sharedProxy.commit();
				constants.userInfo.setMobile(aq.id(R.id.shoujihaoma).getText().toString());
				finish();
			}else{
				constants.userInfo.setUserName(aq.id(R.id.yonghuming).getText().toString());
				setEditView(View.GONE,
					    aq.id(R.id.yonghuming).getEditText(),
					    aq.id(R.id.shoujihaoma).getEditText(),
					    aq.id(R.id.dianziyouxiang).getEditText(),
					    aq.id(R.id.qq).getEditText(),
					    aq.id(R.id.lianxidizhi).getEditText());
				aq.id(R.id.rightImage).text("编辑");
				aq.id(R.id.leftImage).gone();
				ListenerHelper.bindOnCLickListener(PersonInfoActivity.this, null, R.id.personLogo);
				fillDataPublic();
			}
		}
	}
	
	
	private OnClickListener photoClick = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			Builder imageialog = new AlertDialog.Builder(context);
			String[] items = {"从本地获取","照相"};
			imageialog.setTitle("请选择操作").setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (which == 0){//从本地获取
						Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
					    intent.addCategory(Intent.CATEGORY_OPENABLE);
					    intent.setType("image/*");
				        startActivityForResult(intent,LOCAL_WITH_DATA);
					}else{//照相
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
						String sdpath = SDCardUtil.getTakingPhotoDir() + File.separator + SDCardUtil.getPhotoFileName();
						File file = new File(SDCardUtil.getTakingPhotoDir(),SDCardUtil.getPhotoFileName());
						intent.putExtra("imagePath", sdpath);
						picPhotoPath = sdpath;
						intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));//输出文件位置
						startActivityForResult(intent, CAMERA_WITH_DATA);
					}
					dialog.dismiss();
				}
			}).create().show();
		}
	};
	
	public void startPhotoZoom(Uri uri) {
		/*
		 * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * 直接在里面Ctrl+F搜：CROP ，之前没仔细看过，其实安卓系统早已经有自带图片裁剪功能, 是直接调本地库的
		 */
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 1);
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (data != null) {
				ImageView editImg = (ImageView) findViewById(R.id.personLogo);
				Bundle extras = data.getExtras();
				if (extras != null) {
					Bitmap photo = extras.getParcelable("data");
					Drawable drawable = new BitmapDrawable(photo);
					editImg.setImageDrawable(drawable);
					isChangeLogo = true;
				}
			}
			break;
		case CAMERA_WITH_DATA:
			if (resultCode == RESULT_OK) {
				if(!ValidUtil.isNullOrEmpty(picPhotoPath)){
					File file = new File(picPhotoPath);
					if(file != null){
						startPhotoZoom(Uri.fromFile(file));
					}
					picPhotoPath = "";
				}
			}
			break;
		case LOCAL_WITH_DATA:
			if (resultCode == RESULT_OK){
				startPhotoZoom(data.getData());
			}
		break;
		}
	}
	
}
