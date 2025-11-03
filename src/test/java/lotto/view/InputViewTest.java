package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        Console.close();
    }

    @Test
    void 구입_금액을_1000원_단위로_입력() {
        // Given
        String fakeInput = "5000\n";
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes(StandardCharsets.UTF_8)));

        // When
        int result = InputView.inputMoney();

        //then
        assertThat(result).isEqualTo(5000);
    }

    @Test
    void 구입_금액이_1000원_미만이면_예외가_발생한다() {
        // Given
        String fakeInput = "500\n";
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes(StandardCharsets.UTF_8)));

        // ✅ When & Then
        assertThatThrownBy(() -> InputView.inputMoney())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 최소 1000원 이상이어야 합니다.");
    }

    @Test
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
        // Given
        String fakeInput = "1500\n";
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes(StandardCharsets.UTF_8)));

        // When & Then
        assertThatThrownBy(() -> InputView.inputMoney())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1000원 단위로 입력해야 합니다.");
    }
}