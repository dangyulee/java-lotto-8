package lotto;

import lotto.service.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        try {
            int money = InputView.inputMoney();
            List<Lotto> lottos = LottoGenerator.generateLottos(money);
            for (Lotto lotto : lottos) {
                System.out.println(lotto);
            }

            Lotto winningNumber = InputView.inputWinningNumber();
            int bonusNumber = InputView.inputBonusNumber();

            System.out.println(OutputView.outputResult(lottos, winningNumber, bonusNumber, money));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
