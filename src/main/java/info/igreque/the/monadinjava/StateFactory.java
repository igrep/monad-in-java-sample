package info.igreque.the.monadinjava;

class StateFactory<S> implements MonadFactory {
    <T> State<S, T> doNothingReturning(T value){
        return new State<S, T>(
                (nonMutatedState) -> new MutationResult(nonMutatedState, value)
        );
    }
}


