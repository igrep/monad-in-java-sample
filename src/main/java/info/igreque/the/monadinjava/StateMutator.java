package info.igreque.the.monadinjava;

// 状態の書き換えをシミュレートするための関数オブジェクト
@FunctionalInterface
interface StateMutator<T, S> {
    MutationResult<T, S> mutate(S oldState);
}

