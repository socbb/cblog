package cn.socbb.core.bean.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cbb on 2018/7/15.
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public User(Long id) {
        this.id = id;
    }
}
