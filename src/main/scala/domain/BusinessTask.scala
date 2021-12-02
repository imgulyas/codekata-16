package domain

sealed trait BusinessTask
object BusinessTask {
  final case class GeneratePackingSlip(withNote: Option[String]) extends BusinessTask
  final case class ActivateMembership(user: User) extends BusinessTask
  final case class UpgradeMembership(user: User) extends BusinessTask
  final case class NotifyUser(user: User, message: String) extends BusinessTask
  final case class GenerateAgentCommission(paymentId: String) extends BusinessTask
  //manual task for employees
  final case class CustomTask[A](groupId: String, taskDescription: String, triggeredBy: A) extends BusinessTask
}
