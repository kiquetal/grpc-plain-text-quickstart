package io.quarkus.grpc.example;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: helloworld.proto")
public interface Greeter extends MutinyService {

    /**
     * <pre>
     *  Sends a greeting
     * </pre>
     */
    io.smallrye.mutiny.Uni<io.quarkus.grpc.example.HelloReply> sayHello(io.quarkus.grpc.example.HelloRequest request);

    io.smallrye.mutiny.Multi<io.quarkus.grpc.example.HelloReply> sayHelloALot(io.quarkus.grpc.example.HelloRequest request);
}
