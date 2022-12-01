package com.parasuram.server;


import com.parasuram.models.Balance;
import com.parasuram.models.BalanceCheckRequest;
import com.parasuram.models.Money;
import com.parasuram.models.WithDrawRequest;
import io.grpc.stub.StreamObserver;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class BankService extends com.parasuram.models.BankServiceGrpc.BankServiceImplBase {

    protected final Logger logger = LogManager.getLogManager().getLogger("bankService");
    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
        final int accountNumber = request.getAccountNumber();
        final Balance balance = Balance.newBuilder()
                .setAmount(AccountDataBase.getBalance(accountNumber))
                .build();

        responseObserver.onNext(balance);
        responseObserver.onCompleted();

    }

    @Override
    public void withDraw(WithDrawRequest request, StreamObserver<Money> responseObserver) {
        int account =request.getAccountNumber();
        int amount=request.getAmount(); //10,20...100
        int balance=AccountDataBase.getBalance(account);
        System.out.println("account: "+account+" amount: "+amount +" balance: "+balance);
        for (int i=0;i<(amount/10);i++){
            Money money=com.parasuram.models.Money.newBuilder()
                                    .setValue(10).build();
            System.out.println("money is:"+money);
            responseObserver.onNext(money);
            AccountDataBase.deductBalance(account,10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("account: "+account+" amount: "+account +" balance: "+AccountDataBase.getBalance(account));
        responseObserver.onCompleted();

    }
}
