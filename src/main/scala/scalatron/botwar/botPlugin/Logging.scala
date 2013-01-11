package scalatron.botwar.botPlugin

trait Logging {

  def log(message: String, params: Any*) =
    println(message.format(params.toArray: _*))
}