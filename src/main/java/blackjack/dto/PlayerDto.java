package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<Card> cards;

    public PlayerDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = Collections.unmodifiableList(new ArrayList<>(cards));
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}