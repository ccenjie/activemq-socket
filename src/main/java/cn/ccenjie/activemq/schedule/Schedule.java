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
import java.util.stream.Collectors;

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
    @Scheduled(cron = "0 0 18 18 9 ?")
    public void bakxx() {

        long bef = System.currentTimeMillis();
        List<Stress> all = mapper.all();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        String tableName = "t_stress" + new Date().getMonth();
        StressMapper mapper = sqlSession.getMapper(StressMapper.class);
        try {
            mapper.createBakTable(tableName);
            mapper.batchInsertBak(all, tableName);
            mapper.del(all.stream().map(e -> e.getId()).collect(Collectors.toList()));
        }catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
        sqlSession.commit();
        System.out.println(System.currentTimeMillis() - bef);
    }


}
