package net.loyin.util;

import java.io.Serializable;

/**
 * 分页BEAN
 * @author 刘声凤
 */
@SuppressWarnings("serial")
public class Page implements Serializable {
	/**
	 * 页面显示数
	 */
	public int pageSize = 20;
	/**当前页**/
	public int pageNo=1;
	/**
	 * 起始数据
	 */
	public long start=0;
	/**
	 * 数据集
	 */
	public Object data=null;
	/**
	 * 分页起始页码
	 */
	public long startPage=0;
	/**
	 * 分页显示条数
	 */
	public long pageShowcount=10; 
	/**
	 * 总数据数目
	 */
	public long totalCount;
	/**其他总数*/
	public long othertotal;
	/**总页数*/
	public long totalPageCount;
	public Page() {
	}

	/**
	 * 实例化
	 * @param long start 起始
	 * @param long totalSize 总数
	 * @param long pageNo
	 * @param int pageSize
	 * @param Object data
	 */
	public Page( long totalSize,int pageNo, int pageSize, Object data) {
		this.pageSize = pageSize;
		this.start = (pageNo-1)*pageSize;
		this.totalCount = totalSize;
		this.data = data;
		this.pageNo=pageNo;
//		this.startPage=(this.pageNo/this.pageShowcount)*this.pageShowcount;
		long i=this.pageNo %this.pageShowcount;
		this.startPage=i==0?this.pageNo-9:this.pageNo-i+1;
		if (totalCount % pageSize == 0)
			this.totalPageCount= totalCount / pageSize;
		else
			this.totalPageCount= totalCount / pageSize + 1;
	}
	
	public long getTotalPageCount() {
		return this.totalPageCount;
	}
	
	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getStartPage() {
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}

	public long getPageShowcount() {
		return pageShowcount;
	}

	public void setPageShowcount(long pageShowcount) {
		this.pageShowcount = pageShowcount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public synchronized static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo-1)*pageSize;
	}

	public long getOthertotal() {
		return othertotal;
	}

	public void setOthertotal(long othertotal) {
		this.othertotal = othertotal;
	}
	
}