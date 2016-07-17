package info.igreque.the.monadinjava;

class MaybeFactory implements MonadFactory {
  // 本当は↓のような型にしたい
  // public <T> Maybe<T> doNothingReturning(T x)
  public <T> Monad<T> doNothingReturning(T x){
    return new Maybe<>(x);
  }
}
