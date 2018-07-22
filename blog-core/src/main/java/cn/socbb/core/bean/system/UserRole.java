package cn.socbb.core.bean.system;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cbb on 2018/7/15.
 */
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = 2820646412709632294L;

    @Id
    private Long id;

    private String userId;

    private String roleId;
}
