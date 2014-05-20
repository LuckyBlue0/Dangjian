package com.do1.aqzhdj.info;

public class CircleInfo {
	
	private static CircleInfo circleInfo = null;
	
	private String id;
	private String name;
	private String numbers;
	private String labels;
	private String circletype;
	private String info;
	private String applyNum;
	private String createUserId;	//创建群人ID
	private String createUserName;	//创建群人名称
	
	public static  CircleInfo getInstance() {
	    if (circleInfo == null) 	circleInfo = new CircleInfo();
	    return circleInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getCircletype() {
		return circletype;
	}

	public void setCircletype(String circletype) {
		this.circletype = circletype;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}

	
}
