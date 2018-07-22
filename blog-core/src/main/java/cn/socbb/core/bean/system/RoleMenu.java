package cn.socbb.core.bean.system;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cbb on 2018/7/15.
 */
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1938447440252706383L;

    @Id
    private Long id;

    private String roleId;

    private String menuId;

}
