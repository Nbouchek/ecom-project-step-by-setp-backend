package com.nacerbits.ecommerce.config;

import com.nacerbits.ecommerce.entitiy.Product;
import com.nacerbits.ecommerce.entitiy.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

        // call an internal helper method
        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity ids
        //
        // - get a list of all entity classes fro the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // - create array list of entity types
        List<Class> entityClasses = new ArrayList<>();

        // get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // - expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
