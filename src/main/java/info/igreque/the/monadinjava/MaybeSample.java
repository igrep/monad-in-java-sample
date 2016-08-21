package info.igreque.the.monadinjava;

import java.util.HashMap;

public class MaybeSample {
  public static void main(String[] args){
    HashMap<String, Foo> someMap = new HashMap<>();
    String key1 = "key1";
    String key2 = "key2";
    String key3 = "key3";
    String key4 = "key4"; // null を返す場合を試すとき。

    someMap.put(key1, new Foo("foo1"));
    someMap.put(key2, new Foo("foo2"));
    someMap.put(key3, new Foo("foo3"));

    Foo maybeFoo1 = someMap.get(key1);
    Maybe.Return r = new Maybe.Return();
    // 本当は Maybe<Bar> と宣言したいが、コンパイルエラーになっちゃうorz
    Monad<Bar> bar =
      r.doNothingReturning(maybeFoo1).then((foo1) -> {
        Foo maybeFoo2 = someMap.get(key2);
        return r.doNothingReturning(maybeFoo2).then((foo2) -> {
          Foo maybeFoo3 = someMap.get(key3);
          return r.doNothingReturning(maybeFoo3).then((foo3) -> {
            return r.doNothingReturning(foo1.doSomething(foo2, foo3));
          });
        });
      });

    System.out.println(bar.toString());
  }

  private static class Foo {
    private final String string;

    Foo(String string){
      this.string = string;
    }

    public Bar doSomething(Foo foo2, Foo foo3){
      return new Bar(this.string + foo2.string + foo3.string);
    }
  }

  private static class Bar {
    private final String string;

    Bar(String string){
      this.string = string;
    }

    public String toString(){
      return "Bar: " + string;
    }
  }
}
