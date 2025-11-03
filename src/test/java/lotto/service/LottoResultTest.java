package lotto.service;

import lotto.Lotto;
import lotto.domain.LottoRank;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private Lotto lottoOf(int... nums) {
        return new Lotto(IntStream.of(nums).boxed().collect(Collectors.toList()));
    }

    @Test
    void evaluateTest() {
        // given
        Lotto winning = lottoOf(1, 2, 3, 4, 5, 6);
        int bonus = 7;
        List<Lotto> lottos = List.of(
                lottoOf(1, 2, 3, 4, 5, 6),
                lottoOf(1, 2, 3, 4, 5, 7),
                lottoOf(1, 2, 3, 4, 5, 45),
                lottoOf(1, 2, 3, 4, 44, 45),
                lottoOf(1, 2, 3, 43, 44, 45),
                lottoOf(8, 9, 10, 11, 12, 13)
        );

        // when
        Map<LottoRank, Integer> counts = LottoResult.evaluate(lottos, winning, bonus);

        // then
        assertThat(counts.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(counts.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(counts.get(LottoRank.THIRD)).isEqualTo(1);
        assertThat(counts.get(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(counts.get(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(counts.get(LottoRank.MISS)).isEqualTo(1);
    }

    @Test
    void calculateProfitRateTest() {
        // given
        Map<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        for (LottoRank r : LottoRank.values()) counts.put(r, 0);
        counts.put(LottoRank.FIRST, 1);
        counts.put(LottoRank.SECOND, 1);
        counts.put(LottoRank.THIRD, 1);
        counts.put(LottoRank.FOURTH, 1);
        counts.put(LottoRank.FIFTH, 1);
        counts.put(LottoRank.MISS, 1);
        int money = 6_000;
        long totalPrize = 2_000_000_000L + 30_000_000L + 1_500_000L + 50_000L + 5_000L;
        double expected = ((double) totalPrize / money) * 100;

        // when
        double result = LottoResult.calculateProfitRate(counts, money);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateProfitRate_구매금액0이면_수익률0반환() {
        // given
        Map<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        for (LottoRank r : LottoRank.values()) counts.put(r, 0);

        // when
        double rate = LottoResult.calculateProfitRate(counts, 0);

        // then
        assertThat(rate).isZero();
    }
}