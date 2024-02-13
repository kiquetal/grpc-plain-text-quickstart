package io.quarkus.grpc.examples.hello;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


import io.grpc.ServerServiceDefinition;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.example.HelloReplyALot;
import io.smallrye.mutiny.Multi;
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
    public void sayHelloALot(HelloRequest req, StreamObserver<HelloReplyALot> responseObserver)
    {
        log.info("sayHelloALot: " + req.getName());
        AtomicInteger counter = new AtomicInteger();
        ServerCallStreamObserver serverCallStreamObserver = (ServerCallStreamObserver) responseObserver;
        serverCallStreamObserver.setOnCancelHandler(() -> log.info("Client cancelled"));

        Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> "Hello " + req.getName() + " " + counter.incrementAndGet())
                .subscribe().with(reply -> responseObserver.onNext(HelloReplyALot.newBuilder().setMessage(reply).build()),
                        responseObserver::onError, responseObserver::onCompleted    );

    }
    @Override
    public ServerServiceDefinition bindService()
    {
        return super.bindService();
    }
}
