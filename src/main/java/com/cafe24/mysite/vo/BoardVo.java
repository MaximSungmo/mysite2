package com.cafe24.mysite.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class BoardVo {

	private Long no;
	private Long user_no;
	private String user_name;

	@NotEmpty
	@Length(min = 2)
	private String title;
	@NotEmpty
	@Length(min = 2)
	private String contents;

	private Long hit;
	private String reg_date;
	private Long group_no;
	private Long order_no;
	private Long depth;

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BoardVo() {

	}

	public BoardVo(Long no) {
		this.no = no;
	}

	public BoardVo(Long user_no, String title, String contents, Long group_no, Long order_no, Long depth) {
		this.user_no = user_no;
		this.title = title;
		this.contents = contents;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
	}

	public BoardVo(Long no, String title, String contents) {
		this.user_no = no;
		this.title = title;
		this.contents = contents;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getUser_no() {
		return user_no;
	}

	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getcontents() {
		return contents;
	}

	public void setcontents(String contents) {
		this.contents = contents;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public Long getGroup_no() {
		return group_no;
	}

	public void setGroup_no(Long group_no) {
		this.group_no = group_no;
	}

	public Long getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}

	public Long getDepth() {
		return depth;
	}

	public void setDepth(Long depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", user_no=" + user_no + ", title=" + title + ", contents=" + contents + ", hit="
				+ hit + ", reg_date=" + reg_date + ", group_no=" + group_no + ", order_no=" + order_no + ", depth="
				+ depth + "]";
	}

}
