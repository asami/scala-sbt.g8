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
    case "ls" :: Nil => ls()
    case "cat" :: file :: Nil => cat(file)
  }

  protected def parseOptions(in: List[String], out: List[String]): List[String] = {
    in match {
      case Nil => out
      case "-verbose" :: rest => {
        verbose = true
        parseOptions(rest, out)
      }
      case "-dir" :: arg :: rest => {
        directory = arg.some
        parseOptions(rest, out)
      }
      case arg :: rest => parseOptions(rest, out :+ arg)
    }
  }

  protected def usageCommand {
    println("usage: $name$ args")
  }

  def ls() {
    for (path <- Path(directory | ".").children("[^.]*", nil)) {
      println(path.name)
    }
  }

  def cat(file: String) {
    println(Resource.fromFile(file).string)
  }
}
