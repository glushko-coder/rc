package ru.rcslabs.taxes.repositories;

import org.springframework.stereotype.Repository;
import ru.rcslabs.taxes.dto.RequestDto;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("taxesRepo")
public class TaxesRepo {

    @PersistenceContext
    protected EntityManager entityManager;

    public List getAll() {
        return entityManager
                .createNativeQuery("SELECT a, b, c, d, y, v FROM source_data LIMIT 1000")
                .getResultList();
    }

    public List getColumnA() {
        return entityManager
                .createNativeQuery("SELECT DISTINCT a FROM source_data ORDER BY a")
                .getResultList();
    }

    public List getColumnB(String value) {
        return entityManager
                .createNativeQuery("SELECT DISTINCT b FROM source_data WHERE a =:v ORDER BY b")
                .setParameter("v", value)
                .getResultList();
    }

    public List getColumnC() {
        return entityManager
                .createNativeQuery("SELECT DISTINCT c FROM source_data ORDER BY c")
                .getResultList();
    }

    public List getColumnD(String value) {
        return entityManager
                .createNativeQuery("SELECT DISTINCT d FROM source_data WHERE c = :v ORDER BY d")
                .setParameter("v", value)
                .getResultList();
    }

    public List getColumnY() {
        return entityManager
                .createNativeQuery("SELECT DISTINCT y FROM source_data ORDER BY y")
                .getResultList();
    }

    public List getAllByParameters(RequestDto requestDto) {
        StringBuilder query = new StringBuilder("SELECT a, b, c, d, y, sum(v) FROM source_data WHERE ");
        boolean insertAdd = false;
        if (requestDto.getA() != null) {
            query.append("a = :val_a");
            insertAdd = true;
        }
        if (requestDto.getB() != null) {
            query.append(insertAdd ? " AND " : "").append("b = :val_b");
            insertAdd = true;
        }
        if (requestDto.getC() != null) {
            query.append(insertAdd ? " AND " : "").append("c = :val_c");
            insertAdd = true;
        }
        if (requestDto.getD() != null) {
            query.append((insertAdd ? " AND " : "") + "d = :val_d");
            insertAdd = true;
        }
        if (requestDto.getY() != null) {
            query.append((insertAdd ? " AND " : "") + " y = :val_y");
        }
        query.append(" GROUP BY a, b, c, d, y");
        Query queryObg = entityManager.createNativeQuery(query.toString());
        if (requestDto.getA() != null) {
            queryObg.setParameter("val_a", requestDto.getA());
        }
        if (requestDto.getB() != null) {
            queryObg.setParameter("val_b", requestDto.getB());
        }
        if (requestDto.getC() != null) {
            queryObg.setParameter("val_c", requestDto.getC());
        }
        if (requestDto.getD() != null) {
            queryObg.setParameter("val_d", requestDto.getD());
        }
        if (requestDto.getY() != null) {
            queryObg.setParameter("val_y", requestDto.getY());
        }
        return queryObg.getResultList();
    }
}
