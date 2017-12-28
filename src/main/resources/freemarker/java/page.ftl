package com.wind.entity.base;

/**
 * 分页实体类
 * @author wind
 */
public class Page {
    /**
     * 页码
     */
    private int pageNumber;

    /**
     * 该页记录条数
     */
    private int lineNumber;

    /**
     * 记录总条数
     */
    private int totalCount;

    /**
     * 数据结果集
     */
    private Object result;

    public Page() {
        super();
        //默认10行
        this.lineNumber = 10;
    }

    public Page(int pageNumber, int lineNumber) {
        super();
        this.pageNumber = pageNumber;
        this.lineNumber = lineNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getStartRow() {
        //不传或者传1都是从0开始查询
        if(pageNumber <= 1) {
            return 0;
        }
        return (pageNumber - 1) * lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
