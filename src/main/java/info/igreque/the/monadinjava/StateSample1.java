package info.igreque.the.monadinjava;

public class StateSample1 {
  public static void main(String[] args) {
    // 本当は State<String, Void> builder と宣言したい
    Monad<Void> builder =
      // 状態を取得して、次の関数に渡す。
      State.get()
        // ↓getから渡された状態に処理を加え、書き戻す。
        .then((currentString) -> State.put(currentString + "one!"))
        .then((_null /* どうせ State.put が返す（次の関数に渡す）値はnullなので無視する */) ->
          // ↓また状態を取得する。
          State.get()
        )
        // あとはその繰り返し。
        .then((currentString) -> State.put(currentString + "two!"))
        .then((_null) -> State.get())
        .then((currentString) -> State.put(currentString + "three!"));

    System.out.println(
      // 初期値を渡すことで、初期値から書き換えていった結果が得られる
      // この場合 "zero! one!two!three!" と出力される
      // 本当はダウンキャストなんてしたくない...
      ((State<String, Void>) builder).mutator.apply("zero! ").newState
    );
  }
}
