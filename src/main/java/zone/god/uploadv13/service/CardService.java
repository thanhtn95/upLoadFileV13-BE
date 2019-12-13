package zone.god.uploadv13.service;

import org.springframework.stereotype.Service;
import zone.god.uploadv13.model.Card;


public interface CardService {
    Iterable<Card> findAll();
    Card findById(long id);
    Card save(Card card);
    Card delete(long id);
}
