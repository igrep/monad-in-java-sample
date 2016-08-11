package info.igreque.the.monadinjava;

import java.util.function.Function;

// 本当は一つのインターフェースにまとめたいのですが、
// Javaのインターフェースの都合上やむを得ず分けています。
// 本来は2つ合わせて初めてMonadと呼べる点をお忘れなく。
interface Monad<T1> {

  <T2> Monad<T2> then(Function<T1, Monad<T2>> action);

  interface Return {
    <T> Monad<T> doNothingReturning(T value);
  }
}
