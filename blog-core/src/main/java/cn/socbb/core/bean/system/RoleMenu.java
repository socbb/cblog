package cn.socbb.core.bean.system;

import cn.socbb.common.utils.SnowflakeUtils;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by cbb on 2018/7/15.
 */
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1938447440252706383L;

    @Id
    private Long id;

    private Long roleId;

    private Long menuId;

    @Transient
    public void applyDefaultValue() {
        if (getId() == null) {
            setId(SnowflakeUtils.id());
        }
    }
}
