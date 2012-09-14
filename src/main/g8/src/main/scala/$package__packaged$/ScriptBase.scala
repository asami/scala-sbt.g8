package $package$

import scala.util.control.Exception.{catching, allCatch}

trait ScriptBase {
  val args: Array[String]
  def commands: List[String]

  protected def parseOptions(in: List[String], out: List[String]): List[String]
  protected def application: PartialFunction[List[String], Unit]
  protected def usageCommand: Unit

  protected def system: PartialFunction[List[String], Unit] = {
    case Nil => usage
    case _ => {
      outputError("Illegal command %s".format(args(0)))
      usage
    }
  }

  def run() {
    catching(classOf[java.io.IOException]).withApply {
      e => outputError(e.getMessage)
    } apply {
      runBody()
    }
  }

  protected def runBody() {
    normalizeCommand(parseOptions(args.toList)) match {
      case Left(candidates) => {
        outputError("Ambiguity command %s against %s.".format(args(0), commands.mkString(", ")))
      }
      case Right(rargs) => {
        (application orElse system)(rargs)
      }
    }
  }

  protected def outputError(msg: String) {
    Console.err.print(Console.YELLOW_B)
    Console.err.print(Console.RED)
    Console.err.print(msg)
    Console.err.println(Console.RESET)
  }

  protected def parseOptions(args: List[String]): List[String] = {
    parseOptions(args, Nil)
  }

  protected def normalizeCommand(args: List[String]): Either[List[String], List[String]] = {
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
    usageCommand
  }
}
