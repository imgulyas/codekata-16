package domain

final case class PaymentRule(id: String,
                             description: Option[String],
                             processSteps: List[ProcessStep],
                             rulesetVersion: Int)

