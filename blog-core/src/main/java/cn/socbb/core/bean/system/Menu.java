package cn.socbb.core.bean.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cbb on 2018/7/15.
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String parentId;

    private String name;

    private String url;

    private String perms;

    private String icon;

    /**
     * 1 菜单
     * 2 按钮
     */
    private Integer type;

    private Integer order;

    private Date createTime;

    private Date updateTime;
}
