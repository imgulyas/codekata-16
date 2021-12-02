package domain

sealed trait ProcessStep
object ProcessStep {
  final case class GeneratePackingSlip(withNote: Option[String]) extends ProcessStep
  case object ActivateMembership extends ProcessStep
  case object UpgradeMembership extends ProcessStep
  final case class NotifyUser(withMessage: String) extends ProcessStep
  case object GenerateAgentCommission extends ProcessStep
  //custom step which can't be automated yet
  final case class CustomStep(employeeGroupId: String, taskDescription: String) extends ProcessStep
}
