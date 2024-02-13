package io.quarkus.grpc.example;

import static io.quarkus.grpc.example.GreeterGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: helloworld.proto")
public final class MutinyGreeterGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyGreeterGrpc() {
    }

    public static MutinyGreeterStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyGreeterStub(channel);
    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static class MutinyGreeterStub extends io.grpc.stub.AbstractStub<MutinyGreeterStub> implements io.quarkus.grpc.MutinyStub {

        private GreeterGrpc.GreeterStub delegateStub;

        private MutinyGreeterStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = GreeterGrpc.newStub(channel);
        }

        private MutinyGreeterStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = GreeterGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyGreeterStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyGreeterStub(channel, callOptions);
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.smallrye.mutiny.Uni<io.quarkus.grpc.example.HelloReply> sayHello(io.quarkus.grpc.example.HelloRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::sayHello);
        }

        public io.smallrye.mutiny.Multi<io.quarkus.grpc.example.HelloReply> sayHelloALot(io.quarkus.grpc.example.HelloRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToMany(request, delegateStub::sayHelloALot);
        }
    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static abstract class GreeterImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public GreeterImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.smallrye.mutiny.Uni<io.quarkus.grpc.example.HelloReply> sayHello(io.quarkus.grpc.example.HelloRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Multi<io.quarkus.grpc.example.HelloReply> sayHelloALot(io.quarkus.grpc.example.HelloRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(io.quarkus.grpc.example.GreeterGrpc.getSayHelloMethod(), asyncUnaryCall(new MethodHandlers<io.quarkus.grpc.example.HelloRequest, io.quarkus.grpc.example.HelloReply>(this, METHODID_SAY_HELLO, compression))).addMethod(io.quarkus.grpc.example.GreeterGrpc.getSayHelloALotMethod(), asyncServerStreamingCall(new MethodHandlers<io.quarkus.grpc.example.HelloRequest, io.quarkus.grpc.example.HelloReply>(this, METHODID_SAY_HELLO_ALOT, compression))).build();
        }
    }

    private static final int METHODID_SAY_HELLO = 0;

    private static final int METHODID_SAY_HELLO_ALOT = 1;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final GreeterImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(GreeterImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_SAY_HELLO:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((io.quarkus.grpc.example.HelloRequest) request, (io.grpc.stub.StreamObserver<io.quarkus.grpc.example.HelloReply>) responseObserver, compression, serviceImpl::sayHello);
                    break;
                case METHODID_SAY_HELLO_ALOT:
                    io.quarkus.grpc.stubs.ServerCalls.oneToMany((io.quarkus.grpc.example.HelloRequest) request, (io.grpc.stub.StreamObserver<io.quarkus.grpc.example.HelloReply>) responseObserver, compression, serviceImpl::sayHelloALot);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }
}
