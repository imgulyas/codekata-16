package domain

sealed trait ProcessStep {
    //  def ruleId: String
    //  def rulesetVersion: String
    //  def paymentId: String
  }

  //automated steps
  final case class GeneratePackingSlip(note: Option[String]) extends ProcessStep
  final case class ActivateMembership() extends ProcessStep
  final case class UpgradeMembership() extends ProcessStep
  final case class NotifyUser(user: User, message: String) extends ProcessStep
  final case class GenerateAgentCommission(agent: String) extends ProcessStep
  //custom steps
  final case class CustomEmployeeTask(groupId: String, taskDescription: String) extends ProcessStep
