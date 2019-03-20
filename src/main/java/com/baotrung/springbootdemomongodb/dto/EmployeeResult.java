package com.baotrung.springbootdemomongodb.dto;

public class EmployeeResult {

    private String _id;
    private Long total;

    public EmployeeResult() {
    }

    public EmployeeResult(String _id, Long total) {
        this._id = _id;
        this.total = total;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
