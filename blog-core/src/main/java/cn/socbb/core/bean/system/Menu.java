package cn.socbb.core.bean.system;

import cn.socbb.common.enums.BooleanEnmu;
import cn.socbb.common.utils.SnowflakeUtils;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cbb on 2018/7/15.
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = -1902565918560173797L;

    @Id
    private Long id;
    private Long parentId;
    private Integer root;
    private String name;
    private String url;
    private String perms;
    private String icon;
    private Integer type;
    private Integer seq;
    private Date createTime;
    private Date updateTime;
    @Transient
    private List<Menu> children = new ArrayList<>();

    @Transient
    public void applyDefaultValue(){
        if (getId() == null) {
            setId(SnowflakeUtils.id());
        }
        if (getSeq() == null) {
            setSeq(Integer.MAX_VALUE);
        }
        if (getParentId() == null) {
            setRoot(BooleanEnmu.YES.getCode());
        } else {
            setRoot(BooleanEnmu.NO.getCode());
        }
        if (getCreateTime() == null){
            setCreateTime(new Date());
        }
        if (getUpdateTime() == null){
            setUpdateTime(new Date());
        }
    }
}
