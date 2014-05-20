package com.do1.aqzhdj.activity.circle.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;

public class SimpleAdavaceAdapter extends SimpleAdapter {

	private int[] mTo;
	private String[] mFrom;
	private ViewBinder mViewBinder;

	private List<? extends Map<String, ?>> mData;

	private int mResource;

	private String[] mAsyncImagefrom;
	private int[] mAsyncImageto;
	private int mDefaultImageId;

	private LayoutInflater mInflater;

	private int[] where; // 需要设置监听的view id
	private ConvertViewCallback convertViewCallback;

	private AQuery aq;

	public SimpleAdavaceAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
	}

	/**
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 * @param from
	 * @param to
	 * @param asyncImagefrom
	 * @param asyncImageto
	 */
	public SimpleAdavaceAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, int[] where, ConvertViewCallback convertViewCallback) {
		super(context, data, resource, from, to);
		mData = data;
		mResource = resource;
		mFrom = from;
		mTo = to;
		this.where = where;
		this.convertViewCallback = convertViewCallback;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * 添加异步加载图片
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 * @param from
	 * @param to
	 * @param asyncImagefrom
	 * @param asyncImageto
	 * @param defaultImageId
	 *            设置默认背景图，如果没有设置值为0
	 */
	public SimpleAdavaceAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, String[] asyncImagefrom, int[] asyncImageto,
			int defaultImageId) {
		super(context, data, resource, from, to);
		mData = data;
		mResource = resource;
		mFrom = from;
		mTo = to;
		mAsyncImagefrom = asyncImagefrom;
		mAsyncImageto = asyncImageto;
		mDefaultImageId = defaultImageId;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * 给item里的view添加单击事件
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 * @param from
	 * @param to
	 * @param asyncImagefrom
	 * @param asyncImageto
	 * @param where
	 * @param convertViewCallback
	 */
	public SimpleAdavaceAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, String[] asyncImagefrom, int[] asyncImageto,
			int defaultImageId, int[] where,
			ConvertViewCallback convertViewCallback) {
		super(context, data, resource, from, to);
		mData = data;
		mResource = resource;
		mFrom = from;
		mTo = to;
		mAsyncImagefrom = asyncImagefrom;
		mAsyncImageto = asyncImageto;
		mDefaultImageId = defaultImageId;
		this.where = where;
		this.convertViewCallback = convertViewCallback;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return createViewFromResource(position, convertView, parent, mResource);
	}

	private View createViewFromResource(int position, View convertView,
			ViewGroup parent, int resource) {
		View v;
		if (convertView == null) {
			v = mInflater.inflate(resource, parent, false);
		} else {
			v = convertView;
		}

		bindView(position, v);

		if (mAsyncImagefrom != null && mAsyncImageto != null) {
			bindAsyncImageView(position, v);
		}

		if (where != null && convertViewCallback != null) {
			setListeners(position, v);
		}
		if (convertViewCallback != null) {
			convertViewCallback.callback(v, position);
		}

		return v;
	}

	protected void bindView(int position, View view) {

		final Map<String,?> dataSet = mData.get(position);
		if (dataSet == null) {
			return;
		}

		final ViewBinder binder = mViewBinder;
		final String[] from = mFrom;
		final int[] to = mTo;
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
							// Note: keep the instanceof TextView check at the
							// bottom of these
							// ifs since a lot of views are TextViews (e.g.
							// CheckBoxes).
							setViewText((TextView) v, text);
						} else {
							throw new IllegalStateException(v.getClass()
									.getName()
									+ " should be bound to a Boolean, not a "
									+ (data == null ? "<unknown type>"
											: data.getClass()));
						}
					} else if (v instanceof TextView) {
						// Note: keep the instanceof TextView check at the
						// bottom of these
						// ifs since a lot of views are TextViews (e.g.
						// CheckBoxes).
						// setViewText((TextView) v, text);
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
							((RatingBar) v).setRating(Float.parseFloat(data
									.toString()));
						}
					} else if (v instanceof ViewGroup) {// 设置背景
						if (data instanceof Integer) {
							v.setBackgroundResource((Integer) data);
						}
					} else {
						throw new IllegalStateException(
								v.getClass().getName()
										+ " is not a "
										+ " view that can be bounds by this SimpleAdapter");
					}
				}
			}
		}
	}

	private void bindAsyncImageView(int position, View v) {
		aq = new AQuery(v);
			
		Map<String,?> dataSet = mData.get(position);
		for (int i = 0; i < mAsyncImageto.length; i++) {
			String url = (String) dataSet.get(mAsyncImagefrom[i]);
			if (url != null && url != "") {
				if (mDefaultImageId == 0) {
					aq.id(mAsyncImageto[i]).image(url, true, true);
				} else {
					aq.id(mAsyncImageto[i]).image(url, true, true, 0,
							mDefaultImageId);
				}
			}

		}
	}

	/** item里的view设置单击事件 **/
	private void setListeners(final int position, View convertView) {
		for (final int w : where) {
			convertView.findViewById(w).setOnClickListener(
					new OnClickListener() {
						public void onClick(View v) {
							v.setFocusable(false);
							v.setFocusableInTouchMode(false);
							convertViewCallback.setOnListeners(v, position);
						}
					});
		}
	}

	public List<? extends Map<String, ?>> getData() {
		return mData;
	}

	public interface ConvertViewCallback {
		public void setOnListeners(View view, int position);

		public void callback(View convertView, int position);
	}

}
