package info.igreque.the.monadinjava;

interface MonadFactory {
    // Haskellで言うところのreturnに相当します。
    // 本当は1個のinterfaceにまとめたいけど
    // Javaのinterfaceでは無理っぽいorz.
    <T> Monad<T> doNothingReturning(T);
}
