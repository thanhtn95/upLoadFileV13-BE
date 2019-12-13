package zone.god.uploadv13.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import zone.god.uploadv13.model.Card;

public interface CardRepository extends PagingAndSortingRepository<Card,Long> {
}
