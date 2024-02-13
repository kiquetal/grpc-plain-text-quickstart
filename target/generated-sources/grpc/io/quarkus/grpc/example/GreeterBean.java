package io.quarkus.grpc.example;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: helloworld.proto")
public class GreeterBean extends MutinyGreeterGrpc.GreeterImplBase implements BindableService, MutinyBean {

    private final Greeter delegate;

    GreeterBean(@GrpcService Greeter delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<io.quarkus.grpc.example.HelloReply> sayHello(io.quarkus.grpc.example.HelloRequest request) {
        try {
            return delegate.sayHello(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Multi<io.quarkus.grpc.example.HelloReply> sayHelloALot(io.quarkus.grpc.example.HelloRequest request) {
        try {
            return delegate.sayHelloALot(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
