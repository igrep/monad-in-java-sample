package info.igreque.the.monadinjava;

import java.util.function.Function;

interface Monad<T1> {
    // Haskellで言うところの >>= (bind)に該当します。
    <T2> Monad<T2> then(Function<T1, Monad<T2>> action);
}

