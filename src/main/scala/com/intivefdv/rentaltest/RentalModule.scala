package com.intivefdv.rentaltest

/**
  * Contract related with the Rental Definition
  */
sealed trait Rental {

  def price: BigDecimal
}

/**
  * Specification of the Real contract to work with rentals by hour
  */
case class RentalByHour() extends Rental {

  val price: BigDecimal = 5
}

/**
  * Specification of the Real contract to work with rentals by day
  */
case class RentalByDay() extends Rental {

  val price: BigDecimal = 20
}

/**
  * Specification of the Real contract to work with rentals by week
  */
case class RentalByWeek() extends Rental {

  val price: BigDecimal = 60
}

/**
  * Contract related with the Promotion Definition
  */
sealed trait Promotion {

  def applyPromotion(rentalList: List[Rental]): BigDecimal
}

/**
  * Specification of the actual contract to work with family promotions
  */
case class FamilyRentalPromotion() extends Promotion {

  /**
    * Method to implement the application of a given promotion
    * @param rentalList List of rentals
    * @return
    */
  override def applyPromotion(rentalList: List[Rental]): BigDecimal = {
    require(rentalList != null, "The rental list can't be null")
    val quantity = rentalList.size
    require(quantity <= 5 && quantity >= 3, "The quantity of rentals is not supported")
    rentalList.map(r => r.price * 0.7).reduce((r1, r2) => r1 + r2)
  }
}