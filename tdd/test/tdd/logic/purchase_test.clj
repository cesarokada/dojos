(ns tdd.logic.purchase-test
  (:require [clojure.test :refer :all]
            [tdd.logic.purchase :as logic.purchase]))

(deftest purchase-creating-error
  (testing "should raise error when try to create a purchase with greater than 0.00"
    (is (thrown-with-msg? IllegalArgumentException #"Purchase amount must be greater than 0"
                          (logic.purchase/create {:amount 0}))))
  (testing "should raise error when try to create a purchase with installment amount of 0"
    (is (thrown-with-msg? IllegalArgumentException #"Purchase installments must be greater than 0"
                          (logic.purchase/create {:amount 100.00 :installments 0}))))
  (testing "should raise error when try to create a purchase with installment amount of 13"
    (is (thrown-with-msg? IllegalArgumentException #"Purchase installments must be less than 13"
                          (logic.purchase/create {:amount 100.00 :installments 13}))))
  (testing "should raise error when try to create a purchase with amount less than 100 and more than 1 installment"
    (is (thrown-with-msg? IllegalArgumentException #"Purchase with amount less than 100 must has 1 installment"
                          (logic.purchase/create {:amount 10 :installments 2})))))

(deftest purchase-creating-success
  (testing "should create a purchase with amount less than 100 and installment amount of 1"
    (is (= {:amount 50.00 :installments 1}
           (logic.purchase/create {:amount 50.00 :installments 1}))))
  (testing "should create a purchase with amount greater than 100 and installment amount of 1"
    (is (= {:amount 100.00 :installments 12}
           (logic.purchase/create {:amount 100.00 :installments 12})))))

