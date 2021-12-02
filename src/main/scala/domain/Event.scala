package domain

sealed trait Event
final case class PaymentReceived(paidBy: User, product: Product, id: String) extends Event
