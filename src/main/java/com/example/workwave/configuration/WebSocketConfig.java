package com.example.workwave.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker( "/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();


    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        converter.setObjectMapper(objectMapper);
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }
}

/*

voici ce que signifie chaque ligne dans la méthode configureMessageBroker :

config.enableSimpleBroker("/user"): active un courtier (broker) de messages pour la diffusion de messages aux clients abonnés à des destinations commençant par "/user". Cela signifie que lorsque le serveur envoie un message à une destination commençant par "/user", tous les clients abonnés à cette destination recevront ce message.
config.setApplicationDestinationPrefixes("/app"): détermine le préfixe qui sera utilisé pour les destinations de messages qui sont destinées à être traitées par l'application elle-même. Dans cet exemple, toutes les destinations commençant par "/app" seront gérées par l'application elle-même.
config.setUserDestinationPrefix("/user"): définit le préfixe pour les destinations de messages utilisées pour les échanges de messages directs entre les clients et le serveur. Par exemple, lorsque vous envoyez un message à "/user/123/message", le message sera envoyé directement au client ayant l'ID "123".
En résumé, cette méthode configure les options du courtier de messages (broker) utilisé par Spring WebSocket pour gérer les messages envoyés entre le serveur et les clients.

--------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------
public void registerStompEndpoints(StompEndpointRegistry registry): Cette méthode enregistre les points d'extrémité Stomp dans le registre. Les points d'extrémité sont les URL auxquelles les clients peuvent se connecter pour établir une connexion WebSocket.
registry: C'est un objet StompEndpointRegistry fourni par Spring pour enregistrer les points d'extrémité Stomp.
.addEndpoint("/ws"): Cette méthode ajoute un point d'extrémité /ws pour la connexion WebSocket.
.setAllowedOrigins("*"): Cela permet aux demandes provenant de toutes les sources (domaines) d'accéder à l'API WebSocket. Vous pouvez spécifier des domaines spécifiques ici au lieu de l'astérisque (*) si vous le souhaitez.
.withSockJS(): Cette méthode permet aux navigateurs qui ne prennent pas en charge directement les WebSockets d'utiliser une alternative appelée SockJS. Cela assure la compatibilité avec une large gamme de navigateurs Web.

 */