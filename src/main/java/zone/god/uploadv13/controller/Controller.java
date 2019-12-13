package zone.god.uploadv13.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zone.god.uploadv13.model.Card;
import zone.god.uploadv13.model.Card_Form;
import zone.god.uploadv13.model.Card_updateForm;
import zone.god.uploadv13.service.CardService;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class Controller {
    @Autowired
    private CardService cardService;
    @Autowired
    Environment evm;

    @GetMapping("")
    public ResponseEntity<Iterable<Card>> getCardDB() {
        Iterable<Card> cards = cardService.findAll();
        return new ResponseEntity<Iterable<Card>>(cards, HttpStatus.OK);
    }

    @PostMapping("/newCard")
    public ResponseEntity<Card> addCard(@RequestParam("card") String cardForm, @RequestParam("image") Optional<MultipartFile> image) throws JsonParseException, JsonMappingException, IOException {
        Card_Form card_form = new ObjectMapper().readValue(cardForm, Card_Form.class);
        Card card = new Card();
        card.setName(card_form.getName());
        card.setGrade(card_form.getGrade());
        card.setDescription(card_form.getDescription());
        doUpLoad(image, card);
        return new ResponseEntity<Card>(card, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/deleteCard")
    public ResponseEntity<Card> deleteCard(@PathVariable("id") long id) {
        Card card = cardService.delete(id);
        return new ResponseEntity<Card>(card, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateCard")
    public ResponseEntity<Card> updateCard(@RequestParam("card") String cardForm, @RequestParam("image") Optional<MultipartFile> image) throws JsonParseException, JsonMappingException, IOException {
        Card_updateForm card_form = new ObjectMapper().readValue(cardForm, Card_updateForm.class);
        Card card = new Card();
        card.setId(card_form.getId());
        card.setImage(card_form.getImage());
        card.setName(card_form.getName());
        card.setGrade(card_form.getGrade());
        card.setDescription(card_form.getDescription());
        doUpLoad(image, card);
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

    private void doUpLoad(Optional<MultipartFile> file, Card card) {
        if (file.isPresent()) {
            String fileUpload = evm.getProperty("uploadPath").toString();
            String fileName = file.get().getOriginalFilename();
            if (!fileName.equals("")) {
                try {
                    FileCopyUtils.copy(file.get().getBytes(), new File(fileUpload + fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                card.setImage(fileName);
            }
        }
        cardService.save(card);
    }
}
