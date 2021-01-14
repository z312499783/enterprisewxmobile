package com.gag.enterprisewxmobile.system.ticket.dao;

import com.gag.enterprisewxmobile.system.ticket.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Ticket)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-30 14:36:41
 */
@Mapper
public interface TicketDao {

    /**
     * 通过agentidNum查询ticket
     * @param agentidNum
     * @return
     */
    String queryTicket(String agentidNum);

    /**
     * 通过ID查询单条数据
     *
     * @param ticketId 主键
     * @return 实例对象
     */
    Ticket queryById(Integer ticketId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Ticket> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ticket 实例对象
     * @return 对象列表
     */
    List<Ticket> queryAll(Ticket ticket);

    /**
     * 新增数据
     *
     * @param ticket 实例对象
     * @return 影响行数
     */
    int insert(Ticket ticket);

    /**
     * 修改数据
     *
     * @param ticket 实例对象
     * @return 影响行数
     */
    int update(Ticket ticket);

    /**
     * 通过主键删除数据
     *
     * @param ticketId 主键
     * @return 影响行数
     */
    int deleteById(Integer ticketId);

}