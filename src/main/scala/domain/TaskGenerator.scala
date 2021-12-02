package domain

import domain.ProcessStep.{CustomStep, GeneratePackingSlip}


trait TaskGenerator {
  def makeTasks(ruleset: Ruleset, event: Event): List[BusinessTask]
}

class TaskGeneratorImpl extends TaskGenerator {
  override def makeTasks(rules: Ruleset, event: Event): List[BusinessTask] = {
    rules
      .ruleset
      .iterator
      .flatMap(rule => applyRule(rule, event))
      .toList
  }

   def applyRule(rule: BusinessRule, event: Event): List[BusinessTask] = {
    event match {
      case pr: PaymentReceived => applyRuleToPaymentReceived(pr, rule)
    }
  }

  private def applyRuleToPaymentReceived(pr: PaymentReceived, rule: BusinessRule): List[BusinessTask] = {
    rule.condition match {
      case OnPaymentWithProductCategory(category) =>
        val isApplicable = pr.product.categories.contains(category)
        if (isApplicable) {
          rule.processSteps.map(step => paymentStepToTask(step, pr))
        } else List.empty

      case OnPaymentWithProductId(id) =>
        val isApplicable = pr.product.id == id
        if (isApplicable) {
          rule.processSteps.map(step => paymentStepToTask(step, pr))
        } else List.empty

      case _  => List.empty
  }
}

  private def paymentStepToTask(step: ProcessStep, pr: PaymentReceived): BusinessTask = {
    step match {
      case  g: ProcessStep.GeneratePackingSlip => BusinessTask.GeneratePackingSlip(g.withNote)
      case  _: ProcessStep.NotifyUser => BusinessTask.NotifyUser(pr.paidBy, "Your payment was received, thank you")
      case  ProcessStep.ActivateMembership => BusinessTask.ActivateMembership(pr.paidBy)
      case  ProcessStep.UpgradeMembership => BusinessTask.UpgradeMembership(pr.paidBy)
      case  ProcessStep.GenerateAgentCommission => BusinessTask.GenerateAgentCommission(pr.id)
      case  c: CustomStep => BusinessTask.CustomTask[PaymentReceived](c.employeeGroupId, c.taskDescription, triggeredBy = pr)
    }
  }

}