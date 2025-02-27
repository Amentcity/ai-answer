package com.answer.config;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@Data
public class VipSchedulerConfig {

    @Bean
    public Scheduler vipScheduler() {
        final AtomicInteger threadNumber= new AtomicInteger(0);
        ThreadFactory threadFactory= new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r, "VipThreadPool-" + threadNumber.getAndIncrement());
                // 设置为非守护线程
                thread.setDaemon(false);
                return thread;
            }
        };
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10, threadFactory);
        return Schedulers.from(scheduledExecutorService);
    }
}
