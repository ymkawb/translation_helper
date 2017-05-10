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
 	val wideColorGamut : Option[String] = None,
 	val hdr : Option[String] = None,
 	val screenOrientation : Option[String] = None,
 	val uiMode : Option[String] = None,
 	val nightMode : Option[String] = None,
 	val dpi : Option[String] = None,
 	val touchScreenType : Option[String] = None,
 	val keyboardAvailibility : Option[String] = None,
 	val primaryTextInput : Option[String] = None,
 	val primaryNonTouch : Option[String] = None,
 	val platformVersion : Option[String] = None)
 {
 	override def toString : String = {
 		import scala.collection.mutable.StringBuilder
 		val sb = new StringBuilder("{")
 		for( (k,f) <- ResourceQualifier.toStringList ){
 			f(this) match {
 				case Some(x) => sb.append(s" ${k} = ${x}")
 				case _ => 
 			}
 			
 		}
 		sb.append(" }")
 		sb.toString
 	}
 }


 object ResourceQualifier { 	
 	import scala.util.matching.Regex

 	class RichRegex(underlying: Regex) {
		def matches(s: String) = underlying.pattern.matcher(s).matches
	}
	implicit def regexToRichRegex(r: Regex) = new RichRegex(r)

	type BuildOp = (ResourceQualifier,List[String]) => (ResourceQualifier,List[String])

	val toStringList : List[(String,ResourceQualifier => Option[String])] = List(
		("mcc_mnc",_.mcc_mnc),
		("locale", _.locale),
		("layoutDirection",_.layoutDirection),
		("smallestWidth", _.smallestWidth),
		("avalibleWidth", _.avalibleWidth),
		("avalibleHeight", _.avalibleHeight),
		("screenSize", _.screenSize),
		("screenAspect",_.screenAspect),
		("roundScreen",_.roundScreen),
		("wideColorGamut",_.wideColorGamut),
		("hdr",_.hdr),
		("screenOrientation",_.screenOrientation),
		("uiMode",_.uiMode),
		("nightMode",_.nightMode),
		("dpi",_.dpi),
		("touchScreenType",_.touchScreenType),
		("keyboardAvailibility",_.keyboardAvailibility),
		("primaryTextInput",_.primaryTextInput),
		("primaryNonTouch",_.primaryNonTouch),
		("platformVersion",_.platformVersion)
		)
	
	def mcc_mnc(rq:ResourceQualifier, l : List[String]) = l match {
		case f :: s :: tail if("""mcc\d+""".r.matches(f) && """mnc\d+""".r.matches(s)) => (rq.copy(mcc_mnc=Some(s"${f}-${s}")),tail)
		case f :: tail if("""mcc\d+""".r.matches(f)) => (rq.copy(mcc_mnc=Some(f)),tail)
		case _ => (rq,l)
	}

	def locale(rq:ResourceQualifier, l : List[String]) = l match {
		case f :: s :: tail if("""\w\w""".r.matches(f) && """\w\w\w""".r.matches(s)) => (rq.copy(locale=Some(s"${f}-${s}")),tail)
		case f :: tail if ("""\w\w""".r.matches(f)) => (rq.copy(locale=Some(f)),tail) 
		case _ => (rq,l)
	}

	def smallestWidth(rq:ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""sw\d+dp""".r,(x:ResourceQualifier,y:String) => x.copy(smallestWidth = Some(y)))
	
	def layoutDirection(rq:ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(ldrtl)|(ldltr)""".r, (x:ResourceQualifier,y:String) => x.copy(layoutDirection = Some(y)))
	
	def avalibleWidth(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""w\d+dp""".r,(x:ResourceQualifier,y:String) => x.copy(avalibleWidth = Some(y)))
	
	def avalibleHeight(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""h\d+dp""".r,(x:ResourceQualifier,y:String) => x.copy(avalibleHeight = Some(y)))

	def screenSize(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(small)|(normal)|(large)|(xlarge)""".r,(x:ResourceQualifier,y:String) => x.copy(screenSize = Some(y)))

	def screenAspect(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(long)|(notlong)""".r,(x:ResourceQualifier,y:String) => x.copy(screenAspect = Some(y)))

	def roundScreen(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(round)|(notround)""".r,(x:ResourceQualifier, y:String) => x.copy(roundScreen = Some(y)))

	def wideColorGamut(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(widecg)|(nowidecg)""".r,(x:ResourceQualifier, y:String) => x.copy(wideColorGamut = Some(y)))

	def hdr(rq: ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(highdr)|(lowdr)""".r, (x:ResourceQualifier, y:String) => x.copy(hdr = Some(y)))

	def screenOrientation(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(port)|(land)""".r,(x:ResourceQualifier, y:String) => x.copy(screenOrientation = Some(y)))

	def uiMode(rq: ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(car)|(desk)|(television)|(appliance)|(watch)""".r,(x:ResourceQualifier, y:String) => x.copy(uiMode = Some(y)))

	def nightMode(rq: ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(night)|(notnight)""".r,(x:ResourceQualifier, y:String) => x.copy(nightMode = Some(y)))

	def dpi(rq: ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(ldpi)|(mdpi)|(hdpi)|(xhdpi)|(xxhdpi)|(xxxhdpi)|(nodpi)|(tvdpi)|(anydpi)""".r,
			(x:ResourceQualifier,y:String) => x.copy(dpi = Some(y)))

	def touchScreenType(rq: ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(notouch)|(finger)""".r, (x:ResourceQualifier,y:String) => x.copy(touchScreenType = Some(y)))

	def keyboardAvailibility(rq: ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,
			"""(keysexposed)|(keyshidden)|(keyssoft	)""".r,(x:ResourceQualifier, y:String) => x.copy(keyboardAvailibility=Some(y)))

	def primaryTextInput(rq: ResourceQualifier, l: List[String]) = 
		singleTokenMatch(rq,l,"""(nokeys)|(qwerty)|(12key)""".r,(x:ResourceQualifier, y:String) => x.copy(primaryTextInput = Some(y)))

	def primaryNonTouch(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(nonav)|(dpad)|(trackball)|(wheel)""".r,(x:ResourceQualifier, y:String) => x.copy(primaryNonTouch = Some(y)))

	def platformVersion(rq : ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq, l, """v\d+""".r,(x:ResourceQualifier, y:String) => x.copy(platformVersion = Some(y)))





	def singleTokenMatch(rq: ResourceQualifier,
						 l : List[String],
						 re : Regex,
						 bo : (ResourceQualifier,String) => ResourceQualifier ) = l match {
		case head :: tail if(re.matches(head)) => (bo(rq,head),tail)
		case _ => (rq,l)
	} 
		
	

	private val buildList: List[BuildOp] = List(
		mcc_mnc,
		locale,
		layoutDirection,
		smallestWidth,
		avalibleWidth,
		avalibleHeight,
		screenSize,
		screenAspect,
		roundScreen,
		wideColorGamut,
		hdr,
		screenOrientation,
		uiMode,
		nightMode,
		dpi,
		touchScreenType,
		keyboardAvailibility,
		primaryTextInput,
		primaryNonTouch,
		platformVersion
		)

	def apply(input:String) = {
		val tokens = toTokenList(input)
		var rq = (new ResourceQualifier(),tokens)
		for( bo <- buildList ){
			rq = bo(rq._1,rq._2)
		}
		rq._1
	}
	def toTokenList(input:String) : List[String] = input.split('-').toList	
 }
