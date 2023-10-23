package dev.incredas.spring.starter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
@SuppressWarnings("squid:S119")
public interface EntityRepository<ENTITY, ID> extends JpaRepository<ENTITY, ID>, JpaSpecificationExecutor<ENTITY> {
}
