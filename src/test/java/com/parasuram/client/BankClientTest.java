package com.parasuram.client;

import com.parasuram.models.BankServiceGrpc;
import com.parasuram.models.Money;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Iterator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {
     BankServiceGrpc.BankServiceBlockingStub bankServiceBlockingStub;
    @BeforeAll
    public void setup(){
        ManagedChannel managedChannel=ManagedChannelBuilder.forAddress("localhost",6567)
                .usePlaintext()
                .build();
        this.bankServiceBlockingStub= BankServiceGrpc.newBlockingStub(managedChannel);
    }

    @Test
    public void balanceTest(){

        com.parasuram.models.BalanceCheckRequest balanceCheckRequest=com.parasuram.models.BalanceCheckRequest.newBuilder()
                .setAccountNumber(9)
                .build();
        com.parasuram.models.Balance balance=this.bankServiceBlockingStub.getBalance(balanceCheckRequest);
        System.out.println("balance is: "+balance);
    }
    @Test
    public  void withDrawTest(){
        com.parasuram.models.WithDrawRequest withDrawRequest= com.parasuram.models.WithDrawRequest
                .newBuilder()
                .setAmount(40)
                .setAccountNumber(7)
                .build();
        Iterator<Money> money=this.bankServiceBlockingStub.withDraw(withDrawRequest);

       money.forEachRemaining(System.out::println);

    }
}
