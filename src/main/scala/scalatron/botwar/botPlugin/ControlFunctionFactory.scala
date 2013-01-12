package scalatron.botwar.botPlugin

/**
 * This is the factory class that the server attempts to instantiate
 * after the plugin JAR file was loaded via the class loader.
 */
class ControlFunctionFactory {
  def create: (String => String) = new Bot().respond _
}