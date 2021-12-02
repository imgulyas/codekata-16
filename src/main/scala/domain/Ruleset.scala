package domain

final case class Ruleset(ruleset: Set[PaymentRule],
                         version: Int)
