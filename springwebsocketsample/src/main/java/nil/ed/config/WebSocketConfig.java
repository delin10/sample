package nil.ed.config;

import nil.ed.handler.SpringWebSocketHandler;
import nil.ed.interceptor.SpringWebSocketHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //调用方法也是返回同一个Bean Creating shared instance of singleton bean
        webSocketHandlerRegistry.addHandler(springWebSocketHandler(), "/echo.do")
                .addInterceptors(springWebSocketHandlerInterceptor());
        webSocketHandlerRegistry.addHandler(springWebSocketHandler(), "/echo.do")
                .addInterceptors(springWebSocketHandlerInterceptor()).withSockJS();
    }

    @Bean
    public SpringWebSocketHandler springWebSocketHandler() {
        return new SpringWebSocketHandler();
    }

    @Bean
    public SpringWebSocketHandlerInterceptor springWebSocketHandlerInterceptor() {
        return new SpringWebSocketHandlerInterceptor();
    }
}
