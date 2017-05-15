package com.github.ymkawb.translation_helper.io;
import com.github.ymkawb.translation_helper.model._
import com.github.ymkawb.translation_helper.Parser._
import com.typesafe.scalalogging._;
import java.io._;

object FileWalker extends LazyLogging {

	val valuesDirNamePattern = """values[-\w\d]*""".r

	def walkTree(res : File){
		if(res.isDirectory && res.getName == "res"){//Found potential res 
			walkPotentialResFolder(res)
		}
		else if(res.isDirectory) {
			for( f <- res.listFiles) walkTree(f)
		}
	}

	implicit def fileToString(f:File) : String = f.getName

	private def readFile(f:File) = readModel(new FileInputStream(f))

	private def walkPotentialResFolder(dir:File) = {
		val folders = dir.listFiles().filter(valuesDirNamePattern.pattern.matcher(_).matches);
		for(f <- folders) yield {
			val rq = ResourceQualifier(f.getName)
			(rq, f.listFiles.map(readFile).toList.flatten)
		}
	}


}