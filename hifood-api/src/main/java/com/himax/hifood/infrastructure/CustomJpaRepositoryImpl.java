package com.himax.hifood.infrastructure;

import com.himax.hifood.domain.exception.ChildNotFoundException;
import com.himax.hifood.domain.exception.EntityInUseException;
import com.himax.hifood.domain.exception.EntityNotFoundException;
import com.himax.hifood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;
import javax.persistence.EntityManager;

public class CustomJpaRepositoryImpl<T,ID> extends SimpleJpaRepository<T,ID> implements CustomJpaRepository<T,ID> {
    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public T findOrFail(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                String.format("No %s entity with id %s exists!", getDomainClass().getSimpleName(), id)));
    }

    @Override
    public T findChildOrFail(ID id) {
        try {
            return findOrFail(id);
        }catch (EntityNotFoundException e){
            throw new ChildNotFoundException(String.format("No %s entity with id %s exists!", getDomainClass().getSimpleName(), id));
        }
    }

    @Override
    public void deleteOrFail(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        try{
            deleteById(id);
            flush();
        }catch (Exception e){
            throw new EntityInUseException(
                    String.format("Entity %s with id %s can not be removed because has child registry!", getDomainClass().getSimpleName(), id));
        }
    }
}
