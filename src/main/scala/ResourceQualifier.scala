 package com.github.ymkawb.translation_helper.model
 import com.typesafe.scalalogging._;
 //See https://developer.android.com/guide/topics/resources/providing-resources.html Table 2 for up-to-date list of variation
 case class ResourceQualifier(
 	val mcc_mnc : Option[String] = None,
 	val locale : Option[String] = None, // TODO: 
 	val layoutDirection : Option[String] = None,
 	val smallestWidth : Option[String] = None,
 	val avalibleWidth : Option[String] = None,
 	val avalibleHeight : Option[String] = None,
 	val screenSize : Option[String] = None,
 	val screenAspect : Option[String] = None,
 	val roundScreen : Option[String] = None,
 	val screenOrientation : Option[String] = None,
 	val uiMode : Option[String] = None,
 	val nightMode : Option[String] = None,
 	val dpi : Option[String] = None,
 	val touchScreenType : Option[String] = None,
 	val keyboardAvailibility : Option[String] = None,
 	val primaryTextInput : Option[String] = None,
 	val primaryNonTouch : Option[String] = None,
 	val platformVersion : Option[String] = None
 	)


 object ResourceQualifier {
 	object Builder extends LazyLogging {

 		import scala.util.matching.Regex

 		type P = (Regex,((ResourceQualifier,String) => ResourceQualifier))
 		type F = (String,ResourceQualifier)

 		def foldFunc(f:F,p:P) : F = {
 			val fn  =  p._2 //Build func 	
 			val rq = f._2// 
 			p._1.findPrefixOf(f._1) match {
 				case Some(x) => {
 					val vv = stripHeading(p._1.replaceFirstIn(f._1,""))
 					(vv,fn(rq,x))
 				}
 				case None => f
 			} 		
 		}

 		def stripHeading(in:String) : String = in.headOption match {
 			case Some('-') => in.drop(1)
 			case _ =>  in
 		}
 		
 		val order  = List(
 			("""(mcc(\d+))(-mnc\d+)?""".r,	((x:ResourceQualifier,y:String) => x.copy(mcc_mnc = Some(y)))),
 			("""(\w\w(-\w\w\w)?)""".r,		((x:ResourceQualifier,y:String) => x.copy(locale = Some(y)))),
 			("""(ldrtl)|(ldltr)""".r, 			((x:ResourceQualifier,y:String) => x.copy(layoutDirection = Some(y)))),
 			("""sw\d+dp""".r, 				((x:ResourceQualifier,y:String) => x.copy(smallestWidth = Some(y)))),
 			("""w\d+dp""".r, 				((x:ResourceQualifier,y:String) => x.copy(avalibleWidth = Some(y)))),
 			("""h\d+dp""".r, 				((x:ResourceQualifier,y:String) => x.copy(avalibleHeight = Some(y)))),
 			("""(small)|(normal)|(large)|xlarge""".r,((x:ResourceQualifier,y:String) => x.copy(screenSize = Some(y))))
 			)

 		def build(input:String) = order.foldLeft((input,ResourceQualifier()))(foldFunc)._2

 	}
 	def apply(input:String) : ResourceQualifier = Builder.build(input)

 }
