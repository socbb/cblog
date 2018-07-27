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
public class UserRole implements Serializable {

    private static final long serialVersionUID = 2820646412709632294L;

    @Id
    private Long id;

    private Long userId;

    private Long roleId;

    public UserRole(Long userId) {
        this.userId = userId;
    }

    public UserRole() {
    }

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public void applyDefaultValue(){
        if (getId() == null) {
            setId(SnowflakeUtils.id());
        }
    }
}
