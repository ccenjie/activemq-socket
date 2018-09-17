package cn.ccenjie.activemq.schedule;

import cn.ccenjie.activemq.entity.Stress;
import cn.ccenjie.activemq.mapper.StressMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cenjunjie
 * 2018/9/17
 */
@Component
public class Schedule {

    @Autowired
    private StressMapper mapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Async
    @Scheduled(cron = "0 14 17 17 9 ?")
    public void bak() {
        /*SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        StressMapper mapper = sqlSession.getMapper(StressMapper.class);*/
        List<Stress> list = new ArrayList<>();
        for (int i=0; i<1001; i++) {
            Stress stress = new Stress();
            stress.setId(i);
            stress.setName(i + 1 + "");
            stress.setRefNo(i + "");
            list.add(stress);
        }
        long bef = System.currentTimeMillis();
        mapper.batchInsert(list);
        System.out.println(System.currentTimeMillis() - bef);
    }


    @Async
    @Scheduled(cron = "0 12 18 17 9 ?")
    public void bakxx() {

       /* List<Stress> list = new ArrayList<>();
        for (int i=0; i<1001; i++) {
            Stress stress = new Stress();
            stress.setId(i);
            stress.setName(i + 1 + "");
            stress.setRefNo(i + "");
            list.add(stress);
        }*/
        long bef = System.currentTimeMillis();
        List<Stress> all = mapper.all();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            String tableName = "t_stress" + new Date().getMonth();
            sqlSession.update("CREATE TABLE `" + tableName + "` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `name` varchar(50) DEFAULT NULL," +
                    "  `ref_no` varchar(50) DEFAULT NULL," +
                    "  PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=4008 DEFAULT CHARSET=utf8");
            StringBuffer insertSql = new StringBuffer("insert into " + tableName + "(id, name, ref_no) values ");
            all.forEach(e -> {
                insertSql.append("(" + e.getId() + ",");
                insertSql.append(e.getName() + ",");
                insertSql.append(e.getRefNo() + "),");
            });
            insertSql.setLength(insertSql.length() -1);
            sqlSession.insert(insertSql.toString());

            StringBuffer delSql = new StringBuffer("delete from  t_stress where id in (");
            all.forEach(e -> {
                delSql.append(e.getId());
                delSql.append(",");
            });
            delSql.setLength(delSql.length() -1);

            sqlSession.delete(delSql.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - bef);
    }


}
