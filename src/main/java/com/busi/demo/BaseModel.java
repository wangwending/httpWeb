package com.busi.demo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ****************** 类说明 ********************* class : BaseModel
 * 
 * @author : hejinyun@umpay.com
 * @version : 1.0 description : 基础模型==>用户时间段分页查询
 * @see : ***********************************************
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 时间区间 */
	private String _start_time;
	private String _end_time;

	/** 分页 */
	private Integer page;
	private Integer rows;

	/** 价格区间 */
	private BigDecimal _start_price;
	private BigDecimal _end_price;

	public BigDecimal get_start_price() {
		return _start_price;
	}

	public void set_start_price(BigDecimal _start_price) {
		this._start_price = _start_price;
	}

	@Override
	public String toString() {
		return "BaseModel [_start_time=" + _start_time + ", _end_time=" + _end_time + ", page=" + page + ", rows="
				+ rows + ", _start_price=" + _start_price + ", _end_price=" + _end_price + "]";
	}

	public BigDecimal get_end_price() {
		return _end_price;
	}

	public void set_end_price(BigDecimal _end_price) {
		this._end_price = _end_price;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String get_start_time() {
		return _start_time;
	}

	public void set_start_time(String _start_time) {
		this._start_time = _start_time;
	}

	public String get_end_time() {
		return _end_time;
	}

	public void set_end_time(String _end_time) {
		this._end_time = _end_time;
	}

}
