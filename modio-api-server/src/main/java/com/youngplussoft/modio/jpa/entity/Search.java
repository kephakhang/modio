package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;

//import org.apache.commons.lang.builder.ToStringBuilder;

public class Search implements Serializable {

    /* serialVersionUID */
    private static final long serialVersionUID = 706986535304576134L;

    /* 검색조건 */
    private String searchCondition = "";

    /* 검색Keyword */
    private String searchKeyword = "";

    /* 검색사용여부 */
    private String searchUseYn = "";

    /* 현재페이지 */
    private int pageIndex = 1;

    /* 페이지갯수 */
    private int pageUnit = 10;

    /* 페이지사이즈 */
    private int pageSize = 10;

    /* firstIndex */
    private int firstIndex = 1;

    /* lastIndex */
    private int lastIndex = 1;

    /* recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /* totalRecordCount */
    private int totalRecordCount ;
    
    /* limitStart */
    private int limitStart=0 ;
    
    /* pagingDiv */
    private String pagingDiv = "" ;
    
    /* totalPages */
    private int totalPages = 0 ;

    public int getLimitStart() {
    	return limitStart ;
    }
    
    public void setLimitStart(int limitStart) {
    	this.limitStart = limitStart ;
    }
    
    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return "" ; //ToStringBuilder.reflectionToString(this);
    }
    
    
    public void setTotalRecordCount(int totalRecordCount) {
    	this.totalRecordCount = totalRecordCount ;
    }
    
    public int getTotalRecordCount() {
    	return this.totalRecordCount ;
    }
    
    public void setPagingDiv(String pagingDiv) {
    	this.pagingDiv = pagingDiv ;
    }
    
    public String getPagingDiv() {
    	
    	if( totalRecordCount == 0 ) {
    		pagingDiv = "" ;
    		return pagingDiv ;
    	}
    	
    	pagingDiv = "<div class=\"paging\">\n" ;
    	
    	totalPages = totalRecordCount/pageUnit + (totalRecordCount%pageUnit > 0 ? 1 : 0) ;
    	
	    if (pageIndex > 1 && totalPages > 0 ) {
	        pagingDiv += "<a href=\"javascript:gotoPage(1)\" class=\"page_pd\"><img src=\"#CONTEXT/image/btn_first.gif\" alt=\"처음으로\"></a>\n" ;
	    }
	    
	    

	    firstIndex = ( ( (int)( (pageIndex - 1 ) / pageSize) ) * pageSize ) + 1;
	    lastIndex = firstIndex + pageSize - 1;

	    if (lastIndex >= totalPages) lastIndex = totalPages;

	    if (firstIndex > 1) {
	    	int index = (this.pageIndex - this.pageSize) > 0 ? (this.pageIndex - this.pageSize) : 1 ;
	    	pagingDiv += "<a href=\"javascript:gotoPage(" +  index  + ")\" class=\"page_pd\"><img src=\"#CONTEXT/image/btn_prev.gif\" alt=\"이전\"></a>\n" ;
	    }

	    if (totalPages > 1) {
	        for (int k=firstIndex; k<=lastIndex ; k++) {
	            if (pageIndex != k)
	            	pagingDiv += "<a href=\"javascript:gotoPage(" + k + ")\">" + k + "</a>\n" ;
	            else
	            	pagingDiv += "<a href=\"javascript:gotoPage(" + k + ")\" class=\"on\">" + k + "</a>\n" ;
	        }
	    }

	    if (totalPages > lastIndex) {
	    	int index = (this.pageIndex + this.pageSize) > totalPages ? totalPages : (this.pageIndex + this.pageSize) ;
	    	pagingDiv += "<a href=\"javascript:gotoPage(" +  index  + ")\" class=\"page_pd\"><img src=\"#CONTEXT/image/btn_next.gif\" alt=\"다음\"></a>\n" ;	    	
	    }
	    if (pageIndex < totalPages) {
	    	pagingDiv += "<a href=\"javascript:gotoPage(" + totalPages + ")\" class=\"page_pd\"><img src=\"#CONTEXT/image/btn_last.gif\" alt=\"끝으로\"></a>\n" ;
	    }

	    pagingDiv += "</div>\n" ;
	    
	    
	    return pagingDiv ;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
