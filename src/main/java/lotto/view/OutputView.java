package lotto.view;

import lotto.Lotto;
import lotto.domain.LottoRank;
import lotto.service.LottoResult;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static String outputResult(List<Lotto> lottos, Lotto winning, int bonus, int money) {
        Map<LottoRank, Integer> result = LottoResult.evaluate(lottos, winning, bonus);
        double profitRate = LottoResult.calculateProfitRate(result, money);

        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계\n---\n");
        sb.append(String.format("3개 일치 (%,d원) - %d개%n", 5_000, result.get(LottoRank.FIFTH)));
        sb.append(String.format("4개 일치 (%,d원) - %d개%n", 50_000, result.get(LottoRank.FOURTH)));
        sb.append(String.format("5개 일치 (%,d원) - %d개%n", 1_500_000, result.get(LottoRank.THIRD)));
        sb.append(String.format("5개 일치, 보너스 볼 일치 (%,d원) - %d개%n", 30_000_000, result.get(LottoRank.SECOND)));
        sb.append(String.format("6개 일치 (%,d원) - %d개%n", 2_000_000_000, result.get(LottoRank.FIRST)));
        sb.append(String.format("총 수익률은 %.1f%%입니다.", profitRate));

        return sb.toString();
    }
}
