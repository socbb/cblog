package cn.socbb.core.bean.system;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cbb on 2018/7/15.
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = -1902565918560173797L;

    @Id
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
