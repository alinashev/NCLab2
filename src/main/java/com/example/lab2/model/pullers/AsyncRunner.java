package com.example.lab2.model.pullers;

import com.example.lab2.interfaces.InjAsyncRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Configuration
public class AsyncRunner implements InjAsyncRunner {
    private String resultGov;
    private String resultPB;
    private String resultMono;

    @Autowired
    private DataPullerPB dataPullerPB;
    @Autowired
    private DataPullerMono dataPullerMono;
    @Autowired
    private DataPullerGov dataPullerGov;

    private final static Logger logger = Logger.getLogger(AsyncRunner.class);

    @Override
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

    @Override
    public String getResultGov() {
        return resultGov;
    }

    @Override
    public String getResultPB() {
        return resultPB;
    }

    @Override
    public String getResultMono() {
        return resultMono;
    }
}
