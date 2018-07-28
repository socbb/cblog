package cn.socbb.core.bean.system;

import cn.socbb.common.enums.RoleStatusEnum;
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
public class Role implements Serializable {

    private static final long serialVersionUID = -253077245225771973L;

    @Id
    private Long id;

    private String name;

    private String remark;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public void applyDefaultValue(){
        Date date = new Date();
        if (getCreateTime() == null) {
            setCreateTime(date);
        }
        if (getUpdateTime() == null) {
            setUpdateTime(date);
        }
        if (getStatus() == null) {
            setStatus(RoleStatusEnum.NORMAL.getCode());
        }
        if (getId() == null) {
            setId(SnowflakeUtils.id());
        }
    }
}
