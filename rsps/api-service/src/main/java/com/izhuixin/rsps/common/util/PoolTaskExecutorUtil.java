//package com.izhuixin.rsps.common.util;
//
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
///**
// * Spring线程池单例模式（双重校验）
// */
//public class PoolTaskExecutorUtil {
//
//    public static ThreadPoolTaskExecutor poolTaskExecutor = null;
//
//    private PoolTaskExecutorUtil() {  // 防止外部实例化
//
//    }
//
//    /**
//     * 创建线程池实例
//     * @return
//     */
//    public static ThreadPoolTaskExecutor getInstance() {
//        if (poolTaskExecutor == null) {
//            synchronized (ThreadPoolTaskExecutor.class) {
//                if (poolTaskExecutor == null) {
//                    poolTaskExecutor = new ThreadPoolTaskExecutor();
//                    poolTaskExecutor.setCorePoolSize(1);
//                    poolTaskExecutor.setMaxPoolSize(2);
//                    poolTaskExecutor.setQueueCapacity(800);
//                }
//            }
//        }
//        return poolTaskExecutor;
//    }
//
//}
