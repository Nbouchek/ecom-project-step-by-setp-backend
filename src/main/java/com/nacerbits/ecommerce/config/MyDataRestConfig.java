package com.nacerbits.ecommerce.config;

import com.nacerbits.ecommerce.entitiy.Product;
import com.nacerbits.ecommerce.entitiy.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        // disable HTTP methods for Product and ProductCategory: PUT, POST, and DELETE
        disableHttpMethods(config, Product.class, theUnsupportedActions);
        disableHttpMethods(config, ProductCategory.class, theUnsupportedActions);
    }

    private void disableHttpMethods(RepositoryRestConfiguration config, Class<?> theType, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theType)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}
