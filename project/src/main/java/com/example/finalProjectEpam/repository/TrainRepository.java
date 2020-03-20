package com.example.finalProjectEpam.repository;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train,Long> {

    Optional<Train> findTrainByTrainName(String trainName);

}
