package com.dtt.simulations.POA.repo;


import com.dtt.simulations.POA.model.PoaTempDocStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PoaTempDocStatusRepo extends JpaRepository<PoaTempDocStatus,Integer> {
    @Query(value = "select * from temp_doc_status_data where status!=?1",nativeQuery = true)
    List<PoaTempDocStatus> fetchNotCompletedDocs(String status);
}
