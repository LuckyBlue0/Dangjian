package com.do1.zhdj.activity.mine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;
import android.widget.Toast;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.common.WapViewActivity;
import com.do1.zhdj.activity.common.WapViewActivity2;
import com.do1.zhdj.activity.infomation.widght.TryRefreshableView;
import com.do1.zhdj.activity.infomation.widght.TryRefreshableView.RefreshListener;
import com.do1.zhdj.activity.mine.my.MyToDoListActivity;
import com.do1.zhdj.activity.mine.user.LoginActivity;
import com.do1.zhdj.activity.parent.AppManager;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.ImageViewTool;
import com.do1.zhdj.util.Listenertool;
import com.do1.zhdj.util.StringUtil;

/**
 * 我的页面--党员用户进入我的页面；如果为群众用户， 进入的为personInfoActivity auth:YanFangqin
 * data:2013-4-22 thzhd
 */
public class MineNewActivity extends BaseActivity {

	private Context context;
	private AQuery aq;

	private ScrollView sv;
	private TryRefreshableView rv;

	private Handler mHandler;
	private boolean mRunning = false;
	private int requestId = 1;
	String title, type;
	String gerenzongjifen = "", sanhuicount, huodongcount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_fragment_new);
		context = this;
		aq = new AQuery(this);
		setHeadView(findViewById(R.id.headLayout), 0, "", "我的主页",
				R.drawable.btn_head_4, "注销登录", null, this);
		init();

		HandlerThread thread = new HandlerThread("MyHandlerThread");
		thread.start();// 创建一个HandlerThread并启动它
		mHandler = new Handler(thread.getLooper());// 使用HandlerThread的looper对象创建Handler，如果使用默认的构造方法，很有可能阻塞UI线程

		fillData(requestId);
	}

	// 实现耗时操作的线程
	Runnable mBackgroundRunnable = new Runnable() {
		@Override
		public void run() {
			while (!mRunning) {
				mRunning = true;
				request();
			}
		}
	};

	/**
	 * 启动线程请求数据
	 */
	public void fillData(int requestId) {
		this.requestId = requestId;
		mHandler.post(mBackgroundRunnable);// 将线程post到Handler中
	}

	public void request() {
		String url = Constants.SERVER_RUL2 + getString(R.string.mine);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		System.out.println("constas.userid: " + constants.userInfo.getUserId());
		// String userid=SecurityUtil.encryptToDES(Constants.MICHI,
		// constants.userInfo.getUserId());
		// String type=SecurityUtil.encryptToDES(Constants.MICHI,
		// constants.userInfo.getUser_type());
		// String digest=
		// SecurityUtil.encryptToMD5(constants.userInfo.getUserId() +"1");
		// System.out.println("userid: " + constants.userInfo.getUserId());
		// System.out.println("type: " + constants.userInfo.getUser_type());
		// System.out.println("digest: " + digest);
		map.put("userId", constants.userInfo.getUserId());
		map.put("userType", constants.userInfo.getUser_type());
		// map.put("digest", digest.toLowerCase());
		// Map<String, String> map2 = new HashMap<String, String>();
		// map2.put("requestJson", toJsonString(map));
		doRequest(requestId, url, StringUtil.jiami(map));
	}

	public String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json + "";
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (AppManager.needFlesh) {
			AppManager.needFlesh = false;
			fillData(1);
		}
	}

	/**
	 * 控制下拉刷新的handler
	 */
	Handler rehandler = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			rv.finishRefresh();
			if (mRunning) {
				mRunning = false;
				fillData(99999);
			}
		};
	};

	private void init() {
		int[] onclickListenerIds = { R.id.gerenjifenpaimingLayout, // 个人积分排名
				R.id.gerenzongjifenLayout, // 个人总积分
				R.id.wuxianshenghuoLayout, // 无线生活
				R.id.wodezhibuLayout, // 我的支部
				R.id.baibaoxiangLayout, // 百宝箱
				R.id.suozaizhibupaiming, // 所在支部排名
				R.id.dangfeichabanLayout, // 党费查登
				R.id.xiugaimimaLayout, // 修改密码
				R.id.rightImage, // 注销登录
				R.id.gerenxinxiLayout, // 个人信息
				R.id.shujiyueduLayout, // 书籍阅览
				R.id.xuexiceshiLayout, // 学习测试
				R.id.wodedaibanLayout, // 我的待办
				R.id.huiyiqiandaoLayout, // 会议签到
				// R.id.zuzhipaimingLayout, // 组织排名
				// R.id.zhiyuanhuodongLayout, // 职院活动
				R.id.zhinengfenxiLayout // 智能分析
		};
		Listenertool.bindOnCLickListener(this, this, onclickListenerIds);

		aq.find(R.id.wodezhibuLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
//						Intent intent = new Intent(context,
//								BranchActivity.class);
//						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						// startActivity(intent);
						Toast.makeText(MineNewActivity.this, "此模块正在建设中...", 1)
								.show();
						return true;
					}
				});

		// 支部内排行

		aq.find(R.id.suozaizhibupaiming).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context,
								PersonIntegralRankActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("from", "2");
						startActivity(intent);
						return true;
					}
				});

		// 个人积分排行榜

		aq.find(R.id.gerenjifenpaimingLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context,
								PersonIntegralRankActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("gerenzongjifen", gerenzongjifen);
						intent.putExtra("from", "1");
						startActivity(intent);
						return true;
					}
				});
		// 个人积分详情

		aq.find(R.id.gerenzongjifenLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context,
								PersonIntegralDetailActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("gerenzongjifen", gerenzongjifen);
						startActivity(intent);
						return true;
					}
				});

		// ===============个人信息================

		aq.find(R.id.gerenxinxiLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context,
								PersonInfoActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
						return true;
					}
				});
		// ==============修改密码====================

		aq.find(R.id.xiugaimimaLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context,
								ChangePasswordActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
						return true;
					}
				});

		// 三会一课
		aq.find(R.id.wodedaibanLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub

						Intent intent = new Intent(context,
								MyToDoListActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						type = "1";
						title = "三会一课";
						intent.putExtra("type", type);
						intent.putExtra("title", title);
						intent.putExtra("count", sanhuicount);
						startActivity(intent);
						return true;
					}
				});

		// 支部活动
		aq.find(R.id.huiyiqiandaoLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context,
								MyToDoListActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						type = "2";
						title = "支部活动";
						intent.putExtra("type", type);
						intent.putExtra("title", title);
						intent.putExtra("count", huodongcount);
						startActivity(intent);
						return true;
					}
				});
		// 民主生活会
		aq.find(R.id.dangfeichabanLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context,
								MyToDoListActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						type = "3";
						title = "民主生活会";
						intent.putExtra("type", type);
						intent.putExtra("title", title);
						intent.putExtra("count", "");
						startActivity(intent);
						return true;
					}
				});
		// 志愿活动
		aq.find(R.id.baibaoxiangLayout).getView()
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context,
								MyToDoListActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						type = "4";
						title = "志愿活动";
						intent.putExtra("type", type);
						intent.putExtra("title", title);
						intent.putExtra("count", "");
						startActivity(intent);
						return true;
					}
				});

		// 下拉刷新
		sv = (ScrollView) findViewById(R.id.trymySv);// 下拉刷新
		rv = (TryRefreshableView) findViewById(R.id.trymyRV);
		rv.sv = sv;
		rv.setRefreshListener(new RefreshListener() {// 监听是否加载刷新
			@Override
			public void onRefresh() {
				if (rv.mRefreshState == 4) {
					rehandler.sendEmptyMessageDelayed(1, 1000);// 伪处理
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {

		case R.id.zhinengfenxiLayout:
			// intent = new Intent(context, WapViewActivity.class);
			// intent.putExtra("url", getString(R.string.zhinengfenxiURL));
			// intent.putExtra("title", "智能分析");
			// startActivity(intent);
			Map<String, String> map = new HashMap<String, String>();
			String url = SERVER_URL + getString(R.string.getZhinengfenxiUrl);
			doRequest(9, url, map);
			break;
		// case R.id.zhiyuanhuodongLayout:
		// intent = new Intent(context, MyToDoListActivity.class);
		// intent.putExtra("type", "3");
		// intent.putExtra("title", "志愿活动");
		// startActivity(intent);
		// break;
		// case R.id.zuzhipaimingLayout:
		// intent = new Intent(context, BranchRankActivity.class);
		// startActivity(intent);
		// break;
		case R.id.wuxianshenghuoLayout:
			intent = new Intent(context, FreeLifeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		//
		// case R.id.wodezhibuLayout:
		// intent = new Intent(context, BranchActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// // startActivity(intent);
		// Toast.makeText(this, "此模块正在建设中...", 1).show();
		// break;
		// // 支部内排行
		// case R.id.suozaizhibupaiming:
		// intent = new Intent(context, PersonIntegralRankActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// intent.putExtra("from", "2");
		// startActivity(intent);
		// break;
		// // 个人积分排行榜
		// case R.id.gerenjifenpaimingLayout:
		// intent = new Intent(context, PersonIntegralRankActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// intent.putExtra("gerenzongjifen", gerenzongjifen);
		// intent.putExtra("from", "1");
		// startActivity(intent);
		// break;
		// // 个人积分详情
		// case R.id.gerenzongjifenLayout:
		// intent = new Intent(context, PersonIntegralDetailActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// intent.putExtra("gerenzongjifen", gerenzongjifen);
		// startActivity(intent);
		// break;
		// // ===============个人信息================
		// case R.id.gerenxinxiLayout:
		// intent = new Intent(context, PersonInfoActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// startActivity(intent);
		// break;
		// // ==============修改密码====================
		// case R.id.xiugaimimaLayout:
		// intent = new Intent(context, ChangePasswordActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// startActivity(intent);
		// break;

		case R.id.rightImage:
			Dialog dialog = new AlertDialog.Builder(context)
					.setTitle("温馨提示")
					.setMessage("确定退出登录吗？")
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									register();
									constants.userInfo = null;
									Constants.loginInfo.setLogin(false);
									Constants.sharedProxy.putBoolean("isFirst",
											false);
									Constants.sharedProxy.commit();
									Intent intent = new Intent(context,
											LoginActivity.class);
									intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
									intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(intent);
									constants.exit(context);
									finish();
								}
							})
					.setNegativeButton("否",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									dialog.dismiss();
								}
							}).create();
			dialog.show();
			break;
		case R.id.shujiyueduLayout:
			// intent = new Intent(context, DuesLearnListActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent);
			String testurl = SERVER_URL + getString(R.string.yun_study);
			Map<String, String> test = new HashMap<String, String>();
			test.put("user_id", constants.userInfo.getUserId());
			test.put("user_name", constants.userInfo.getUserName());
			doRequest(100, testurl, test);
			break;

//		case R.id.xuexiceshiLayout:
//			intent = new Intent(context, TestIndexActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			break;

		case R.id.wodedaibanLayout:
			// intent = new Intent(context, MyToDoIndexActivity.class); // 三会一课
			// MyToDoIndexActivity
			// intent.putExtra("type", "1");
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent);

			// ===================三会一课=====================
			// intent = new Intent(context, MyToDoListActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// type = "1";
			// title = "三会一课";
			// intent.putExtra("type", type);
			// intent.putExtra("title", title);
			// intent.putExtra("count", sanhuicount);
			// startActivity(intent);
			break;

		case R.id.huiyiqiandaoLayout:
			// intent = new Intent(context, MySignMeetingListActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// // intent = new Intent(context, CaptureActivity.class);
			// startActivity(intent);
			// ==============会议签到=====================
			// intent = new Intent(context, MyToDoListActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// type = "2";
			// title = "支部活动";
			// intent.putExtra("type", type);
			// intent.putExtra("title", title);
			// intent.putExtra("count", huodongcount);
			// startActivity(intent);
			break;

		case R.id.dangfeichabanLayout:
			// intent = new Intent(context, DuesPayActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent);
			// ============民主生活会===================
			// intent = new Intent(context, MyToDoListActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// type = "3";
			// title = "民主生活会";
			// intent.putExtra("type", type);
			// intent.putExtra("title", title);
			// intent.putExtra("count", "");
			// startActivity(intent);
			break;

		case R.id.baibaoxiangLayout:
			// intent = new Intent(context, BoxIndexActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent);
			// =============志愿者活动==================
			// intent = new Intent(context, MyToDoListActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// type = "4";
			// title = "志愿活动";
			// intent.putExtra("type", type);
			// intent.putExtra("title", title);
			// intent.putExtra("count", "");
			// startActivity(intent);
			break;
		}
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		System.out.println("成功了................");
		if (requestId == 9) {
			Intent intent = new Intent(context, WapViewActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", resultObject.getDataMap().get("reportUrl")
					+ "");
			intent.putExtra("title", "智能分析");
			startActivity(intent);
		} else if (requestId == 100) {
			Intent intent = new Intent(this, WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", resultObject.getDataMap().get("wap_url")
					+ "");
			intent.putExtra("title", "书籍阅览");
			startActivity(intent);
		} else {
			Map<String, Object> maps = resultObject.getDataMap();

			try {

				Map<String, Object> map = JsonUtil.json2Map(new JSONObject(maps
						.get("partyMenberInfo") + ""));
				System.out.println("minenewmap: " + map);
				gerenzongjifen = map.get("integralTotal") + "";
				// 姓名
				aq.id(R.id.personName).text(map.get("name") + "");
				// 个人总积分
				aq.id(R.id.gerenzongjifen).text(
						map.get("integralTotal") != null ? map.get(
								"integralTotal").toString() : "0.00");
				// 全区总排名
				String integral_rank = map.get("integralRank").toString()
						.trim();
				if (null == integral_rank || integral_rank.equals(""))
					integral_rank = "0";
				else {
					// BigDecimal b = new
					// BigDecimal(Double.valueOf(map.get("integral_rank")
					// .toString()));
					// double d = b.setScale(2,
					// BigDecimal.ROUND_HALF_UP).doubleValue();
					// integral_rank = d+"";

					// DecimalFormat df = new DecimalFormat("#.00");
					// integral_rank = df.format(Double.valueOf(integral_rank));

				}
				// 个人积分
				aq.id(R.id.gerenjifenpaiming).text(integral_rank);

				// 所在支部排名
				String branch_rank = map.get("branchRanking").toString().trim();

				if (null == branch_rank || branch_rank.equals(""))
					branch_rank = "0";
				else
					branch_rank = map.get("branchRanking").toString();
				aq.id(R.id.txtzhibupaiming).text(
						map.get("branchRanking") + "");

				// if(!ValidUtil.isNullOrEmpty(map.get("rank_num")+"")){
				// aq.id(R.id.zuzhipaiming).text(map.get("rank_num")+"");
				// }

				// 代办数量
				if (!"".equals(map.get("meetingCount") + "")
						&& Integer.parseInt(map.get("meetingCount") + "") > 0) {
					aq.id(R.id.daibanCounts).visible();
					// aq.id(R.id.daibanCounts).text(map.get("todos") + "");
					setFrameBg(map.get("meetingCount") + "",
							aq.id(R.id.daibanCounts).getView());
				} else {
					aq.id(R.id.daibanCounts).invisible();
				}
				sanhuicount = map.get("meetingCount").toString();
				huodongcount = map.get("activityCount").toString();

				// 支部活动数量
				if (!"".equals(map.get("activityCount") + "")
						&& Integer.parseInt(map.get("activityCount") + "") > 0) {
					aq.id(R.id.signCounts).visible();
					// aq.id(R.id.signCounts).text("" +
					// map.get("metting_signs"));
					setFrameBg(map.get("activityCount") + "",
							aq.id(R.id.signCounts).getView());
				} else {
					aq.id(R.id.signCounts).invisible();
				}

				// 民主生活会数目
				if (!"".equals(map.get("democrticlifeCount") + "")
						&& Integer.parseInt(map.get("democrticlifeCount") + "") > 0) {
					aq.id(R.id.mingzhuCounts).visible();
					// aq.id(R.id.signCounts).text("" +
					// map.get("metting_signs"));
					setFrameBg(map.get("democrticlifeCount") + "",
							aq.id(R.id.mingzhuCounts).getView());
				} else {
					aq.id(R.id.mingzhuCounts).invisible();
				}

				// if (!"".equals(map.get("analysis_reports") + "")
				// && Integer.parseInt(map.get("analysis_reports") + "") > 0) {
				// aq.id(R.id.fenxiCounts).visible();
				// // aq.id(R.id.fenxiCounts).text(map.get("analysis_reports") +
				// // "");
				// setFrameBg(map.get("analysis_reports") + "",
				// aq.id(R.id.fenxiCounts).getView());
				// } else {
				// aq.id(R.id.fenxiCounts).invisible();
				// }
				// 头像
				if (!"".equals(map.get("portraitPic") + "")) {
					ImageViewTool.getAsyncImageBg(map.get("portraitPic") + "",
							aq.id(R.id.personLogo).getImageView(), 0);
				}

				// constants.userInfo.setTotalIntegral(map.get("integral_total")
				// + "");
				// constants.userInfo.setHeadImg(map.get("head_img") + "");
				// constants.userInfo.setBranchId(map.get("branch_id") + "");
				// constants.userInfo.setBranchInt(map.get("branch_int") + "");
				// constants.userInfo.setBranchPeoples(map.get("branch_peoples")
				// + "");
				// constants.userInfo.setBranchRank(map.get("branch_rank") +
				// "");
				// constants.userInfo.setBranchSec(map.get("branch_sec") + "");
				System.out.println("partyAge1: " + map.get("organizationName")
						+ "");
				System.out
						.println("partyAge1: " + map.get("positionDesc") + "");
				System.out.println("partyAge1: " + map.get("partyTime") + "");
				System.out.println("partyAge1: " + map.get("partyAge") + "");
				System.out.println("partyAge1: " + map.get("userName") + "");

				constants.userInfo.setCheckDept(map.get("organizationName")
						+ "");
				// constants.userInfo.setContactAddr(map.get("contact_addr") +
				// "");
				// constants.userInfo.setEmail(map.get("email") + "");
				// constants.userInfo.(map.get("idcard") + "");
				// constants.userInfo.setIsetIDcardntegralRank(map.get("integral_rank")
				// + "");
				// constants.userInfo.setIs_can_test(map.get("is_can_test") +
				// "");
				// constants.userInfo.setIsCanTest(map.get("is_can_test") + "");
				// constants.userInfo.setIsPartyWorkers(map.get("is_party_workers")
				// + "");
				constants.userInfo.setUser_type("1");
				constants.userInfo.setJob(map.get("positionDesc") + "");
				constants.userInfo.setJoinTime(map.get("partyTime") + "");
				constants.userInfo.setMobile(map.get("mobile") + "");
				constants.userInfo.setName(map.get("name") + "");
				constants.userInfo.setPartAge(map.get("partyAge") + "");
				constants.userInfo.setHeadImg(map.get("portraitPic") + "");
				constants.userInfo.setBranchId(map.get("organizationId") + "");

				// constants.userInfo.setPartyStuUrl(map.get("party_stu_url") +
				// "");
				// constants.userInfo.setQq(map.get("qq") + "");
				// constants.userInfo.setRegDept(map.get("reg_dept") + "");
				constants.userInfo.setUserName(map.get("name") + "");
				// constants.userInfo.setTodos(map.get("todos") + "");
				// constants.userInfo.setMettingSigns(map.get("metting_signs") +
				// "");
				// constants.userInfo.setAnalysisReports(map.get("analysis_reports")
				// + "");
				// constants.userInfo.setBranchImg(map.get("branch_img") + "");
				// constants.userInfo.setBranchName(map.get("organizationName")
				// + "");
				// constants.userInfo.setRankNum(map.get("rank_num") + "");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 销毁线程
		mHandler.removeCallbacks(mBackgroundRunnable);
	}
}
