package $package$

import scala.util.control.Exception.{catching, allCatch}
import scalaz.{Resource => _, _}, Scalaz._
import scalax.io._
import scalax.file._

class $name;format="Camel"$(val args: Array[String]) extends ScriptBase {
  def commands = List("ls", "cat")
  var verbose: Boolean = false
  var directory: Option[String] = None

  protected def application: PartialFunction[List[String], Unit] = {
    case "ls" :: Nil => _ls()
    case "cat" :: file :: Nil => _cat(file)
  }

  protected def parse_Options(in: List[String], out: List[String]): List[String] = {
    in match {
      case Nil => out
      case "-verbose" :: rest => {
        verbose = true
        parse_Options(rest, out)
      }
      case "-dir" :: arg :: rest => {
        directory = arg.some
        parse_Options(rest, out)
      }
      case arg :: rest => parse_Options(rest, out :+ arg)
    }
  }

  protected def usage_Command {
    println("usage: $name$ args")
  }

  private def _ls() {
    for (path <- Path(directory | ".").children("[^.]*", nil)) {
      println(path.name)
    }
  }

  private def _cat(file: String) {
    println(Resource.fromFile(file).string)
  }
}
