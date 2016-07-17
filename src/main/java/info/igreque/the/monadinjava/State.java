package info.igreque.the.monadinjava;

import java.util.function.Function;

// Stateモナドの実装
class State<S, T1> implements Monad<T1> {
  public final StateMutator<T1, S> mutator;

  State(StateMutator<T1, S> mutator){
    this.mutator = mutator;
  }

  // 本当は↓のような型にしたい
  // public <T2> State<S, T2> then(Function<T1, State<S, T2>> nextAction)
  public <T2> Monad<T2> then(Function<T1, Monad<T2>> nextAction){
    StateMutator<T2, S> composedMutator = (oldState) -> {
      MutationResult<T1, S> result = this.mutator.mutate(oldState);
      // 本当はダウンキャストなんてしたくない
      State<S, T2> other = (State) nextAction.apply(result.value);
      return other.mutator.mutate(result.newState);
    };
    return new State<>(composedMutator);
  }

  // 引数で与えた状態に書き換える
  public static <S> State<S, Void> put(S newState){
    StateMutator<Void, S> putter =
      (ignoredState) -> new MutationResult<>(newState, null);
    return new State<>(putter);
  }

  // 現在の状態を取得する
  public static <S> State<S, S> get() {
    StateMutator<S, S> getter =
      (currentState) -> new MutationResult<>(currentState, currentState);
    return new State<>(getter);
  }

  // 引数で与えた関数で、状態を書き換える
  public static <S> State<S, Void> modify(Function<S, S> simpleMutator){
    StateMutator<Void, S> modifier =
      (currentState) -> {
        S newState = simpleMutator.apply(currentState);
        return new MutationResult<>(newState, null);
      };
    return new State<>(modifier);
  }
}
