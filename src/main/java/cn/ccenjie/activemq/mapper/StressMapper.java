package cn.ccenjie.activemq.mapper;

import cn.ccenjie.activemq.entity.Stress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cenjunjie
 * 2018/9/17
 */
public interface StressMapper {


    @Insert("<script>" +
            "insert into t_stress(id, `name`, ref_no) " +
            "values" +
            "<foreach collection ='list' item='s' separator =','> " +
            "(#{s.id}, #{s.name}, #{s.refNo})" +
            "</foreach >" +
    "</script>")
    int batchInsert(List<Stress> data);

    @Insert("insert into t_stress(id, `name`, ref_no) values(#{id},#{name},#{refNo})")
    int insert(Stress stress);

    @Select("select * from t_stress limit 1000")
    List<Stress> all();
}
