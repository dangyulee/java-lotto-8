package lotto;

import lotto.service.LottoGenerator;
import lotto.view.InputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int money = InputView.inputMoney();
        List<Lotto> lottos = LottoGenerator.generateLottos(money);
    }
}
