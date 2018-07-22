package cn.socbb.core.bean.system;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by cbb on 2018/7/22.
 */
@Data
public class Config implements Serializable {

    private static final long serialVersionUID = 678151372909270649L;

    private Long id;
    private String name;
    private String domain;
    private String desc;
}
