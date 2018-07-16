package cn.socbb.core.bean.system;

import lombok.Data;

/**
 * Created by cbb on 2018/7/16.
 */
@Data
public class Seq {

    private String table;
    private Long seq;

    public Seq(String table, Long seq) {
        this.table = table;
        this.seq = seq;
    }
}
