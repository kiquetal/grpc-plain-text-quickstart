package io.quarkus.grpc.examples.hello;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.quarkus.grpc.example.HelloReply;
import io.quarkus.grpc.example.HelloRequest;

import io.quarkus.grpc.example.GreeterGrpc.GreeterImplBase;

@GrpcService
public class HelloWorldService extends GreeterImplBase
{

    private Logger log = Logger.getLogger(HelloWorldService.class.getName());


    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver)
    {
        log.info("sayHello: " + request.getName());
        responseObserver.onNext(HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void sayHelloALot(HelloRequest request, StreamObserver<HelloReply> responseObserver)
    {

        log.info("sayHelloALot: " + request.getName());
        AtomicInteger counter = new AtomicInteger(0);
        Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> "Hello " + request.getName() + " " + counter.incrementAndGet())
                .onItem().transform(n -> HelloReply.newBuilder().setMessage(n).build())
                .subscribe().with(responseObserver::onNext, responseObserver::onError, responseObserver::onCompleted);
    }

    @Override
    public ServerServiceDefinition bindService()
    {
        return super.bindService();
    }
}
