package com.example.lab2.model.pullers;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
public class AsyncRunner {
    private String resultGov;
    private String resultPB;
    private String resultMono;

    private DataPuller dataPullerPB = new DataPullerPB();
    private DataPuller dataPullerMono = new DataPullerMono();
    private DataPuller dataPullerGov = new DataPullerGov();

    private final static Logger logger = Logger.getLogger(AsyncRunner.class);

    public void executeAll(String date){
        CompletableFuture<String> gov = CompletableFuture.supplyAsync(()-> dataPullerGov.getData(date));
        CompletableFuture<String> pb = CompletableFuture.supplyAsync(()-> dataPullerPB.getData(date));
        CompletableFuture<String> mono = CompletableFuture.supplyAsync(()-> dataPullerMono.getData(date));
        CompletableFuture<Void> allCompleted = CompletableFuture.allOf(gov,pb,mono);
        allCompleted.thenRun(()->{
            try {
                resultGov = gov.get();
                resultPB = pb.get();
                resultMono = mono.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error(e);
            }
        });
    }

    public String getResultGov() {
        return resultGov;
    }

    public String getResultPB() {
        return resultPB;
    }

    public String getResultMono() {
        return resultMono;
    }
}
