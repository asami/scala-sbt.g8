package $package$

import scala.util.control.Exception.{catching, allCatch}

trait ScriptBase {
  val args: Array[String]
  def commands: List[String]

  protected def parse_Options(in: List[String], out: List[String]): List[String]
  protected def application: PartialFunction[List[String], Unit]
  protected def usage_Command: Unit

  protected def system: PartialFunction[List[String], Unit] = {
    case Nil => usage
    case _ => {
      output_error("Illegal command %s".format(args(0)))
      usage
    }
  }

  def run() {
    catching(classOf[java.io.IOException]).withApply {
      e => output_error(e.getMessage)
    } apply {
      run_body()
    }
  }

  protected def run_body() {
    normalize_command(parse_options(args.toList)) match {
      case Left(candidates) => {
        output_error("Ambiguity command %s against %s.".format(args(0), commands.mkString(", ")))
      }
      case Right(rargs) => {
        (application orElse system)(rargs)
      }
    }
  }

  protected def output_error(msg: String) {
    Console.err.print(Console.YELLOW_B)
    Console.err.print(Console.RED)
    Console.err.print(msg)
    Console.err.println(Console.RESET)
  }

  protected def parse_options(args: List[String]): List[String] = {
    parse_Options(args, Nil)
  }

  protected def normalize_command(args: List[String]): Either[List[String], List[String]] = {
    if (args.isEmpty) Right(Nil)
    else {
      val arg0 = args(0).toLowerCase()
      commands.filter(_.startsWith(arg0)) match {
        case Nil => Left(Nil)
        case x :: Nil => Right(x :: args.toList.tail)
        case xs => Left(xs)
      }
    }
  }

  def usage {
    usage_Command
  }
}
