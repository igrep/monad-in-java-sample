package info.igreque.the.monadinjava;

public class StateSample2 {
  public static void main(String[] args) {
    // 本当は State<String, Void> builder と宣言したい
    Monad<Void> builder =
      State.modify((currentString) -> currentString + "one!")
        .then((_null) -> State.modify((currentString) -> currentString + "two!"))
        .then((_null) -> State.modify((currentString) -> currentString + "three!"));

    System.out.println(
      // 初期値を渡すことで、初期値から書き換えていった結果が得られる
      // この場合 "zero! one!two!three!" と出力される
      // 本当はダウンキャストなんてしたくない...
      ((State<String, Void>) builder).mutator.mutate("zero! ").newState
    );
  }
}
