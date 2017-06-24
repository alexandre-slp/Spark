/**
  * Created by bigdata on 5/23/17.
  */

package bigdata

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.log4j.{Level, Logger}

/**
  * Json:
  * {
  *   "id":"590892d45b251e0004476a2a",
  *   "value":{
  *             "voltage":"10",
  *             "current":"5",
  *             "timestamp":1496716624572
  *           }
  * }
  */

object SmartPlugStreaminApp {

  def isJson(string:String): Boolean ={
    return string{0}.equals('{') && string{string.length - 1}.equals('}')
  }

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val intervalRefresh = 5
    val conf = new SparkConf().setMaster(args(0)).setAppName("SmartPlugStreaming")
    val ssc = new StreamingContext(conf, Seconds(intervalRefresh))
    val stream = ssc.receiverStream(new SmartPlugReceiver())
//
//    stream.foreachRDD( rdd => {
//        var string:String = ""
//
//        for ( item <- rdd.toString){
//          string = string + item
//        }
//        if (isJson(string)) {
//          val json = new Json(string)
//        }
//      }
//    )
    stream.print()

    if (args.length > 2) {
      stream.saveAsTextFiles(args(2))
    }
    ssc.start()
    ssc.awaitTermination()
  }

}