package cn.socbb.core.bean.system;

import cn.socbb.common.enums.UserStatusEnum;
import cn.socbb.common.utils.SnowflakeUtils;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cbb on 2018/7/15.
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -3323491027712255843L;
    @Id
    private Long id;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String mobile;

    private String description;

    private String avatar;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private String lastLoginIp;

    private Integer loginCount;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(Long id) {
        this.id = id;
    }

    public void applyDefaultValue(){
        Date date = new Date();
        if (getCreateTime() == null) {
            setCreateTime(date);
        }
        if (getUpdateTime() == null) {
            setUpdateTime(date);
        }
        if (getStatus() == null) {
            setStatus(UserStatusEnum.NORMAL.getCode());
        }
        if (getLoginCount() == null) {
            setLoginCount(0);
        }
        if (getId() == null) {
            setId(SnowflakeUtils.id());
        }
    }
}
