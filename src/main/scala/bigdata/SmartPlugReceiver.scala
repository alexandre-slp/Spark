/**
  * Created by bigdata on 5/23/17.
  */

package bigdata

import bigdata.Sensor

import org.apache.spark.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.jfarcand.wcs.{TextListener, WebSocket}
import net.liftweb.json._


class SmartPlugReceiver() extends Receiver[String](StorageLevel.MEMORY_ONLY) with Runnable with Logging {

//  private val smartPlugURL = "ws://smart-plug-1.herokuapp.com"
  private val smartPlugURL = "ws://localhost:3000"
  implicit val formats = DefaultFormats

  @transient
  private var thread: Thread = _

  override def onStart(): Unit = {
    thread = new Thread(this)
    thread.start()
  }

  override def onStop(): Unit = {
    thread.interrupt()
  }

  override def run(): Unit = {
    receive()
  }

  def isJson(string:String): Boolean ={
    return string{0}.equals('{') && string{string.length - 1}.equals('}')
  }

  private def receive(): Unit = {
    val webSocket = WebSocket().open(smartPlugURL)
    webSocket.listener(new TextListener {
        override def onMessage(message: String) {
          print(message)
          val json = parse(message)
          print(json)
          val sensor: Sensor = json.extract[Sensor]

          print(sensor.getId)
//          if (isJson(message)) {
//            val json = new Json(message)
//            store(json.toString)
//            store(json.getVoltage.toString)
//            store(json.getCurrent.toString)
//            store(json.getTimestamp.toString)
//            store(json.instantPower.toString)
//          }
        }
      }
    )
  }
}