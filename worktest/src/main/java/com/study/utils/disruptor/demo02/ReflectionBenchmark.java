package com.study.utils.disruptor.demo02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class ReflectionBenchmark {

    private static final Method static_method;
    private static final MethodHandle static_unreflect;
    private static final MethodHandle static_mh;

    private static Method method;
    private static MethodHandle unreflect;
    private static MethodHandle mh;

    private static final Method static_private_method;
    private static final MethodHandle static_private_unreflect;
//    private static final MethodHandle static_private_mh;

    private static Method private_method;
    private static MethodHandle private_unreflect;
//    private static MethodHandle private_mh;

    static {
        try {
            // public method
            Method method = Calculator.class.getMethod("add", int.class, int.class);
            unreflect = MethodHandles.lookup().unreflect(method);
            MethodType mt = MethodType.methodType(int.class, int.class, int.class);
            mh = MethodHandles.lookup().findVirtual(Calculator.class, "add", mt);
            ReflectionBenchmark.method = Calculator.class.getMethod("add", int.class, int.class);
            // public static method
            Method staticMethod = Calculator.class.getMethod("addStatic", int.class, int.class);
            static_unreflect = MethodHandles.lookup().unreflect(staticMethod);
            MethodType mtStatic = MethodType.methodType(int.class, int.class, int.class);
            static_mh = MethodHandles.lookup().findStatic(Calculator.class, "addStatic", mtStatic);
            static_method = Calculator.class.getMethod("addStatic", int.class, int.class);

            // private method
            Method addPrivate = Calculator.class.getDeclaredMethod("addPrivate", int.class, int.class);
            addPrivate.setAccessible(true);
            private_method = addPrivate;
            Method addPrivate1 = Calculator.class.getDeclaredMethod("addPrivate", int.class, int.class);
            addPrivate1.setAccessible(true);
            private_unreflect = MethodHandles.lookup().unreflect(addPrivate1);
//            MethodType pmt = MethodType.methodType(int.class, int.class, int.class);
//            private_mh = MethodHandles.lookup().findSpecial(Calculator.class, "addPrivate", pmt, Calculator.class);
            // private static method
            Method addStaticPrivate = Calculator.class.getDeclaredMethod("addStaticPrivate", int.class, int.class);
            addStaticPrivate.setAccessible(true);
            static_private_method = addStaticPrivate;
            Method addStaticPrivate1 = Calculator.class.getDeclaredMethod("addStaticPrivate", int.class, int.class);
            addStaticPrivate1.setAccessible(true);
            static_private_unreflect = MethodHandles.lookup().unreflect(addStaticPrivate1);
//            MethodType spmt = MethodType.methodType(int.class, int.class, int.class);
//            static_private_mh = MethodHandles.lookup().findSpecial(Calculator.class, "addStaticPrivate", spmt, Calculator.class);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public int unreflectInvokeTest() throws Throwable {
        Calculator cal = new Calculator();
        Random random = new Random();
        return (int) unreflect.invoke(cal, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int unreflectInvokeExactTest() throws Throwable {
        Calculator cal = new Calculator();
        Random random = new Random();
        return (int) unreflect.invokeExact(cal, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int mhInvokeTest() throws Throwable {
        Calculator cal = new Calculator();
        Random random = new Random();
        return (int) mh.invoke(cal, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int mhInvokeExactTest() throws Throwable {
        Calculator cal = new Calculator();
        Random random = new Random();
        return (int) mh.invokeExact(cal, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int unreflectStaticInvokeTest() throws Throwable {
        Random random = new Random();
        return (int) static_unreflect.invoke(random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int unreflectStaticInvokeExactTest() throws Throwable {
        Random random = new Random();
        return (int) static_unreflect.invokeExact(random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int mhStaticInvokeTest() throws Throwable {
        Random random = new Random();
        return (int) static_mh.invoke(random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int mhStaticInvokeExactTest() throws Throwable {
        Random random = new Random();
        return (int) static_mh.invokeExact(random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int methodTest() throws Throwable {
        Calculator cal = new Calculator();
        Random random = new Random();
        return (int) method.invoke(cal, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int methodStaticTest() throws Throwable {
        Random random = new Random();
        return (int) static_method.invoke(null, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int directCall() {
        Calculator cal = new Calculator();
        Random random = new Random();
        return cal.add(random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int directCallStatic() {
        Random random = new Random();
        return Calculator.addStatic(random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int privateMethod() throws Throwable {
        Calculator cal = new Calculator();
        Random random = new Random();
        return (int) private_method.invoke(cal, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int privateUnreflect() throws Throwable {
        Calculator cal = new Calculator();
        Random random = new Random();
        return (int) private_unreflect.invoke(cal, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int privateUnreflectExact() throws Throwable {
        Calculator cal = new Calculator();
        Random random = new Random();
        return (int) private_unreflect.invokeExact(cal, random.nextInt(), random.nextInt());
    }

//    @Benchmark
//    public int privateMh() throws Throwable {
//        Calculator cal = new Calculator();
//        Random random = new Random();
//        return (int) private_mh.invoke(cal, random.nextInt(), random.nextInt());
//    }

//    @Benchmark
//    public int privateMhExact() throws Throwable {
//        Calculator cal = new Calculator();
//        Random random = new Random();
//        return (int) private_mh.invokeExact(cal, random.nextInt(), random.nextInt());
//    }

    @Benchmark
    public int staticPrivateMethod() throws Throwable {
        Random random = new Random();
        return (int) static_private_method.invoke(null, random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int staticPrivateUnreflect() throws Throwable {
        Random random = new Random();
        return (int) static_private_unreflect.invoke(random.nextInt(), random.nextInt());
    }

    @Benchmark
    public int staticPrivateUnreflectExact() throws Throwable {
        Random random = new Random();
        return (int) static_private_unreflect.invokeExact(random.nextInt(), random.nextInt());
    }

//    @Benchmark
//    public int staticPrivateMh() throws Throwable {
//        Random random = new Random();
//        return (int) static_private_mh.invoke(random.nextInt(), random.nextInt());
//    }

//    @Benchmark
//    public int staticPrivateMhExact() throws Throwable {
//        Random random = new Random();
//        return (int) static_private_mh.invokeExact(random.nextInt(), random.nextInt());
//    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ReflectionBenchmark.class.getSimpleName())
                .forks(3)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();
        new Runner(options).run();
    }

}