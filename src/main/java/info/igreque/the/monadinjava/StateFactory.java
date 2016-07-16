package info.igreque.the.monadinjava;

class StateFactory<S> implements MonadFactory {
    public <T> Monad<T> doNothingReturning(T value){
        return new State<S, T>(
                (nonMutatedState) -> new MutationResult(nonMutatedState, value)
        );
    }
}


