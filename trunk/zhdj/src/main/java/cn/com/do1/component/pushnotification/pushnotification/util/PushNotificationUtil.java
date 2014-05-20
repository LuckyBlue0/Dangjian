package cn.com.do1.component.pushnotification.pushnotification.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javapns.Push;
import javapns.devices.Device;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.mobileclient.device.service.IDeviceService;
import cn.com.do1.component.pushnotification.pushnotification.vo.InfoVo;
import cn.com.do1.dqdp.core.ConfigLoadExcetion;
import cn.com.do1.dqdp.core.ConfigMgr;
import cn.com.do1.dqdp.core.DqdpAppContext;

//推送服务
public class PushNotificationUtil {

	private final static transient Logger logger = LoggerFactory.getLogger(PushNotificationUtil.class);

	// private final static PushNotificationManager PUSH_MANAGER = new
	// PushNotificationManager();

	private static final String ANDROID_SUCCESS_TIPS = "hasSession";

	private static final String ANDROID_FAIL_TIPS = "noSession";

	/**
	 * 推送服务 android 必须参数：broadcast;username;title;message;uri
	 ***/
	public static Boolean push4Android(InfoVo vo) {
		StopWatch sw = new StopWatch();
		sw.start();
		Boolean result = false;
		try {
			HttpPost post = new HttpPost(ConfigMgr.get("pushnotification", "android_push_url"));
			HttpClient client = new DefaultHttpClient();
			List<BasicNameValuePair> formParams = new ArrayList<BasicNameValuePair>(4);
			logger.debug("android推送信息:");
			formParams.add(new BasicNameValuePair("broadcast", "Y"));
			logger.debug("broadcast:Y");
			formParams.add(new BasicNameValuePair("message", vo.getTitle()));
			logger.debug("message:" + vo.getTitle());
			if(AssertUtil.isEmpty(vo.getTypeDesc())){
				formParams.add(new BasicNameValuePair("title", ConfigMgr.get("pushnotification", "android_app_name")));
			}else{
				formParams.add(new BasicNameValuePair("title", vo.getTypeDesc()));
			}
			
			logger.debug("title:" + ConfigMgr.get("pushnotification", "android_app_name"));
			String uri = "{'type':'" + vo.getType() + "','url':'" + vo.getUrl() + "?newsInfoType=" + vo.getType() + "&InfoID=" + vo.getId() + "','title':'" + vo.getTitle() + "','newsInfoId':'" + vo.getId() + "'}";
			formParams.add(new BasicNameValuePair("uri", uri));
			logger.debug("uri:" + uri);
			HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			logger.info("HttpStatus:" + response.getStatusLine().getStatusCode());
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {
				logger.info("ContentType:" + resEntity.getContentType());
				logger.info("ContentLength:" + resEntity.getContentLength());
				String content = URLDecoder.decode(EntityUtils.toString(resEntity), "UTF-8");
				if (ANDROID_FAIL_TIPS.equals(content)) {
					logger.info("------------------------------FAIL");
				} else if (ANDROID_SUCCESS_TIPS.equals(content)) {
					logger.info("------------------------------OK");
					result = true;
				}
			}
			client.getConnectionManager().shutdown();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} catch (ConfigLoadExcetion e) {
			logger.error(e.getMessage(), e);
		}
		sw.stop();
		logger.info("调用推送android客户端耗时:" + sw.toString());
		return result;
	}

	public static void push4Iphone(InfoVo vo, List<String> devices) {
		StopWatch sw = new StopWatch();
		sw.start();
		try {
			PushNotificationPayload payLoad = PushNotificationPayload.complex();
			payLoad.addAlert(vo.getTitle()); // 消息内容
			payLoad.addBadge(1); // iphone应用图标上小红圈上的数值
			payLoad.addSound("default"); // 铃音 默认
			payLoad.addCustomDictionary("type", vo.getType());
			payLoad.addCustomDictionary("id", vo.getId());
			// payLoad.addCustomDictionary("publishTime", vo.getPublishTime());
			// payLoad.addCustomDictionary("url", vo.getUrl());
			// payLoad.addCustomDictionary("source", vo.getSource());
			logger.debug("iOS推送信息:");
			logger.debug("message:" + vo.getTitle());
			logger.debug("type:" + vo.getType());
			// logger.debug("id:"+vo.getId());
			// logger.debug("publishTime:"+vo.getPublishTime());
			// logger.debug("url:"+vo.getUrl());
			// logger.debug("source:"+vo.getSource());
			// 发送push消息
			String keyPath = DqdpAppContext.getAppRootPath() + ConfigMgr.get("pushnotification", "iphone_key_path");
			String password = ConfigMgr.get("pushnotification", "iphone_password");
			boolean isProduction = Boolean.valueOf(ConfigMgr.get("pushnotification", "iphone_production"));

			int numberPerThread = 30;
			int threads = devices.size() / numberPerThread;// 推送服务的线程数量,为客户端数量的1/10
			if (threads == 0)
				threads = 1;
			if (threads > 30)
				threads = 30;
			// PushQueue queue = Push.queue(keyPath, password, isProduction,
			// threads);
			// for (String device : devices) {
			// queue=queue.add(payLoad,device);
			// }
			// List<PushedNotification> failedNotifications = queue
			// .getPushedNotifications().getFailedNotifications();
			// List<PushedNotification> successfulNotifications = queue
			// .getPushedNotifications().getSuccessfulNotifications();

			PushedNotifications notifications = Push.payload(payLoad, keyPath, password, isProduction, threads, devices);
			List<PushedNotification> failedNotifications = notifications.getFailedNotifications();
			int i = 0;
			for (PushedNotification pushedNotification : failedNotifications) {
				i++;
				Device device = pushedNotification.getDevice();
				logger.debug("-----" + i + "---失败token：" + device.getToken() + "，设备ID：" + device.getDeviceId());
			}
			List<PushedNotification> successfulNotifications = notifications.getSuccessfulNotifications();
			for (PushedNotification pushedNotification : successfulNotifications) {
				i++;
				Device device = pushedNotification.getDevice();
				logger.debug("-----" + i + "---成功token：" + device.getToken() + "，设备ID：" + device.getDeviceId());
			}

			int failed = failedNotifications.size();
			int successful = successfulNotifications.size();
			if (successful > 0 && failed == 0) {
				logger.debug("-----All notifications pushed 成功 (" + successfulNotifications.size() + "):");
			} else if (successful == 0 && failed > 0) {
				logger.debug("-----All notifications 失败 (" + failedNotifications.size() + "):");
			} else if (successful == 0 && failed == 0) {
				logger.debug("No notifications could be sent, probably because of a critical error");
			} else {
				logger.debug("------Some notifications 失败 (" + failedNotifications.size() + "):");
				logger.debug("------Others 成功 (" + successfulNotifications.size() + "):");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} catch (ConfigLoadExcetion e) {
			logger.error(e.getMessage(), e);
		}
		sw.stop();
		logger.info("推送iOS客户端耗时:" + sw.toString());
	}
	/**
	 * 新闻消息推送
	 * @param news
	 * @throws BaseException
	 * @throws Exception
	 */
	public static void pushNews(InfoVo news) throws BaseException, Exception{
		Boolean pushNewsMsg = Boolean.valueOf(ConfigMgr.get("pushnotification", "pushNewsMsg"));
		if (pushNewsMsg) {
			final InfoVo vo = new InfoVo();
			vo.setId(news.getId());
			vo.setTitle(news.getTitle());
			vo.setType(news.getType());
			vo.setTypeDesc(news.getTypeDesc());
			// vo.setPublishTime(news.getPushTime());
			// vo.setSource(news.getSource());
			// vo.setUrl("/jsp/mobileclient/view.jsp");
			// iOS推送
			IDeviceService deviceService = (IDeviceService) DqdpAppContext.getSpringContext().getBean("deviceService");
			final List<String> devices = deviceService.getDeviceForiOS();
			new Thread(new Runnable() {
				@Override
				public void run() {
					push4Iphone(vo, devices);
				}
			}).start();

			// android推送
			new Thread(new Runnable() {
				@Override
				public void run() {
					push4Android(vo);
				}
			}).start();
		}
	}
}
