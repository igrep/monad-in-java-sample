package info.igreque.the.monadinjava;

class MaybeFactory implements MonadFactory {
    <T> Maybe<T> doNothingReturning(T x){
        return new Maybe<T>(x);
    }
}
