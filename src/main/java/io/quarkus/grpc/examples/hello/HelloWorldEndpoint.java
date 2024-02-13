package io.quarkus.grpc.examples.hello;

import io.quarkus.grpc.example.Greeter;
import io.quarkus.grpc.example.GreeterGrpc;
import io.quarkus.grpc.example.HelloRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

@Path("/hello")
public class HelloWorldEndpoint {

        @GrpcClient
        Greeter greeter;

        @GET
        public Uni<String> hello() {
            return greeter.sayHello(HelloRequest.newBuilder().setName("World").build())
                    .map(reply -> reply.getMessage());
        }


}
