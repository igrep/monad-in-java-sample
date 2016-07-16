package info.igreque.the.monadinjava;

class MaybeFactory implements MonadFactory {
  public <T> Monad<T> doNothingReturning(T x){
    return new Maybe<T>(x);
  }
}
