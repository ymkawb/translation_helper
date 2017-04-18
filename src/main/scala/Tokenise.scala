package com.github.ymkawb.translation_helper.model

import com.typesafe.scalalogging._;
import scala.util.matching.Regex

object Tokenizer extends LazyLogging{

	class RichRegex(underlying: Regex) {
		def matches(s: String) = underlying.pattern.matcher(s).matches
	}
	implicit def regexToRichRegex(r: Regex) = new RichRegex(r)

	type BuildOp = (ResourceQualifier,List[String]) => (ResourceQualifier,List[String])
	
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
		singleTokenMatch(rq,l,"""(car)(desk)(television)(appliance)(watch)""".r,(x:ResourceQualifier, y:String) => x.copy(uiMode = Some(y)))

	def nightMode(rq: ResourceQualifier, l : List[String]) = 
		singleTokenMatch(rq,l,"""(night)|(notnight)""".r,(x:ResourceQualifier, y:String) => x.copy(nightMode = Some(y)))


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
		screenOrientation
		)

	def build(input:String) = {
		val tokens = toTokenList(input)
		var rq = (new ResourceQualifier(),tokens)
		for( bo <- buildList ){
			rq = bo(rq._1,rq._2)
		}
		rq._1
	}
	def toTokenList(input:String) : List[String] = input.split('-').toList		
}