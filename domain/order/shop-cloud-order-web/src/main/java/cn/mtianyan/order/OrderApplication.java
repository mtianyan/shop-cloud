package cn.mtianyan.order;

import cn.mtianyan.item.service.ItemService;
import cn.mtianyan.order.fallback.itemservice.ItemCommentsFeignClient;
import cn.mtianyan.user.service.AddressService;
import cn.mtianyan.user.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "cn.mtianyan.order.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"cn.mtianyan", "org.n3r.idworker"})
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients(
//        basePackages = {
//        "cn.mtianyan.user.service",
//        "cn.mtianyan.item.service"
//}
clients = {
        ItemCommentsFeignClient.class,
        ItemService.class,
        UserService.class,
        AddressService.class
}
)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
