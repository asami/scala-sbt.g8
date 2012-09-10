package $package$

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class $name;format="Camel"$Spec extends WordSpec with ShouldMatchers {
  "$name;format="Camel"$" should {
    "execute" that {
      "empty arguments" in {
        val app = new $name;format="Camel"$(Array())
        app.run()
      }
      "one argument" in {
        val app = new $name;format="Camel"$(Array("ls"))
        app.run()
      }
    }
  }
}
