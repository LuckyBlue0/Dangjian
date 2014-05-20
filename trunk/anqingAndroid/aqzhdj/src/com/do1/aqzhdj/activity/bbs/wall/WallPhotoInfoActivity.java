package com.do1.aqzhdj.activity.bbs.wall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.util.ToastUtil;
import cn.com.do1.component.util.ViewUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.ImageViewTool;
import com.do1.aqzhdj.util.StringUtil;
import com.do1.aqzhdj.util.ValidUtil;

/**
 * 留影墙--相册详情 auth:YanFangqin data:2013-4-25 thzhd
 */
public class WallPhotoInfoActivity extends BaseActivity {

	private Context context;
	private String wallId; // 留影墙ID
	private String wallName; // 留影墙名称
	private String wallDesc; // 留影墙描述
	private String createTime; // 留影墙创建时间
	private String author; // 留影墙作者
	private String wallDescLue; // 描述省略后
	private String id;
	private int currentPage = 1;
	private int totalPage = 1;
	private int pageSize = 10000;
	private View headView;
	private LayoutInflater inflater;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");// 时间转换
	List<Map<String, Object>> imglist = null;
	List<Map<String, Object>> commentlist = null;
	private Map<String, Object> map = new HashMap<String, Object>();// 参数map
	private ListView mListView;
	// 图片
	private Gallery gallery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wall_list_view);
		context = this;
		aq = new AQuery(this);

		inflater = LayoutInflater.from(context);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "留影墙", R.drawable.btn_head_2, "评论", null, this);
		mListView = (ListView) findViewById(R.id.listview);
		id = AppManager.wallid;
		request();
	}
	
	protected void request() {
	
		String url = SERVER_URL
				+ getString(R.string.wall_comment_list);
		map.put("id", id);
		map.put("pageIndex", currentPage + "");
		map.put("pageSize", pageSize + "");
		// String key = Entryption.getJsonKey(map);
		// doRequestPostString(requestId, url, StringUtil.jiami(map));
		doRequest(4, url, StringUtil.jiami(map));
	}

	public void initItems() {
		headView = inflater.inflate(R.layout.list_head, null);
		mListView.addHeaderView(headView);
		headView.setVisibility(View.GONE);
		gallery = (Gallery) headView.findViewById(R.id.gallery);
		ListenerHelper.bindOnCLickListener(this, this, R.id.sendBtn);
		requestInfo();
		// initHeadView();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		ViewUtil.hideKeyboard(this);
		switch (v.getId()) {
		case R.id.sendBtn:
			if (ValidUtil.isNullOrEmpty(aq.id(R.id.say).getText().toString())) {
				ToastUtil.showShortMsg(context, "请输入发表内容");
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("user_id", constants.userInfo.getUserId());
				map.put("content", aq.id(R.id.say).getText().toString().trim());
				map.put("wall_id", wallId);
				String url = SERVER_URL + getString(R.string.wall_comment);
				doRequest(3, url, map);
			}
			break;
		case R.id.rightImage:
			Intent intent = new Intent(context, CommentActivity.class);
			intent.putExtra("wallName", wallName);
			intent.putExtra("wallId", id);
			startActivity(intent);
			AppManager.list.add(this);
			break;
		}
	}

	/**
	 * 相册图片请求
	 */
	public void fillImageView() {
		new Thread() {
			public void run() {
				String url = SERVER_URL + getString(R.string.wall_image_list);
				Map<String, String> map = new HashMap<String, String>();
				map.put("wall_id", wallId);
				map.put("page_no", "1");
				map.put("page_count", "100");
				doRequest(99999, url, map);
			};
		}.start();
	}

	/**
	 * 请求留影墙信息
	 */
	public void requestInfo() {
		new Thread() {
			public void run() {
				String url = SERVER_URL + getString(R.string.wall_image_list);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				// map.put("page_no", "1");
				// map.put("page_count", "100");
//				setProgressMode(2);
				doRequest(1, url, StringUtil.jiami(map));
			};
		}.start();
	}

	public void initHeadView() {
		((TextView) headView.findViewById(R.id.name)).setText(wallName);
		if (wallDesc.length() > 36) {
			wallDescLue = wallDesc.substring(0, 36);
			headView.findViewById(R.id.content).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							String con = ((TextView) v).getText().toString();
							if (wallDesc.equals(con)) {
								((TextView) v).setText(Html
										.fromHtml(wallDescLue
												+ "<font color='#E79528'>...更多</font>"));
							} else {
								((TextView) v).setText(wallDesc);
							}
						}
					});
		}
		((TextView) headView.findViewById(R.id.content)).setText(wallDesc
				.length() <= 36 ? wallDesc : Html.fromHtml(wallDescLue
				+ "<font color='#E79528'>...更多</font>"));
		((TextView) headView.findViewById(R.id.author)).setText(author);
		((TextView) headView.findViewById(R.id.createTime))
				.setText(toDate(createTime));

		if (mListView != null)
			mListView.addHeaderView(headView);
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);

		if (requestId == 99999) {
			if (resultObject.getListMap().size() > 0) {
				headView.findViewById(R.id.imageListLayout).setVisibility(
						View.VISIBLE);
				String[] imgfrom = { "det_img" };
				int[] imgto = { R.id.image };
				String[] mfrom = new String[] {};
				int[] mto = new int[] {};
				RockAdapter adapter = new RockAdapter(context,
						resultObject.getListMap(), R.layout.wall_img_item,
						mfrom, mto, imgfrom, imgto);
				gallery.setAdapter(adapter);
			} else {
				headView.findViewById(R.id.imageListLayout).setVisibility(
						View.GONE);
			}
		} else if (requestId == 3) {
			aq.id(R.id.say).text("");
			ToastUtil.showShortMsg(context, "发布成功");
//			doSearch();
		}else if(requestId==4){
			initItems();
			commentlist = resultObject.getListMap();
			CommentAdapter adapter = new CommentAdapter(context);
			mListView.setAdapter(adapter);
	
			if (resultObject.getListMap().size() == 0) {
				((TextView) findViewById(R.id.nullText)).setText("");
				headView.findViewById(R.id.noneText).setVisibility(View.VISIBLE);
			} else {
				headView.findViewById(R.id.noneText).setVisibility(View.GONE);
			}
		} else if (requestId == 1) {
			headView.findViewById(R.id.imageListLayout).setVisibility(
					View.VISIBLE);
			// wallName=resultObject.getDataMap().get("data").toString();
			// Map<String, Object> resultMap = json2Map(new
			// JSONObject(dataKey));
			// 基本信息显示
			try {
				Map<String, Object> infoMap = JsonUtil.json2Map(new JSONObject(
						resultObject.getDataMap().get("data").toString()));
				wallName = infoMap.get("title").toString();
				wallDesc = infoMap.get("des").toString();
				author = infoMap.get("author").toString();
				createTime = infoMap.get("pushTime").toString();
				Object object = infoMap.get("imgs");
				JSONArray array = new JSONArray(object.toString());
				imglist = JsonUtil.jsonArray2List(array);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			((TextView) headView.findViewById(R.id.name)).setText(wallName);
			if (wallDesc.length() > 36) {
				wallDescLue = wallDesc.substring(0, 36);
				headView.findViewById(R.id.content).setOnClickListener(
						new OnClickListener() {
							@Override
							public void onClick(View v) {
								String con = ((TextView) v).getText()
										.toString();
								if (wallDesc.equals(con)) {
									((TextView) v).setText(Html
											.fromHtml(wallDescLue
													+ "<font color='#E79528'>...更多</font>"));
								} else {
									((TextView) v).setText(wallDesc);
								}
							}
						});
			}
			((TextView) headView.findViewById(R.id.content)).setText(wallDesc
					.length() <= 36 ? wallDesc : Html.fromHtml(wallDescLue
					+ "<font color='#E79528'>...更多</font>"));
			((TextView) headView.findViewById(R.id.author)).setText(author);
			((TextView) headView.findViewById(R.id.createTime))
					.setText(createTime);

			if (mListView != null)
				headView.setVisibility(View.VISIBLE);
		}

		// 图片显示
		if (imglist.size() > 0) {
			headView.findViewById(R.id.imageListLayout).setVisibility(
					View.VISIBLE);
			// String[] imgfrom = { "imgs" };
			// int[] imgto = { R.id.image };
			// String[] mfrom = new String[] {};
			// int[] mto = new int[] {};
			// RockAdapter adapter = new RockAdapter(context,
			// imglist, R.layout.wall_img_item,
			// mfrom, mto, imgfrom, imgto);
			// gallery.setAdapter(adapter);
			ImageAdapter adapter = new ImageAdapter(context);
			gallery.setAdapter(adapter);
		} else {
			headView.findViewById(R.id.imageListLayout)
					.setVisibility(View.GONE);
		}

	}

	/**
	 * 自定义图片显示适配器
	 * 
	 */
	class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context context) {
			this.context = context;
		}

		public int getCount() {
			return imglist.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			// imageView.setImageResource(picture[position % picture.length]);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setBackgroundResource(R.drawable.index_default);
			// imageView.setTag(imglist.get(position % imglist.size())
			// .get("newsInfoId").toString());
			imageView.setLayoutParams(new Gallery.LayoutParams(
					Gallery.LayoutParams.FILL_PARENT,
					Gallery.LayoutParams.FILL_PARENT));
			// System.out.println("position: " + position);
			// System.out.println("imgList.size: " + imgList.size());
			// System.out.println("imglist: " +
			// imgList.get(position%imgList.size()).get("imgPath").toString());
			if (position <= imglist.size() - 1) {
				ImageViewTool.getAsyncImageBg(
						imglist.get(position).get("thumImgUrl").toString(),
						imageView, R.drawable.index_default);
			}
			return imageView;
		}
	}

	/**
	 * 自定义评论显示适配器
	 * 
	 */
	class CommentAdapter extends BaseAdapter {
		private Context context;

		public CommentAdapter(Context context) {
			this.context = context;
		}

		public int getCount() {
			return commentlist.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView
			 = LayoutInflater.from(context).inflate(
					R.layout.wall_com_item, null);
			convertView=itemView;
			// 用户名
			String userName = commentlist.get(position).get("userName")
					.toString();
			((TextView) itemView.findViewById(R.id.title)).setText(userName);
			// 内容
			String content = commentlist.get(position).get("content")
					.toString();
			((TextView) itemView.findViewById(R.id.content)).setText(content);
			// 获取时间
			String time = commentlist.get(position).get("reviewTime")
					.toString();
			((TextView) itemView.findViewById(R.id.time))
					.setText(getLastTime(time));
			ImageView v = (ImageView) itemView.findViewById(R.id.image);
			// 评论头像地址
			String url = commentlist.get(position).get("portraitPic")
					.toString();
			ImageViewTool.getAsyncImageBg(url, v, 0);
			ImageView comv = (ImageView) itemView.findViewById(R.id.comimg);
			comv.setTag(time);
			// 评论图片
			String image = commentlist.get(position).get("imgUrl").toString();
			String time2 = comv.getTag().toString();
			if (time.equals(time2)) {
				if (!ValidUtil.isNullOrEmpty(image)) {
					comv.setVisibility(View.VISIBLE);
					ImageViewTool.getAsyncImageBg(image, comv, 0);
					// String s = mSlpControll.getmListData().get(position)
					// .get("imageAddr").toString();
					// if (!ValidUtil.isNullOrEmpty(s)) {
					// itemView.findViewById(R.id.address).setVisibility(View.VISIBLE);
					// ((TextView) itemView.findViewById(R.id.address))
					// .setText("图片地点：" + s);
					// }
				}
			}
			return convertView;
		}
	}

	/**
	 * 根据时间字符串获取该时间过去了多久
	 * 
	 * @param time
	 * @return
	 */
	public String getLastTime(String time) {
		String str = "";
		time = time.replace(":", ",").replace("-", ",").replace(" ", ",");
		List<Integer> old = new ArrayList<Integer>();
		List<Integer> now = new ArrayList<Integer>();

		String[] a = time.split(",");
		for (String s : a) {
			old.add(Integer.parseInt(s));
		}

		String[] b = sdf.format(new Date()).split(",");
		for (String s : b) {
			now.add(Integer.parseInt(s));
		}

		int size = old.size() > now.size() ? old.size() : now.size();

		for (int i = 0; i < size; i++) {
			if (now.get(i) > old.get(i)) {
				str = "刚刚".equals(getLast(i)) ? "刚刚"
						: (now.get(i) - old.get(i)) + getLast(i);
				break;
			}
		}

		return str;
	}

	/**
	 * 返回尾字符串
	 * 
	 * @param i
	 * @return
	 */
	public String getLast(int i) {
		String s = "";
		switch (i) {
		case 0:
			s = "年前";
			break;
		case 1:
			s = "个月前";
			break;
		case 2:
			s = "天前";
			break;
		case 3:
			s = "小时前";
			break;
		case 4:
			s = "分钟前";
			break;
		case 5:
			s = "刚刚";
			break;
		}
		return s;
	}
}
