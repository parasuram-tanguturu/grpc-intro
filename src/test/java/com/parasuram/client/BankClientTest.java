package com.parasuram.client;

import com.parasuram.models.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {
     BankServiceGrpc.BankServiceBlockingStub bankServiceBlockingStub;
    @BeforeAll
    public void setup(){
        ManagedChannel managedChannel=ManagedChannelBuilder.forAddress("localhost",6565)
                .usePlaintext()
                .build();
        this.bankServiceBlockingStub= BankServiceGrpc.newBlockingStub(managedChannel);
    }

    @Test
    public void balanceTest(){

        com.parasuram.models.BalanceCheckRequest balanceCheckRequest=com.parasuram.models.BalanceCheckRequest.newBuilder()
                .setAccountNumber(5)
                .build();
        com.parasuram.models.Balance balance=this.bankServiceBlockingStub.getBalance(balanceCheckRequest);
        System.out.println("balance is: "+balance);
    }
}
