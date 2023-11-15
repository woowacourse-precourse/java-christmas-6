//////////////////////////////
텍스트 -- 안녕하세요 이하
InputText
텍스트 -- 주문하실 이하
InputText
결과 텍스트
//////////////////////////////
텍스트 - TextOutPut(enum TextType)
날짜 Input & 날짜 Parsing => 1~31 범위 밖, int가 아닌경우 => Parse Int Fail
메뉴 Input & 메뉴 Parsing, 기준으로 나누기 => , 앞 또는 뒤가 빈 스트링일 경우 string.Empty
기준으로 나누기 => - 앞 또는 뒤가 빈 스트링일 경우 string.Empty, - 없는 경우
뒤에 숫자를 Parse int => Int가 아닐경우
앞에는 고대로 저장 => 다른 애들이 겹치는게 있을경우 => Map // 메뉴가 없는 메뉴일때
결과 텍스트
=> PrintReservationResult()

메뉴 => PrintMenu()
할인전 총액 => CalculatePrice(), PrintPrice()
증정 메뉴  => PrintGift(), IsGift()
혜택 내역 => CalculateDiscount(), PrintDiscount()
총 혜택 금액 => CalculateTotalDiscount(), PrintTotalDiscount()
할인 후 예상 결재 금액  => CalculateTotalPrice(), PrintTotalPrice()
=> DDayDiscount()
=> WeekdayDiscount()
=> WeekendDiscount()
=> SpecialDiscount()
=> GiftAvailable()

12월 이벤트 배지
=> PrintBedge(), BadgeAvailable()
