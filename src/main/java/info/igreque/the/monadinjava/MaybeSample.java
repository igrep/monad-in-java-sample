package info.igreque.the.monadinjava;

import java.util.HashMap;

public class MaybeSample {
  public static void main(String args){
    HashMap<String, Foo> someMap = new HashMap<>();
    String key1 = "key1";
    String key2 = "key2";
    String key3 = "key3";

    someMap.put(key1, new Foo("foo1"));
    someMap.put(key2, new Foo("foo2"));
    someMap.put(key3, new Foo("foo3"));

    Foo maybeFoo1 = someMap.get(key1);
    MaybeFactory f = new MaybeFactory();
    Maybe<Bar> bar =
      f.doNothingReturning(maybeFoo1).then((foo1) -> {
        Foo maybeFoo2 = someMap.get(key2);
        return f.doNothingReturning(maybeFoo2).then((foo2) -> {
          Foo maybeFoo3 = someMap.get(key3);
          return f.doNothingReturning(maybeFoo3).then((foo3) -> {
            Bar b = foo1.doSomething(foo2, foo3);
            return f.doNothingReturning(b);
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
