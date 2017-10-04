package ch.smartlink.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * Created by KAI on 10/4/17.
 */
@Configuration
@EnableResourceServer
public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${auth.server.url}")
    private String checkTokenEndpointUrl;

    @Value("${auth.server.clientId}")
    private String clientId;

    @Value("${auth.server.clientsecret}")
    private String secret;

    @Value("${authorization.resources.ids}")
    private String resourcesIds;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourcesIds).stateless(false);
    }

    @Bean
    public RemoteTokenServices tokenServices(){
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(secret);
        return remoteTokenServices;
    }

}
