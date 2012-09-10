package $package$

import scala.util.control.Exception.{catching, allCatch}
import scalaz.{Resource => _, _}, Scalaz._
import scalax.io._
import scalax.file._

class $name;format="Camel"$(val args: Array[String]) {
  var verbose: Boolean = false
  var directory: Option[String] = None

  def run() {
    catching(classOf[java.io.IOException]).withApply {
      e => _error(e.getMessage)
    } apply {
      _run()
    }
  }

  private def _run() {
    _parse_options(args.toList) match {
      case "ls" :: Nil => _ls()
      case "cat" :: file :: Nil => _cat(file)
      case _ => _usage
    }
  }

  private def _error(msg: String) {
    Console.err.println(msg)
  }

  private def _parse_options(args: List[String]): List[String] = {
    _parse_options(args, Nil)
  }

  private def _parse_options(in: List[String], out: List[String]): List[String] = {
    in match {
      case Nil => out
      case "-verbose" :: rest => {
        verbose = true
        _parse_options(rest, out)
      }
      case "-dir" :: arg :: rest => {
        directory = arg.some
        _parse_options(rest, out)
      }
      case arg :: rest => _parse_options(rest, out :+ arg)
    }
  }

  private def _usage {
    println("usage: sample command args")
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
