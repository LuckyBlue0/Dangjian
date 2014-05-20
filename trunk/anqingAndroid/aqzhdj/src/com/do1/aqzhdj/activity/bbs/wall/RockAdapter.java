package com.do1.aqzhdj.activity.bbs.wall;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.do1.aqzhdj.util.ImageViewTool;
/**
 * adapter模版--横屏滑动图片
 * auth:YanFangqin
 * data:2013-4-26
 * thzhd
 */
public class RockAdapter extends SimpleAdapter{
	
	public Context context;
	private List<Map<String,Object>> items;  	     //ListView中的数据项列表
	private int itemTemplateId;						 //ListView中的数据项显示模板资源ID
	private LayoutInflater inflater;				 //用于直接加载视图资源
	private int[] to;
	private String[] from;
	private int[] imageTo;
	private String[] imageFrom;
	private ViewBinder mViewBinder;
	
	public RockAdapter(Context context, List<Map<String, Object>> items,int itemTemplateId, String[] mfrom, int[] mto) {
		super(context, items, itemTemplateId, mfrom, mto);
		
		this.itemTemplateId = itemTemplateId;
		this.items = items;
		this.from = mfrom;
		this.to = mto;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	public RockAdapter(Context context, List<Map<String, Object>> items,int itemTemplateId, String[] mfrom, int[] mto,String[] mimageFrom,int[] mimageTo) {
		super(context, items, itemTemplateId, mfrom, mto);
		
		this.itemTemplateId = itemTemplateId;
		this.items = items;
		this.from = mfrom;
		this.to = mto;
		this.imageFrom = mimageFrom;
		this.imageTo = mimageTo;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return items.size();
	}

	public Object getItem(int position) {
		return items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(itemTemplateId, null);
		}
		bindView(position, convertView);
		if (imageFrom != null && imageTo != null) {
			bindAsyncImageView(position, convertView);
		}
		return convertView;
	}
	
	protected void bindView(int position, View view) {
		final Map<String, Object> dataSet = items.get(position);
		if (dataSet == null) {
			return;
		}
		final ViewBinder binder = mViewBinder;
		final int count = to.length;
		for (int i = 0; i < count; i++) {
			final View v = view.findViewById(to[i]);
			if (v != null) {
				final Object data = dataSet.get(from[i]);
				String text = data == null ? "" : data.toString();
				if (text == null) {
					text = "";
				}

				boolean bound = false;
				if (binder != null) {
					bound = binder.setViewValue(v, data, text);
				}

				if (!bound) {
					if (v instanceof Checkable) {
						if (data instanceof Boolean) {
							((Checkable) v).setChecked((Boolean) data);
						} else if (v instanceof TextView) {
							setViewText((TextView) v, text);
						} else {
							throw new IllegalStateException(v.getClass().getName()
									+ " should be bound to a Boolean, not a "
									+ (data == null ? "<unknown type>" : data.getClass()));
						}
					} else if (v instanceof TextView) {
						Spanned textHtml = Html.fromHtml(text);
						if (((TextView) v).getText() instanceof Editable) {
							((TextView) v).append(textHtml);
						} else {
							((TextView) v).setText(textHtml);
						}
					} else if (v instanceof ImageView) {
						if (data != null) {
							if (data instanceof Integer) {
								setViewImage((ImageView) v, (Integer) data);
							} else if (data.toString().trim().length() != 0) {
								setViewImage((ImageView) v, text);
							}
						}
					} else if (v instanceof RatingBar) {// 添加对RatingBar处理
						if (text.trim().length() != 0) {
							((RatingBar) v).setRating(Float.parseFloat(data.toString()));
						}
					} else if (v instanceof ViewGroup) {// 设置背景
						if (data instanceof Integer) {
							v.setBackgroundResource((Integer) data);
						}
					} else {
						throw new IllegalStateException(
								v.getClass().getName() + " is not a view that can be bounds by this SimpleAdapter");
					}
				}
			}
		}
	}
	
	/**
	 * 绑定异步加载的图片
	 */
	private void bindAsyncImageView(int position, View v) {
		Map<?, ?> dataSet = items.get(position);
		
		for (int i = 0; i < imageTo.length; i++) {
			String url = (String) dataSet.get(imageFrom[i]);
			if(url != null && url != ""){
				ImageView imageView = (ImageView) v.findViewById(imageTo[i]);
				ImageViewTool.getAsyncImageBg(url, imageView, 0);
			}
		}
	}
	
}
