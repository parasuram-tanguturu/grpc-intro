package com.parasuram.server;


import com.parasuram.models.Balance;
import com.parasuram.models.BalanceCheckRequest;
import io.grpc.stub.StreamObserver;

public class BankService extends com.parasuram.models.BankServiceGrpc.BankServiceImplBase {
    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
        final int accountNumber = request.getAccountNumber();
        final Balance balance = Balance.newBuilder()
                .setAmount(AccountDataBase.getBalance(accountNumber))
                .build();

        responseObserver.onNext(balance);
        responseObserver.onCompleted();

    }
}
