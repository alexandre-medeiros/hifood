package com.himax.hifood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaRepository<T,ID> extends JpaRepository<T,ID> {
    T findOrFail(ID id);

    T findChildOrFail(ID id);

    void deleteOrFail(ID id);
}
