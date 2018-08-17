/** *****************  JAVA头文件说明  ****************
 * file name  :  MerInf.java
 * owner      :  xu
 * copyright  :  UMPAY
 * description:  
 * modified   :  2016-11-3
 * *************************************************/ 

package com.busi.demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;



/**
 * ******************  类说明  *********************
 * class       :  SettleTransOrgdata
 * @author     :  htj
 * @version    :  1.0  
 * description :  借贷交易表
 * @see        :                        
 * ***********************************************
 */

public class SettleTrans extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1745036732096063818L;
	
	private String id;
	private String entryserialid;
	private String lendsubid;
	private String lendaccid;
	private String loansubid;
	private String loanaccid;
	private String transcertserialid;
	private String transuserid;
	private String transtype;
	private Integer currency;
	private BigDecimal amount;
	private String entrycode;
	private String transdate;
	private String entryaccdate;
	private String settledate;
	private String moduser;
	private Timestamp motime;
	private Timestamp intime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEntryserialid() {
		return entryserialid;
	}
	public void setEntryserialid(String entryserialid) {
		this.entryserialid = entryserialid;
	}
	public String getLendsubid() {
		return lendsubid;
	}
	public void setLendsubid(String lendsubid) {
		this.lendsubid = lendsubid;
	}
	public String getLendaccid() {
		return lendaccid;
	}
	public void setLendaccid(String lendaccid) {
		this.lendaccid = lendaccid;
	}
	public String getLoansubid() {
		return loansubid;
	}
	public void setLoansubid(String loansubid) {
		this.loansubid = loansubid;
	}
	public String getLoanaccid() {
		return loanaccid;
	}
	public void setLoanaccid(String loanaccid) {
		this.loanaccid = loanaccid;
	}
	public String getTranscertserialid() {
		return transcertserialid;
	}
	public void setTranscertserialid(String transcertserialid) {
		this.transcertserialid = transcertserialid;
	}
	public String getTransuserid() {
		return transuserid;
	}
	public void setTransuserid(String transuserid) {
		this.transuserid = transuserid;
	}
	public String getTranstype() {
		return transtype;
	}
	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getEntrycode() {
		return entrycode;
	}
	public void setEntrycode(String entrycode) {
		this.entrycode = entrycode;
	}
	public String getTransdate() {
		return transdate;
	}
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}
	public String getEntryaccdate() {
		return entryaccdate;
	}
	public void setEntryaccdate(String entryaccdate) {
		this.entryaccdate = entryaccdate;
	}
	public String getSettledate() {
		return settledate;
	}
	public void setSettledate(String settledate) {
		this.settledate = settledate;
	}
	public String getModuser() {
		return moduser;
	}
	public void setModuser(String moduser) {
		this.moduser = moduser;
	}
	public Timestamp getMotime() {
		return motime;
	}
	public void setMotime(Timestamp motime) {
		this.motime = motime;
	}
	public Timestamp getIntime() {
		return intime;
	}
	public void setIntime(Timestamp intime) {
		this.intime = intime;
	}
}
