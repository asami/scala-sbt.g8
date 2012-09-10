package $package$

object Main {
  def main(args: Array[String]) {
    val app = new $name;format="Camel"$(args)
    app.run()
  }
}

class AppMain extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) = {
    val app = new $name;format="Camel"$(config.arguments)
    app.run()
    new xsbti.Exit {
      val code = 0
    }
  }    
}
