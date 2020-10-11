package hg.std.shequ.mapper;

import org.apache.ibatis.annotations.Mapper;
import hg.std.shequ.model.User;
import org.apache.ibatis.annotations.Insert;


@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_Create,gmt_Modified)values(#{name},#{accountid},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
