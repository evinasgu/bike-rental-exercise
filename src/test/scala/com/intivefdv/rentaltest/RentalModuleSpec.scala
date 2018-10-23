package com.intivefdv.rentaltest

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.mockito.MockitoSugar

class RentalModuleSpec extends FlatSpec with Matchers with MockitoSugar {

  "A RentalPerHour" should "contains a price equals to five(5)" in {
    RentalByHour().price should be(5)
  }

  "A RentalByDay" should "contains a price equals to twenty(20)" in {
    RentalByDay().price should be(20)
  }

  "A RentalByWeek" should "contains a price equals to sixty(60)" in {
    RentalByWeek().price should be(60)
  }

  "A FamilyRentalPromotion" should "fails if rentals recived is null" in {
    assertThrows[IllegalArgumentException] {
      FamilyRentalPromotion().applyPromotion(null)
    }
  }


  "A FamilyRentalPromotion" should "fails if the number of rentals recived is less than 3" in {
    assertThrows[IllegalArgumentException] {
      FamilyRentalPromotion().applyPromotion(List[Rental](mock[Rental], mock[Rental]))
    }
  }

  "A FamilyRentalPromotion" should "fails if the number of rentals recived is bigger than 5" in {
    assertThrows[IllegalArgumentException] {
      FamilyRentalPromotion().applyPromotion(List[Rental](mock[Rental], mock[Rental], mock[Rental], mock[Rental],
        mock[Rental], mock[Rental]))
    }
  }

  "A FamilyRentalPromotion" should "apply a 30% of discount over a given Rental List with length 3" in {
    val rentalList = List[Rental](RentalByDay(), RentalByHour(), RentalByWeek())
    val expectedResult: BigDecimal = 85 * 0.7
    (FamilyRentalPromotion().applyPromotion(rentalList) - expectedResult).abs < 0.01 should be(true)
  }

  "A FamilyRentalPromotion" should "apply a 30% of discount over a given Rental List with length 4" in {
    val rentalList = List[Rental](RentalByDay(), RentalByHour(), RentalByWeek(), RentalByDay())
    val expectedResult: BigDecimal = 105.0 * 0.7
    (FamilyRentalPromotion().applyPromotion(rentalList) - expectedResult).abs < 0.01 should be(true)
  }

  "A FamilyRentalPromotion" should "apply a 30% of discount over a given Rental List with length 5" in {
    val rentalList = List[Rental](RentalByDay(), RentalByHour(), RentalByWeek(), RentalByDay(), RentalByDay())
    val expectedResult: BigDecimal = 125.0 * 0.7
    (FamilyRentalPromotion().applyPromotion(rentalList) - expectedResult).abs < 0.01 should be(true)
  }
}
