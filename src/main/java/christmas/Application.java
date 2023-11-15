package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Application {

	// 변수로
	public static int inputDay; // 입력날짜
	public static String inputMenu; // 입력메뉴
	public static int inputN; // 입력갯수
	public static int totalN; // 총주문갯수
	public static int totalAmount; // 총주문금액
	public static int totalDC; // 총혜택금액
	public static int drinkN; // 샴페인 증정갯수
	public static int christmasDC; // 크리스마스 디데이 할인금액
	public static int dayDC; // 평일할인금액
	public static int specialDC; // 특별할인금액
	public static int giftDC; // 증정 이벤트
	public static String giftEvent; // 증정이벤트
	public static String eventBG; // 이벤트뱃지(star, tree, santa)

	// 날짜입력받기
	public class InputView {
		public int readDate() {
			System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
			System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
			String input = Console.readLine();
			inputDay = Integer.parseInt(input);
			if (inputDay > 1 && inputDay <= 31) {
			}
			return inputDay;
		}

		// 메뉴와 가격 메소드
		public java.util.Map<String, Integer> Menu() {
			System.out.println("주문하실 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2, 레드와인-1, 초코케이크-1");
			String[] menuList = { "양송이스프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림",
					"제로콜라", "레드와인", "샴페인" };
			int[] menufee = { 6600, 5500, 8000, 55000, 54000, 35000, 25000, 15000, 5000, 3000, 60000, 25000 };

			// 주문정보저장
			java.util.Map<String, Integer> orderMap = new java.util.HashMap<>();

			// 메뉴와 개수입력받기
			while (true) {
				String input = Console.readLine();
				if (input.isEmpty()) {
					break;
				}
				
				// 콤마로 구분된 값들을 배열로 분리
			    String[] orderInfos = input.split(",");
			    // 각 주문 정보에 대해 처리
			    for (String orderInfo : orderInfos) {
			        // 주문 정보를 다시 "-"로 구분하여 메뉴와 수량 추출
			        String[] parts = orderInfo.split("-");
			        if (parts.length == 2) {
			            String menu = parts[0].trim();
			            int quantity = Integer.parseInt(parts[1].trim());

			            // 메뉴가 유효한지 확인하고 주문 정보 저장
			            if (java.util.Arrays.asList(menuList).contains(menu)) {
			                orderMap.put(menu, quantity);
			            }else{
						// 메뉴에러문구입력
			            }
				}
			    }
			}
			// 총 주문 금액 계산
		
			totalAmount = 0;
			for (java.util.Map.Entry<String, Integer> entry : orderMap.entrySet()) {
				String menu = entry.getKey();
				int totalN = entry.getValue();
				int price = menufee[java.util.Arrays.asList(menuList).indexOf(menu)];
				totalAmount += totalN * price;
			}

			//System.out.println("총 주문 금액은 " + totalAmount + "원 입니다.");
			// orderMap 반환
			return orderMap;
		}
	}
	// 에러코드입력하기

	public static void main(String[] args) {
		// InputView의 인스턴스 생성
		InputView inputView = new Application().new InputView();

		// 날짜메소드 호출
		inputView.readDate();
		// 메뉴 메소드 호출
		java.util.Map<String, Integer> orderMap = inputView.Menu();
		System.out.println("12월" + inputDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

		System.out.println("<주문 메뉴>");
		// 메뉴이름 //갯수
		for (java.util.Map.Entry<String, Integer> entry : orderMap.entrySet()) {
			String menu = entry.getKey();
			int totalN = entry.getValue();
			System.out.println(menu+" "+ totalN+ "개");
		}

		System.out.println("<할인 전 총주문 금액>");
		System.out.println(totalAmount+"원");
		System.out.println("<증정 메뉴>");//120000이상시 샴페인
			if(totalAmount>= 120000) {
				System.out.println("샴페인 1개");
			}
		System.out.println("<혜택내역>"); 
			if(totalAmount<10000) {
				System.out.println("혜택없음");
			}else if(totalAmount<=10000) {
				System.out.println("크리스마스 디데이 할인:"+"-"+christmasDC+"원");
				System.out.println("평일 할인:"+"-"+dayDC+"원");
				System.out.println("특별 할인:"+"-"+specialDC+"원");
				System.out.println("증정 이벤트: "+"-"+giftDC+"원");
			}
		System.out.println("<총혜택 금액>");
			System.out.println("-"+christmasDC+dayDC+specialDC+giftDC+"원");
		System.out.println("<할인 후 예상 결제 금액>");
			System.out.println(totalAmount-christmasDC-dayDC-specialDC-giftDC+"원");
		System.out.println("<12월 이벤트 배지>");
			if(totalAmount>=5000) {
				System.out.println("별");
			}else if(totalAmount>=10000){
				System.out.println("트리");
			}else if(totalAmount>=20000) {
				System.out.println("산타");
			}else if(totalAmount<5000) {
				System.out.println("없음");
			}
	}
}
