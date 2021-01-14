package com.gag.enterprisewxmobile.system.ticket.controller;

import com.gag.enterprisewxmobile.system.ticket.entity.Ticket;
import com.gag.enterprisewxmobile.system.ticket.service.TicketService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Ticket)表控制层
 *
 * @author makejava
 * @since 2020-04-30 14:36:42
 */
@RestController
@RequestMapping("ticket")
public class TicketController {
    /**
     * 服务对象
     */
    @Resource
    private TicketService ticketService;


}