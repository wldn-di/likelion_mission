package account;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 3. **간이 ATM 시뮬레이터**
 *
 * - **입력**: 초기 잔액 설정 → 입금 또는 출금 선택 → 금액 입력
 * - **기능**: 잔액을 실시간으로 업데이트하고, 거래 내용을 출력
 * - **학습 포인트**: 조건문, 반복문, 변수 업데이트
 */

public class Account {
    private static long sequence = 0L; // id 자동 증가용 static 필드

    private long id = 1;     // 계좌 식별자값
    private String name; // 계좌주
    private int balance = 0; // 잔액

    public Account(String name, int balance) {
        this.id = ++sequence; // 생성할 때마다 자동으로 1씩 증가
        this.name = name;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }
}


