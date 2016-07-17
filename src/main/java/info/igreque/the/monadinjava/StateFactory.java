package info.igreque.the.monadinjava;

class StateFactory<S> implements MonadFactory {
  // 本当は↓のような型にしたい
  // public <T> State<S, T> doNothingReturning(T value)
  public <T> Monad<T> doNothingReturning(T value){
    return new State<S, T>(
        (nonMutatedState) -> new MutationResult<>(nonMutatedState, value)
        );
  }
}
