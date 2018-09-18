package cn.ccenjie.activemq.mapper;

import cn.ccenjie.activemq.entity.Stress;
import org.apache.ibatis.annotations.*;

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



    @Select("select * from t_stress limit 1000")
    List<Stress> all();

    /**
     * 创建表
     * @param tableName
     * @return
     */
    @Update("CREATE TABLE #{tableName} (" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT," +
            "  `name` varchar(50) DEFAULT NULL," +
            "  `ref_no` varchar(50) DEFAULT NULL," +
            "  PRIMARY KEY (`id`)" +
            ") ENGINE=InnoDB AUTO_INCREMENT=4008 DEFAULT CHARSET=utf8")
    int createBakTable(@Param("tableName") String tableName);

    @Insert("<script>" +
            "insert into #{tbleName}(id, `name`, ref_no) " +
            "values" +
            "<foreach collection ='list' item='s' separator =','> " +
            "(#{s.id}, #{s.name}, #{s.refNo})" +
            "</foreach >" +
            "</script>")
    int batchInsertBak(@Param("list") List<Stress> list, @Param("tableName") String tbleName);

    @Delete("<script>" +
            "delete from t_stress where id in " +
            "<foreach collection ='list' item='s' separator =',' open='(' close=','> " +
            "#{s}" +
            "</foreach >" +
            "</script>")
    int del(@Param("list") List<Integer> list);


}
