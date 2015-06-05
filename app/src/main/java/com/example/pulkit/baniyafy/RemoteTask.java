package com.example.pulkit.baniyafy;

/**
 * Created by pulkit on 6/4/15.
 */
public class RemoteTask<ReturnTypeOfFirstFunction, ReturnTypeOfSecondFunction> {
    public static abstract class GetResult<ReturnTypeOfFirstFunction, ReturnTypeOfSecondFunction> {
        public abstract ReturnTypeOfFirstFunction getService1();
        public abstract ReturnTypeOfSecondFunction getService2();
    }

    public ReturnTypeOfFirstFunction getFirst(GetResult getResult) {
        return (ReturnTypeOfFirstFunction) getResult.getService1();
    }

    public ReturnTypeOfSecondFunction getSecond(GetResult getResult) {
        return (ReturnTypeOfSecondFunction) getResult.getService2();
    }
}
