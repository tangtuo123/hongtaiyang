package com.hongtaiyang.admin.controller;

import com.hongtaiyang.common.entity.HttpResponse;
import com.hongtaiyang.common.utils.RedisUtil;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/12/13 18:18
 */
@RestController
@RequestMapping("/lock")
public class LockController {


    @Autowired
    private RedissonClient redisson;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.profiles.active}")
    private String env;

    @GetMapping("test1")
    public String testLock() {
        RLock lock = redisson.getLock("my-lock");
        lock.lock();
        try {
            System.out.println("加锁成功" + Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("解锁成功");
        }
        return "lock";
    }

    @GetMapping("test2")
    public String testTryLock() {
        RLock lock = redisson.getLock("lock");
        try {
            boolean flag = lock.tryLock(1, 60, TimeUnit.SECONDS);

            if (flag) {
                System.out.println("加锁成功--" + Thread.currentThread().getId());
                Thread.sleep(30000);
            } else {
                System.out.println("获取锁失败");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("解锁成功--" + Thread.currentThread().getId());

        }
        return "try-lock";
    }

    @GetMapping(value = "test3")
    public String writeLock() {
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("read-write-lock");
        RLock lock = readWriteLock.writeLock();
        lock.lock();
        String id = "";
        try {
            id = UUID.randomUUID().toString();
            redisUtil.set("val", id);
            System.out.println("写锁加入成功");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return id;
    }

    @GetMapping(value = "test4")
    public String readLock() {
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("read-write-lock");
        RLock lock = readWriteLock.readLock();
        lock.lock();
        String val = "";
        try {
            System.out.println("读锁获取成功");
            val = (String) redisUtil.get("val");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return val;
    }

    @GetMapping(value = "/get")
    public String getCurrentProfiles(){
        return env;
    }

    @GetMapping("/lockDoor")
    public String lockDoor() {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.trySetCount(5);
        try {
            door.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "放假了";
    }

    @GetMapping(value = "goToSchool/{id}")
    public String goToSchool(@PathVariable Long id) {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.countDown();
        return id + "班放学了";
    }

    @GetMapping(value = "test5")
    public HttpResponse test5(){
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        boolean b = semaphore.trySetPermits(10);
        return HttpResponse.success(b);
    }

    @GetMapping(value = "test5/{cnt}")
    public HttpResponse test6(@PathVariable Integer cnt) throws InterruptedException {
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        boolean b = semaphore.tryAcquire(cnt);

        return HttpResponse.success(b);
    }


}
