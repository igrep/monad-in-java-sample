package info.igreque.the.monadinjava;

import java.util.function.Function;

// Stateモナドの実装
class State<S, T1> implements Monad<T1> {
  public final Function<S, MutationResult<T1, S>> mutator;

  State(Function<S, MutationResult<T1, S>> mutator){
    this.mutator = mutator;
  }

  // 本当は↓のような型にしたい
  // public <T2> State<S, T2> then(Function<T1, State<S, T2>> nextAction)
  public <T2> Monad<T2> then(Function<T1, Monad<T2>> nextAction){
    Function<S, MutationResult<T2, S>> composedMutator = (oldState) -> {
      MutationResult<T1, S> result = this.mutator.apply(oldState);
      // 本当はダウンキャストなんてしたくない
      State<S, T2> other = (State) nextAction.apply(result.value);
      return other.mutator.apply(result.newState);
    };
    return new State<>(composedMutator);
  }

  public static class Return<S> implements Monad.Return {
    public <T> State<S, T> doNothingReturning(T value){
      return new State<>(
        (nonMutatedState) -> new MutationResult<>(nonMutatedState, value)
      );
    }
  }

  // 引数で与えた状態に書き換える
  public static <S> State<S, Void> put(S newState){
    Function<S, MutationResult<Void, S>> putter =
      (ignoredState) -> new MutationResult<>(newState, null);
    return new State<>(putter);
  }

  // 現在の状態を取得する
  public static <S> State<S, S> get() {
    Function<S, MutationResult<S, S>> getter =
      (currentState) -> new MutationResult<>(currentState, currentState);
    return new State<>(getter);
  }

  // 引数で与えた関数で、状態を書き換える
  public static <S> State<S, Void> modify(Function<S, S> simpleMutator){
    Function<S, MutationResult<Void, S>> modifier =
      (currentState) -> {
        S newState = simpleMutator.apply(currentState);
        return new MutationResult<>(newState, null);
      };
    return new State<>(modifier);
  }
}
