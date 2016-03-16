package info.igreque.the.monadinjava;

// Stateモナドの実装
class State<S, T1> implements Monad<T1> {
    private final StateMutator<T1, S> mutator;

    State(StateMutator<T1, S> mutator){
        this.mutator = mutator;
    }

    <T2> State<S, T2> then(Function<T1, State<S, T2>> nextAction){
        StateMutator<S, T2> composedMutator = (s) -> {
            MutationResult<S, T1> result = this.mutator.mutate(s);
            State<S, T2> other = nextAction.apply(result.value);
            return other.mutator.mutate(result.newState);
        }
        return new State(composedMutator);
    }

    // ないと困る、ユーティリティメソッド。引数で与えた状態に書き換える
    public static <S> State<S, Object> put(S newState){
        return new State(
                (ignoredState) -> new MutationResult(newState, null)
        );
    }

    // ないと困る、ユーティリティメソッド。現在の状態を取得する
    public static <S> State<S, S> get{
        return new State(
                (currentState) -> new MutationResult(currentState, currentState)
        );
    }
}

