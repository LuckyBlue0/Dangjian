package cn.com.do1.component.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import cn.com.do1.component.news.newsinfo.vo.NewsIndex;

public class NewsConfig{
	private static NewsConfig instance;
	//配置ID
	private NewsConfig(){}
	public static synchronized NewsConfig getInstance() {
		if (instance == null) {
			instance = new NewsConfig();
		}
		return instance;
	}
	public static List<NewsIndex> list = new ArrayList<NewsIndex>();
	static{
		init();
	}

	public static List<NewsIndex> getNewsIndexList() {
		if (list == null) {
			init();
		}
		return list;
	}

	public static void init(){
		if(list == null){
			list = new ArrayList<NewsIndex>();
		}
		try {
			InputStream is = NewsConfig.class.getClassLoader().getResourceAsStream("/META-INF/config/news.xml");
			DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder dombuilder = domfac.newDocumentBuilder();
			Document doc = dombuilder.parse(is);
			Element root = doc.getDocumentElement();
			NodeList newsList = root.getChildNodes();
			if (newsList != null) {
				for (int i = 0; i < newsList.getLength(); i++) {
					Node news = newsList.item(i);
					
					if(news.getFirstChild() == null){
						continue;
					}
					NewsIndex newsIndex  = new NewsIndex();
					for (Node node = news.getFirstChild(); node != null; node = node.getNextSibling()) {
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							if (node.getNodeName().equals("title")) {
								newsIndex.setTitle(node.getFirstChild().getNodeValue());
							}else if (node.getNodeName().equals("orderValue")) {
								newsIndex.setOrderValue(node.getFirstChild().getNodeValue());
							}else if (node.getNodeName().equals("imgPath")) {
								newsIndex.setImgPath(node.getFirstChild().getNodeValue());
							}else if (node.getNodeName().equals("getContentUrl")) {
								newsIndex.setGetContentUrl(node.getFirstChild().getNodeValue());
							}
						}
					}
					list.add(newsIndex);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}
