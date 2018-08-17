package com.busi.demo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ******************  类说明  *********************
 * class       :  Activity
 * @author     :  htj
 * @version    :  1.0  
 * description :  
 * @see        :                        
 * ***********************************************
 */
public class Activity extends BaseModel implements Serializable{

	private static final long serialVersionUID = 4161697638654921968L;
	private String activityid;
	private String activityname;
	private String linkurl;
	private String useplat;
	private Integer state;
	private int count;
	private String activitydesc;
	private String createuser;
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public String getUseplat() {
		return useplat;
	}
	public void setUseplat(String useplat) {
		this.useplat = useplat;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getActivitydesc() {
		return activitydesc;
	}
	public void setActivitydesc(String activitydesc) {
		this.activitydesc = activitydesc;
	}

	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getModuser() {
		return moduser;
	}
	public void setModuser(String moduser) {
		this.moduser = moduser;
	}
	public Timestamp getModtime() {
		return modtime;
	}
	public void setModtime(Timestamp modtime) {
		this.modtime = modtime;
	}
	public Timestamp getIntime() {
		return intime;
	}
	public void setIntime(Timestamp intime) {
		this.intime = intime;
	}
	private String moduser;
	private Timestamp modtime;
	private Timestamp intime;
}
