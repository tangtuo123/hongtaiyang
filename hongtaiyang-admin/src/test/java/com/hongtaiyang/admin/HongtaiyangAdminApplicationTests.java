package com.hongtaiyang.admin;

import com.hongtaiyang.admin.service.ResourceService;
import com.hongtaiyang.admin.service.RoleService;
import com.hongtaiyang.common.entity.Role;
import com.hongtaiyang.common.entity.request.RoleRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HongtaiyangAdminApplicationTests {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    AmqpAdmin amqpAdmin;

    @Resource
    private ResourceService resourceService;

    @Resource
    private RoleService roleService;






    @Test
    public void contextLoadsTest() {
        List<com.hongtaiyang.common.entity.Resource> resources = resourceService.selectByRoleId(1);
        System.out.println(resources);
    }

    @Test
    public void createExchange() {
        DirectExchange directExchange = new DirectExchange("citydo.rabbit", true, false);
        amqpAdmin.declareExchange(directExchange);
    }

    @Test
    public void createQueue() {
        Queue queue = new Queue("citydo.queue", true, false, false);
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void createBinding() {
        Binding binding = new Binding("citydo.queue", Binding.DestinationType.QUEUE, "citydo.rabbit", "citydo.queue", null);
        amqpAdmin.declareBinding(binding);
    }

    @Test
    public void sendMessage() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "tangtuo");
        map.put("age", "12");
        rabbitTemplate.convertAndSend("citydo.rabbit", "citydo.queue", map);
        log.info("sendMessage success");
    }

    @Test
    public void cacheAble() {
        Role role = roleService.getById(2);
        System.out.println(role);
    }

    @Test
    public void cachePut() {
        System.out.println(UUID.randomUUID().toString());
        Role role1 = new Role();
        role1.setDescription("哈哈哈哈111");
        role1.setId(2);
        RoleRequest roleRequest = new RoleRequest();
        roleRequest.setRole(role1);
        roleService.updateById(roleRequest);
        Role role = roleService.getById(2);
        System.out.println(role);
    }


}
