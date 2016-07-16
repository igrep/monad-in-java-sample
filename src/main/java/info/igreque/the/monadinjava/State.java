package info.igreque.the.monadinjava;

import java.util.function.Function;

// Stateモナドの実装
class State<S, T1> implements Monad<T1> {
    private final StateMutator<T1, S> mutator;

    State(StateMutator<T1, S> mutator){
        this.mutator = mutator;
    }

    public <T2> Monad<T2> then(Function<T1, Monad<T2>> nextAction){
        StateMutator<T2, S> composedMutator = (S s) -> {
            MutationResult<T1, S> result = this.mutator.mutate(s);
            State<S, T2> other = (State) nextAction.apply(result.value);
            return other.mutator.mutate(result.newState);
        };
        return new State(composedMutator);
    }

    // ないと困る、ユーティリティメソッド。引数で与えた状態に書き換える
    public static <S> State<S, Object> put(S newState){
        return new State(
                (ignoredState) -> new MutationResult(newState, null)
        );
    }

    // ないと困る、ユーティリティメソッド。現在の状態を取得する
    public static <S> State<S, S> get(){
        return new State(
                (currentState) -> new MutationResult(currentState, currentState)
        );
    }
}

