package com.dtt.simulations.POA.service.Impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoaRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    public String deleteFromPoaTables() {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ra_0_2.delete_from_poa_tables");

            boolean hasResult = query.execute();

            if (hasResult) {
                List<?> resultList = query.getResultList();
                if (!resultList.isEmpty() && resultList.get(0) != null) {
                    return resultList.get(0).toString();
                }
            }

            return "All data deleted from all three tables successfully";

        } catch (Exception e) {
            throw new RuntimeException("Error executing stored procedure: " + e.getMessage(), e);
        }
    }
}
