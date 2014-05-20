package cn.com.do1.component.news.newsinfo.vo;

import java.util.List;

public class AqNewsListVO {

	private String resultCode;
	private List<AqNewsVO> allContent;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public List<AqNewsVO> getAllContent() {
		return allContent;
	}
	public void setAllContent(List<AqNewsVO> allContent) {
		this.allContent = allContent;
	}
}
