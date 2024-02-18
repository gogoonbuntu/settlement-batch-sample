package com.danal.settlement.batch.writer;

public class PaymentWriter implements ItemWriter<Payment> {

    @Override
    public void write(List<? extends Payment> items) throws Exception {
        // 가맹점 ID 별로 파일에 결제 내역 작성하는 로직 구현
        // 파일 쓰기 작업을 수행
    }
}

