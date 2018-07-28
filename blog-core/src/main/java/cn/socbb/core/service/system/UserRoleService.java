package cn.socbb.core.service.system;

/**
 * Created by cbb on 2018/7/27.
 */
public interface UserRoleService {

    void deleteByUserId(Long... id);

    void deleteByRoleId(Long... id);

}
