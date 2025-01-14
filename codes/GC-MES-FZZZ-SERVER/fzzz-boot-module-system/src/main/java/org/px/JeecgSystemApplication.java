package org.px;

import lombok.extern.slf4j.Slf4j;
import org.px.common.util.oConvertUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
* 单体启动类（采用此类启动为单体模式） 想笑 别叫 有用
*/
@Slf4j
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
//        ,org.activiti.spring.boot.SecurityAutoConfiguration.class
})
@EnableScheduling
@EnableTransactionManagement
public class JeecgSystemApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JeecgSystemApplication.class);
    }


    public static void main(String[] args) throws UnknownHostException, IOException {
        ConfigurableApplicationContext application = SpringApplication.run(JeecgSystemApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = oConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Jeecg-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
        //项目启动时加载ServerSocket
/*        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(8266);
            while (true){
                try{
                    socket = serverSocket.accept();
                }catch (Exception e){
                    System.out.println("建立连接异常");
                    e.printStackTrace();
                }
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        }catch (Exception e){
            System.out.println("端口被占用");
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }*/
    }


    @Autowired
    private Environment environment;

    //创建队列
    @Bean
    public Queue queue() {
        return new Queue(environment.getProperty("mq.pay.queue.order"));
    }

    //创建交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(environment.getProperty("mq.pay.exchange.order"));
    }

    //将队列与交换机进行绑定
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(environment.getProperty("mq.pay.routing.order"));
    }
}
