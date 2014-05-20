package com.do1.aqzhdj.activity.mine.minsheng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.common.WapViewActivity2;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class MinshengActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.minsheng);
		setHeadView(findViewById(R.id.headLayout), 0, "", "便民服务", 0, "", this,
				this);
		ListenerHelper.bindOnCLickListener(this, this, R.id.shuifei,
				R.id.dianfei, R.id.ranqifei, R.id.tongxun, R.id.yibao,
				R.id.shebao, R.id.gongjijin, R.id.jiashi, R.id.shiyongjishu,
				R.id.gongqiuxinxi, R.id.chuangye, R.id.laowuzixun);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		// 水费
		case R.id.shuifei:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://www.aqwater.com/WaterQuery.aspx?item=12&logo=5");
			intent.putExtra("title", "水费");
			startActivity(intent);
			break;
		// 电费
		case R.id.dianfei:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://8013082415.shop.fengj.com/");
			intent.putExtra("title", "电费");
			startActivity(intent);
			break;
		// 燃气费
		case R.id.ranqifei:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://aqxxgk.anqing.gov.cn/list.php?unit=HA603");
			intent.putExtra("title", "燃气费");
			startActivity(intent);
			break;
		// 通讯费
		case R.id.tongxun:
			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
//			intent = new Intent(this,WapViewActivity2.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			intent.putExtra("url", "");
//			intent.putExtra("title", "通讯费");
//			startActivity(intent);
			break;
		// 医保
		case R.id.yibao:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://www.ah.hrss.gov.cn/Root/web/12333/12333_yiliao.htm");
			intent.putExtra("title", "医保");
			startActivity(intent);
			break;
		// 社保
		case R.id.shebao:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://www.aqshbx.gov.cn/index_i.php");
			intent.putExtra("title", "社保");
			startActivity(intent);
			break;
		// 公积金
		case R.id.gongjijin:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://www.aqgjj.cn/gjjyecx.asp");
			intent.putExtra("title", "公积金");
			startActivity(intent);
			break;
		// 驾驶违章
		case R.id.jiashi:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://www.ahjtxx.com/query/");
			intent.putExtra("title", "驾驶违章");
			startActivity(intent);
			break;
		// 实用技术
		case R.id.shiyongjishu:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://aq.ahxf.gov.cn/channels/197.html");
			intent.putExtra("title", "实用技术");
			startActivity(intent);
			break;
		//供求信息
		case R.id.gongqiuxinxi:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://aq.ahxf.gov.cn/channels/200.html");
			intent.putExtra("title", "供求信息");
			startActivity(intent);
			break;
		//创业服务
		case R.id.chuangye:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://aq.ahxf.gov.cn/channels/199.html");
			intent.putExtra("title", "创业服务");
			startActivity(intent);
			break;
		//劳务资讯
		case R.id.laowuzixun:
//			Toast.makeText(this, "此模块正在建设中...", Toast.LENGTH_SHORT).show();
			intent = new Intent(this,WapViewActivity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("url", "http://aq.ahxf.gov.cn/channels/200.html");
			intent.putExtra("title", "劳务资讯");
			startActivity(intent);
			break;
		}
	}

}
