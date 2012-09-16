package $package$

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class $name;format="Camel"$FeatureSpec extends FeatureSpec with ShouldMatchers with GivenWhenThen {
  feature("The user can execute using empty arguments") {
    info("As a programmer")
    info("I want to be able to execute using empty arguments")
    info("So that I exuecute with no arguments")
  }
  scenario("execute using empty arguments") {
    given("...")
    when("...")
    then("...")
    and("...")
    val app = new $name;format="Camel"$(Array())
    app.run()
   // pending
  }
}
