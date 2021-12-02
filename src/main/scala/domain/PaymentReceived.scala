package domain



final case class PaymentReceived(paidBy: User,
                                 cart: List[Product],
                                 id: String)
