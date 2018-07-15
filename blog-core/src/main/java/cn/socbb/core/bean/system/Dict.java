package cn.socbb.core.bean.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cbb on 2018/7/15.
 */
@Data
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String key;

    private String value;

    private String tableName;

    private String fieldName;

}
