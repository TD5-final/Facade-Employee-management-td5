package com.example.prog4.repository.cnaps;

import com.example.prog4.repository.employee.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionCnapsRepository extends JpaRepository<com.example.prog4.repository.cnaps.cnapsEntity.Position, String> {
    Optional<Position> findPositionByNameEquals(String name);
}
