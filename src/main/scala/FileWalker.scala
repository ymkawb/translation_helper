package com.github.ymkawb.translation_helper.io;
import com.github.ymkawb.translation_helper.model._
import com.github.ymkawb.translation_helper.Parser._
import com.typesafe.scalalogging._;
import java.io.File;

object FileWalker extends LazyLogging{

	val valuesDirNamePattern = """values(-(\w+))?""".r

	def walkTree(in : File){
	}


}