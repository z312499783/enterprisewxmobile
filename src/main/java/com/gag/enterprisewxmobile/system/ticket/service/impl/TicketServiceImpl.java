package com.gag.enterprisewxmobile.system.ticket.service.impl;

import com.gag.enterprisewxmobile.system.ticket.entity.Ticket;
import com.gag.enterprisewxmobile.system.ticket.dao.TicketDao;
import com.gag.enterprisewxmobile.system.ticket.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Ticket)表服务实现类
 *
 * @author makejava
 * @since 2020-04-30 14:36:41
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService {
    @Resource
    private TicketDao ticketDao;

    /**
     * 通过agentidNum查询ticket
     * @param agentidNum
     * @return
     */
    @Override
    public String queryTicket(String agentidNum){
        return this.ticketDao.queryTicket(agentidNum);
    };

    /**
     * 通过实体作为筛选条件查询
     *
     * @param ticket 实例对象
     * @return 对象列表
     */
    @Override
    public List<Ticket> queryAll(Ticket ticket){
        return this.ticketDao.queryAll(ticket);
    };

    /**
     * 通过ID查询单条数据
     *
     * @param ticketId 主键
     * @return 实例对象
     */
    @Override
    public Ticket queryById(Integer ticketId) {
        return this.ticketDao.queryById(ticketId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Ticket> queryAllByLimit(int offset, int limit) {
        return this.ticketDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ticket 实例对象
     * @return 实例对象
     */
    @Override
    public Ticket insert(Ticket ticket) {
        this.ticketDao.insert(ticket);
        return ticket;
    }

    /**
     * 修改数据
     *
     * @param ticket 实例对象
     * @return 实例对象
     */
    @Override
    public Ticket update(Ticket ticket) {
        this.ticketDao.update(ticket);
        return this.queryById(ticket.getTicketId());
    }

    /**
     * 通过主键删除数据
     *
     * @param ticketId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer ticketId) {
        return this.ticketDao.deleteById(ticketId) > 0;
    }
}