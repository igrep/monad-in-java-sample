package info.igreque.the.monadinjava;

// 状態を書き換えた結果を表すValue Object
class MutationResult<T, S> {
    public final S newState;
    public final T value;

    MutationResult(S newState, T value){
        this.newState = newState;
        this.value = value;
    }
}

