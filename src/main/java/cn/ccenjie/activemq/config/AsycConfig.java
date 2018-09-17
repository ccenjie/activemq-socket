package cn.ccenjie.activemq.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author cenjunjie
 * 2018/9/17
 */
@EnableAsync
@Configuration
public class AsycConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        taskExecutor.setCorePoolSize(50);
        //线程池维护线程的最大数量
        taskExecutor.setMaxPoolSize(200);
        //线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(200);
        //保存时间
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
