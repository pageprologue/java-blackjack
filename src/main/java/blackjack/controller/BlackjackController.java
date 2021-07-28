package blackjack.controller;

import blackjack.domain.game.Blackjack;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    // 1. Table 객체를 생성하면 paticipants가 세팅 된다. 패가 다 돌아감.
    // 2. 플레이어 마다 hit / stand 여부에 따라 패를 추가한다.
    // 3. 모든 플레이어가 카드를 받은 경우, 딜러의 카드를 추가 할지 판단한다.
    // 4. 최종 결과를 표출한다.
    public void run() {
        List<String> players = InputView.getPlayers();
        Blackjack blackjack = new Blackjack(players);

        OutputView.printInitialDeal(blackjack.getParticipants());

        ParticipantDto participants = deal(blackjack);
    }

    private ParticipantDto deal(Blackjack blackjack) {
        dealEachPlayer(blackjack);
        blackjack.dealDealer();
        return blackjack.getParticipants();
    }

    private void dealEachPlayer(Blackjack blackjack) {
        for (Player player : blackjack.getPlayers()) {
            hitOrStand(blackjack, player);
        }
    }

    private void hitOrStand(Blackjack blackjack, Player player) {
        while (InputView.hit(player.getName())) {
            PlayerDto playerDto = blackjack.hit(player);
            OutputView.printPlayerHands(playerDto);
        }

        if (player.neverHit()) {
            OutputView.printPlayerHands(new PlayerDto(player));
        }
    }
}
