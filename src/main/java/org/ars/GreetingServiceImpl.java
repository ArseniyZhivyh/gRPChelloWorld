package org.ars;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) throws InterruptedException {

        System.out.println(request);

        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse.
                    newBuilder().setGreeting("Hello from server, " + request.getName()).build();

            responseObserver.onNext(response);

        }

        responseObserver.onCompleted();
    }
}
