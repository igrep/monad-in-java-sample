package info.igreque.the.monadinjava;

import java.util.function.Function;

/**
 * 本当は一つのインターフェースにまとめたいのですが、
 * Javaのインターフェースの都合上やむを得ず分けています。
 * 本来は2つ合わせて初めてMonadと呼べる、という点をお忘れなく。
 */
interface Monad<T1> /* (1) */{

  <T2> Monad<T2> then(Function<T1, Monad<T2>> action /* (2) */);

  interface Return {
    /* (3) */
    <T> Monad<T> doNothingReturning(T value);
  }
}

/**
 * (1): 型引数を1つとります。なので、渡した型をラップする型となります。
 * (2): 引数として、
 *        「ラップした型T1を受け取り、別の型T2をラップしたMonadを返す関数」
 *      を受け取ります。
 *      戻り値は、そのT2をラップしたMonadでなければなりません。
 * (3): 任意の値一つを受け取って、そのMonadのインスタンスを作る
 *      ファクトリーメソッド的なメソッドが必要です。
 */
