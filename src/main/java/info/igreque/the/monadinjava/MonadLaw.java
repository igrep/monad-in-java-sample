package info.igreque.the.monadinjava;

import java.util.function.Function;

public class MonadLaw {
    public static void main(String[] args){
        // 任意の型X, Y, Zの値をx, y, zとします。
        X x;
        Y y;
        Z z;

        // MonadFactoryをfとします
        MonadFactory<X> f = new SomeMonadFactory<>();

        // それから、Xを受け取って別のMonadの値を返す関数をax, ay, azとしましょう。
        Function<X, Monad<Y>> ax = (x) -> new SomeMonad<Y>(y);
        Function<Y, Monad<Z>> ay = (y) -> new SomeMonad<Z>(z);
        Function<Z, Monad<A>> az = (z) -> new SomeMonad<A>(new A());


        // 下記の3組の式が**常に同じ意味となる**doNothingReturningとthenを
        // 実装している時、Monad則が成立します。
        /* (A ) */ f.doNothingReturning(x).then(ax);
                   ax.apply(x);
        /* (A') */ ax.apply(x).then(f::doNothingReturning);
                   ax.apply(x);
        /* (B ) */ ax.apply(x).then((y) -> ay.apply(y).then(az));
                   ax.apply(x).then(       ay        ).then(az);

        System.out.println("OK");
    }

    private static class X {
        X(){}
    }
    private static class Y {
        Y(){}
    }
    private static class Z {
        Z(){}
    }
    private static class A {
        A(){}
    }

    private static class SomeMonad<T> implements Monad<T>{
        private final T value;

        SomeMonad(T value){
            this.value = value;
        }

        <T2> Monad<T2> then(Function<T, SomeMonad<T2>> action){
            return action.apply(this.value);
        }
    }
    private static class SomeMonadFactory implements MonadFactory {
        SomeMonadFactory(){}
        <T> Monad<T> doNothingReturning(T value){
            return new SomeMonad<T>(value);
        }
    }
}
