package com.forteach.education.common.domain;

import java.util.List;

public class PageModel<E> {

    /**
     * 结果集
     */
    private List<E> list;

    /**
     * 查询记录数
     */
    private int totalRecords;

    /**
     * 每页多少条数据
     */
    private int pageSize;

    /**
     * 第几页
     */
    private int pageNo;
    /**
     * 总页数
     */
    private int totalPages;
 
       
    /** 
    * @Title: getTotalPages 
    * @Description: TODO(总页数)  
    * @return int    返回类型 
    * @throws
     */
    public int getTotalPages() { 
        return (totalRecords + pageSize - 1) / pageSize; 
    } 
       
    /** 
    * @Title: getTopPageNo 
    * @Description: TODO(取得首页) 
    * @return int    返回类型 
    * @throws
     */
    public int getTopPageNo() { 
        return 1; 
    } 
       
    /**
     * 
    * @Title: getPreviousPageNo 
    * @Description: TODO(上一页) 
    * @return int    返回类型 
    * @throws
     */
    public int getPreviousPageNo() { 
        if (pageNo <= 1) { 
            return 1; 
        } 
        return pageNo - 1; 
    } 
       
    /**
     * 
    * @Title: getNextPageNo 
    * @Description: TODO(下一页)  
    * @return int    返回类型 
    * @throws
     */
    public int getNextPageNo() { 
        if (pageNo >= getBottomPageNo()) { 
            return getBottomPageNo(); 
        } 
        return pageNo + 1;   
    } 
       
    /**
     * 
    * @Title: getBottomPageNo 
    * @Description: TODO(取得尾页) 
    * @return int    返回类型 
    * @throws
     */
    public int getBottomPageNo() { 
        return getTotalPages(); 
    } 
       
    public List<E> getList() { 
        return list; 
    } 
   
    public void setList(List<E> list) { 
        this.list = list; 
    } 
   
    public int getTotalRecords() { 
        return totalRecords; 
    } 
   
    public void setTotalRecords(int totalRecords) { 
        this.totalRecords = totalRecords; 
    } 
   
    public int getPageSize() { 
        return pageSize; 
    } 
   
    public void setPageSize(int pageSize) { 
        this.pageSize = pageSize; 
    } 
   
    public int getPageNo() { 
        return pageNo; 
    } 
   
    public void setPageNo(int pageNo) { 
        this.pageNo = pageNo; 
    }

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}  
}
