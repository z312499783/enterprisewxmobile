package com.gag.enterprisewxmobile.tool.common.utils;

import com.gag.enterprisewxmobile.system.menu.entity.QywxSysMenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 权限数据处理
 *
 * @author zjc
 */
public class TreeUtils
{
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<QywxSysMenu> getChildPerms(List<QywxSysMenu> list, int parentId)
    {
        List<QywxSysMenu> returnList = new ArrayList<QywxSysMenu>();
        for (Iterator<QywxSysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            QywxSysMenu t = (QywxSysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static void recursionFn(List<QywxSysMenu> list, QywxSysMenu t)
    {
        // 得到子节点列表
        List<QywxSysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (QywxSysMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<QywxSysMenu> it = childList.iterator();
                while (it.hasNext())
                {
                    QywxSysMenu n = (QywxSysMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<QywxSysMenu> getChildList(List<QywxSysMenu> list, QywxSysMenu t)
    {

        List<QywxSysMenu> tlist = new ArrayList<QywxSysMenu>();
        Iterator<QywxSysMenu> it = list.iterator();
        while (it.hasNext())
        {
            QywxSysMenu n = (QywxSysMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    List<QywxSysMenu> returnList = new ArrayList<QywxSysMenu>();

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<QywxSysMenu> getChildPerms(List<QywxSysMenu> list, int typeId, String prefix)
    {
        if (list == null)
        {
            return null;
        }
        for (Iterator<QywxSysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            QywxSysMenu node = (QywxSysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId)
            {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<QywxSysMenu> list, QywxSysMenu node, String p)
    {
        // 得到子节点列表
        List<QywxSysMenu> childList = getChildList(list, node);
        if (hasChild(list, node))
        {
            // 判断是否有子节点
            returnList.add(node);
            Iterator<QywxSysMenu> it = childList.iterator();
            while (it.hasNext())
            {
                QywxSysMenu n = (QywxSysMenu) it.next();
                n.setMenuName(p + n.getMenuName());
                recursionFn(list, n, p + p);
            }
        }
        else
        {
            returnList.add(node);
        }
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<QywxSysMenu> list, QywxSysMenu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
