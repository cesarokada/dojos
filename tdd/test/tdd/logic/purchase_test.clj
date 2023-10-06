(ns tdd.logic.purchase-test
  (:require [clojure.test :refer :all]
            [tdd.logic.purchase :as logic.purchase]))

(deftest purchase-creating-error
  (testing "should raise error when try to create a purchase with greater than 0.00"
    (is (thrown-with-msg? IllegalArgumentException #"Purchase amount must be greater than 0"
                          (logic.purchase/create {:amount 0}))))
  (testing "should raise error when try to create a purchase with installment amount of 0"
    (is (thrown-with-msg? IllegalArgumentException #"Purchase installments must be greater than 0 and less than 12"
                          (logic.purchase/create {:amount 100.00 :installments 0})))))
