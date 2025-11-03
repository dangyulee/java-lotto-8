package lotto.service;

import lotto.Lotto;
import lotto.domain.LottoRank;

import java.util.*;

public class LottoResult {
    public static Map<LottoRank, Integer> evaluate(List<Lotto> lottos, Lotto winning, int bonus) {
        Map<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        for (LottoRank rank : LottoRank.values()) {
            counts.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matched = countMatch(lotto, winning);
            boolean bonusMatched = lotto.getNumbers().contains(bonus);
            LottoRank rank = LottoRank.of(matched, bonusMatched);
            counts.put(rank, counts.get(rank) + 1);
        }

        return counts;
    }

    private static int countMatch(Lotto a, Lotto b) {
        Set<Integer> set = new HashSet<>(a.getNumbers());
        set.retainAll(b.getNumbers());
        return set.size();
    }

    public static double calculateProfitRate(Map<LottoRank, Integer> counts, int money) {
        long total = 0;

        if (money == 0) return 0;
        for (LottoRank rank : counts.keySet()) {
            total += (long) rank.getPrize() * counts.get(rank);
        }

        return (double) total / money * 100;
    }
}
