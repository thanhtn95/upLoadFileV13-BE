package zone.god.uploadv13.service;

import org.springframework.beans.factory.annotation.Autowired;
import zone.god.uploadv13.model.Card;
import zone.god.uploadv13.repository.CardRepository;

public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Iterable<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card findById(long id) {
        if (cardRepository.findById(id).isPresent()) {
            return cardRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Card save(Card card) {
        cardRepository.save(card);
        return card;
    }

    @Override
    public Card delete(long id) {
        Card card = findById(id);
        cardRepository.deleteById(id);
        return card;
    }
}
