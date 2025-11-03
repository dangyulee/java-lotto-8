# java-lotto-precourse

## ✅ 구현할 기능 목록

### 1. 로또 구매 및 번호 생성
- 구입 금액 입력 (InputView)
    - 1,000원 단위인지 검증
    - 숫자가 아니거나 1,000 미만이면 예외 처리
- 로또 번호 자동 생성 (LottoGenerator)
    - 구입 금액 / 1000 만큼 로또 발행
    - 1~45 사이의 중복되지 않는 숫자 6개 생성
- 로또 번호 검증 (Lotto)
    - 숫자 개수 6개인지 확인
    - 범위(1~45) 벗어나지 않는지 확인
    - 중복 여부 확인

---

### 2. 당첨 번호 입력 및 결과 계산
- 당첨 번호 및 보너스 번호 입력 (InputView)
    - "1,2,3,4,5,6" 형식으로 입력받아 Lotto 객체 생성
    - 보너스 번호는 숫자 및 범위 검증
- LottoRank(enum)를 사용하여 당첨 결과를 관리 (LottoRank)
    - 당첨 번호와 일치하는 개수, 보너스 번호 포함 여부에 따라 등수를 결정
    - 각 등수에 대한 상금 금액도 LottoRank에서 함께 관리
- 당첨 결과 계산 (LottoResult)
    - 구매한 로또와 당첨 번호 비교 후 등수별 개수 집계
    - 총 당첨 금액 및 수익률 계산
- 결과 출력 (OutputView)
    - 등수별 당첨 내역 출력
    - 총 수익률을 백분율(%)로 출력
---
## ⚙️ 작동 흐름 (Flow)

Application.main()  
↓  
InputView.inputMoney()  
↓  
LottoGenerator.generateLottos(money)  
↓  
구매한 로또 번호 출력  
↓  
InputView.inputWinningNumber()  
InputView.inputBonusNumber()  
↓  
LottoResult.evaluate(lottos, winning, bonus)  
↓  
LottoResult.calculateProfitRate(...)  
↓  
OutputView.outputResult(...)  
---
## 📁 디렉토리 구조
src  
└─ main  
└─ java  
└─ lotto  
├─ Application.java  
├─ Lotto.java  
│  
├─ domain  
│ └─ LottoRank.java  
│  
├─ service  
│ ├─ LottoGenerator.java  
│ └─ LottoResult.java  
│  
└─ view  
├─ InputView.java  
└─ OutputView.java  
