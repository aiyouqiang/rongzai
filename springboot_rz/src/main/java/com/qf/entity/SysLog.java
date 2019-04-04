package com.qf.entity;

import java.util.Date;

public class SysLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.id
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.username
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.operation
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    private String operation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.method
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    private String method;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.params
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    private String params;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.ip
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.create_date
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.id
     *
     * @return the value of sys_log.id
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.id
     *
     * @param id the value for sys_log.id
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.username
     *
     * @return the value of sys_log.username
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.username
     *
     * @param username the value for sys_log.username
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.operation
     *
     * @return the value of sys_log.operation
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public String getOperation() {
        return operation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.operation
     *
     * @param operation the value for sys_log.operation
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.method
     *
     * @return the value of sys_log.method
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.method
     *
     * @param method the value for sys_log.method
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.params
     *
     * @return the value of sys_log.params
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public String getParams() {
        return params;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.params
     *
     * @param params the value for sys_log.params
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.ip
     *
     * @return the value of sys_log.ip
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.ip
     *
     * @param ip the value for sys_log.ip
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.create_date
     *
     * @return the value of sys_log.create_date
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.create_date
     *
     * @param createDate the value for sys_log.create_date
     *
     * @mbggenerated Mon Mar 25 16:45:28 CST 2019
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}