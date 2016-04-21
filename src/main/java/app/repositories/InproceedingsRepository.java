package app.repositories;

import app.domain.Inproceedings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InproceedingsRepository extends JpaRepository<Inproceedings, Long> {
    
}
