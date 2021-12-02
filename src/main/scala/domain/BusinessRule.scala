package domain

sealed trait RuleCondition
final case class OnPaymentWithProductId(id: String) extends RuleCondition
final case class OnPaymentWithProductCategory(category: String) extends RuleCondition

final case class BusinessRule(id: String,
                              condition: RuleCondition,
                              description: Option[String],
                              processSteps: List[ProcessStep],
                              rulesetVersion: Int)

