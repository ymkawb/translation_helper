package com.github.ymkawb.translation_helper
import com.typesafe.scalalogging._;
import org.rogach.scallop._;
import java.io.File

class Conf(arguments: scala.collection.Seq[String]) extends ScallopConf(arguments) {  
  version("Translation helper 0.0.1  (c) 2017 ymkwb") 
  val base = opt[String](descr = s"Directory to look for base resources. Current default value is ${(new File(".")).getCanonicalPath}",default=Some((new File("")).getCanonicalPath))
  val incoming = opt[String](descr="Directory to look for incoming resources.")
  verify()
}

object App extends scala.App with LazyLogging{
	val c = new Conf(args)
	
}
