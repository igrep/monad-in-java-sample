package info.igreque.the.monadinjava;

// 状態を書き換えた結果を表すValue Object
class MutationResult<T, S> {
  public final S newState; // 新しい状態
  public final T value;    // 一緒に返す値

  MutationResult(S newState, T value){
    this.newState = newState;
    this.value = value;
  }
}

