package account;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {

    private Map<Long, Account> members = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        int menu = 0;

        while (menu != 5) {
            System.out.println("---------------------------------------------");
            System.out.println("1. 계좌개설 | 2. 입금 | 3. 출금 | 4. 잔액 | 5. 종료");
            System.out.println("---------------------------------------------");
            System.out.print("메뉴를 선택하세요. ");

            menu = scanner.nextInt();
            System.out.println();

            switch (menu) {
                case 1:
                    join();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    findAll();
                    break;
                case 5:
                    System.out.println("시스템을 종료합니다.");
                    break;
                default:
                    System.out.println("1~5 사이의 숫자를 입력하세요.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
        System.exit(0);
    }

    // 각 메뉴별 로직 실행(case 1)
    public void join() {
        System.out.println("---------------------------------------------");
        System.out.println("1. 계좌개설");
        System.out.println("---------------------------------------------");
        System.out.println("이름과 초기금액을 설정해주세요.");

        scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("금액: ");
        int balance= scanner.nextInt();

        Account newAccount = new Account(name,balance);
        members.put(newAccount.getId(),newAccount);

        System.out.println("계좌 등록이 정상적으로 완료되었습니다. " + name + "님 안녕하세요!");
        run();
    }

    // 각 메뉴별 로직 실행(case 2)
    public void deposit() {
        System.out.println("---------------------------------------------");
        System.out.println("2. 입금");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.print("입금할 계좌 ID를 입력해주세요. ");
            long id = scanner.nextLong();
            Account acc = members.get(id);

            if(members.containsKey(id)) {
                System.out.println("예금주명이 " + acc.getName() + "이(가) 맞다면 1, 아니라면 2를 눌러주세요.");
                scanner.nextLine();
                String check = scanner.nextLine();
                if (!check.equals("1")) {
                    continue;
                } else {
                    System.out.print("입금할 금액을 입력해주세요. ");
                    int amount = scanner.nextInt();
                    acc.deposit(amount);

                    System.out.println("입금이 정상적으로 완료되었습니다.");
                    System.out.println("현재 잔액은 " + acc.getBalance() + "원 입니다.");
                    break;
                }
            }
            System.out.println("일치하는 계좌가 존재하지 않습니다.");
        }
    }

    // 각 메뉴별 로직 실행(case 3)
    public void withdraw() {
        System.out.println("---------------------------------------------");
        System.out.println("3. 출금");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.print("출금할 계좌 식별자와 금액을 입력해주세요. ");
            long id = scanner.nextLong();
            Account acc = members.get(id);

            if (members.containsKey(id)) {
                System.out.println("예금주명이 " + acc.getName() + "이(가) 맞다면 1, 아니라면 2를 눌러주세요.");
                scanner.nextLine();
                String check = scanner.nextLine();
                if (!check.equals("1")) {
                    continue;
                }else {
                    System.out.print("출금할 금액을 입력해주세요. ");
                    int amount = scanner.nextInt();
                    if (acc.getBalance() - amount < 0) {
                        System.out.println("잔액이 부족합니다. 현재 잔액은 " + acc.getBalance() + "원 입니다. 출금에 실패하여 초기화면으로 돌아갑니다.");
                        return;
                    } else {
                        acc.withdraw(amount);

                        System.out.println("출금이 정상적으로 완료되었습니다.");
                        System.out.println("현재 잔액은 " + acc.getBalance() + "원 입니다.");
                        break;
                    }
                }
            }
            System.out.println("일치하는 계좌가 존재하지 않습니다. ");
        }
    }

    // 각 메뉴별 로직 실행(case 4)
    public void findAll() {
        System.out.println("---------------------------------------------");
        System.out.println("4. 잔액");
        System.out.println("전체 회원의 잔액을 조회합니다.");
        System.out.println("---------------------------------------------");


        for (Map.Entry<Long, Account> entry : members.entrySet()) {
            System.out.println("식별자: " + entry.getKey());
            System.out.println("예금주: " + entry.getValue().getName());
            System.out.println("잔액: " + entry.getValue().getBalance());
            System.out.println("----------------------------------------");
        }
    }

}
