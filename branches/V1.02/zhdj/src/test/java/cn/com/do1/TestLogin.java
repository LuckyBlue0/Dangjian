package cn.com.do1;

import cn.com.do1.common.util.xml.JDomXMLUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-6-28
 * Time: 上午10:28
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class TestLogin {
    String userName;
    String pwd;
//    HttpPost postMethod = new HttpPost("http://localhost:8080/dqdp-web/j_spring_security_check?j_username=admin&j_password=test");
    HttpPost postMethod = new HttpPost("http://localhost:8300/churujing/newscontent.mobo?cmd=request&directurl=newscontent.mobo%2f.&iswap=1&modename=zxdt");
//    HttpPost postMethod = new HttpPost("http://localhost:8300/churujing/newscontent.mobo?cmd=request&directurl=/zxdt/200911/t20091118_16892&iswap=1&modename=zxdt");

    public TestLogin(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    public String login() throws IOException {
        HttpClient client = new DefaultHttpClient();
        List<NameValuePair> parm = new ArrayList();
//        parm.add(new BasicNameValuePair("j_username", userName));
//        parm.add(new BasicNameValuePair("j_password", pwd));
        postMethod.setEntity(new UrlEncodedFormEntity(parm, HTTP.UTF_8));
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String str = client.execute(postMethod, responseHandler);
        postMethod.abort();
        client.getConnectionManager().shutdown();
        return str;
    }

    public String ajaxLogin() throws IOException {
        postMethod.setHeader("X-Requested-With", "XMLHttpRequest");
        return login();
    }

    public static void main(String[] args) throws Exception {
        JDomXMLUtil xml = new JDomXMLUtil("d:\\new.xml");
        Element recordCount = (Element) XPath.selectSingleNode(xml.getRootElement(), "/Result/item[@Name='searchlist']");
        System.out.println(recordCount.getValue());

//        for (int i = 0; i < 2; i++) {
//            LoginThread loginThread = new LoginThread(i);
//            loginThread.start();
//        }
//        System.out.println("完成");
    }

    static class LoginThread extends Thread {
        private int index;

        public LoginThread(int index) {
            this.index = index;
        }

        public void run() {
            try {
                for (int i = 0; i < 10000; i++) {
                    TestLogin testLogin = new TestLogin("admin", "test");
                    String str = testLogin.ajaxLogin();
                    System.out.println(str);
//                    JSONObject jsonObject = JSONObject.fromObject(str);
//                    if ("0".equals(jsonObject.getString("code"))) System.out.println(index + jsonObject.getString("desc") );
//                    else System.out.println(index + "登陆失败！！！！！！！！！！！！！" + jsonObject.getString("desc"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
