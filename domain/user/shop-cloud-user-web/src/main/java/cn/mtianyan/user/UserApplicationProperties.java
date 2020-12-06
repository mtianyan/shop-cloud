package cn.mtianyan.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
public class UserApplicationProperties {

    @Value("${userservice.registration.disabled:true}")
    private boolean disableRegistration;

}
