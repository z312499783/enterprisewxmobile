package com.gag.enterprisewxmobile.system.ticket.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Ticket)实体类
 *
 * @author makejava
 * @since 2020-04-30 14:36:40
 */
public class Ticket implements Serializable {
    private static final long serialVersionUID = -20254874625541987L;
    
    private Integer ticketId;
    
    private String agentidNum;
    
    private String ticket;
    /**
    * 7200
    */
    private Integer expiresIn;
    
    private Date startTime;
    
    private Date endTime;


    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getAgentidNum() {
        return agentidNum;
    }

    public void setAgentidNum(String agentidNum) {
        this.agentidNum = agentidNum;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}