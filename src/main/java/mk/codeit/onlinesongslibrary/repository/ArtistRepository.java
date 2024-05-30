package mk.codeit.onlinesongslibrary.repository;

import mk.codeit.onlinesongslibrary.model.Artist;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository <Artist, Long> {
    List<Artist> findAllByNationalityEqualsAndDateOfBirthIsBefore (String nationality, LocalDate date);
}
