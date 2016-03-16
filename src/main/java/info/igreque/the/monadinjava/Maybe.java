package info.igreque.the.monadinjava;

import java.util.function.Function;

class Maybe<T1> implements Monad<T1> {
    private final T1 x;

    Maybe(T1 x){
        this.x = x;
    }

    <T2> Maybe<T2> then(Function<T1, Maybe<T2>> nextAction){
        if (x != null){
            return nextAction.apply(x);
        } else {
            return new Maybe(null);
        }
    }
}
