package com.do1.aqzhdj.activity.mine.branch;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.BranchIntegralDetailActivity;
import com.do1.aqzhdj.activity.mine.BranchRankActivity;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.ImageViewTool;

public class BranchActivity<asyncImageLoader> extends BaseActivity {

	private String branchName;
	private String branchId;
	private String branchSec;
	private String branchCount;
	private String branchRank;
	private String branchImg;
	private String branchScore;
	private String isPartyWorkers;

	private RelativeLayout relIsPartyWorker;
	private RelativeLayout relBranchList;
	private TextView tvBranchName;
	private ImageView imBranchImg;
	private TextView tvBranchCount;
	private TextView tvBranchChairman;
	private TextView tvBranchScore;
	private TextView tvBranchRank;

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.branch);
		// 党支部id //"417771b6362941ab92476b668b0ac52d";// :
		// "3160ad2dda9646f8b6c6d2a5ee5144f7";
		branchId = constants.userInfo.getBranchId();
		// System.err.println("党支部id>>>>>>>>>" + branchId);
		// 党支部名称
		branchName = constants.userInfo.getBranchName();
		// System.err.println("党支部名称>>>>>>>>>" + branchName);
		// 党支部书记
		branchSec = constants.userInfo.getBranchSec();
		// System.err.println("党支部书记>>>>>>>>>" + branchSec);
		// 党支部人数
		branchCount = constants.userInfo.getBranchPeoples();
		// System.err.println("党支部人数>>>>>>>>>" + branchCount);
		// 党支部排名
		branchRank = constants.userInfo.getRankNum();
		// System.err.println("党支部人数>>>>>>>>>" + branchRank);
		// 党支部积分
		branchScore = constants.userInfo.getBranchInt();
		// System.err.println("党支部积分>>>>>>>>>" + branchScore);
		// 党支部图片
		branchImg = constants.userInfo.getBranchImg();
		Log.e(branchImg);
		// System.err.println("党支部图片>>>>>>>>>" + branchImg);
		// 党务人员
		isPartyWorkers = constants.userInfo.getIsPartyWorkers();
		// System.err.println("党务人员>>>>>>>>>" + isPartyWorkers);
		initView();

	}

	private void initView() {

		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"我的支部", 0, "", null, null);

		tvBranchName = (TextView) findViewById(R.id.branch_name);
		imBranchImg = (ImageView) findViewById(R.id.branch_img);
		tvBranchCount = (TextView) findViewById(R.id.branch_count);
		tvBranchChairman = (TextView) findViewById(R.id.branch_chairman);
		tvBranchScore = (TextView) findViewById(R.id.branch_score);
		tvBranchRank = (TextView) findViewById(R.id.branch_rank);
		relBranchList = (RelativeLayout) findViewById(R.id.relBranchList);
		relIsPartyWorker = (RelativeLayout) findViewById(R.id.relPartyworkList);

		if ("0".equals(isPartyWorkers)) {
			relIsPartyWorker.setVisibility(View.GONE);
		} else {
			relIsPartyWorker.setVisibility(View.VISIBLE);
		}

		tvBranchName.setText(branchName);
//		aq.id(R.id.branch_img).image(branchImg, true, true);
		ImageViewTool.getAsyncImageBg(branchImg,aq.id(R.id.branch_img).getImageView(),R.drawable.index_default);
		tvBranchCount.setText(branchCount);
		tvBranchChairman.setText(branchSec);
		tvBranchScore.setText(branchScore);
		tvBranchRank.setText(branchRank);

		relBranchList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LayoutInflater inflater = getLayoutInflater();
				final View layout = inflater.inflate(R.layout.dialog_zidingyi,
						(ViewGroup) findViewById(R.id.dialog));
				final AlertDialog dialog = new AlertDialog.Builder(BranchActivity.this).setTitle("请输入登录密码")
						.setView(layout).setPositiveButton("确定", null)
						.setNegativeButton("取消", null).show();
				dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						EditText editPassword = (EditText) layout.findViewById(R.id.etname);
						String text = editPassword.getText().toString();
						if(null != text && text.equals(constants.userInfo.getPassword())){
							dialog.dismiss();
							intent = new Intent();
							intent.setClass(BranchActivity.this, BranchListActivity.class);
							// intent.putExtra("is_party_workers", isPartyWorkers);
							intent.putExtra("type", "1");
							startActivity(intent);
						}else{
							ToastUtil.showShortMsg(BranchActivity.this, "密码不正确");
							dialog.dismiss();
							return;
						}
					}
				});
				dialog.setCanceledOnTouchOutside(false);
			}
		});

		relIsPartyWorker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LayoutInflater inflater = getLayoutInflater();
				final View layout = inflater.inflate(R.layout.dialog_zidingyi,
						(ViewGroup) findViewById(R.id.dialog));
				final AlertDialog dialog = new AlertDialog.Builder(BranchActivity.this).setTitle("请输入登录密码")
						.setView(layout).setPositiveButton("确定", null)
						.setNegativeButton("取消", null).show();
				dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						EditText editPassword = (EditText) layout.findViewById(R.id.etname);
						String text = editPassword.getText().toString();
						if(null != text && text.equals(constants.userInfo.getPassword())){
							dialog.dismiss();
							intent = new Intent();
							intent.setClass(BranchActivity.this, BranchListActivity.class);
							// intent.putExtra("is_party_workers", isPartyWorkers);
							intent.putExtra("type", "2");
							startActivity(intent);
						}else{
							ToastUtil.showShortMsg(BranchActivity.this, "密码不正确");
							dialog.dismiss();
							return;
						}
					}
				});
				dialog.setCanceledOnTouchOutside(false);
			}
		});
		
		findViewById(R.id.relRank).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(BranchActivity.this, BranchRankActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.relScore).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(BranchActivity.this, BranchIntegralDetailActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.relBranchList:
//
//			break;
//		case R.id.relPartyworkList:
//			
//			break;
//		case R.id.leftImage:
//			
//			break;
//
//		default:
//			break;
//		}
//	}

}
