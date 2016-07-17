package info.igreque.the.monadinjava;

// 状態の書き換えをシミュレートするための関数オブジェクト
@FunctionalInterface
interface StateMutator<T, S> {
  // Sという型の新しい状態とともに、Tという型の書き換えた結果も返す
  MutationResult<T, S> mutate(S oldState);
}

