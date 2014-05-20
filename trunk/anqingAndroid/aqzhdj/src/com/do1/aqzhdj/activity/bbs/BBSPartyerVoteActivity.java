package com.do1.aqzhdj.activity.bbs;

import java.util.LinkedHashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.ImageViewTool;
import com.do1.aqzhdj.util.LhqStringUtil;

/**
 * 评选投票页面
 * 
 * @author LHQ
 * 
 */
public class BBSPartyerVoteActivity extends BaseListActivity implements
		ItemViewHandler {

	private String id;// 投票的主题id
	protected String isVote;
	private CharSequence voteTopic;
	private CharSequence totalCount;
	private String userId;
	private String userName;
	private int voteStatu;
	private LinkedHashMap<Integer, Object> userIds;
	private int toDoCount;

	// 请求链接
	private void request() {
		setCusItemViewHandler(this);
		String[] keys = new String[] { "userImgPath", "userName" };
		int[] ids = new int[] { R.id.user_img, R.id.user_name };
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", id);
		map.put("voteUserId", constants.userInfo.getUserId());
		setAdapterParams(keys, ids, R.layout.bbs_vote_grid_item, map);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getStringExtra("id");
		voteTopic = getIntent().getStringExtra("voteTopic");
		voteStatu = Integer.parseInt(getIntent().getStringExtra("voteStatus"));
		System.out.println("voteStatus:" + voteStatu);
		userIds = new LinkedHashMap<Integer, Object>();
		aq.find(R.id.vote_title).getTextView().setText(voteTopic);
		request();
	}

	@Override
	public void handleItemView(BaseAdapter adapter, final int position,
			final View itemView, ViewGroup parent) {
		System.out.println("handleItemView:被执行了le");
		aq = new AQuery(itemView);
		// 显示图片
		ImageView imgView = (ImageView) itemView.findViewById(R.id.user_img);
		String imgPath = listMap.get(position).get("userImgPath").toString();
		ImageViewTool.getAsyncImageBg(imgPath, imgView,
				R.drawable.new_mine_logo2);
		// 显示名字
		userName = listMap.get(position).get("userName") + "";
		System.out.println("名字：" + userName);
		aq.id(R.id.user_name).text(userName);

		if (voteStatu == 2 || voteStatu == 0) {// 已结束或未开始
			aq.id(R.id.person_frame).getView().setEnabled(false);
		}
		// 把已投票显示出来
		isVote = listMap.get(position).get("isVote").toString();
		System.out.println("isVote:::::" + isVote);
		if (Integer.parseInt(isVote) == 0) {// 已投过的
			aq.id(R.id.user_img_voteover).visible();
			itemView.setEnabled(false);
		}else {
			// 显示勾选框
			if (userIds.containsKey(position)) {
				aq.id(R.id.user_img_selected).visible();
			}
		}
		aq.id(R.id.person_frame).getView()
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						System.out.println("person_frame被点击了。。。。");
						isVote = listMap.get(position).get("isVote") + "";
						userId = listMap.get(position).get("userId") + "";
						aq = new AQuery(v);
						if (toDoCount == 0) {
							v.setEnabled(false);
							return;
						}
						if (isVote.equals("0")) {
							// 已被投过了
							v.setEnabled(false);
							return;
						}
						if (userIds.containsKey(position)) {
							userIds.remove(position);
							aq.id(R.id.user_img_selected).invisible();
						} else {
							userIds.put(position, userId);
							aq.id(R.id.user_img_selected).visible();
							if (userIds.values().size() > toDoCount) {
								userIds.remove(position);
								aq.id(R.id.user_img_selected).invisible();
							}
						}
						System.out.println("userIds:" + userIds);
						System.out.println("userIds.values:" + userIds.values());
						System.out.println("userIds.values().size():"
								+ (userIds.values().size()));
					}
				});
		aq.id(R.id.user_name).getView()
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						System.out.println("查看成员信息");
						Intent intent = new Intent(BBSPartyerVoteActivity.this,
								BBSPartyerInfoActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("id", id);
						intent.putExtra("userId",
								listMap.get(position).get("userId") + "");
						startActivity(intent);
					}
				});
	}

	public void onClick(View v) {
		System.out.println("onClick被执行了");
		switch (v.getId()) {
		case R.id.leftImage:
			this.finish();
			break;
		case R.id.rightImage:// 进行投票或者查看结果
			AQuery aq = new AQuery(v);
			if (aq.id(R.id.rightImage).getText().equals("投票")) {
				if (userIds.isEmpty()) {
					ToastUtil.showShortMsg(this, "请先选择！");
					return;
				}
				// 弹出投票警告提示框 - start
				new AlertDialog.Builder(BBSPartyerVoteActivity.this)
						.setTitle("温馨提示")
						.setMessage("投票之后将无法修改，您确认要投给他(们)吗？")
						.setPositiveButton("确认",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										System.out.println("userIds.values():"
												+ userIds.values());
										// 发送请求数据
										String url = Constants.SERVER_RUL2
												+ getString(R.string.addVoteResult);
										Map<String, Object> params = new LinkedHashMap<String, Object>();
										params.put("voteUserId",
												constants.userInfo.getUserId());
										params.put("id", id);
										params.put("userIds", userIds.values());
										doRequest(1, url,
												LhqStringUtil.jiami(params));
									}
								}).setNeutralButton("取消", null).create().show();
			} else if (aq.id(R.id.rightImage).getText().equals("查看结果")) {
				Intent intent = new Intent(BBSPartyerVoteActivity.this,
						BBSPartyerVoteResultActivity.class);
				intent.putExtra("id", id);
				intent.putExtra("voteTopic", voteTopic);
				intent.putExtra("totalCount", totalCount);
				startActivity(intent);
			}
		}
	}

	/**
	 * 列表请求成功
	 * 
	 * @param requestId
	 * @param resultObject
	 */
	public void success(ResultObject data) {
		System.out.println("调用了。。。。success。。。。。。。");
		System.out.println("listMap.size():" + listMap.size());
		aq = new AQuery(this);
		toDoCount = Integer.parseInt(data.getDataMap().get("toDoCount")
				.toString());
		System.out.println("toDoCount::剩余投票人数" + toDoCount);
		aq.id(R.id.vote_todocount).text(toDoCount + "");
		if (toDoCount == 0) {// 没有可投的人选
			// 投票之后，或toDoCount=0时
			aq.id(R.id.rightImage).text("查看结果");
		}
		aq.id(R.id.vote_count).text(listMap.size() + "");
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		System.out.println("onExecuteSuccess执行了。。。。");
		aq = new AQuery(this);
		userIds.clear();
		doSearch();
		aq.id(R.id.vote_todocount).text(toDoCount + "");
	}

	@Override
	public void setHeadMethod() {
		if (voteStatu == 0) {// 未开始
			setHeadView(findViewById(R.id.headLayout),
					R.drawable.btn_back_thzhd, "", "评选投票",
					R.drawable.btn_head_4, "未开始", this, this);
		} else if (voteStatu == 2) {// 已结束
			setHeadView(findViewById(R.id.headLayout),
					R.drawable.btn_back_thzhd, "", "评选投票",
					R.drawable.btn_head_4, "查看结果", this, this);
		} else if (voteStatu == 1) {
			setHeadView(findViewById(R.id.headLayout),
					R.drawable.btn_back_thzhd, "", "评选投票",
					R.drawable.btn_head_4, "投票", this, this);
		}
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.votePartyMemberList);
		parentResId = R.layout.bbs_vote_view;
	}
}